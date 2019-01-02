package com.three.service.orderItem;

import com.three.beans.Book;
import com.three.beans.Order;
import com.three.beans.OrderItem;
import com.three.beans.User;
import com.three.dao.OrderDao;
import com.three.dao.OrderItemDao;
import com.three.service.user.IUserService;
import com.three.service.user.UserService;
import com.three.utils.HibernateDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class OrderItemServiceTest {


    private static Logger logger = LogManager.getLogger(User.class);
    private SessionFactory sessionFactory;
    ApplicationContext ctx;
    IOrderItemService iOrderItemService;
    OrderDao orderDao;


    @Before
    public void setUp() throws Exception {

        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");
        iOrderItemService = (IOrderItemService) ctx.getBean("orderItemService");
        orderDao = (OrderDao) ctx.getBean("orderDao");

    }

    @Test
    public void addOrderItem() {

        User user = new User();
        user.setEmail("1");                             //order id = 11

        Book book = new Book();
        book.setName("name is+4");                    //book id = 6
        book.setNum(8);


        iOrderItemService.addOrderItem(book,user);

    }

    //ok
    @Test
    public void findOne() {
        User user = new User();
        user.setEmail("1");                             //order id = 11

        Book book = new Book();
        book.setName("name is+1");                    //book id = 6

        OrderItem item = iOrderItemService.findOne(book,user);

        System.out.println(item.getItemId());                      // sout   10


    }

    @Test
    public void deleteOrderItem() {
        User user = new User();
        user.setEmail("8");                             //order id = 11

        Book book = new Book();
        book.setName("zk");                    //book id = 6

        iOrderItemService.deleteOrderItem(book,user);
    }


@Test
    public void findOne_dao(){

        String hql = "form OrderItem where item_id = "+10+" and b_id = "+3;
//        String []param = {item_id};

        orderDao.find(hql,null);

//        OrderItem orderItem = iOrderItemService.findOne(hql,null);
    }
}