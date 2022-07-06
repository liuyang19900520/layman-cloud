package com.liuyang19900520.laymanmall.product.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.liuyang19900520.laymanmall.product.entity.AttrEntity;
import java.util.List;
import lombok.Data;

@Data
public class AttrGroupWithAttrVo{
    /**
     * 分组id
     */
    @TableId
    private Long attrGroupId;
    /**
     * 组名
     */
    private String attrGroupName;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    private Long catelogId;

    private List<AttrEntity> attrs;
}
