package com.houseWork.server;

import com.houseWork.utils.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.houseWork"})
@MapperScan({"com.houseWork.mapper"})
public class MainServer {
    private final Logger log = LoggerFactory.getLogger(MainServer.class);

    public static void main(String[] args) {
        SpringApplication.run(MainServer.class, args);
    }

    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }

}
