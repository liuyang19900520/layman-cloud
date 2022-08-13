package com.liuyang19900520.laymanmall.search;

import com.liuyang19900520.laymanmall.search.vo.Product;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class LaymanmallSearchApplicationTests {

  //注入 ElasticsearchRestTemplate
  @Autowired
  private ElasticsearchRestTemplate elasticsearchRestTemplate;

  //创建索引并增加映射配置
  @Test
  public void createIndex() {
    //创建索引，系统初始化会自动创建索引
//    elasticsearchRestTemplate.createIndex(Product.class);
    System.out.println("创建索引");
  }

  @Test
  public void deleteIndex() {
    //创建索引，系统初始化会自动创建索引
    boolean flg = elasticsearchRestTemplate.deleteIndex(Product.class);
    System.out.println("删除索引 = " + flg);
  }

}
