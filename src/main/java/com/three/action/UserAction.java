package com.three.action;


import com.opensymphony.xwork2.ActionSupport;
import com.three.beans.User;
import com.three.dao.UserDao;
import com.three.service.user.IUserService;
import com.three.utils.HibernateDao;
import com.three.utils.other.LoginSession;
import com.three.utils.other.UUIDUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("userAction")
@Scope(value = "prototype")
@ParentPackage(value = "struts-default")
@Namespace("/user")
public class UserAction extends ActionSupport {


    private static Logger logger = LogManager.getLogger(UserAction.class);

    private User user;
    @Autowired
    IUserService userService;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


    //注册
    @Action(value = "registe",
            results = {
            @Result(name = "success",location = "/success.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }
    )
    public String registe(){
        String u_id = UUIDUtils.getId();
        user.setU_id(u_id);                                 //
        int n = userService.register(user);
        if(n == 1)
            return SUCCESS;
        else
            return "fail";

    }

//    ,location = "findAllBooks.action"
//登陆
    @Action( value = "login",
            results = {
            @Result(name = "success",type = "chain",
                    params = {"namespace","/book","actionName","findAllBooks"}),
                    @Result(name = "fail",location = "/index.jsp")
        }

    )
    public String login(){
        int n = userService.login(user);
        if(n == 1){
            LoginSession.getSession().setAttribute("username",user);                  //
            return SUCCESS;
        }
        return "fail";
    }

//删除用户
    @Action( value = "deleteUser",
            results = {
                    @Result(name = "success",location = "/success.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }

    )
    public String deleteUser(){

//        logger.info("action deleteUser + user'sEmail:"+user.getEmail());
        int n = userService.deleteUser(user);
        if(n == 1)
            return SUCCESS;
        return "fail";
    }

//忘记密码
    @Action( value = "reFindPassw",
            results = {
                    @Result(name = "success",location = "/success.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }

    )
    public String reFindPassw() {
        int n = userService.reFindPass(user);
        if(n == 1)
            return SUCCESS;
        return "fail";
    }

    //更改个人信息
    @Action( value = "updateInfo",
            results = {
                    @Result(name = "success",location = "/success.jsp"),
                    @Result(name = "fail",location = "/index.jsp")
            }

    )
    public String updateInfo(){
        logger.info("action:"+user.getEmail());
        int n = userService.updateInfo(user);
        if(n == 1)
            return SUCCESS;
        return "fail";
    }



}
