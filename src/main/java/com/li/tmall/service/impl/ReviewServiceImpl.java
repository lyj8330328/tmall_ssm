package com.li.tmall.service.impl;

import com.li.tmall.mapper.ReviewMapper;
import com.li.tmall.pojo.Review;
import com.li.tmall.pojo.ReviewExample;
import com.li.tmall.pojo.User;
import com.li.tmall.service.ReviewService;
import com.li.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-27 13:54
 * Feature:CRUD
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private UserService userService;

    @Override
    public void add(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public void delete(Integer id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review review) {
        reviewMapper.updateByPrimaryKeySelective(review);
    }

    @Override
    public Review get(Integer id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Review> list(Integer pid) {
        ReviewExample example = new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");

        List<Review> reviews = reviewMapper.selectByExample(example);

        //添加user对象
        setUser(reviews);
        return reviews;
    }

    @Override
    public int getCount(Integer pid) {
        return list(pid).size();
    }

    private void setUser(List<Review> reviews) {
        for (Review review : reviews){
            setUser(review);
        }
    }

    private void setUser(Review review){
        User user = userService.get(review.getUid());
        review.setUser(user);
    }

}
