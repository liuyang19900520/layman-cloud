package com.liuyang19900520.laymanmall.member.dao;

import com.liuyang19900520.laymanmall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author Max Liu
 * @email liuyang19900520@hotmail.com
 * @date 2022-06-09 17:43:32
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
