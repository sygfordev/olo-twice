package com.hdfs.olo.olo_web.plugins.common.utils.web;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author: 化登峰
 * @date: May 23, 2011 
 * @description:
 */
public class LayAjaxHelper {
	
	public final static String CODE_FAIL = "300";
	public final static String CODE_SUCCESS = "200";
	
	public final static String KEY_STATUS_CODE = "statusCode";
	public final static String KEY_MESSAGE = "message";
	public final static String KEY_DATA = "data";
	public final static String MEG_SUCCESS="操作成功";
	public final static String MEG_FAIL="操作失败";
	
	public final static String CR="\n";
	
	/**
	 *  发送错误信息
	 * @param request
	 * @param message
	 * @return
	 */
	public static Map<String, Object> fail(String message)
	{
		return setParameter(CODE_FAIL,message,null);
		
	}
	
	/**
	 * 发送成功信息
	 * @param map
	 * @param message
	 */
	public static Map<String, Object> success(String message)
	{
		return setParameter(CODE_SUCCESS,message,null);
	}
	
	/**
	 * 发送成功信息
	 * @param map
	 * @param message
	 */
	public static Map<String, Object> success(String message,Object data)
	{
		return setParameter(CODE_SUCCESS,message,data);
	}
	
	/**
	 * 
	 * @param map 
	 * @param statusCode
	 * 			状态码：200代表成功，300代表操作失败，301代表会话超时
	 * @param message
	 * 			消息
	 */
	public static Map<String,Object> setParameter(String statusCode,String message,Object data)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put(KEY_STATUS_CODE, statusCode); 
		map.put(KEY_MESSAGE, message);
		if(null != data)map.put(KEY_DATA, data);
		return map;
	}
}
