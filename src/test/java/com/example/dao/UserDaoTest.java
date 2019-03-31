package com.example.dao;

import com.alibaba.fastjson.JSON;
import com.example.entity.User;
import com.example.util.JsonUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author yuming
 * @date 2019/3/23
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDaoTest extends BaseDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void a_whenSaveUser_returnInteger() {
        String json = JsonUtils.getJsonString("user_data_insert.json");
        User user = JSON.parseObject(json, User.class);
        Integer count = userDao.saveUser(user);
        assertThat(count).isEqualTo(1);
    }

    @Test
    public void b_whenFindByName_returnUser() {
        User user = userDao.findByName("jimmy");
        assertThat(user.getId()).isEqualTo(1);
    }

    @Test
    public void c_whenUpdateUser_returnInteger() {
        String json = JsonUtils.getJsonString("user_data_update.json");
        User user = JSON.parseObject(json, User.class);
        Integer integer = userDao.updateUser(user);
        assertThat(integer).isEqualTo(1);

        User tom = userDao.findByName("Tom");
        assertThat(tom.getAge()).isEqualTo(50);
    }
}
