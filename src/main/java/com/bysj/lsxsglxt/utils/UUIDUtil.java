package com.bysj.lsxsglxt.utils;


import java.util.UUID;

/**
 * @ClassName: UUIDUtil
 * @description: TODO
 * @author: LiQiaoChen
 * @date: Created in 2019/10/7 22:25
 * @version: 1.0
 */
public class UUIDUtil {

    public static String getUUID32(){
        UUID uuid = UUID.randomUUID();
        String uuidStr =  uuid.toString();
        uuidStr = uuidStr.replace("-", "");
        return uuidStr;
    }
}
