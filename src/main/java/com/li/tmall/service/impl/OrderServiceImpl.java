package com.li.tmall.service.impl;

import com.li.tmall.mapper.OrderMapper;
import com.li.tmall.pojo.*;
import com.li.tmall.service.OrderItemService;
import com.li.tmall.service.OrderService;
import com.li.tmall.service.ProductService;
import com.li.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private ProductService productService;

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

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackForClassName = "Exception")
    public float addDetail(Order order, List<OrderItem> orderItems) {
        float total = 0;
        add(order);
        if (false){
            throw new RuntimeException();
        }
        for (OrderItem orderItem : orderItems){
            orderItem.setOid(order.getId());
            orderItemService.update(orderItem);
            total+=orderItem.getProduct().getPromotePrice()*orderItem.getNumber();
        }
        return total;
    }

    @Override
    public List<Order> list(int uid, String excludedStatus) {
        OrderExample example = new OrderExample();
        example.createCriteria().andUidEqualTo(uid).andStatusNotEqualTo(excludedStatus);
        example.setOrderByClause("id desc");
        return orderMapper.selectByExample(example);
    }

    @Override
    public void updateStock(int oid) {
        //根据order id获取对应的orderitem
        List<OrderItem> orderItems = orderItemService.listByOid(oid);
        for (OrderItem orderItem : orderItems){
            Product product = productService.get(orderItem.getPid());
            product.setStock(product.getStock() - orderItem.getNumber());
            productService.update(product);
        }
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
