package com.hdfs.olo.olo_web.plugins.common.utils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author: 李逸聪
 * @date: May 23, 2011
 * @description: 字符串帮助类
 */
public class StringHelper {
	private static final String REG_NUMBER = "^[0-9]+$";// 数字
	private static final String REG_NUMBER_SIGN = "^[+-]?[0-9]+$";// 数字，有带正负
	private static final String REG_DECIMAL = "^[0-9]+[.]?[0-9]+$"; // 浮点数
	private static final String REG_DECIMAL_SIGN = "^[+-]?[0-9]+[.]?[0-9]+$";// 浮点数，有带正负
	private static final String REG_CHZH = "[\\u4e00-\\u9fa5]";// 中文
	private static final String REG_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?"; // URL
	private static final String REG_EMAIL = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";// 电子邮箱
	private static final String REG_POST = "\\d{6}";// 邮编
	private static final String REG_ID_CARD = "\\d{17}[\\d|X|x]|\\d{15}";// 身份证
	private static final String REG_JS = "<script(.|\\n)+</script>";// javascript
	private static final String REG_XMLControl = "[\\x00-\\x08|\\x0b-\\x0c|\\x0e-\\x1f]";// xml控制字符
	private static final String REG_CSS = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";// css
	private static final String REG_HTML = "<[^>]+>";// html

	/**
	 * 判断字符串是否为空或为空串
	 * 
	 * @param str 字符串
	 * @return true表示为空或空串，false表示不为空或不是空串
	 */
	public static boolean isNullOrEmpty(String str) {
		boolean flag = false;
		if (null == str || "".equals(str.trim()) ||
				"null".equalsIgnoreCase(str.trim())) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 判断字符串是否为空或为空串
	 * @param str
	 * 			字符串
	 * @return true表示为空或空串，false表示不为空或不是空串
	 */
	public static boolean isNullOrEmpty(String str[]) {
		boolean retFlag = false;
		for(String item:str)
		{
			retFlag = isNullOrEmpty(item);
			if(retFlag)break;
		}
		return retFlag;
	}
	
	/**
	 * 判断字符串是否为空或为空串
	 * @param str
	 * 			字符串
	 * @return true表示为空或空串，false表示不为空或不是空串
	 */
	public static boolean isNullOrEmpty(Object obj[]) {
		boolean retFlag = false;
		for(Object item:obj)
		{
			retFlag = isNullOrEmpty(String.valueOf(item));
			if(retFlag)break;
		}
		return retFlag;
	}
	
	/**
	 * 反转字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String reverse(String str) {
		StringBuffer buffer = new StringBuffer(str);
		return buffer.reverse().toString();

	}

	/**
	 * 字符串改成以分隔符隔开 如090909转成09-09-09
	 * 
	 * @param str   注：位数必须被num整除
	 * @param num   位数
	 * @param split 分隔符
	 * @return
	 */
	public static String convert2Split(String str, int num, String split) {
		StringBuffer buffer = new StringBuffer();
		int length = str.length();
		int count = length / num;
		for (int i = 0; i < count; i++) {
			buffer.append(str.substring(i * num, i * num + num));
			buffer.append(split);
		}

		return buffer.substring(0, buffer.length() - 1);
	}

	/**
	 * 字符串数组转换为字符串,指定分隔符分隔
	 * 
	 * @param strs  字符串数组
	 * @param split 分隔符
	 * @return 生成字符串
	 */
	public static String array2StringBySplit(String[] strs, String split) {
		// 当字符串数组为空或数组个数为0时，则返回空串
		if (strs == null || strs.length == 0)
			return "";
		// 当数组个数为1时，则返回第一个值
		if (strs.length == 1)
			return strs[0];
		StringBuffer buffer = new StringBuffer();
		// 数组个数大约1情况
		for (int i = 0; i < strs.length; i++) {
			buffer.append(strs[i]).append(split);
		}
		buffer.deleteCharAt(buffer.lastIndexOf(split));
		return buffer.toString();
	}

	/**
	 * 转变字符串的编码
	 * 
	 * @param str            字符串
	 * @param oldCharsetName 旧编码格式
	 * @param newCharsetName 新编码格式
	 * @return 编码后的字符串,字符串不合法返回空串
	 * @throws UnsupportedEncodingException
	 */
	public static String convertCharset(String str, String oldCharsetName, String newCharsetName)
			throws UnsupportedEncodingException {
		// 格式为空或者空串，则返回空串
		if (str == null || isNullOrEmpty(oldCharsetName) || isNullOrEmpty(newCharsetName))
			return "";
		String result = "";
		result = new String(str.getBytes(oldCharsetName), newCharsetName);
		return result;
	}

	/**
	 * 将字符串的第一个字母大写
	 * 
	 * @param str 字符串
	 * @return
	 */
	public static String firstCharToUpperCase(String str) {
		if (isNullOrEmpty(str))
			return "";
		char[] chars = str.toCharArray();
		chars[0] = Character.toUpperCase(chars[0]);
		return String.copyValueOf(chars);
	}

	/**
	 * 获取字符串的二进制长度，即汉字等双字节长度算为2，单字节长度算1
	 * 
	 * @param str
	 * @return
	 */
	public static int binaryLength(String str) {
		return str.getBytes().length;
	}

	/**
	 * 字节截取字符串
	 * 
	 * @param str 字符串
	 * @param len 总长度
	 * @return
	 */
	public static String cutString(String str, int count) {
		int reInt = 0;
		String reStr = "";
		if (str == null)
			return "";
		char[] tempChar = str.toCharArray();
		for (int kk = 0; (kk < tempChar.length && count > reInt); kk++) {
			String s1 = String.valueOf(tempChar[kk]);
			byte[] b = s1.getBytes();
			reInt += b.length;
			reStr += tempChar[kk];
		}
		return reStr;
	}

	/**
	 * 是否为数字字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMatchNumber(String str) {
		return isRegexpValidate(str, REG_NUMBER);
	}

	/**
	 * 是否数字字符串 可带正负号
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMatchNumberSign(String str) {
		return isRegexpValidate(str, REG_NUMBER_SIGN);
	}

	/**
	 * 是否是浮点数
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMatchDecimal(String str) {
		return isRegexpValidate(str, REG_DECIMAL);
	}

	/**
	 * 是否是浮点数 可带正负号
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMatchDecimalSign(String str) {
		return isRegexpValidate(str, REG_DECIMAL_SIGN);
	}

	/**
	 * 是否电子邮箱字符串
	 * 
	 * @param str 字符串
	 * @return true表示匹配成功,false表示匹配失败
	 */
	public static boolean isMatchEmail(String str) {
		return isRegexpValidate(str, REG_EMAIL);
	}

	/**
	 * 是否为url字符串
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMatchURL(String str) {
		return isRegexpValidate(str, REG_URL);
	}

	/**
	 * 是否为邮码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMatchPost(String str) {
		return isRegexpValidate(str, REG_POST);
	}

	/**
	 * 是否为身份证号码
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isMatchID_CARD(String str) {
		return isRegexpValidate(str, REG_ID_CARD);
	}

	/**
	 * 字符串是否包含中文
	 * 
	 * @param str 字符串
	 * @return true表示有包含,false表示没有包含
	 */
	public static boolean isHasCHZN(String str) {
		return isRegexpValidate(str, REG_CHZH);
	}

	/**
	 * 过滤JS代码
	 * 
	 * @param str
	 * @return
	 */
	public static String filterJS(String str) {
		// Pattern.CASE_INSENSITIVE：忽略大小写
		Pattern pattern = Pattern.compile(REG_JS, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher.replaceAll("");
	}

	/**
	 * 过滤css
	 * 
	 * @param str
	 * @return
	 */
	public static String filterCSS(String str) {
		Pattern pattern = Pattern.compile(REG_CSS, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher.replaceAll("");
	}

	/**
	 * 过滤html代码
	 * 
	 * @param str
	 * @return
	 */
	public static String filterHTML(String str) {
		Pattern pattern = Pattern.compile(REG_HTML, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher.replaceAll("");
	}

	/**
	 * 过滤html,css,js代码
	 * 
	 * @param str
	 * @return
	 */
	public static String filterHTMLTab(String str) {
		str = filterJS(str);
		str = filterCSS(str);
		str = filterHTML(str);
		return str;
	}

	/**
	 * 过滤xml控制字符
	 * 
	 * @param str
	 * @return
	 */
	public static String filterXmlControlChar(String str) {
		// Pattern.CASE_INSENSITIVE：忽略大小写
		Pattern pattern = Pattern.compile(REG_XMLControl, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		return matcher.replaceAll("");
	}

	/**
	 * 匹配正则表达式
	 * 
	 * @param str   字符串
	 * @param regex 正则表达式
	 * @return true表示匹配成功,false表示匹配失败
	 */
	public static boolean isRegexpValidate(String str, String regex) {
		return isRegexpValidate(str, regex, 0);
	}

	/**
	 * 匹配正则表达式
	 * 
	 * @param str   字符串
	 * @param regex 正则表达式
	 * @param flags 标志
	 * @return true表示匹配成功,false表示匹配失败
	 */
	public static boolean isRegexpValidate(String str, String regex, int flags) {
		Pattern pattern = Pattern.compile(regex, flags);
		Matcher matcher = pattern.matcher(str);
		return matcher.find();
	}

	/**
	 * 替换回车换行符为html换行符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceEnterToHtmlNewline(String str) {
		String result = "";

		if (str != null) {
			str = str.replace("\r\n", "<br />");
			str = str.replace("\n", "<br />");
			result = str;
		}
		return result;
	}

	/**
	 * 替换html换行符为回车换行符
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceHtmlLineToEnter(String str) {
		String result = "";

		if (str != null) {
			str = str.replace("<br />", "\r\n");
			str = str.replace("<br />", "\n");
			result = str;
		}
		return result;
	}

	/**
	 * 格式化数字
	 * 
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static String formatNumber(double value, String pattern) {
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(value);
	}

	/**
	 * 格式化
	 * 
	 * @param value
	 * @param pattern
	 * @return
	 */
	public static String format(Object value, String pattern) {
		DecimalFormat decimalFormat = new DecimalFormat(pattern);
		return decimalFormat.format(value);
	}

	/**
	 * 
	 * 格式化数字
	 * 
	 * @param value
	 * @return
	 */
	public static String formatNumber(double value) {
		String pattern = ",##0.00";
		return formatNumber(value, pattern);
	}

	/**
	 * 截取字符串后几位
	 * 
	 * @param str
	 * @param length 后几位
	 * @return
	 */
	public static String cutLastString(String str, int length) {
		String s = "";
		if (str.length() <= length) {
			return str;
		}
		s = str.substring(str.length() - length, str.length());
		return s;
	}

	/**
	 * 全角转半角
	 * 
	 * @param str
	 * @return
	 */
	public static String full2HalfChange(String str) {
		String outStr = "";
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < str.length(); i++) {
			try {
				Tstr = str.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;

				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}
		return outStr.replace(",", "");
	}

	/**
	 * 半角转全角
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String half2Fullchange(String str) throws UnsupportedEncodingException {
		StringBuffer outStrBuf = new StringBuffer("");
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < str.length(); i++) {

			Tstr = str.substring(i, i + 1);
			if (Tstr.equals(" ")) {// 半角空格
				outStrBuf.append(Tstr);
				continue;
			}

			b = Tstr.getBytes("unicode");

			if (b[3] == 0) { // 半角
				b[2] = (byte) (b[2] - 32);
				b[3] = -1;
				outStrBuf.append(new String(b, "unicode"));
			} else {
				outStrBuf.append(Tstr);
			}
		}

		return outStrBuf.toString();
	}

	/**
	 * 替换sql中的单引号,将一个单引号替换成两个单引号
	 * 
	 * @param str
	 * @return
	 */
	public static final String replaceSqlApostrophe(String str) {
		return str.replace("'", "''");
	}

}
