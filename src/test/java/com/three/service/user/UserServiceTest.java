package com.three.service.user;

import com.three.beans.Order;
import com.three.beans.OrderItem;
import com.three.beans.User;
import com.three.dao.UserDao;
import com.three.utils.HibernateDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserServiceTest {


    private static Logger logger = LogManager.getLogger(User.class);
    private SessionFactory sessionFactory;
    ApplicationContext ctx;
    IUserService userService;
    HibernateDao<User> userHibernateDao;

    @Before
    public void setUp() throws Exception {

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        userService = (UserService) ctx.getBean("userService");
        userHibernateDao = (HibernateDao<User>) ctx.getBean("hibernateDao");

    }

    @Test
    public void register() {
        User user = new User();
        user.setEmail("1");
        user.setPassword("1");

        Assert.assertTrue(userService.register(user) == 1);


    }

    @Test
    public void login() {
        User user = new User();
        user.setEmail("1");
        user.setPassword("1");
        System.out.println(userService.login(user));
        Assert.assertTrue(userService.login(user) == 1);
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setEmail("8888");
        Assert.assertTrue(userService.deleteUser(user) == 1);

    }

    @Test
    public void reFindPass() {
        User user = new User();
        user.setEmail("361765366@qq.com");
        user.setPassword("helloworld");
        user.setCode(0);
        System.out.println(userService.reFindPass(user));
        Assert.assertTrue(userService.reFindPass(user) == 1);

    }

    @Test
    public void updateInfo() {
        User user = new User();
        user.setEmail("361765366@qq.com");
        user.setPhone("11");
        user.setUsername("kkk");
        int n = userService.updateInfo(user);
        Assert.assertTrue(userService.updateInfo(user) == 1);

    }

    //(发送 找回密码的验证码code)
    @Test
    public void insert_code() {
        User user = new User();
        user.setEmail("361765366@qq.com");
        int n = userService.insert_code(user);
        System.out.println(n);
    }

    @Test
    public void print() {
        System.out.println("这是汉字");
    }


    //six
    @Test
    public void findOrder() {
        User user = new User();
        user.setEmail("1");
        user = userService.findUserByEmail(user);
        System.out.println(user.getPassword());

        List<Order> orderList = userService.findUserByEmail(user).getOrders();

        for (Order order : orderList) {
            System.out.println(order.getName());
            for (OrderItem item : order.getOrderItems()) {

                System.out.println(item.getBook().getName() + "  " + item.getBook().getAuthor());
            }


        }

    }

    //seven  -------ending
    @Test
    public void deleteOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setItemId(10);                     //订单编号 （就是订单的id）

        orderItem.setbId(3);



    }

    @Test
    public void updateOrder(){
        User user = new User();
        user.setEmail("1");
        user = userService.findUserByEmail(user);
        System.out.println(user.getPassword());

        List<Order> orderList = userService.findUserByEmail(user).getOrders();


        for(Order order:orderList){
            System.out.println(order.getName());
//            order.setName("12.31/zk");
            for(OrderItem item:order.getOrderItems()){
//                item.setCount(125);
                System.out.println(item.getBook().getName()+"  "+item.getBook().getAuthor());
            }


        }

//        userHibernateDao.update(user);

        logger.info("ok");
    }

}