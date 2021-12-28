package com.hdfs.olo.olo_web.plugins.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.hdfs.olo.olo_web.plugins.filter.wrapper.XssHttpServletRequestWrapper;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author huadf
 *
 */
public class XssFilter implements Filter {
	
	//html过滤
	private final static HTMLFilter htmlFilter = new HTMLFilter();
		
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @SuppressWarnings({"static-access" })
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
//        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
//                (HttpServletRequest) request);
    	HttpServletRequest hrequest = (HttpServletRequest) request;
    	String str1= hrequest.getRemoteAddr();
    	String str2= hrequest.getRemoteHost();
    	Object str3= hrequest.getServletContext();
    	String str4 = hrequest.getRequestURI();
    	StringBuffer str5 = hrequest.getRequestURL();
    	
        String rejectMsg = null;
        Map<Boolean,String> ck4sqlMap = null;
        Map<Boolean,String> ck4htmMap = null;
        Collection<String[]> tmp = request.getParameterMap().values();
        for(String[] item:tmp)
        {
        	ck4sqlMap = this.sqlInject(item[0]);
        	ck4htmMap = this.htmlInject(item[0]);
        	if(ck4sqlMap.containsKey(true) && 
        			ck4htmMap.containsKey(true))
        		continue;
        	rejectMsg = ck4sqlMap.containsKey(false)?ck4sqlMap.get(false):ck4htmMap.get(false);
        	this.doPrint((HttpServletResponse)response, rejectMsg);
        	return;
        }
        
        //chain.doFilter(xssRequest, response);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
    
    public static Map<Boolean,String> htmlInject(String item)
    {
    	Map<Boolean,String> retMap = new HashMap<Boolean,String>();
    	if (StringUtils.isBlank(item)) {
    		retMap.put(true, item);
            return retMap;
        }
    	String rItem = htmlFilter.filter(item);
    	Boolean key = rItem.equals(item)?true:false;
    	retMap.put(key,key?item:"传入的参数存在特殊字符!");
    	return retMap;
    }
    
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
        String[] keywords = {"insert", "select", "delete", "update", "declare",  "substr", "ascii", "exec",
        		"join", "into", "and", "truncate", "execute", "from", "order by", "group by", "create", "drop" };
        //判断是否包含非法字符
        boolean isExist = false;
        String illegal = null;
        for (String keyword : keywords) {
            if (str.indexOf(keyword)!=-1) {
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
    
    public void doPrint(HttpServletResponse response,String msg)
	{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");

		OutputStream outputStream = null;
		try {
			outputStream = response.getOutputStream();
			JSONObject res = new JSONObject();
			res.put("message", msg);
			res.put("statusCode", "300");
			outputStream.write(res.toString().getBytes("UTF-8"));
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}