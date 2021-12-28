package com.hdfs.olo.olo_web.plugins.common.utils;

import java.security.*;
import java.util.Arrays;

/**
 * 
 * @author: 李逸聪
 * @date: Jun 4, 2011 
 * @description:
 * 		 加密解密
 */
public class EncryptHelper {
    //加密后是20个字节
    public static final String ENC_SHA_1 = "SHA-1";
    
    public static final String ENC_SHA_256 = "SHA-256";
    public static final String ENC_MD5 = "MD5";

    private static EncryptHelper instance = null;
    private MessageDigest md = null;

    private EncryptHelper() {
    }

    private EncryptHelper(String enName) {
        try {
            md = MessageDigest.getInstance(enName);
        } catch (NoSuchAlgorithmException ex) {
            //此处不需要处理异常
        }

    }
    /**
     * 默认为sha256加密
     * @return
     */
    public  static EncryptHelper getInstance()
    {
    	return getInstance(EncryptHelper.ENC_SHA_256);
    }
    public  static EncryptHelper getInstance(String enName) {
        if (instance == null) {
            instance = new EncryptHelper(enName);
        }
        return instance;
    }
    /**
     * 二行制转字符串
     * @param bytes
     * @return
     */
    private String byte2hex(byte[] bytes) 
    {
     String hs="";
     String temp="";
     for (int n=0;n<bytes.length;n++)
      {
    	 temp=(java.lang.Integer.toHexString(bytes[n] & 0XFF));
       if (temp.length()==1) hs=hs+"0"+temp;
       else hs=hs+temp;
       if (n<bytes.length-1)  hs=hs+":";
      }
     return hs.toUpperCase();
    }


   /**
    * 加密
    * @param oldByte
    * @return
    */
    public byte[] encryptString(byte[] oldByte) {
        return md.digest(oldByte);
    }
    
    /**
     * 加密
     * @param oldString
     * @return
     */
    public String encryptString(String oldString) {
    	byte[] bytes=oldString.getBytes();
        return byte2hex(md.digest(bytes));
    }

   /**
    * 比较两个值是否一样
    * @param oldByte
    * @param newByte
    * @return
    */
    public synchronized boolean compareString(byte[] oldByte, byte[] newByte) {
        return Arrays.equals(oldByte, newByte);
    }
    
    /**
     * 比较两个值是否一样
     * @param oldString
     * @param newString
     * @return
     */
    public synchronized boolean compareString(String oldString, String newString) {
        return oldString.equals(newString);
    }
}
