package com.liuyang19900520.laymanmall.search.service;


import com.liuyang19900520.laymanmall.search.vo.SearchParam;
import com.liuyang19900520.laymanmall.search.vo.SearchReult;

public interface MallSearchService {
    /**
     *
     * @param param 检索的所有参数
     * @return  检索的结果
     */
    SearchReult search(SearchParam param);
}
