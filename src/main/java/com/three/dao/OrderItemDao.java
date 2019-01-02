package com.three.dao;

import com.three.beans.Book;
import com.three.beans.Order;
import com.three.beans.OrderItem;
import com.three.service.orderItem.OrderItemService;
import com.three.utils.HibernateDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemDao extends HibernateDao<OrderItem> {


    Logger logger = LogManager.getLogger(OrderItemService.class);

    @Autowired
    OrderDao orderDao;               //找出购物车

                                //订单编号    书号
    public OrderItem findOne(int item_id,int b_id){
        String hql = "from OrderItem where item_id = "+item_id+" and b_id = "+b_id+" and status = 1";
//        String []param = {item_id};

        return this.findOne(hql,null);
    }

//    public List<OrderItem> findItems(int item_id, int b_id){
//        String hql = "from OrderItem where item_id = "+item_id+" and b_id = "+b_id;
////        String []param = {item_id};
//        return this.find(hql,null);
//
//    }



    public int addItem(Book book,String u_id){

                                                    //book name
        int b_id = book.getId();
        Order bookCar = orderDao.find_Car_OrderByFlag(u_id);              //找到购物车的id
        int item_id = bookCar.getId();

        logger.info("b_id:"+b_id);
        logger.info("item_id:"+item_id);

        logger.info("book's number:"+book.getNum());

        OrderItem orderItem = new OrderItem();
//        orderItem.setBook(book);
        orderItem.setCount(book.getNum());                  //数量
        orderItem.setSubtotal(orderItem.getCount()*book.getPrice());

        orderItem.setItemId(item_id);                                     //插入外键
        orderItem.setbId(b_id);

        orderItem.setStatus(1);

        return this.insert(orderItem);


    }


    public int deleteItem(int item_id,int b_id){
        OrderItem orderItem = this.findOne(item_id,b_id);
        logger.info("ididid:"+orderItem.getId());
        orderItem.setStatus(0);
        return this.update(orderItem);
    }


}
