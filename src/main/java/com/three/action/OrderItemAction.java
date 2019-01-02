package com.three.action;

import com.opensymphony.xwork2.ActionSupport;
import com.three.beans.Book;
import com.three.beans.User;
import com.three.dao.OrderItemDao;
import com.three.service.orderItem.OrderItemService;
import com.three.utils.other.LoginSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller("orderItemAction")
@Scope(value = "prototype")
@ParentPackage(value = "struts-default")
@Namespace("/orderItem")
public class OrderItemAction extends ActionSupport {

    private static Logger logger = LogManager.getLogger(OrderItemAction.class);

    private User user;                       //取用户 的 用户名   也就是email
    private Book book;                     //取数的名字 还有数的 个数

    @Autowired
    OrderItemService orderItemService;


    @Action(value = "addOrderItem",
            results = {
                    @Result(name = "success",type = "chain",
                            params = {"namespace","/book","actionName","findAllBooks"}),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String  addOrderItem(){

        user = LoginSession.getLoginUser();                                 //
        logger.info("user:"+user.getEmail());

        int n = orderItemService.addOrderItem(book,user);
        if(n == 1)
            return "success";
        return "fail";
    }


    @Action(value = "deleteOrderItem",
            results = {
                    @Result(name = "success",type = "chain",
                            params = {"namespace","/book","actionName","findAllBooks"}),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String deleteOrderItem(){

        user = LoginSession.getLoginUser();                                 //
        logger.info(user.getEmail()+"  "+book.getName());

        int n = orderItemService.deleteOrderItem(book,user);
        if(n == 1)
            return "success";
        return "fail";
    }




    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
