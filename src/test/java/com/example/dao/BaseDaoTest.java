package com.example.dao;

import com.example.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 使用内存数据库进行dao层的单元测试
 * 1.使用@FixMethodOrder指定单元测试执行顺序按字母升序,期望先执行的方法应该排在字母表前面
 * 2.数据库默认事务回滚,使用@Commit注解自动提交,才可以查询的插入的数据
 *
 * @author yuming
 * @date 2019/3/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@Commit
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
// @TestPropertySource(properties = {
//         "spring.datasource.schema=classpath:user_schema.sql",
//         "spring.datasource.data=classpath:user_data.sql"})
@TestPropertySource(locations = "classpath:db.properties")
public class BaseDaoTest {
    @Test
    public void test() {
        System.out.println("----------------------------------------------------------");
        System.out.println("----------                test               -------------");
        System.out.println("----------------------------------------------------------");
    }
}
