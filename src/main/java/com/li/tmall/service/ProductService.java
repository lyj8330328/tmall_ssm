package com.li.tmall.service;

import com.li.tmall.pojo.Category;
import com.li.tmall.pojo.Product;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-18 20:25
 * Feature:CRUD
 */
public interface ProductService {
    /**
     * 商品增加
     * @param product
     */
    void add(Product product);

    /**
     * 商品删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 商品更新
     * @param product
     */
    void update(Product product);

    /**
     * 根据id获取商品
     * @param id
     * @return
     */
    Product get(Integer id);

    /**
     * 根据分类id查询对应的商品
     * @param cid
     * @return
     */
    List<Product> list(Integer cid);

    /**
     * 设置商品的第一张显示图片
     * @param product
     */
    void setFirstProductImage(Product product);

    void fill(List<Category> categories);

    void fill(Category category);

    void fillByRow(List<Category> categories);

    /**
     * 为产品设置销量和评价数量
     * @param product
     */
    void setSaleAndReviewNumber(Product product);

    /**
     * 为产品集设置销量和评价数量
     * @param products
     */
    void setSaleAndReviewNumber(List<Product> products);

    /**
     * 根据关键字查询商品
     * @param keyword
     * @return
     */
    List<Product> search(String keyword);
}
