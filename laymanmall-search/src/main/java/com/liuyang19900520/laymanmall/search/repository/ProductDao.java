package com.liuyang19900520.laymanmall.search.repository;

import com.liuyang19900520.laymanmall.search.vo.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2022/08/13
 */
@Repository
public interface ProductDao extends ElasticsearchRepository<Product, Long> {

}
