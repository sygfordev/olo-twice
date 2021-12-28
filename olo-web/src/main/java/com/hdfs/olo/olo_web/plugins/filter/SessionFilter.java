package com.hdfs.olo.olo_web.plugins.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hdfs.olo.olo_web.plugins.common.constant.DictionaryUtil;
import com.hdfs.olo.olo_web.plugins.common.utils.StringHelper;

/**
 * session过滤器
 * @author huadf
 *
 */
public class SessionFilter implements javax.servlet.Filter{
	/**
	 * 
	 */
	private FilterConfig filterConfig = null;
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		Object obj = req.getSession().getAttribute(DictionaryUtil.KEY_SESSION_USER);
		if(obj == null)//用户信息不存在
		{
			//返回的是WebModule/user.do?oper=toLogin
			String uri = req.getRequestURI().toLowerCase();
			//返回的是WebModule
			String path = req.getContextPath();
			//返回的是user.do
			String str = uri.substring(path.length() + 1, uri.length());
			//若是微信查询，则直接放开
			if(str.startsWith("wechat"))
			{
				filterChain.doFilter(request, response);
				return;
			}
			
			if(!str.startsWith("login"))
			{
				res.sendRedirect(path+"/login.jhtm");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}
}
