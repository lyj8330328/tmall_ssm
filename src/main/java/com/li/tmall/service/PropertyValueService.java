package com.li.tmall.service;

import com.li.tmall.pojo.Product;
import com.li.tmall.pojo.PropertyValue;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-19 22:45
 * Feature:CRUD
 */
public interface PropertyValueService {

    /**
     * 初始化
     * @param product
     */
    void init(Product product);

    /**
     * 更新属性值
     * @param propertyValue
     */
    void update(PropertyValue propertyValue);

    /**
     * 获取属性值
     * @param ptid
     * @param pid
     * @return
     */
    PropertyValue get(Integer ptid,Integer pid);

    /**
     * 根据商品id查询对应属性
     * @param pid
     * @return
     */
    List<PropertyValue> list(Integer pid);
}
