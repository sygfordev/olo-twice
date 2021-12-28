package com.hdfs.olo.olo_web.plugins.common.utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 
 * @author: 李逸聪
 * @date: May 30, 2011 
 * @description:
 * 		XStream帮助类
 */
public class XStream4GenericHelper {
	
	private static Logger logger = LoggerFactory.getLogger(XStream4GenericHelper.class);
	
	private final static String ROOT_XML = "config";
	
	private final static String HEAD_STRING_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	
	/**
	 * 读取xml，然后序列化成对象
	 * @param absolutePath	绝对路径
	 * @param clazz	类class
	 * @return
	 */
	public static Object readXml(String absolutePath,Class<?> clazz)
	{
		FileInputStream fi = null;
		Object obj = null;
		try {
			//XStream将实体序化为xml的一个开源框架
			XStream xs = new XStream(new DomDriver());
			 //设置xml根节点名称
			   xs.alias(ROOT_XML, clazz);
			fi = new FileInputStream(absolutePath);
			//加载并转化成实体信息
			obj = xs.fromXML(fi);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}finally
		{
			try {
				fi.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}
		return obj;
	}
	
	/**
	 * 将对象序列化成xml
	 * @param absolutePath 绝对路径
	 * @param obj 对象
	 * @return	true为成功,false为失败
	 */
	public static boolean writeXml(String absolutePath,Object obj)
	{
		boolean isok = false;
		try
		{
			//XStream将实体序化为xml的一个开源框架
			XStream xs = new XStream(new DomDriver());
			 //设置xml根节点名称
			xs.alias(ROOT_XML, obj.getClass());
			String xmlString = xs.toXML(obj);
			FilesHelper.deleteFile(absolutePath);
			isok = FilesHelper.writeFile(absolutePath, HEAD_STRING_XML + xmlString);
		}catch(Exception e)
		{
			isok = false;
			logger.error(e.getMessage());
		}
		return isok;
	}
	
	
}
