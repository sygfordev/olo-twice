package com.hdfs.olo.olo_web.plugins.filter;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

/**
 * sql过滤
 * @author xlf
 * @email xlfbe696@gmail.com
 * @date 2017年4月19日 上午10:41:25
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str 待验证的字符串
     * @return retMap key：校验是否通过  true通过|false未通过   val：key=true val=str[待验证的字符串]  key=false val=描述信息
     */
    public static Map<Boolean,String> sqlInject(String str){
        Map<Boolean,String> retMap = new HashMap<Boolean,String>();
    	if (StringUtils.isBlank(str)) {
    		retMap.put(true, str);
            return retMap;
        }
    	
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = { "master", "truncate", "insert", "select", "delete", "update", "declare", "alert",
                "create", "drop" };
        //判断是否包含非法字符
        boolean isExist = false;
        String illegal = null;
        for (String keyword : keywords) {
            if (str.indexOf(keyword)>=0) {
            	isExist = true;
            	illegal = keyword;
            	break;
            }
        }
        if(!isExist) 
        	retMap.put(true, str);
        else 
        	retMap.put(false,"传入的参数存在非法字符串["+illegal+"]!");
        return retMap;
    }
}