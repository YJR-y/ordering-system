package com.yjr.ordering_system.utils;

import java.util.Random;

/**
 * @author yjr
 * @since 2020/11/10 14:54
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();

        Integer number = random.nextInt(900_000) + 100_000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
