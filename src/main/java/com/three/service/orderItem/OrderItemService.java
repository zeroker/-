package com.three.service.orderItem;

import com.three.beans.Book;
import com.three.beans.Order;
import com.three.beans.OrderItem;
import com.three.beans.User;
import com.three.dao.BookDao;
import com.three.dao.OrderDao;
import com.three.dao.OrderItemDao;
import com.three.dao.UserDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService implements IOrderItemService {

    Logger logger = LogManager.getLogger(OrderItemService.class);

    @Autowired
    OrderItemDao orderItemDao;
    @Autowired
    BookDao bookDao;
    @Autowired
    OrderDao orderDao;
    @Autowired
    UserDao userDao;

    //a
    @Override
    public int addOrderItem(Book book, User user) {

        String u_id = userDao.findUserByEmail(user).getU_id();

        Book book1 = bookDao.findBookByName(book);

        book1.setNum(book.getNum());
        logger.info("user_name +"+u_id +"  book1:"+book1.getId());
        return orderItemDao.addItem(book1,u_id);
    }
    //a
    @Override
    public int deleteOrderItem(Book book, User user) {

        String u_id = userDao.findUserByEmail(user).getU_id();
        int o_id = orderDao.find_Car_OrderByFlag(u_id).getId();               //找到购物车的id

        int item_id = o_id;

        Book book1 = bookDao.findBookByName(book);
        int b_id =book1.getId();

        logger.info("item_id:"+ item_id+"  b_id:"+b_id);
        return orderItemDao.deleteItem(item_id,b_id);
    }

    @Override
    public OrderItem findOne(Book book, User user) {
        String u_id = userDao.findUserByEmail(user).getU_id();

        int o_id = orderDao.find_Car_OrderByFlag(u_id).getId();               //找到购物车

        int item_id = o_id;
        Book book1 = bookDao.findBookByName(book);
        int b_id =book1.getId();                                         //book 的id

        logger.info("item_id:"+ item_id+"  b_id:"+b_id);   //ok
        return orderItemDao.findOne(item_id,b_id);
    }


}
