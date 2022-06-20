package com.liuyang19900520.laymanmall.product;

import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LaymanmallProductApplication {

  public static void main(String[] args) {
    SpringApplication.run(LaymanmallProductApplication.class, args);
  }

}
