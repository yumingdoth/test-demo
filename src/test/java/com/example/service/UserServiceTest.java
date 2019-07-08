package com.example.service;

import com.example.dao.UserDao;
import com.example.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * @author ming
 * @date 2019-07-08
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserService.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @MockBean
    private UserDao userDao;

    @Test
    public void whenInvoke_thenReturn() {
        User user = new User();
        user.setName("Bob");
        user.setAge(0);

        // 1.设定mock对象期望的结果
        when(userDao.findByName("Bob")).thenReturn(user);
        // 2.调用被测试方法
        User bob = userService.findByName("Bob");
        // 3.验证测试结果
        assertEquals(user.getAge(), bob.getAge());
        // 验证findByName被调用过
        verify(userDao).findByName("Bob");
        // verify(userDao).findByName("Alice");
    }
}
