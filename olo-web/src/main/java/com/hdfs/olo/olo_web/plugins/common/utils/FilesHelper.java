package com.hdfs.olo.olo_web.plugins.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;


/**
 * 
 * @author: 李逸聪
 * @date: Aug 5, 2011 
 * @description:
 * 		 文件操作类
 */
public class FilesHelper {

	/**
	 * 创建文件
	 * @param path
	 * @return true表示创建成功，false表示创建失败

	 */
	public static boolean createFile(String path) {
		boolean isok = true;
		try {
			File file = new File(path);
			if (!file.exists())//文件不存在情况
			{
				isok = file.createNewFile();
			}
		} catch (IOException e) {
			System.out.println("创建文件出错！");
			isok = false;
			e.printStackTrace();
		}
		return isok;
	}

	/**
	 * 删除一个文件
	 * @param path
	 * @return
	 */
	public static boolean deleteFile(String path) {
		File f = new File(path);
		if (f.exists())
			return f.delete();
		else
			return true;
	}

	/**
	 * 删除文件夹,先删除文件夹里面的所有文件，再删除空的文件夹！
	 * @param path
	 * @return
	 */
	public static boolean deleteFolder(String path) {
		boolean isok = true;
		try {
			deleteAllFiles(path);//删除文件夹里的所有文件！
			File file = new File(path);
			if (file.exists()) {
				file.delete();//删除空的文件夹
			}
		} catch (Exception e) {

			System.out.println("删除文件夹操作有误！");
			isok = false;
			e.printStackTrace();
		}
		return isok;
	}

	/**
	 * 删除文件夹下的所有文件
	 * @param path
	 */
	public static void deleteAllFiles(String path) {

		File file = new File(path);
		if (!file.exists())//文件夹不存在
		{
			return;
		}
		if (!file.isDirectory())//不是文件夹
		{
			return;
		}
		// tempList数组得到的只是该文件夹下所有的相对路径名！
		String[] tempList = file.list();
		File tempfile = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				tempfile = new File(path + tempList[i]);
			} else {
				tempfile = new File(path + File.separator + tempList[i]);
			}
			if (tempfile.isFile()) {
				tempfile.delete();
			}
			if (tempfile.isDirectory()) {
				deleteFolder(tempfile.getAbsolutePath());
			}
		}
	}

	/**
	 * 单个文件的复制
	 * @param srcPath
	 * @param destPath
	 * @return
	 */
	public static boolean copyFile(String srcPath, String destPath) {
		boolean isok = true;
		try {
			int bytesum = 0;
			int byteread = 0;
			File srcFile = new File(srcPath);
			if (srcFile.exists()) {
				InputStream inputStream = new FileInputStream(srcFile);
				OutputStream outputStream = new FileOutputStream(destPath);
				byte[] buffer = new byte[1024];
				while ((byteread = inputStream.read(buffer)) != -1) {
					bytesum += byteread;
					outputStream.write(buffer, 0, byteread);
				}
				inputStream.close();
				outputStream.close();
			} else {
				isok = false;
				System.out.println("源文件不存在！");
			}
		} catch (Exception e) {
			isok = false;
			System.out.println("单个文件复制有误！");
			e.printStackTrace();
		}
		return isok;
	}

	/**
	 *  复制整个文件夹,目标文件夹不存在,则创建
	 * @param srcPath
	 * @param destPath
	 * @return
	 */
	public static boolean copyAllFolder(String srcPath, String destPath) {
		boolean isok = true;
		try {
			new File(destPath).mkdirs();
			File file = new File(srcPath);
			String[] tempList = file.list();
			File tempfile = null;
			File tempNewFile = null;
			for (int i = 0; i < tempList.length; i++) {
				if (srcPath.endsWith(File.separator)
						&& destPath.endsWith(File.separator)) {
					tempfile = new File(srcPath + tempList[i]);
					tempNewFile = new File(destPath + tempList[i]);
				} else {
					tempfile = new File(srcPath + File.separator + tempList[i]);
					tempNewFile = new File(srcPath + File.separator
							+ tempList[i]);
				}
				if (tempfile.isFile()) {
					copyFile(tempfile.getAbsolutePath(), tempNewFile
							.getAbsolutePath());
				}
				if (tempfile.isDirectory()) {
					copyAllFolder(tempfile.getAbsolutePath()+ File.separator, tempNewFile
							.getAbsolutePath()+ File.separator);
				}
			}
		} catch (Exception e) {
			isok = false;
			System.out.println("文件夹复制出现问题！");
			e.printStackTrace();
		}
		return isok;
	}

	/**
	 *  创建文件夹，如果父文件夹不存在，则会创建
	 * @param path
	 * @return
	 */
	public static boolean createDirectory(String path) {
		boolean isok = true;
		File file = new File(path);
		if (!file.isDirectory())//不是为目录情况
		{
			isok = file.mkdirs();
		}
		return isok;
	}
	
	/***
	* 移动文件到指定的目录下
	* @param oldPath
	* @param newPath
	*/
	public static void moveFile(String oldPath,String newPath)
	{
	   copyFile(oldPath, newPath);
	   deleteFile(oldPath);
	}
	/***
	* 移动文件夹到指定的文件夹下！
	* @param oldPath
	* @param newPath
	*/
	public static void moveFolder(String oldPath,String newPath)
	{
	   copyAllFolder(oldPath, newPath);
	   deleteFolder(oldPath);
	}


	/**
	 * 得到文件名的后缀名,有包括小数点,如.txt
	 * @param fileName
	 * @return
	 */
	public static String getPostfix(String fileName) {

		return getPostfix(fileName, true);
	}

	/**
	 * 得到文件名的后缀名
	 * @param fileName
	 * @param isDot
	 * 			返回的格式是否包含'.',true表示包含,false表示不包含
	 * @return
	 */
	public static String getPostfix(String fileName, boolean isDot) {
		int start = fileName.lastIndexOf(".");
		if(start == -1)//如果文件没有后缀名，则返回空字符串
		{
			return "";
		}
		int length = fileName.length();
		String result = null;
		if (isDot) {
			result = fileName.substring(start, length);

		} else {
			result = fileName.substring(start + 1, length);
		}
		return result;
	}
	/**
	* 读取文本文件内容，以行的形式读取
	* 
	* @param String
	*            filePath 带有完整绝对路径的文件名        
	* @return String 返回文本文件的内容
	 * @throws IOException 
	*/
	public static String readFile(String filePath) throws IOException
	{
		return readFile(filePath, "", "", 1024);
	}
	/**
	* 读取文本文件内容，以行的形式读取
	* 
	* @param String
	*            filePath 带有完整绝对路径的文件名
	* @param String
	*            encoding 文本文件打开的编码方式 例如 GBK,UTF-8
	* @param String
	*            sep 分隔符 例如：#，默认为\n;
	* @param int
	*            bufLen 设置缓冲区大小            
	* @return String 返回文本文件的内容
	*/
	public static String readFile(String filePath, String encoding, String sep,int bufLen )
			throws IOException {
		if (filePath == null || filePath.equals("")) {
			return "";
		}
		if(sep==null||sep.equals(""))
		{
			sep="\r\n";
		}
		StringBuffer buffer = new StringBuffer();
		FileInputStream fs = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			fs = new FileInputStream(filePath);
			if (encoding == null||encoding.trim().equals("")) {
				isr = new InputStreamReader(fs);
			} else {
				isr = new InputStreamReader(fs, encoding.trim());
			}
			br = new BufferedReader(isr,bufLen);


			String data = null;
			while ((data = br.readLine()) != null) {
				buffer.append(data).append(sep);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			try
			{
			if (br != null)
				br.close();
			if (isr != null)
				isr.close();
			if (fs != null)
				fs.close();
			}catch(IOException e)
			{
				throw e;
			}
		}
		return buffer.toString();
	}
	/**
	 * 新建一个文件并写入内容,若存在文件,则覆盖原文件
	 * @param filePath
	 * @param content
	 * @return
	 * @throws IOException
	 */
	public static boolean writeFile(String filePath, String content) throws IOException {
		return writeFile(filePath,content,"UTF-8",false);
	}
	/**
	* 新建一个文件并写入内容
	* 
	* @param  filePath 文件全路径
	* @param  content 内容
	* @param encoding  编码
	* @param boolean  是否追加写入文件
	* @throws IOException
	*/
	public static boolean writeFile(String filePath, String content,String encoding,boolean isAppendWrite) throws IOException {

		if (filePath == null || filePath.equals("") || content == null
				|| content.equals(""))
			return false;
		boolean flag = false;
		FileOutputStream fos = null;
		OutputStreamWriter  out = null;
		 
		try {
			fos =  new FileOutputStream(filePath,isAppendWrite);
			out =  new OutputStreamWriter(fos, encoding);
			out.write(content);
			out.flush();
			flag = true;
		} catch (IOException e) {
			flag = false;
			throw e;
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
			if (fos != null)
				fos.close();
		}
		return flag;
	}
	
	/**
     * bfile 需要转换成文件的byte数组
     * filePath  生成的文件保存路径
     * fileName  生成文件后保存的名称如test.pdf，test.jpg等
     */
    public static void writeFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
        	createDirectory(filePath);
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
	/**
	 * 读取文件夹下面所有文件
	 * @param path
	 * @return
	 */
	public static File[] getFiles(String path) 
	{
		return   new File(path).listFiles();
	}
	
	/**
	 * 读取文件夹下面指定的文件
	 * @param path
	 * 		文件夹绝对路径
	 * @param postfix
	 * 		后缀名如:.jpg,.tif
	 * @return
	 */
	public static List<File>  getFiles(String path,String postfix) {
		List<File> list = new ArrayList<File>();
		//读取本地文件夹的文件
		File[] files = new File(path).listFiles();
		String temp = null;//后缀名
		String[] postfixs = postfix.split(",");
		for (File item : files) {
			if(item.isDirectory())//如果是文件夹
			{
				continue;
			}
			//得到文件的后缀名,且转为小写字母
			temp = getPostfix(item.getName(), true).toLowerCase();
			for (int i = 0; i < postfixs.length; i++) {
				if(temp.endsWith(postfixs[i]))
				{
					list.add(item);
					break;
				}
			}

		}
		return list;
	}
	
	public static void download(String filePath,HttpServletResponse response)
	{
		OutputStream os = null;
		try {
            // path是指欲下载的文件的路径。
            File file = new File(filePath);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            os = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            os.write(buffer);
            os.flush();
        } catch (IOException ex) {
        	if(null != os)
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            ex.printStackTrace();
        }
	}
	
	/**
	 * 下载
	* @param filePath 文件路径
	* @param fileName 下载的文件名
	* @throws Exception
	 */
	public static void download(HttpServletResponse response,
			String filePath, String fileName) throws Exception {
		File file = new File(filePath);
		response.setContentType("application/x-download");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control",
				"must-revalidate, post-check=0, pre-check=0");
		OutputStream out = null;
		InputStream in = null;
		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		response.addHeader("Content-disposition", "attachment;filename="
				+ fileName);// 设定输出文件头
		try {
			out = response.getOutputStream();
			in = new FileInputStream(file);
			int len = in.available();
			byte[] b = new byte[len];
			in.read(b);
			out.write(b);
			out.flush();
		} catch (Exception e) {
		} finally {
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
		}
	}
}
