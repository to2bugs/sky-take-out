package com.sky.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自定义定时任务类，其中包含定时任务的方法
 * 定时任务方法没有返回值的
 */
@Component
@Slf4j
public class MyTask {

    /**
     * 定义定时任务的方法，注意方法的返回值，没有返回值哦
     * 每隔5秒执行一次
     */
    // @Scheduled(cron = "0/5 * * * * ?")
    public void executeTask() {
        log.info("定时任务执行了...{}", new Date());
    }
}
