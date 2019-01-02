package com.three.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.three.beans.Book;
import com.three.beans.Order;
import com.three.beans.User;
import com.three.dao.OrderDao;
import com.three.service.order.IOrderService;
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

import java.util.ArrayList;
import java.util.List;

@Controller("orderAction")
@Scope(value = "prototype")
@ParentPackage(value = "struts-default")
@Namespace("/order")
public class OrderAction extends ActionSupport {
    private static Logger logger = LogManager.getLogger(OrderAction.class);
    private User user;              //取用户 的 用户名   也就是email
    private String phone,address;

    @Autowired
    IOrderService orderService;

//0提交购物车 变成订单
    @Action(value = "addOrder",
            results = {
                    @Result(name = "success",type = "chain",
                            params = {"namespace","/book","actionName","findAllBooks"}),   //gai 跳到展示所有商品的action
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String addOrder(){
        user = LoginSession.getLoginUser();                                 //
        logger.info("user:"+user.getEmail());
        double sum = orderService.getTotal(user);
        if(sum == 0)
            return "fail";
        int n = orderService.addOrder(user);
        if(n ==1)
            return "success";
        return "fail";
    }


    //1添加订单的地址 和 联系方式
    @Action(value = "addOrderInfo",
            results = {
                    @Result(name = "success",type = "chain",
                            params = {"namespace","/book","actionName","findAllBooks"}), //gai 跳到展示所有商品的action
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String addPhoneAddress(){
        user = LoginSession.getLoginUser();                                 //
        logger.info("user:"+user.getEmail());
        int n = orderService.updateOrderInfo(user,phone,address);
        if(n == 1)
            return "success";
        return "fail";

    }


    //01查看购物车
    @Action(value = "findCarOrder",
            results = {
                    @Result(name = "success",location = "/jsp/user/show/showOrderCar.jsp"),   //yes
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String findCar_order(){

        user = LoginSession.getLoginUser();                                 //
        logger.info("user:"+user.getEmail());
        Order order = orderService.findCar_order(user);


        if(order == null)
            return "fail";
        else
            ActionContext.getContext().put("car_order", order);
        return "success";

    }


    //01查看所有订单
    @Action(value = "findAllOrder",
            results = {
                    @Result(name = "success",location = "/jsp/user/show/showOrders.jsp"),            //yes
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String findAllOrder(){
        user = LoginSession.getLoginUser();                                 //

        logger.info("user:"+user.getEmail());
        List<Order> orderList = orderService.findOrders(user);
        if(orderList == null)
            return "fail";
        for(int i=0; i<orderList.size(); i++){
            if(orderList.get(i).getFlag() == 0)
                orderList.remove(i);

        }

        ActionContext.getContext().put("orders", orderList);               //其中包含了购物车 这里要注意(test)

        return "success";
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
