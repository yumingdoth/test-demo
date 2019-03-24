package com.example.dao;

import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuming
 * @date 2019/3/23
 */
@Mapper
public interface UserDao {
    /**
     * find by name
     * @param name name
     * @return user
     */
    User findByName(String name);

    /**
     * save user
     * @param user user
     * @return result
     */
    Integer saveUser(User user);

    /**
     * updare user
     * @param user user
     * @return result
     */
    Integer updateUser(User user);
}
