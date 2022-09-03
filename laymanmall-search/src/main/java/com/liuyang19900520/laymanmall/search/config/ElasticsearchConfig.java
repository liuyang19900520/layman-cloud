package com.liuyang19900520.laymanmall.search.config;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * <p>
 *
 * </p>
 *
 * @author Max Liu
 * @since 2022/08/13
 */
@ConfigurationProperties(prefix = "elasticsearch")
@Configuration
@Data
public class ElasticsearchConfig extends AbstractElasticsearchConfiguration {

  private String host;
  private Integer port;

  public static final RequestOptions COMMON_OPTIONS;

  static {
    RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
    //builder.addHeader("Authorization", "Bearer " + TOKEN);
//        builder.setHttpAsyncResponseConsumerFactory(
//                new HttpAsyncResponseConsumerFactory
//                        .HeapBufferedResponseConsumerFactory(30 * 1024 * 1024 * 1024));
    COMMON_OPTIONS = builder.build();
  }

  //重写父类方法
  @Override
  @Bean
  public RestHighLevelClient elasticsearchClient() {
    RestClientBuilder builder = RestClient.builder(new HttpHost(host, port));
    RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
    return restHighLevelClient;
  }
}
