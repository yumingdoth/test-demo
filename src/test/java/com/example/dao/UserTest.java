package com.example.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yuming
 * @date 2019/3/23
 */
@RunWith(SpringRunner.class)
@MybatisTest
@TestPropertySource(properties = {
        "spring.datasource.schema=classpath:schema.sql",
        "spring.datasource.data=classpath:data.sql"})
public class UserTest {

    @Test
    public void userTest() {
        // test
    }
}
