package com.liuyang19900520.laymanmall.ware.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.ware.entity.PurchaseEntity;
import com.liuyang19900520.laymanmall.ware.vo.MergeVo;
import com.liuyang19900520.laymanmall.ware.vo.PurchaseFinishVo;
import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author cosmoswong
 * @email cosmoswong@sina.com
 * @date 2020-04-23 23:42:51
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceived(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);

    void received(List<Long> ids);

    void done(PurchaseFinishVo finishVo);
}

