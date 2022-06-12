package com.liuyang19900520.laymanmall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.member.entity.MemberStatisticsInfoEntity;

import java.util.Map;

/**
 * 会员统计信息
 *
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-06-09 17:43:32
 */
public interface MemberStatisticsInfoService extends IService<MemberStatisticsInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

