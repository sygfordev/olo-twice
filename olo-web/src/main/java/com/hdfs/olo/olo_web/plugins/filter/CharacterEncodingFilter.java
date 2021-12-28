package com.hdfs.olo.olo_web.plugins.filter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;

		// 解决post请求的中文乱码
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		chain.doFilter((ServletRequest) Proxy.newProxyInstance(this.getClass().getClassLoader(),
				request.getClass().getInterfaces(), new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						String methodname = method.getName(); // 拿到当前的方法
						if (methodname.equals("getParameter")) {
							// 执行request.getparameter获取结果
							String value = (String) method.invoke(request, args);
							if (value == null) {
								return null;
							}
							if (!request.getMethod().equalsIgnoreCase("get")) { // 判断是为get请求
								return value;
							}
							// 转换编码返回
							value = new String(value.getBytes("iso8859-1"), "UTF-8");
							return value;
						}
						// 交给request执行请求
						return method.invoke(request, args);
					}

				}), res);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		//System.out.println("过滤器初始化");
	}

	@Override
	public void destroy() {
		//System.out.println("过滤器完成");
	}
}