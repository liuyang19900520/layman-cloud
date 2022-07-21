package com.liuyang19900520.laymanmall.ware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.liuyang19900520.laymanmall.ware.feign")
public class LaymanmallWareApplication {

  public static void main(String[] args) {
    SpringApplication.run(LaymanmallWareApplication.class, args);
  }

}
