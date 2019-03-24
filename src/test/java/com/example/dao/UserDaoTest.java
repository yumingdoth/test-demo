package com.example.dao;

import com.alibaba.fastjson.JSON;
import com.example.entity.User;
import com.example.util.JsonUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 使用内存数据库进行dao层的单元测试
 * 1.使用@FixMethodOrder指定单元测试执行顺序按字母升序,期望先执行的方法应该排在字母表前面
 * 2.数据库默认事务回滚,使用@Commit注解自动提交,才可以查询的插入的数据
 *
 * @author yuming
 * @date 2019/3/23
 */
@RunWith(SpringRunner.class)
@MybatisTest
@Commit
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestPropertySource(properties = {
        "spring.datasource.schema=classpath:user_schema.sql",
        "spring.datasource.data=classpath:user_data.sql"})
public class UserDaoTest {

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
