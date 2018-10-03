package com.li.tmall.service;

import com.li.tmall.pojo.Order;
import com.li.tmall.pojo.OrderItem;

import java.util.List;

/**
 * @Author: 98050
 * Time: 2018-09-20 13:40
 * Feature:CRUD,订单状态信息
 */
public interface OrderService {
    String WAIT_PAY = "waitPay";
    String WAIT_DELIVERY = "waitDelivery";
    String WAIT_CONFIRM = "waitConfirm";
    String WAIT_REVIEW = "waitReview";
    String FINISH = "finish";
    String DELETE = "delete";

    /**
     * 订单增加
     * @param order
     */
    void add(Order order);

    /**
     * 订单删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 订单更新
     * @param order
     */
    void update(Order order);

    /**
     * 根据id获取订单
     * @param id
     * @return
     */
    Order get(Integer id);

    /**
     * 获取全部订单
     * @return
     */
    List<Order> list();

    /**
     * 根据订单和订单项算出支付金额并返回，且保存订单对象
     * @param order
     * @param orderItems
     * @return
     */
    float addDetail(Order order, List<OrderItem> orderItems);

    /**
     * 根据用户id查询所有状态（不包括删除状态）订单
     * @param uid
     * @param excludedStatus
     * @return
     */
    List<Order> list(int uid,String excludedStatus);
}
