package com.hdfs.olo.olo_web.plugins.common.utils;

import java.util.Properties;

/**
 * 单利模式获取系统配置
 * @author Administrator
 *
 */
public class PropertiesHolder {
	//资源文件
	private Properties prop = null;
	private static class SingletonHoler {
		/**
		 * 静态初始化器，由JVM来保证线程安全
		 */
		private static PropertiesHolder instance = new PropertiesHolder();
	}
 
	private PropertiesHolder() {
		prop = PropertiesUtil.getProp4Type3("/config/globalset.properties");
	}
 
	public static PropertiesHolder getInstance() {
		return SingletonHoler.instance;
	}
	
	public static String readByKey(String key)
	{
		Properties prop = PropertiesHolder.getInstance().prop;
		if(null == prop) return null;
		return prop.getProperty(key);
	}
}
