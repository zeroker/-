package com.three.service.order;

import com.three.beans.Order;
import com.three.beans.User;
import com.three.dao.OrderDao;
import com.three.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    UserDao userDao;



    //a
    //提交订单               !!!!
    @Override
    public int addOrder(User user) {

        user = userDao.findUserByEmail(user);

        int n = orderDao.addOrder(user);

        if(n == 1)
            return orderDao.createCarOrder(user);
        return 0;
    }


    //a
    //  结算//
    @Override
    public double getTotal(User user) {
        user = userDao.findUserByEmail(user);

        double sum = orderDao.updateOrderTotal(this.findCar_order(user));
        if(sum !=0)
            return sum;
        return sum;
    }

    //a
    // 增加/更改 订单的手机号和地址
    @Override
    public int updateOrderInfo(User user,String phone,String address) {
        user = userDao.findUserByEmail(user);

        return orderDao.updateOrderInfo(this.findCar_order(user),phone,address);
    }

    //a  查看购物车信息
    //找出未提交的订单来
    public Order findCar_order(User user){                  //只有name() 的user

        String u_id = userDao.findUserByEmail(user).getU_id();
        return orderDao.find_Car_OrderByFlag(u_id);
    }


    //a
    //查看所有订单 信息
    @Override
    public List<Order> findOrders(User user) {
        user = userDao.findUserByEmail(user);

        return orderDao.findOrders(user);
    }




//暂定功能
    //删除订单
    @Override
    public int deleteOrder(Order order) {
        return orderDao.deleteOrder(order);
    }

}
