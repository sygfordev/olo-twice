package com.hdfs.olo.olo_web.plugins.common.utils.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author: 李逸聪
 * @date: May 28, 2011 
 * @description:
 * 		cookie帮助类
 */
public class CookieHelper {

	public CookieHelper() {
		
	}

	/**
	 * 写入cookie
	 * @param response
	 * @param days
	 * 			天数
	 * @param key
	 * @param value
	 */
	public static void WriteCookie(HttpServletResponse response,int days,String key, String value)
	{
		WriteCookie(response,new String[]{key},new String[]{value},days*24*60*60);
	}
	
	/**
	 * 写入cookie
	 * @param response
	 * @param days
	 * 			天数
	 * @param keys
	 * @param values
	 */
	public static void WriteCookie(HttpServletResponse response,int days,String[] keys, String[] values)
	{
		WriteCookie(response,keys,values,days*24*60*60);
	}
	
	/**
	 *  写入cookie
	 *  @param response
	 * @param key
	 * 			键
	 * @param value
	 * 			值
	 * @param seconds
	 * 			保存时间，以秒计算
	 */	
	public static void WriteCookie(HttpServletResponse response,String key, String value, int seconds)
	{
		WriteCookie(response,new String[]{key},new String[]{value},seconds);
	}
	
	/**
	 *  写入cookie
	 * @param  response
	 *    		响应
	 * @param keys
	 * 			键集合
	 * @param values 
	 * 			值集合
	 * @param seconds
	 * 			保存时间，以秒计算
	 */			
    public static void WriteCookie(HttpServletResponse response,String[] keys, String[] values, int seconds)
    {
            int number = keys.length;
            for (int i = 0; i < number; i++)
            {
            	Cookie cookie = new Cookie(keys[i], values[i]);
            	//保存时间
                cookie.setMaxAge(seconds);
                response.addCookie(cookie);
            }
   }
    
    /**
     * 通过键读取cookie的值
     * @param request
     * @param key
     * @return
     */
    public static String ReadCookieByKey(HttpServletRequest request,String key)
    {
    	String str = "";
    	Cookie[] cookies = request.getCookies();
    	if(cookies != null)
    	{
    		for (Cookie cookie : cookies) {
    			if(cookie.getName().equals(key))
    			{
    				str = cookie.getValue();
    				break;
    			}
    		}
    	}
    	return str;
    }
    /**
     * 清空cookie
     * @param response
     * @param key
     * 			键
     */
    public static void ClearCookie(HttpServletResponse response,String key)
    {
    	ClearCookie(response,key);
    }
    
    /**
     * 清空cookie
     * @param response
     * @param keys
     * 			键的集合
     */
    public static void ClearCookie(HttpServletResponse response,String[] keys)
    {
    	Cookie cookie = null;
    	for (String key : keys) {
    		cookie = new Cookie(key,"");
    		//设置时间为0秒
    		cookie.setMaxAge(0);
            response.addCookie(cookie);
		}
    }

}
