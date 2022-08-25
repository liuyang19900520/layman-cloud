package com.liuyang19900520.laymanmall.search.repository;

import com.liuyang19900520.laymanmall.search.vo.Product;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
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
public interface ProductDao extends CrudRepository<Product, Long> {

  List<Product>  findByTitle(String title);

}
