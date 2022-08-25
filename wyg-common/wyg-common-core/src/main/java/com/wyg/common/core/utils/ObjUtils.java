package com.wyg.common.core.utils;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ObjUtils {
    public static String obj2Str(Object obj) {
        return obj==null?"":String.valueOf(obj);
    }

    public static String obj2Str(Object obj, String def) {
        return obj==null?def:obj+"";
    }

    public static long obj2Long(Object obj) {
        return obj==null?0:Long.valueOf(obj+"");
    }

    public static double obj2double(Object obj) {
        return obj==null?0:Double.valueOf(obj+"");
    }

    public static Double obj2Double(Object obj) {
        return obj==null?null:Double.valueOf(obj+"");
    }

    public static int obj2Int(Object obj) {
        return obj==null?0:Integer.valueOf(obj+"");
    }

    public static boolean obj2Bool(Object obj) {
        return obj==null?false:Boolean.valueOf(obj+"");
    }


    public static Integer obj2Integer(Object obj) {
        return (obj==null || "null".equals(String.valueOf(obj)) || "".equals(String.valueOf(obj)))?null:Integer.valueOf(obj+"");
    }

    public static Date obj2Date(Object obj) {
        return (Date)obj;
    }

    public static String int2Str(Integer I, String def) {
        return I==null?def:I+"";
    }

    public static String long2Str(Long L, String def) {
        return L==null?def:L+"";
    }

    public static String list2Str(List<String> list) {
        StringBuffer sb=new StringBuffer();
        for(String e: list){
            if(sb.length()==0) sb.append(e);
            else sb.append(","+e);
        }
        return sb.toString();
    }

    public static boolean strIsEmpty(String s) {
        return s==null || "".equals(s);
    }

    public static boolean strIsNotEmpty(String s) {
        return !strIsEmpty(s);
    }

    public static String nvl(String str, String def){
        if(str==null) return def;
        else return str;
    }

    public static String substring(String str,String beginStr,String endStr){
        int beginIndex=str.indexOf(beginStr)+beginStr.length();
        int endIndex=str.indexOf(endStr);
        if(beginIndex<0 || endIndex<0 || endIndex<beginIndex){
            return "";
        }
        return str.substring(beginIndex, endIndex);
    }

    public static String lastSubstring(String str,String beginStr,String endStr){
        int beginIndex=str.lastIndexOf(beginStr)+beginStr.length();
        int endIndex=str.lastIndexOf(endStr);
        if(beginIndex<0 || endIndex<0 || endIndex<beginIndex){
            return "";
        }
        return str.substring(beginIndex, endIndex);
    }

    /** 格式化XML, 去掉多余的空格、回车 */
    public static String formateXml(String xml){
        String regex="(?<=>)\\s+(?=<)";
        return xml.replaceAll(regex, "");
    }

    /** 正则表达式查找类似"key=value,key=value"的数据	 */
    public static String regexFind(String s, String key, String def){
        String regex="(?<="+key+"=)\\w*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        if(m.find()) {
            return m.group();
        }else return def;
    }

    @SuppressWarnings("unchecked")
    public static <T> T cloneSerializable(T src) throws RuntimeException {
        ByteArrayOutputStream memoryBuffer = new ByteArrayOutputStream();
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        T dest = null;
        try {
            out = new ObjectOutputStream(memoryBuffer);
            out.writeObject(src);
            out.flush();
            in = new ObjectInputStream(new ByteArrayInputStream(memoryBuffer.toByteArray()));
            dest = (T) in.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (out!=null){
                try {out.close();out = null;} catch (Exception e) {}
            }
            if (in!=null){
                try {in.close();in = null;} catch (Exception e) {}
            }
        }
        return dest;
    }


    /**普通javaBean 转成map<String,Object>**/
    public static Map<String,Object> bean2Map(Object obj, boolean allowNull){
        Map<String, Object> map = new HashMap<String, Object>();
        try{
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

            for (PropertyDescriptor property : propertyDescriptors) {
//	        	System.out.println(property.getName()+" "+ property.getPropertyType()+"是字符串? "+(property.getPropertyType()==String.class));
                if(property.getPropertyType()!=Class.class && null!=property.getReadMethod()){
                    Object value = null;
                    if(property.getPropertyType()==String.class){
                        value = (String)property.getReadMethod().invoke(obj, null);
                    }else if(property.getPropertyType()==int.class
                            ||property.getPropertyType()==double.class
                            ||property.getPropertyType()==float.class
                            ||property.getPropertyType()==byte.class
                            ||property.getPropertyType()==short.class){
                        value = String.valueOf(property.getReadMethod().invoke(obj, null));
                    }else{
                        value = property.getReadMethod().invoke(obj, null);
                    }

                    String key = property.getName();
                    if(allowNull){
                        map.put(key, value);
                        continue;
                    }
                    if(value instanceof String && !StringUtils.isEmpty((String)value)){
                        map.put(key, value);
                        continue;
                    }
                    if(null != value){
                        map.put(key, value);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return map;
    }

    /**普通javaBean 转成map<String,String>**/
    public static Map<String,String> bean2StrMap(Object obj, boolean allowNull){
        Map<String, String> strMap = new HashMap<String, String>();
        Map<String,Object> bean2Map = bean2Map(obj,allowNull);
        Set<String> keySet = bean2Map.keySet();
        for (String key : keySet) {
            strMap.put(key, bean2Map.get(key)==null? null: bean2Map.get(key).toString());
        }
        return strMap;
    }


    /**
     * 判断是否有小数
     * @param num
     * @return
     */
    public static boolean isXiaoshu(double num){
        String str=""+num;

        if(!str.contains(".")) {
            return false;
        }

        String[] array=str.split("\\.");

        double part=ObjUtils.obj2double(array[1]);

        if(part==0){
            return false;
        }

        return true;

    }

}
