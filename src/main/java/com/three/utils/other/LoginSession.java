package com.three.utils.other;

import com.three.beans.User;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpSession;

public class LoginSession {

    //获取session对象
    public static HttpSession getSession(){
        return ServletActionContext.getRequest().getSession();
    }
    //获取登录用户对象
    public static User getLoginUser(){
        return (User) getSession().getAttribute("username");
    }

}
