package com.three.dao;

import com.three.beans.Order;
import com.three.beans.OrderItem;
import com.three.beans.User;
import com.three.service.user.UserService;
import com.three.utils.HibernateDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Repository
public class OrderDao extends HibernateDao<Order> {

    private  static Logger logger = LogManager.getLogger(OrderDao.class);


    //用户 u_id 的 寻找购物车！！！！！！！！！！！！！！！！！！！！！！！！！！！
    public Order find_Car_OrderByFlag(String u_id){
        String hql = "from Order where o_id = ? and flag = 0 ";  //没有提交的订单
        String pram[] = {u_id};
        Order order1 = this.findOne(hql,pram);

        return order1;
    }


    public Order findOrderByO_id(Order order){

        logger.info("order id:"+order.getId());
        String hql = "from Order where id = "+order.getId()+" and status = 1 and flag = 1";  //没有被删除，已经提交的订单
//        String pram[] = {order.getoId()};

        Order order1 = this.findOne(hql,null);

        return order1;
    }


//创建购物车
    public int createCarOrder(User user){

        List<Order> list = user.getOrders();
        int size = list.size()+1;
        logger.info("size:"+size);
        Order order = new Order();
        order.setStatus(1);
        order.setFlag(0);
        order.setOrdertime(new Timestamp(new Date().getTime()));
        order.setoId(user.getU_id());
        order.setName(user.getUsername()+"的第 "+size+"个订单");

        return this.insert(order);
    }

    //提交
    // flag   == 1
    public int addOrder(User user){

        logger.info("user_id: "+user.getU_id());


        Order order = this.find_Car_OrderByFlag(user.getU_id());
        logger.info("order:"+order.getId());
        order.setFlag(1);

        return this.update(order);


    }

//删除订单
    public int deleteOrder(Order order){

        order = this.findOrderByO_id(order);
        order.setStatus(0);                         //删除

        return this.update(order);

    }

    //更改 手机、地址
    public int updateOrderInfo(Order order,String phone,String address){

        order.setTelephone(phone);
        order.setAddress(address);
        return this.update(order);

    }
    //更改 应付多少钱
    public double updateOrderTotal(Order order){

        double sum = 0.0;
        logger.info("order:"+order.getName());
//        order = this.findOrderByO_id(order);
        List<OrderItem> lists = order.getOrderItems();
        logger.info("size:"+lists.size());
        for(OrderItem orderItem:lists){
            logger.info(orderItem.getSubtotal());
            sum += orderItem.getSubtotal();
        }
        logger.info("sum:"+sum);
        order.setTotal(sum);
        int n = this.update(order);

        if(n == 1)
            return sum;
        else return 0;
    }



    //
    public List<Order> findOrders(User user){

        return user.getOrders();

    }

}
