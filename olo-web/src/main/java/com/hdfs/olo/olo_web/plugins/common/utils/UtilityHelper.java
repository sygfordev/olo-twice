package com.hdfs.olo.olo_web.plugins.common.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

public class UtilityHelper {
	
	protected static final Logger log4jLogger =Logger.getLogger(UtilityHelper.class);
	
	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	/**
	 * 得到本地的ip地址
	 * 
	 * @return
	 */
	public static String getLocalhostIP() {
		String str = "";
		try {
			str = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
//			e.printStackTrace();
			log4jLogger.error(e.getMessage());
		}
		return str;

	}

	/**
	 * 得到当前操作系统
	 * 
	 * @return
	 */
	public static String getOperatingSystem() {
		return System.getProperty("os.name");
	}

	/**
	 * 获取机器的MAC地址
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getAllMacAddress() {
		String os = getOperatingSystem();
		if (os.startsWith("Windows")) {
			return getWindowsMACAddress();
		} else if (os.startsWith("Linux")) {
			return getUnixMACAddress();
		} else {
			return "";
		}
	}

	/**
	 * 获取机器第一张网卡的MAC地址
	 * @return
	 */
	public static String getMacAddress() {
		String os = getOperatingSystem();
		if (os.startsWith("Windows")) {
			return getWindowsMACAddress().split("#")[0];
		} else if (os.startsWith("Linux")) {
			return getUnixMACAddress();
		} else {
			return "";
		}
	}

	/**
	 * 获取unix网卡的mac地址. 非windows的系统默认调用本方法获取.如果有特殊系统请继续扩充新的取mac地址方法.
	 * 
	 * @return mac地址
	 */
	public static String getUnixMACAddress() {
		StringBuffer mac = new StringBuffer();
		int index = 0;
		//      有时要用到这个命令，权限的问题: /sbin/ifconfig
		String ipConfig = runConsoleCommand("ifconfig eth0");// linux下的命令，一般取eth0作为本地主网卡
		// 显示信息中包含有mac地址信息
		StringTokenizer tokenizer = new StringTokenizer(ipConfig, "\n");
		while (tokenizer.hasMoreTokens()) {
			// 转为小写,去掉前后空格
			String line = tokenizer.nextToken().toLowerCase().trim();
			index = line.indexOf("hwaddr");// 寻找标示字符串[hwaddr]
			if (index >= 0) {// 找到了
				index = line.indexOf(":");// 寻找":"的位置
				if (index >= 0) {
					mac.append(line.substring(index + 1).trim());// 取出mac地址并去除2边空格
				}
				break;
			}
		}
		return mac.toString();
	}

	/**
	 * 获取widnows网卡的mac地址,多个网卡默认以#隔开
	 * 
	 * @return 小写格式字符串的mac地址
	 */
	public static String getWindowsMACAddress() {
		return getWindowsMACAddress("#");
	}

	/**
	 * 获取widnows网卡的mac地址
	 * 
	 * @param split
	 *            分隔符,用来分隔多个网卡的mac地址
	 * @return 小写格式字符串的mac地址
	 */
	public static String getWindowsMACAddress(String split) {
		StringBuffer mac = new StringBuffer();
		int index = 0;
		String ipConfig = runConsoleCommand("ipconfig /all");// windows下的命令，显示信息中包含有mac地址信息
		StringTokenizer tokenizer = new StringTokenizer(ipConfig, "\n");
		while (tokenizer.hasMoreTokens()) {
			// 转为小写,去掉前后空格
			String line = tokenizer.nextToken().toLowerCase().trim();
			index = line.indexOf("physical address");// 寻找标示字符串[physical
			// address]
			if (index >= 0) {// 找到了
				index = line.indexOf(":");// 寻找":"的位置
				if (index >= 0) {
					mac.append(line.substring(index + 1).trim() + split);// 取出mac地址并去除2边空格
				}
			}
		}
		return mac.subSequence(0, mac.length() - 1).toString();
	}

	/**
	 * 执行本地命令
	 * 
	 * @param command
	 *            命令
	 * @return
	 */
	public static String runConsoleCommand(String command) {
		StringBuffer buffer = new StringBuffer();
		InputStream inputStream = null;
		Process p = null;
		try {
			p = Runtime.getRuntime().exec(command);
			inputStream = new BufferedInputStream(p.getInputStream());
			while (true) {
				int c = inputStream.read();
				if (c == -1) {
					break;
				}
				buffer.append((char) c);
			}
		} catch (IOException ex) {
//			ex.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
//					e.printStackTrace();
				}
			}
			if (p != null) {
				p.destroy();
				p = null;
			}
		}
		return buffer.toString();
	}
	/**
	 * 获取客户端的ip地址
	 * @param request
	 * @return
	 */
	 public static String getClientIP(HttpServletRequest request)
     {
         return request.getRemoteAddr();
     }
	 /**
	  * 获取客户端的mac地址
	  * @param ip
	  * @return
	  * 		多个mac用#隔开
	  */
	  public static String getCustomerMac(String ip) //para IP is the client's IP 
      {
		  if(ip.equals(""))
		  {
			  return "";
		  }
		  if("127.0.0.1".equals(ip))
		  {
			  return getMacAddress();
		  }
		  StringBuffer mac = new StringBuffer();
		  String info = runConsoleCommand("nbtstat -a " + ip);
		  
		  StringTokenizer tokenizer = new StringTokenizer(info, "\n");
		  int index = 0;
			while (tokenizer.hasMoreTokens()) {
				// 转为小写,去掉前后空格
				String line = tokenizer.nextToken().toLowerCase().trim();
				index = line.indexOf("mac address");// 寻找标示字符串[physical
				// address]
				if (index >= 0) {// 找到了
					index = line.indexOf("=");// 寻找"="的位置
					if (index >= 0) {
						mac.append(line.substring(index + 1).trim() + "#");// 取出mac地址并去除2边空格
					}
				}
			}
			if(mac.length() > 0)
			{
				return mac.subSequence(0, mac.length() - 1).toString();
			}
			return "";
      }
	  
	  
	  /**
		 * 对象深度克隆
		 * @param obj
		 * 		对象
		 * @return
		 * 		深度克隆后的对象,失败返回null
		 * @throws Exception
		 */
		public static Object cloneObject(Object obj) throws Exception{
			Object cloneObj = null;
			ObjectOutputStream objectOutput = null;
			ObjectInputStream objectInput = null;
			try
			{
		       ByteArrayOutputStream  byteOutput = new ByteArrayOutputStream();     
		       objectOutput  = new ObjectOutputStream(byteOutput);     
		       objectOutput.writeObject(obj);    
		       
		       ByteArrayInputStream byteInput = new ByteArrayInputStream(byteOutput.toByteArray());     
		       objectInput = new ObjectInputStream(byteInput);
		       
		       cloneObj = objectInput.readObject();   
		       
			}catch (Exception e) {
//				System.out.println(e.getMessage());
			
				throw e;
				
			}finally
			{
				if(objectInput != null)
				{
					try {
						objectInput.close();
					} catch (IOException e) {
//						System.out.println(e.getMessage());
					}
				}
				if(objectOutput != null)
				{
					try {
						objectOutput.close();
					} catch (IOException e) {
//						System.out.println(e.getMessage());
					}
				}
			}
		   return cloneObj;
		}
		
		
		/**
		 * 得到classes绝对路径
		 * @return
		 */
		public static String getAbsoluteClassPath()
		{
			return getAbsoluteClassPath("");
		}
		
		/**
		 * 获取项目的根目录绝对路径
		 * @return
		 */
		public static String getRootAbsoluteClassPath()
		{
			String absoluteClassPath =getAbsoluteClassPath();
			//获取根目录的绝对路径
			return absoluteClassPath.substring(0,absoluteClassPath.indexOf("WEB-INF")).replace('\\', '/');
			
		}
		
		
		/**
		 * 得到classPath绝对路径
		 * @param classPath
		 * 			相对路径
		 * @return
		 */
		public static String getAbsoluteClassPath(String classPath)
		{
			
			String path = null;
			try {
				File file = new File(Thread.currentThread().getContextClassLoader().getResource(classPath).getPath());
				path = URLDecoder.decode(file.getAbsolutePath(), "utf-8");
			} catch (UnsupportedEncodingException e) {
				log4jLogger.error(e.getMessage());
//				e.printStackTrace();
			}
			return path;
		}
}
