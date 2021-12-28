package com.hdfs.olo.olo_web.plugins.common.message;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

/**
 * 接口返回定义对象
 * 
 * @author huadf
 *
 * @param <T>
 */
public class Result4Page<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	private Header header = new Header();

	private List<?> data;
	
	private Long count;

	public Result4Page() {

	}

	public Result4Page(String json) {
		super();
		JSONObject re = JSONObject.parseObject(json);
		this.data = (List<?>) re.get("data");
		this.header.msg = re.getString("msg");
		this.header.code = re.getInteger("code");
		this.count = (Long) re.get("count");
	}
	public Result4Page(int code,String msg,List<?> data,Long count) {
		super();
		this.data = data;
		this.header.msg = msg;
		this.header.code = code;
		this.count = count;
	}
	
	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public int getCode() {
		return header.code;
	}

	public void setCode(int code) {
		header.code = code;
	}

	public String getMsg() {
		return header.msg;
	}

	public void setMsg(String msg) {
		header.msg = msg;
	}
	private final class Header implements Serializable {
		private static final long serialVersionUID = 308021320306483019L;
		int code = Page4LayStatus.SUCCESS;
		String msg = null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Header [code=");
		builder.append(getCode());
		builder.append(", msg=");
		builder.append(getMsg());
		builder.append("]");
		builder.append("   Body [");
		if (data != null) {
			builder.append(data.toString());
		}
		builder.append("]");
		return builder.toString();
	}
}