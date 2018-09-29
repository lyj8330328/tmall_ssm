package com.li.tmall.service.impl;

import com.li.tmall.mapper.OrderItemMapper;
import com.li.tmall.pojo.Order;
import com.li.tmall.pojo.OrderItem;
import com.li.tmall.pojo.OrderItemExample;
import com.li.tmall.pojo.Product;
import com.li.tmall.service.OrderItemService;
import com.li.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-20 14:24
 * Feature:CRUD
 */
@Service
public class OrderItemServiceImpl implements OrderItemService {


    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private ProductService productService;

    @Override
    public void add(OrderItem orderItem) {
        orderItemMapper.insert(orderItem);
    }

    @Override
    public void delete(Integer id) {
        orderItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(OrderItem orderItem) {
        orderItemMapper.updateByPrimaryKeySelective(orderItem);
    }

    @Override
    public OrderItem get(Integer id) {
        OrderItem orderItem = orderItemMapper.selectByPrimaryKey(id);
        setProduct(orderItem);
        return orderItem;
    }

    @Override
    public List<OrderItem> list() {
        OrderItemExample example = new OrderItemExample();
        example.setOrderByClause("id desc");
        List<OrderItem> items = orderItemMapper.selectByExample(example);
        setProduct(items);
        return items;
    }

    @Override
    public void fill(List<Order> orders) {
        for (Order order : orders){
            fill(order);
        }
    }

    @Override
    public void fill(Order order) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andOidEqualTo(order.getId());
        example.setOrderByClause("id desc");
        List<OrderItem> items = orderItemMapper.selectByExample(example);
        /**
         * 给orderitem中的product属性赋值
         */
        setProduct(items);
        /**
         * 求订单金额，商品数量
         */
        float total = 0;
        int totalNumber = 0;

        for (OrderItem orderItem : items){
            total += orderItem.getNumber()*orderItem.getProduct().getPromotePrice();
            totalNumber += orderItem.getNumber();
        }
        order.setTotal(total);
        order.setTotalNumber(totalNumber);
        order.setOrderItems(items);
    }

    /**
     * 问题：如何确定一个完整的订单
     * @param pid
     * @return
     */
    @Override
    public int getSaleCount(int pid) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andPidEqualTo(pid).andOidIsNull();
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        int result = 0;
        for (OrderItem orderItem : orderItems){
            result += orderItem.getNumber();
        }
        return result;
    }

    @Override
    public List<OrderItem> listByUser(int uid) {
        OrderItemExample example = new OrderItemExample();
        example.createCriteria().andUidEqualTo(uid);
        List<OrderItem> orderItems = orderItemMapper.selectByExample(example);
        setProduct(orderItems);
        return orderItems;
    }

    public void setProduct(List<OrderItem> items){
        for (OrderItem orderItem : items){
            setProduct(orderItem);
        }
    }

    public void setProduct(OrderItem orderItem){
        Product product = productService.get(orderItem.getPid());
        orderItem.setProduct(product);
    }
}
