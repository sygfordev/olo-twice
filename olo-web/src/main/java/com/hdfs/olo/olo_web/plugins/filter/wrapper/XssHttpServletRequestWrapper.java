package com.hdfs.olo.olo_web.plugins.filter.wrapper;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.hdfs.olo.olo_web.plugins.filter.HTMLFilter;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

	//没被包装过的HttpServletRequest（特殊场景，需求自己过滤）
	HttpServletRequest request;
	//html过滤
	private final static HTMLFilter htmlFilter = new HTMLFilter();

	public XssHttpServletRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	public String getParameter(String name){
		String value = super.getParameter(xssEncode(name));
		if (StringUtils.isNotBlank(value)) {
			value = xssEncode(value);
		}
		return StringEscapeUtils.unescapeHtml3(value);
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] parameters = super.getParameterValues(name);
		if (parameters == null || parameters.length == 0) {
			return null;
		}
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = xssEncode(parameters[i]);
			parameters[i] = StringEscapeUtils.unescapeHtml3(parameters[i]);
		}
		return parameters;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> map = new LinkedHashMap<>();
		Map<String, String[]> parameters = super.getParameterMap();
		for (String key : parameters.keySet()) {
			String[] values = parameters.get(key);
			for (int i = 0; i < values.length; i++) {
				values[i] = xssEncode(values[i]);
				values[i] = StringEscapeUtils.unescapeHtml3(values[i]);
			}
			map.put(key, values);
		}
		return map;
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(xssEncode(name));
		if (StringUtils.isNotBlank(value)) {
			value = xssEncode(value);
		}
		return StringEscapeUtils.unescapeHtml3(value);
	}

	private String xssEncode(String input) {
		return htmlFilter.filter(input);
	}

	/**
	 * 获取最原始的request
	 */
	public HttpServletRequest getOrgRequest() {
		return request;
	}

	/**
	 * 获取最原始的request
	 */
	public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
		if (request instanceof XssHttpServletRequestWrapper) {
			return ((XssHttpServletRequestWrapper) request).getOrgRequest();
		}

		return request;
	}
}
