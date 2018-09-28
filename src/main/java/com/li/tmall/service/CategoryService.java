package com.li.tmall.service;

import com.li.tmall.pojo.Category;
import util.Page;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-15 21:47
 * Feature:
 */
public interface CategoryService {

    /**
     * 分页查询
     * @return
     */
    List<Category> list();

//    /**
//     * 分页查询
//     * @return
//     */
//    List<Category> list(Page page);
//
//    /**
//     * 查询总数
//     * @return
//     */
//    int total();

    /**
     * 增加
     * @param category
     */
    void add(Category category);

    /**
     * 删除
     * @param id
     */
    void delete(int id);

    /**
     * 根据id获取category
     * @param id
     * @return
     */
    Category get(int id);

    /**
     * 更新
     * @param category
     */
    void update(Category category);
}
