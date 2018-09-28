package com.li.tmall.service.impl;

import com.li.tmall.mapper.ProductMapper;
import com.li.tmall.pojo.Category;
import com.li.tmall.pojo.Product;
import com.li.tmall.pojo.ProductExample;
import com.li.tmall.pojo.ProductImage;
import com.li.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-18 20:31
 * Feature:CRUD
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ReviewService reviewService;

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void delete(Integer id) {
        productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        productMapper.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product get(Integer id) {
        Product product = productMapper.selectByPrimaryKey(id);
        set(product);
        setFirstProductImage(product);
        return product;
    }

    @Override
    public List<Product> list(Integer cid) {
        ProductExample example = new ProductExample();
        example.createCriteria().andCidEqualTo(cid);
        example.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(example);
        setCategory(products);
        setFirstProductImage(products);
        return products;
    }

    @Override
    public void setFirstProductImage(Product product) {
        List<ProductImage> list = productImageService.list(product.getId(),ProductImageService.TYPE_SINGLE);
        if (!list.isEmpty()){
            ProductImage productImage = list.get(0);
            product.setProductImage(productImage);
        }
    }

    /**
     * 为多个分类填充产品集合
     * @param categories
     */
    @Override
    public void fill(List<Category> categories) {
        for (Category category : categories){
            fill(category);
        }
    }

    /**
     * 为分类填充产品集合
     * @param category
     */
    @Override
    public void fill(Category category) {
        List<Product> products = list(category.getId());
        category.setProducts(products);
    }


    /**
     * 为多个分类填充推荐产品集合，即把分类下的产品集合，按照8个为一行，拆成多行，以利于后续页面上进行显示
     * @param categories
     */
    @Override
    public void fillByRow(List<Category> categories) {
        int productNumEachRow = 8;
        for (Category category : categories){
            List<Product> products = category.getProducts();
            List<List<Product>> productRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += productNumEachRow){
                int size = i + productNumEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> temp = products.subList(i,size);
                productRow.add(temp);
            }
            category.setProductsRow(productRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(Product product) {
        int saleCount = orderItemService.getSaleCount(product.getId());
        int reviewCount = reviewService.getCount(product.getId());

        product.setSaleCount(saleCount);
        product.setReviewCount(reviewCount);

    }

    @Override
    public void setSaleAndReviewNumber(List<Product> products) {
        for (Product product : products){
            setSaleAndReviewNumber(product);
        }
    }

    @Override
    public List<Product> search(String keyword) {
        ProductExample example = new ProductExample();
        example.createCriteria().andNameLike("%"+keyword+"%");
        example.setOrderByClause("id desc");
        List<Product> products = productMapper.selectByExample(example);
        setFirstProductImage(products);
        setCategory(products);
        return products;
    }

    public void setFirstProductImage(List<Product> products){
        for (Product product : products){
            setFirstProductImage(product);
        }
    }

    public void setCategory(List<Product> products){
        for (Product p:products){
            set(p);
        }
    }

    public void set(Product product){
        int cid = product.getCid();
        Category category = categoryService.get(cid);
        product.setCategory(category);
    }

}
