package com.hdfs.olo.olo_web.plugins.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 身份证号码工具类
 * @author huadf
 *
 */
public class IdCardUtil {

	//根据身份证号输出年龄
	public static Integer cardNo2Age(String cardNo){
		int leh = cardNo.length();
		String dates="";
		int age = 0;
		if (leh == 18) {
			dates = cardNo.substring(6, 10);
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String year = df.format(new Date());
			age = Integer.parseInt(year)-Integer.parseInt(dates);
		}else if(leh == 15){
			cardNo2Age(trans15bitTo18bit(cardNo));
		}else {
			System.out.println("出错！身份证长度不是18位！");
		}
		return age;
	}
	/**
	 * 根據15位身份證號轉18位
	 * @param input
	 * @return
	 */
	public static String[] trans15bitTo18bit(String[] input){
		String[] result = new String[18];
		for(int i=0;i<input.length;i++){
			if(i<=5){
				result[i] = input[i];
			}else{
				result[i+2] = input[i];
			}
		}
		//年份最后两位小于17,年份为20XX，否则为19XX
		if(Integer.valueOf(input[6])<=1&&Integer.valueOf(input[7])<=7){
			result[6]="2";
			result[7]="0";
		}else{
			result[6]="1";
			result[7]="9";
		}
		//计算最后一位
		String[] xs = {"7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2"};
		//前十七位乘以系数[7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2],
		int sum = 0;
		for(int i=0;i<17;i++){
			sum+= Integer.valueOf(result[i]) * Integer.valueOf(xs[i]);
		}
		//对11求余，的余数 0 - 10
		int rod = sum % 11;
		//所得余数映射到对应数字即可
		if(rod==0){ 
			result[17] = "1";
		}else if(rod==1){ 
			result[17] = "0";
		}else if(rod==2){ 
			result[17] = "X";
		}else if(rod==3){ 
			result[17] = "9";
		}else if(rod==4){ 
			result[17] = "8";
		}else if(rod==5){ 
			result[17] = "7";
		}else if(rod==6){ 
			result[17] = "6";
		}else if(rod==7){ 
			result[17] = "5";
		}else if(rod==8){ 
			result[17] = "4";
		}else if(rod==9){ 
			result[17] = "3";
		}else if(rod==10){ 
			result[17] = "2";
		}
		return result;
	}
	
	public static String trans15bitTo18bit(String cardNo)
	{
		if(null == cardNo || "".equals(cardNo.trim()) || cardNo.length()!=15)
			return null;
		String[] array = new String[15];
		for(int i=0;i<cardNo.length();i++)
		{
			array[i] = String.valueOf(cardNo.charAt(i));
		}
		String[] result = trans15bitTo18bit(array);
		StringBuffer retMsg = new StringBuffer();
		for(int i=0;i<result.length;i++)
		{
			retMsg.append(result[i]);
		}
		return retMsg.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(cardNo2Age("120000197802150561"));
		System.out.println(cardNo2Age("32000019951110538X"));
		String result = trans15bitTo18bit("130503670401001");
		System.out.println(result);
		
//		//创建输入对象
//		Scanner sc=new Scanner(System.in);
//		//获取用户输入的字符串
//		String str="";
//		System.out.print("请输入您的15位身份证号：");
//		str=sc.nextLine();
//		System.out.println("您输入的15位身份证号为："+str);
//		if(str.length()==15){
//			String[] input = str.split("");
//			String[] result = trans15bitTo18bit(input);
//			System.out.print("您的18位身份证号是：");
//			System.out.println(Arrays.toString(result));
//		}else{
//			System.out.println("不符合格式的身份证号！");
//		}
	}
}
