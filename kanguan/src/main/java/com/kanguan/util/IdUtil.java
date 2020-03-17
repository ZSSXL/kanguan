package com.kanguan.util;

import java.util.Random;
import java.util.UUID;

/**
 * @author ZSS
 * @date 2020/3/17 20:34
 * @description id 工具包
 */
public class IdUtil {

    /**
     * 获取uuid
     *
     * @return String
     */
    public static String getId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取六位随机字符串
     *
     * @return String
     */
    public static String generateVerifyCode() {
        String word = "qewrtyuiopasdfghjklzxcvbnm1234567890";
        char[] chars = word.toCharArray();

        Random rd = new Random();

        StringBuilder shortId = new StringBuilder();
        int length = 6;
        for (int k = 0; k <= length; k++) {
            int index = rd.nextInt(chars.length);
            shortId.append(chars[index]);
        }
        return shortId.toString();
    }

}
