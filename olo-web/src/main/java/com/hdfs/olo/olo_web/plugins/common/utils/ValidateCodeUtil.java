package com.hdfs.olo.olo_web.plugins.common.utils;

import java.util.Random;
public class ValidateCodeUtil {  
	/**
	 * 	生成指定长度的随机字符串(不包含数字0，和字母l、o和i)
	 * @param capacity 验证码长度
	 * @return
	 */
	public static String genCode(Integer capacity) {    
		//随机字符集(不包含数字0和字母o、i和l)    
		String str = "abcdefghjkmnpqrstuvwxyz123456789";    
		Random rand = new Random();    
		StringBuilder a = new StringBuilder();    
		for (int i = 0; i < capacity; i++) {      
			char c = str.charAt(rand.nextInt(str.length()));      
			a.append(c);    
		}    
		return a.toString();  
	}  
	public static void main(String[] args) {    
		//生产6位长度的随机验证码    
		for(int i=0;i<100;i++) {
			System.out.println(ValidateCodeUtil.genCode(5));  
		}
	}
}
