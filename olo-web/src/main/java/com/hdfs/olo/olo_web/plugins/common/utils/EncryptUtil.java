package com.hdfs.olo.olo_web.plugins.common.utils;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.util.StringUtils;

public class EncryptUtil {
	private static final String CODE_TYPE = "UTF-8";
	/**
	 * DES加密
	 * @param datasource
	 * @return
	 */
	public static String desEncode(String content,String secretKey){
	    try{
	        SecureRandom random = new SecureRandom();
	        DESKeySpec desKey = new DESKeySpec(secretKey.getBytes(CODE_TYPE));
	        //创建一个密匙工厂，然后用它把DESKeySpec转换成
	        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	        SecretKey securekey = keyFactory.generateSecret(desKey);
	        //Cipher对象实际完成加密操作
	        Cipher cipher = Cipher.getInstance("DES");
	        //用密匙初始化Cipher对象
	        cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
	        //现在，获取数据并加密
//	        byte[] temp = Base64.encodeBase64(cipher.doFinal(content.getBytes()));
//	        return IOUtils.toString(temp,"UTF-8");
	        
	        byte[] encryptStr = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
	        return Base64.getEncoder().encodeToString(encryptStr);
	    }catch(Throwable e){
	        e.printStackTrace();
	        return null;
	    }
	}

	/**
	 * DES解密
	 * @return
	 */
	public static String desDecode(String content,String secretKey) throws Exception {
	    // DES算法要求有一个可信任的随机数源
	    SecureRandom random = new SecureRandom();
	    // 创建一个DESKeySpec对象
	    DESKeySpec desKey = new DESKeySpec(secretKey.getBytes(CODE_TYPE));
	    // 创建一个密匙工厂
	    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
	    // 将DESKeySpec对象转换成SecretKey对象
	    SecretKey securekey = keyFactory.generateSecret(desKey);
	    // Cipher对象实际完成解密操作
	    Cipher cipher = Cipher.getInstance("DES");
	    // 用密匙初始化Cipher对象
	    cipher.init(Cipher.DECRYPT_MODE, securekey, random);
	    // 真正开始解密操作
	    //return IOUtils.toString(cipher.doFinal(Base64.decodeBase64(src)),"UTF-8");
	    byte[] encryptByte = Base64.getDecoder().decode(content);
	    byte[] decryptBytes = cipher.doFinal(encryptByte);
        return new String(decryptBytes);
	}
	/**
     * AES解密
     * @param encryptStr 密文
     * @param decryptKey 秘钥，必须为16个字符组成
     * @return 明文
     * @throws Exception
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        if (StringUtils.isEmpty(encryptStr) || StringUtils.isEmpty(decryptKey)) {
            return null;
        }

        byte[] encryptByte = Base64.getDecoder().decode(encryptStr);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptByte);
        return new String(decryptBytes);
    }

    /**
     * AES加密
     * @param content 明文
     * @param encryptKey 秘钥，必须为16个字符组成
     * @return 密文
     * @throws Exception
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(encryptKey)) {
            return null;
        }

        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(encryptKey.getBytes(), "AES"));

        byte[] encryptStr = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));
        return Base64.getEncoder().encodeToString(encryptStr);
     }

     // 测试加密与解密
     public static void main(String[] args) {
        try {
//            String secretKey = "aaaabbbbccccdddd";
//            String content = "hello world";
//            String s1 = aesEncrypt(content, secretKey);
//            System.out.println(s1);
//            String s = aesDecrypt(s1, secretKey);
//            System.out.println(s);\
            String content = "hello world";
            String s1 = desEncode(content,"@jiaomei");
            System.out.println(s1);
            String s = desDecode(s1,"@jiaomei");
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}