package com.liuyang19900520.laymanmall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LaymanmallThirdPartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LaymanmallThirdPartyApplication.class, args);
	}

}
