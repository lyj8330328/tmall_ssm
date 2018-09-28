package com.li.tmall.pojo;

import javax.persistence.Transient;
import java.util.List;

public class Category {
    private Integer id;

    private String name;

    /**
     * 在前端展示分类时，要同时获取分类下的所有产品信息
     */
    @Transient
    private List<Product> products;

    /**
     * 在前端导航栏里，点击对应的分类右侧会显示推荐商品列表
     */
    @Transient
    private List<List<Product>> productsRow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<List<Product>> getProductsRow() {
        return productsRow;
    }

    public void setProductsRow(List<List<Product>> productsRow) {
        this.productsRow = productsRow;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", products=" + products +
                ", productsRow=" + productsRow +
                '}';
    }
}