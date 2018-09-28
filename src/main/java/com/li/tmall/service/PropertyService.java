package com.li.tmall.service;

import com.li.tmall.pojo.Property;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-18 17:41
 * Feature:CRUD
 */
public interface PropertyService {

    /**
     * 属性增加
     * @param property
     */
    void add(Property property);

    /**
     * 属性删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 属性更新
     * @param property
     */
    void update(Property property);

    /**
     * 根据id查询属性
     * @param id
     * @return
     */
    Property get(Integer id);

    /**
     * 根据分类id查询对应的属性
     * @param cid
     * @return
     */
    List<Property> list(Integer cid);
}
