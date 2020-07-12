package com.tulies.api.utils;

import java.util.Random;

/**
 * @author 王嘉炀
 * @date 2018/7/11 上午12:42
 */

public class KeyUtil {

    /**
     * 生成用户token
     * 格式: 时间+随机数 最后md5下
     * @return
     */
    public static synchronized String genUserToken() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return MD5Util.md5(System.currentTimeMillis() + String.valueOf(number));
    }

    public static synchronized String genUid() {
        Random random = new Random();
        Integer number = random.nextInt(900000000) + 100000000;
        return '1'+String.valueOf(number);
    }

}
