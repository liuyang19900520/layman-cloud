package com.liuyang19900520.laymanmall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.liuyang19900520.laymanmall.member.feign")
public class LaymanmallMemberApplication {

  public static void main(String[] args) {
    SpringApplication.run(LaymanmallMemberApplication.class, args);
  }

}
