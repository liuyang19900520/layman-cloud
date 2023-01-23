/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package io.renren;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class RenrenApplication {

  public static void main(String[] args) {
    log.info("气象装备智能化仓储管理系统（ME-WMS）环境加载");
    log.info("气象装备智能化仓储管理系统（ME-WMS）开始启动");
    SpringApplication.run(RenrenApplication.class, args);
    log.info("气象装备智能化仓储管理系统（ME-WMS）启动完成");

  }

}
