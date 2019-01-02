package com.three.service.order;

import com.three.beans.Order;
import com.three.beans.OrderItem;
import com.three.beans.User;
import com.three.service.book.IBookService;
import com.three.utils.HibernateDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {

    private static Logger logger = LogManager.getLogger(User.class);
    private SessionFactory sessionFactory;
    ApplicationContext ctx;
    IOrderService iOrderService;
//    HibernateDao<OrderItem> orderItemHibernateDao;
    @Before
    public void setUp() throws Exception {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        iOrderService = (IOrderService) ctx.getBean("orderService");

//        orderItemHibernateDao = (HibernateDao<OrderItem>) ctx.getBean("hibernateDao");
    }


//查看购物车信息
    @Test
    public void findCar_order() {

        User user  = new User();
        user.setEmail("1");
        Order order = iOrderService.findCar_order(user);
        List<OrderItem> list = order.getOrderItems();
        for(OrderItem orderItem:list){
            logger.info("name:"+orderItem.getBook().getName());
            logger.info("name:"+orderItem.getBook().getNum());
        }

    }
//提交购物车
    @Test
    public void addOrder() {
        User user  = new User();
        user.setEmail("1");
        int id = iOrderService.addOrder(user);
        System.out.println(id);
    }

    @Test
    public void getTotal() {
        User user  = new User();
        user.setEmail("1");
        double num = iOrderService.getTotal(user);
        logger.info("num:"+num);
    }
//
    @Test
    public void updateOrder() {
        User user  = new User();
        user.setEmail("1");
        String phone = "110";
        String address = "山东农业大学";

        iOrderService.updateOrderInfo(user,phone,address);

    }

    @Test
    public void findOrders() {
        User user  = new User();
        user.setEmail("8");
        List<Order> orderList = iOrderService.findOrders(user);
        for(int i=0; i<orderList.size(); i++){
            Order order = orderList.get(i);
            List<OrderItem> itemList = order.getOrderItems();
            for(int j=0; j<orderList.size(); j++){
                if(orderList.get(j).getFlag() == 0)
                    orderList.remove(j);

            }

            for(OrderItem orderItem:itemList){

                logger.info("book name:"+orderItem.getBook().getName());
                logger.info("book number:"+orderItem.getBook().getNum());

            }

        }

    }
}