package com.lrd.cool.api.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogMan {

    private static Logger logger = LoggerFactory.getLogger("cool");

    public static void i(Object log) {
        if (log==null){
            e("要输出的信息为空");
            return;
        }
        logger.info(log.toString());
    }

    public static void e(Object log) {
        if (log==null){
            e("要输出的信息为空");
            return;
        }
        logger.error(log.toString());
    }
}
