package com.li.tmall.service;

import com.li.tmall.pojo.Order;
import com.li.tmall.pojo.OrderItem;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-20 14:20
 * Feature:CRUD
 */
public interface OrderItemService {

    /**
     * 新增订单条目
     * @param orderItem
     */
    void add(OrderItem orderItem);

    /**
     * 删除订单条目
     * @param id
     */
    void delete(Integer id);

    /**
     * 更新订单条目
     * @param orderItem
     */
    void update(OrderItem orderItem);

    /**
     * 根据id获取订单条目
     * @param id
     * @return
     */
    OrderItem get(Integer id);

    /**
     * 获取全部订单
     * @return
     */
    List<OrderItem> list();

    void fill(List<Order> orders);

    void fill(Order order);

    /**
     * 根据产品获取销售量
     * @param pid
     * @return
     */
    int getSaleCount(int pid);

    /**
     * 根据用户id查询对应的订单条目
     * @param uid
     * @return
     */
    List<OrderItem> listByUser(int uid);
}
