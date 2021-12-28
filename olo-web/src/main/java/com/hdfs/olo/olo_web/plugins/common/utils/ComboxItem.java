package com.hdfs.olo.olo_web.plugins.common.utils;

/**
 *	组件 - 下拉框
 * @author huadf
 *
 */
public class ComboxItem implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1750986668377097117L;
	/**
	 * 
	 */
	public ComboxItem(){}
	/**
	 * 
	 * @param key
	 * @param value
	 */
	public ComboxItem(Object key,Object value){
		this.key = key;
		this.value = value;
	}
	
	/**
	 * 
	 */
	private Object key;
	/**
	 * 
	 */
	private Object value;
	public Object getKey() {
		return key;
	}
	public void setKey(Object key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

}
