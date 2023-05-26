package com.wei.order.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class RedissonConfig {

    @Bean
    public RedissonClient redisson() throws IOException {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://42.193.131.33:6379");
        config.useSingleServer().setPassword("wei123456");
        RedissonClient redisson = Redisson.create(config);
        return redisson;
    }

}
