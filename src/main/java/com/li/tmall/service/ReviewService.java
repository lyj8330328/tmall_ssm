package com.li.tmall.service;

import com.li.tmall.pojo.Review;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-27 13:48
 * Feature:CRUD
 */
public interface ReviewService {

    void add(Review review);

    void delete(Integer id);

    void update(Review review);

    Review get(Integer id);

    List<Review> list(Integer pid);

    int getCount(Integer pid);
}
