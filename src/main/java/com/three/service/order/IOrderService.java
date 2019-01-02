package com.three.service.order;

import com.three.beans.Order;
import com.three.beans.User;

import java.util.List;

public interface IOrderService {


    public Order findCar_order(User user);
    public int addOrder(User user);

    public double getTotal(User user);

    //更改，添加 手机号，地址
    public int updateOrderInfo(User user,String phone,String address);

    //在这里查找User
    public List<Order> findOrders(User user);

    public int deleteOrder(Order order);


}
