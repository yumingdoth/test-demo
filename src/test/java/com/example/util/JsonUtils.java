package com.example.util;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

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
}
