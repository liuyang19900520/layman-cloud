package com.liuyang19900520.laymanmall.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LaymanmallMemberApplication {

  public static void main(String[] args) {
    SpringApplication.run(LaymanmallMemberApplication.class, args);
  }

}
