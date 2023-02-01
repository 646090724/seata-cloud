package com.leij.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.leij.product.model.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

}
