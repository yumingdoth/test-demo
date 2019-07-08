package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ming
 * @date 2019-07-08
 */
@Component
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByName(String name) {
        return userDao.findByName(name);
    }
}
