package com.liuyang19900520.laymanmall.search.service;

import com.liuyang19900520.laymanmall.common.to.es.SkuEsModel;
import java.io.IOException;
import java.util.List;

public interface ProductSaveService {

    boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
