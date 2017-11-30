package org.xies.redis.util;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @author xies
 * @date 2017/11/29
 */
public class FastJsonConvert {

    /**
     * 将对象json串转换为对象
     * @param data
     * @param clzss
     * @param <T>
     * @return
     */
    public static <T> T convertJSONToObject(String data,Class<T> clzss){
        try {
            T t = JSON.parseObject(data, clzss);
            return t;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 将json串转换为数组
     * @param data
     * @param clzss
     * @param <T>
     * @return
     */
    public static <T> List<T> convertJSONToArray(String data, Class<T> clzss){
        try{
            List<T> list = JSON.parseArray(data,clzss);
            return list;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * 将对象转换为json串
     * @param object
     * @return
     */
    public static String convertObjectToJSON(Object object){
        try{
            String text = JSON.toJSONString(object);
            return text;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
