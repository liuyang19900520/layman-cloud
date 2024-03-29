package com.liuyang19900520.laymanmall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liuyang19900520.laymanmall.common.utils.PageUtils;
import com.liuyang19900520.laymanmall.common.utils.Query;
import com.liuyang19900520.laymanmall.product.dao.BrandDao;
import com.liuyang19900520.laymanmall.product.dao.CategoryBrandRelationDao;
import com.liuyang19900520.laymanmall.product.dao.CategoryDao;
import com.liuyang19900520.laymanmall.product.entity.BrandEntity;
import com.liuyang19900520.laymanmall.product.entity.CategoryBrandRelationEntity;
import com.liuyang19900520.laymanmall.product.entity.CategoryEntity;
import com.liuyang19900520.laymanmall.product.service.BrandService;
import com.liuyang19900520.laymanmall.product.service.CategoryBrandRelationService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("categoryBrandRelationService")
public class CategoryBrandRelationServiceImpl extends ServiceImpl<CategoryBrandRelationDao, CategoryBrandRelationEntity> implements
  CategoryBrandRelationService {

    @Autowired
    BrandDao brandDao;
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    CategoryBrandRelationDao relationDao;
    @Autowired
    BrandService brandService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryBrandRelationEntity> page = this.page(
                new Query<CategoryBrandRelationEntity>().getPage(params),
                new QueryWrapper<CategoryBrandRelationEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void saveDetails(CategoryBrandRelationEntity categoryBrandRelation) {
        Long brandId = categoryBrandRelation.getBrandId();
        Long catelogId = categoryBrandRelation.getCatelogId();
        BrandEntity brandEntity = brandDao.selectById(brandId);
        CategoryEntity categoryEntity = categoryDao.selectById(catelogId);
        categoryBrandRelation.setBrandName(brandEntity.getName());
        categoryBrandRelation.setCatelogName(categoryEntity.getName());
        this.save(categoryBrandRelation);
    }

    @Override
    public void updateBrand(Long brandId, String name) {
        CategoryBrandRelationEntity brandRelationEntity=new CategoryBrandRelationEntity();
        brandRelationEntity.setBrandId(brandId);
        brandRelationEntity.setBrandName(name);
        this.update(brandRelationEntity,new UpdateWrapper<CategoryBrandRelationEntity>()
        .eq("brand_id",brandId)
        );
    }

    @Override
    public void updateCategory(Long catId, String name) {
        this.baseMapper.updateCategory(catId,name);
    }

    @Override
    public List<BrandEntity> getBrandById(Long catId) {
        List<CategoryBrandRelationEntity> entityList = relationDao.selectList(new QueryWrapper<CategoryBrandRelationEntity>().eq("catelog_id", catId));
        //下面的封装是为了以后的重用
        List<BrandEntity> collect = entityList.stream().map(relationEntity -> {
            Long brandId = relationEntity.getBrandId();
            return brandService.getById(brandId);
        }).collect(Collectors.toList());

        return collect;
    }

}
