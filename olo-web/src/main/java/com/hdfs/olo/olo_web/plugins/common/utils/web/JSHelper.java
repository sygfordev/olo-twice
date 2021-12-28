package com.hdfs.olo.olo_web.plugins.common.utils.web;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @author: 李逸聪
 * @date: Jun 6, 2011 
 * @description:
 * 		js帮助类
 */
public class JSHelper {
	
   /**
    * 显示消息提示对话框
    * @param response
    * @param msg
 * @throws IOException 
    */
    public static void show(HttpServletResponse response,String msg) throws IOException
    {
    	setParameters(response,msg);
    }
   /**
    * 显示消息提示对话框，并且页面后退
    * @param response
    * @param msg
 * @throws IOException 
    */
    public static void showAndBackoff(HttpServletResponse response,String msg) throws IOException
    {
    	setParameters(response,msg);
    }
  /**
   * 显示消息提示对话框，并进行页面跳转
   * @param response
   * @param msg
   * @param url
 * @throws IOException 
   */
    public static void showAndRedirect(HttpServletResponse response,String msg, String url) throws IOException
    {
    	setParameters(response,msg);
    }
  /**
   *  显示消息提示对话框，并进行页面跳转
   * @param response
   * @param msg
   * @param url
 * @throws IOException 
   */
    public static void showAndRedirects(HttpServletResponse response,String msg, String url) throws IOException
    {
        StringBuffer buffer = new StringBuffer();
        buffer.append("<script language='javascript' defer>");
        buffer.append("alert('"+msg+"');");
        buffer.append("top.location.href='"+url+"'");
        buffer.append("</script>");
        setParameters(response,buffer.toString());

    }
   /**
    * 输出自定义脚本信息
    * @param response
    * @param script
 * @throws IOException 
    */
    public static void responseScript(HttpServletResponse response,String script) throws IOException
    {
    	setParameters(response,script);
    }
    
    private static void setParameters(HttpServletResponse response,String str) throws IOException
    {
    	response.setContentType("text/html");
    	response.setCharacterEncoding("UTF-8");
    	response.getWriter().print(str);
    }
  
}
