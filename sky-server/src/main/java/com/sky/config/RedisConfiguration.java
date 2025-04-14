package com.sky.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
@Configuration
public class RedisConfiguration {

    /**
     * 引入的spring-boot-starter-data-redis会创建RedisConnectionFactory对象，并注入到IOC容器中，
     * @param redisConnectionFactory
     * @return
     */
    @Bean
    public RedisTemplate<Object, Object> createRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("开始创建并配置RedisTemplate对象，之后通过它操作Redis");
        RedisTemplate redisTemplate = new RedisTemplate<>();
        // 设置redis的连接工厂对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        // 设置redis中key的序列化器
        redisTemplate.setKeySerializer(new StringRedisSerializer()); // StringRedisSerializer：将key序列化为字符串
        // redisTemplate.setValueSerializer(new StringRedisSerializer());
        // redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        // redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
