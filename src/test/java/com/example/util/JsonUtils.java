package com.example.util;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author yuming
 * @date 2019/3/23
 */
public class JsonUtils {
    public static String getJsonString(String path) {
        String jsonStr = "";
        ClassPathResource resource = new ClassPathResource(path);
        try {
            jsonStr = IOUtils.toString(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }

    @Test
    public void pathTest() {
        // 获得同级目录 /D:/workspace/ideaworkspace/test-demo/test-demo/target/test-classes/com/example/util/
        URL resource = getClass().getResource("");
        // 使用斜杆从根目录加载
        // URL resource = getClass().getResource("/user_data.sql");
        System.out.println("resource.getPath() = " + resource.getPath());
        File file = new File(resource.getPath());
        System.out.println("file.getName() = " + file.getName());
    }

    @Test
    public void pathTest2() {
        // 获得根目录,不能使用斜杆
        URL resource = getClass().getClassLoader().getResource("user_data.sql");
        System.out.println("resource.getPath() = " + resource.getPath());
        File file = new File(resource.getPath());
        System.out.println("file.getName() = " + file.getAbsolutePath());
    }
}
