package com.three.dao;

import com.three.beans.User;
import com.three.utils.HibernateDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDao extends HibernateDao<User> {

    private  static Logger logger = LogManager.getLogger(UserDao.class);
    //Email 当作是账号
    public User findUserByEmail(User user){
        String hql = "select user from User user where email = ? and status = 1";
        String pram[] = {user.getEmail()};
        User user1 = this.findOne(hql,pram);

        return user1;
    }


    //注册
    public int register(User user){
        return this.insert(user);
    }

    //登陆
    public int login(User user){


        User user1 = this.findUserByEmail(user);
        if(user1.getPassword().equals(user.getPassword()))
            return 1;
        return 0;
    }


    //注销
    public int delete_end(User user){
        User user1 = findUserByEmail(user);
        System.out.println(user1.getId() +"   ->id");
        return delete(User.class,user1.getId());

    }

    //假删除
    public int delete_status(User user){
        User user_data = this.findUserByEmail(user);
        user_data.setStatus(0);
        return update(user_data);
    }



    //发送验证码(把验证码存到数据库)
    public int insert_code(User user){
        User user_data = findUserByEmail(user);
        user_data.setCode(user.getCode());
        return this.update(user_data);
    }

    //更改个人信息(地址)
    public int update_info(User user){

        User user_data = this.findUserByEmail(user);

        user_data.setUsername(user.getUsername());
        user_data.setAddress(user.getAddress());
        user_data.setPhone(user.getPhone());
        return this.update(user_data);

    }

    //(找回)更改密码（通过 比对  code 字段）
    public int updatePassword(User user){
        User user_data = this.findUserByEmail(user);

        user_data.setPassword(user.getPassword());

        if(user.getCode() == user_data.getCode()){
           return  this.update(user_data);
        }

        return 0;
    }

}
