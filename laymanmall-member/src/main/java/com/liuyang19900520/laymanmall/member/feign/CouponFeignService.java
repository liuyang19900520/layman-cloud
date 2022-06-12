package com.liuyang19900520.laymanmall.member.feign;

import com.liuyang19900520.laymanmall.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "layman-coupon")
public interface CouponFeignService {

  @RequestMapping("/coupon/coupon/member/list")
  public R memberCoupons();
}
