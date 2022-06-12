package com.liuyang19900520.laymanmall.coupon.controller;

import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liuyang19900520.laymanmall.coupon.entity.CouponEntity;
import com.liuyang19900520.laymanmall.coupon.service.CouponService;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.common.utils.R;


/**
 * 优惠券信息
 *
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-04-26 18:38:49
 */
@RestController
@RefreshScope
@RequestMapping("coupon/coupon")
public class CouponController {

  @Autowired
  private CouponService couponService;

  @Value("${coupon.user.name}")
  private String name;
  @Value("${coupon.user.age}")
  private Integer age;

  @RequestMapping("/test")
  public R getConfigInfo() {
    return R.ok().put("name", name).put("age", age);
  }


  /**
   * 列表
   */
  @RequestMapping("/member/list")
  public R memberCoupons(@RequestParam Map<String, Object> params) {
    CouponEntity couponEntity = new CouponEntity();
    couponEntity.setCouponName("discount 20%");
    return R.ok().put("coupons", Arrays.asList(couponEntity));
  }

  /**
   * 列表
   */
  @RequestMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = couponService.queryPage(params);

    return R.ok().put("page", page);
  }


  /**
   * 信息
   */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    CouponEntity coupon = couponService.getById(id);

    return R.ok().put("coupon", coupon);
  }

  /**
   * 保存
   */
  @RequestMapping("/save")
  public R save(@RequestBody CouponEntity coupon) {
    couponService.save(coupon);

    return R.ok();
  }

  /**
   * 修改
   */
  @RequestMapping("/update")
  public R update(@RequestBody CouponEntity coupon) {
    couponService.updateById(coupon);

    return R.ok();
  }

  /**
   * 删除
   */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    couponService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

}
