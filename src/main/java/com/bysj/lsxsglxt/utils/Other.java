package com.bysj.lsxsglxt.utils;


/**
 * @ClassName: Other
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/20 17:37
 * @version: 1.0
 */
public class Other {

    /**
     * 判断字符串能不能转换成数字
     * @param msg
     * @return
     */
    public static boolean isInt(String msg){
        try {
            Integer.parseInt(msg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
