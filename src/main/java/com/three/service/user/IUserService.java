package com.three.service.user;

import com.three.beans.User;

public interface IUserService {


    public User findUserByEmail(User user);
    public int register(User user);
    public int login(User user);
    public int deleteUser(User user);  //注销
    public int updateInfo(User user); //更改 个人信息
    public int insert_code(User user); //接受验证码
    public int reFindPass(User user);  //更改密码

}
