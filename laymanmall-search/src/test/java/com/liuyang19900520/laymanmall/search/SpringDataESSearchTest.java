package com.liuyang19900520.laymanmall.search;

import com.liuyang19900520.laymanmall.search.repository.ProductDao;
import com.liuyang19900520.laymanmall.search.vo.Product;
import java.util.List;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2022/08/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataESSearchTest {

  @Autowired
  private ProductDao productDao;

  @Autowired
  private ElasticsearchRestTemplate elasticsearchRestTemplate;

  /**
   * term 查询 search(termQueryBuilder) 调用搜索方法，参数查询构建器对象
   */
  @Test
  public void termQuery() {
    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("category", "手机");

    Iterable<Product> products = productDao.findAll();
    for (Product product : products) {
      System.out.println(product);
    }
  }

  /**
   * term 查询加分页
   */
  @Test
  public void termQueryByPage() {
    int currentPage = 0;
    int pageSize = 5;
    //设置查询分页
    PageRequest pageRequest = PageRequest.of(currentPage, pageSize);
    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("title", "小米");
    Iterable<Product> products = productDao.findByTitle("小米");
    for (Product product : products) {
      System.out.println(product);
    }

  }

  @Test
  public void aggrQuery() {
// 原生查询构建器
    NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
    // 1.1 source过滤
    queryBuilder.withSourceFilter(new FetchSourceFilter(new String[0], new String[0]));
    // 1.2搜索条件
    queryBuilder.withQuery(QueryBuilders.matchQuery("category", "手机"));
    // 1.3分页及排序条件
    // 1.4高亮显示
    // queryBuilder.withHighlightBuilder(new HighlightBuilder().field("title"));
    // 1.5聚合

    queryBuilder.addAggregation(AggregationBuilders.avg("agg").field("price"));

    NativeSearchQuery qu = queryBuilder.build();

    SearchHits<Product> search = elasticsearchRestTemplate.search(qu, Product.class);

    List<SearchHit<Product>> searchHits = search.getSearchHits();

    System.out.println("a");
  }
}
