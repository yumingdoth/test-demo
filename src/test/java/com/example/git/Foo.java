package com.example.git;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ming
 * @date 2019-07-28
 */
public class Foo {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void barTest() {
        logger.info("第一次提交");
        logger.info("没有冲突");
    }
}
