package com.li.tmall.service;

import com.li.tmall.pojo.User;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-20 10:50
 * Feature:R
 */
public interface UserService {

    /**
     * 用户列表
     * @return
     */
    List<User> list();

    /**
     * 在查询订单时，为订单项中的user字段赋值
     * @param id
     * @return
     */
    User get(Integer id);

    /**
     * 增加用户
     * @param user
     */
    void add(User user);

    /**
     * 判断用户名是否重复
     * @param name
     * @return
     */
    boolean isExist(String name);

    /**
     * 登录时用户验证
     * @param name
     * @param password
     * @return
     */
    User get(String name,String password);
}
