package com.three.service.orderItem;

import com.three.beans.Book;
import com.three.beans.OrderItem;
import com.three.beans.User;

public interface IOrderItemService {



    public int addOrderItem(Book book,User user);

    public OrderItem findOne(Book book, User user);

    public int deleteOrderItem(Book book, User user);

}
