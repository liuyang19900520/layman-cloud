package com.liuyang19900520.laymanmall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LaymanmallCouponApplication {

  public static void main(String[] args) {
    SpringApplication.run(LaymanmallCouponApplication.class, args);
  }

}
