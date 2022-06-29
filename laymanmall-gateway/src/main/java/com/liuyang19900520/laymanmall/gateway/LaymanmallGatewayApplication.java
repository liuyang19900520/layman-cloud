package com.liuyang19900520.laymanmall.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDiscoveryClient
public class LaymanmallGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaymanmallGatewayApplication.class, args);
	}

}
