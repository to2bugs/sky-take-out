package com.sky.mapper;


import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 向订单表插入1条数据
     * @param orders
     */
    void insert(Orders orders);


    /**
     * 获取超时的订单
     * 根据订单状态和下单时间，查询订单
     */
    List<Orders> getOrdersByStatusAndTimeout(Integer status, LocalDateTime orderTime);


    /**
     * 修改订单状态
     * @param orders
     */
    void update(Orders orders);
}
