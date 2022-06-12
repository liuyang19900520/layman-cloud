package com.liuyang19900520.laymanmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.member.entity.MemberLevelEntity;

import java.util.Map;

/**
 * 会员等级
 *
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-06-09 17:43:32
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

