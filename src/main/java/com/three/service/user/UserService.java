package com.three.service.user;

import com.three.beans.User;
import com.three.dao.UserDao;
import com.three.utils.email.Randomm;
import com.three.utils.email.SendEmail;
import com.three.utils.other.Md5;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class UserService implements IUserService {

    private Logger logger = LogManager.getLogger(UserService.class);
    @Resource
    private UserDao userDao;
    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User findUserByEmail(User user) {
        return userDao.findUserByEmail(user);
    }

    @Override
    public int register(User user) {

        User user1 = null;
        user1 = userDao.findUserByEmail(user);
        if(user1 == null){
            logger.info("register end");
            String password = Md5.MD5(user.getPassword());
            user.setPassword(password);
            user.setStatus(1);
            return userDao.register(user);
        }
       return 0;
    }


    @Override
    public int login(User user) {
        String password = Md5.MD5(user.getPassword());
        user.setPassword(password);
        return userDao.login(user);
    }

    @Override
    public int deleteUser(User user) {
        return userDao.delete_status(user);

    }

    @Override
    public int reFindPass(User user) {
        String password = Md5.MD5(user.getPassword());
        user.setPassword(password);
        return userDao.updatePassword(user);
    }

    @Override
    public int updateInfo(User user) {
        logger.info(user.getUsername());
        logger.info(user.getEmail());
        logger.info(user.getPhone());

        return userDao.update_info(user);
    }

    @Override
    public int insert_code(User user) {
        int code = Randomm.getRandom();
        SendEmail.send(user.getEmail(),code+"");
        user.setCode(code);
        return userDao.insert_code(user);

    }


}
