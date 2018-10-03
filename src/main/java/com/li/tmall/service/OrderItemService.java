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

    /**
     * 为订单对象order填充total、totalNumber、orderItems等属性值，为order对应的orderItem填充product属性值
     * 参数为order对象集合
     * @param orders
     */
    void fill(List<Order> orders);

    /**
     * 为订单对象order填充total、totalNumber、orderItems等属性值，为order对应的orderItem填充product属性值
     * 参数为单个order对象
     * @param order
     */
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

    /**
     * 根据订单id获取订单条目
     * @param oid
     * @return
     */
    List<OrderItem> listByOid(int oid);
}
