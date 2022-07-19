package com.liuyang19900520.laymanmall.product;

import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.liuyang19900520.laymanmall.product.feign")

public class LaymanmallProductApplication {

  public static void main(String[] args) {
    SpringApplication.run(LaymanmallProductApplication.class, args);
  }

}
