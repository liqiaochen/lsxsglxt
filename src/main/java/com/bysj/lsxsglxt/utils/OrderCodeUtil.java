package com.bysj.lsxsglxt.utils;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @ClassName: OrdeCodeUtil
 * @description: 生成订单编号
 * @author: LiQiaoChen
 * @date: Created in 2019/10/24 14:52
 * @version: 1.0
 */
public class OrderCodeUtil {

    public static String getOrderCode() {
        //格式化当前时间
        SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String strDate = sfDate.format(new Date());
        //为了防止高并发重复,再获取5个随机数
        String random = getRandom620(5);
        String orderCode = strDate + random;
        return orderCode;
    }
    /**
     * 获取6-10 的随机位数数字
     * @param length	想要生成的长度
     * @return result
     */
    public static String getRandom620(Integer length) {
        String result = "";
        Random rand = new Random();
        int n = 20;
        if (null != length && length > 0) {
            n = length;
        }
        int randInt = 0;
        for (int i = 0; i < n; i++) {
            randInt = rand.nextInt(10);
            result += randInt;
        }
        return result;
    }
}
