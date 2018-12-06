package com.dtk.satc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 功能描述:
 * 项目名:
 * 创建者: wangliang
 * 创建日期: 2018年11月27日11:18:19
 */
@SpringBootApplication
public class ServiceBootApplication {
    public static void main(String[] args) {
        System.out.println(123);
        SpringApplication.run(ServiceBootApplication.class, args);
    }
}
