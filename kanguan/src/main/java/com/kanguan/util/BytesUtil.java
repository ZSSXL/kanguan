package com.kanguan.util;

import java.text.DecimalFormat;

/**
 * @author ZSS
 * @date 2020/3/22 22:06
 * @description 字节工具
 */
public class BytesUtil {

    /**
     * 把byte转化为KB、MB、GB
     *
     * @param size
     * @return
     */
    public static String setSize(int size) {
        //获取到的size为：1705230
        //定义GB的计算常量
        int gb = 1024 * 1024 * 1024;
        //定义MB的计算常量
        int mb = 1024 * 1024;
        //定义KB的计算常量
        int kb = 1024;
        //格式化小数
        DecimalFormat df = new DecimalFormat("0.00");
        String resultSize = "";
        if (size / gb >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = df.format(size / (float) gb) + "GB   ";
        } else if (size / mb >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = df.format(size / (float) mb) + "MB   ";
        } else if (size / kb >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = df.format(size / (float) kb) + "KB   ";
        } else {
            resultSize = size + "B   ";
        }
        return resultSize;
    }
}
