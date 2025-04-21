package com.sky.task;

import com.sky.constant.MessageConstant;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 定时处理订单的状态
 */
@Slf4j
@Component
public class OrderTask {

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 处理超时订单的方法
     * 每分钟触发一次
     * 参数：
     *      1，待付款状态
     *      2，当前时间 - 15分钟
     */
    @Scheduled(cron = "0 * * * * ?")
    public void processTimeoutOrder() {
        log.info("定时处理超时订单: {}", LocalDateTime.now());
        // 获取到超时订单
        List<Orders> ordersList = orderMapper.getOrdersByStatusAndTimeout(Orders.PENDING_PAYMENT, LocalDateTime.now().plusMinutes(-15));
        if (ordersList == null || ordersList.isEmpty()) return;

        for (Orders orders : ordersList) {
            orders.setStatus(Orders.CANCELLED);
            orders.setCancelReason("订单超时，自动取消");
            orders.setCancelTime(LocalDateTime.now());
            orderMapper.update(orders);
        }
    }


    /**
     * 处理一直处于派送中的订单
     * 每天凌晨一点处理，即处理前一天的一直派送中的订单
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrder() {
        log.info("定时处理一直处于派送中的订单: {}", LocalDateTime.now());
        // 获取到处于派送中的订单
        List<Orders> ordersList = orderMapper.getOrdersByStatusAndTimeout(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().plusHours(-1));
        if (ordersList == null || ordersList.isEmpty()) return;

        for (Orders orders : ordersList) {
            orders.setStatus(Orders.COMPLETED);
            orderMapper.update(orders);
        }
    }
}
