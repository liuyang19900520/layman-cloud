package com.liuyang19900520.laymanmall.member.controller;

import com.liuyang19900520.laymanmall.member.feign.CouponFeignService;
import java.util.Arrays;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liuyang19900520.laymanmall.member.entity.MemberEntity;
import com.liuyang19900520.laymanmall.member.service.MemberService;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.common.utils.R;


/**
 * 会员
 *
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-06-09 17:43:32
 */
@RestController
@RequestMapping("member/member")
public class MemberController {

  @Autowired
  private MemberService memberService;

  @Autowired
  private CouponFeignService couponFeignService;

  /**
   * 列表
   */
  @RequestMapping("/test")
  public R test(@RequestParam Map<String, Object> params) {
    R r = couponFeignService.memberCoupons();

    return R.ok().put("page", r);
  }

  /**
   * 列表
   */
  @RequestMapping("/list")
  public R list(@RequestParam Map<String, Object> params) {
    PageUtils page = memberService.queryPage(params);

    return R.ok().put("page", page);
  }


  /**
   * 信息
   */
  @RequestMapping("/info/{id}")
  public R info(@PathVariable("id") Long id) {
    MemberEntity member = memberService.getById(id);

    return R.ok().put("member", member);
  }

  /**
   * 保存
   */
  @RequestMapping("/save")
  public R save(@RequestBody MemberEntity member) {
    memberService.save(member);

    return R.ok();
  }

  /**
   * 修改
   */
  @RequestMapping("/update")
  public R update(@RequestBody MemberEntity member) {
    memberService.updateById(member);

    return R.ok();
  }

  /**
   * 删除
   */
  @RequestMapping("/delete")
  public R delete(@RequestBody Long[] ids) {
    memberService.removeByIds(Arrays.asList(ids));

    return R.ok();
  }

}
