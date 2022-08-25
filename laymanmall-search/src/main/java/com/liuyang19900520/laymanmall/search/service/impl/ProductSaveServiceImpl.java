package com.liuyang19900520.laymanmall.search.service.impl;

import com.liuyang19900520.laymanmall.common.to.es.SkuEsModel;
import com.liuyang19900520.laymanmall.search.repository.ProductDao;
import com.liuyang19900520.laymanmall.search.service.ProductSaveService;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductSaveServiceImpl implements ProductSaveService {

  @Autowired
  private ProductDao productDao;

  @Override
  public boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException {
    //1.在es中建立索引，建立号映射关系（doc/json/product-mapping.json）

    //2. 在ES中保存这些数据
//        BulkRequest bulkRequest = new BulkRequest();
//        for (SkuEsModel skuEsModel : skuEsModels) {
//            //构造保存请求
//            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
//            indexRequest.id(skuEsModel.getSkuId().toString());
//            String jsonString = JSON.toJSONString(skuEsModel);
//            indexRequest.source(jsonString, XContentType.JSON);
//            bulkRequest.add(indexRequest);
//        }

    // BulkResponse bulk = elasticsearchRestTemplate.(bulkRequest, GulimallElasticSearchConfig.COMMON_OPTIONS);


    //TODO 如果批量错误
    // boolean hasFailures = bulk.hasFailures();

    // List<String> collect = Arrays.asList(bulk.getItems()).stream().map(item -> {
    //   return item.getId();
    // }).collect(Collectors.toList());

    // log.info("商品上架完成：{}",collect);
    return true;
    // return hasFailures;
  }
}
