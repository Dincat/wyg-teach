package com.wyg.common.core.utils;


import com.alibaba.fastjson2.JSONObject;
import com.wyg.common.core.utils.reflect.ReflectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;

public class MapUtils {
    private static Logger log =  LoggerFactory.getLogger(MapUtils.class);

    /**
     * 实体对象转成Map      
     * @param obj 实体对象    
     * @return      
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new LinkedHashMap<String,Object>();
        if(obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for(Field field:fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }

    public static Map<String, String> object2StringMap(Object obj) {
        Map<String, String> map = new LinkedHashMap<String,String>();
        if(obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for(Field field:fields) {
                field.setAccessible(true);
                map.put(field.getName(), (String)field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;

    }

    public static  Map<String, String> object2StringMap2(Object obj){
        String json=JsonUtil.toJson(obj);
        Map<String,Object> map=  JSONObject.parseObject(json);
        Map<String,String> newMap =new HashMap<String,String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(entry.getValue() instanceof String){
                newMap.put(entry.getKey(), (String) entry.getValue());
            }
        }

        return newMap;
    }

    /**
     *      
     * Map转成实体对象      
     * @param map map实体对象包含属性      
     * @param clazz 实体对象类型      
     * @return      
     */
    public static Object map2Object(Map<String, Object> map, Class<?> clazz) {
        if(map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = clazz.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for(Field field:fields) {
                int mod = field.getModifiers();
                if(Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return obj;
    }


    public static String getString(Map map, String key){

        try {
            String val = "";

            if (map == null) return val;

            if (!map.containsKey(key)) return val;

            Object obj = map.get(key);

            val = obj == null ? "" : obj.toString();
            return val;
        }catch (Exception ex){
            log.error(ex.toString());
        }

        return "";

    }

    public static String getDateString(Map map, String key){
        return getDateString(map,key,"");
    }

    public static String getDateString(Map map, String key,String formatter){

        if( map==null || StringUtils.isBlank(key)) return "";

        String strDate=MapUtils.getString(map,key);

        if(StringUtils.isBlank(strDate)) return "";

        Date date=new Date(strDate);

        if(date==null) return "";

        if(StringUtils.isBlank(formatter)) formatter="yyyy-MM-dd HH:mm:ss";

        SimpleDateFormat sdf = new SimpleDateFormat(formatter);

        String str=sdf.format(date);

        return  str;

    }

    public static Float getFloat(Map map, String key){
        String val=getString(map,key);
        return   NumericUtils.StringToFlost(val);
    }

    public static Double getDouble(Map map, String key){
        String val=getString(map,key);
        return   NumericUtils.StringToDouble(val);
    }


    public static Integer getInteger(Map map, String key){

        String val=getString(map,key);
        return   NumericUtils.StringToInteger(val);

    }

    public static boolean getBoolean(Map map, String key){

        String val=getString(map,key);

        if(StringUtils.isBlank(val)) return false;

        return val.toLowerCase().equals("true");

    }

    public static Long getLong(Map map, String key){

        String val=getString(map,key);
        return (Long)NumericUtils.StringToLong(val);

    }


    /**
     * 对请求内容参数按照字母先后顺序排序
     * @param params
     * @return
     */
    public static String createLinkString(Map<String, String> params) {
        if(params.size()<1) return "";

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if(value != null && !"".equals(value)){
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr.substring(0,prestr.length()-1);
    }
    public static String createLinkString2(Map<String, Object> params) {
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = ObjUtils.obj2Str(params.get(key));
            if(value != null && !"".equals(value)){
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr.substring(0,prestr.length()-1);
    }


}

