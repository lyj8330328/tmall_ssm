package com.li.tmall.service.impl;

import com.li.tmall.mapper.OrderMapper;
import com.li.tmall.pojo.Order;
import com.li.tmall.pojo.OrderExample;
import com.li.tmall.pojo.User;
import com.li.tmall.service.OrderService;
import com.li.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-20 14:24
 * Feature:CRUD
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserService userService;

    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }

    @Override
    public void delete(Integer id) {
        orderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public Order get(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> list() {
        OrderExample example = new OrderExample();
        example.setOrderByClause("id desc");
        List<Order> result = orderMapper.selectByExample(example);
        setUser(result);
        return result;
    }

    public void setUser(List<Order> orders){
        for (Order order : orders){
            setUser(order);
        }
    }

    public void setUser(Order order){
        User user = userService.get(order.getUid());
        order.setUser(user);
    }
}
