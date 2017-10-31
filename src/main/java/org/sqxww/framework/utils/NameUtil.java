package org.sqxww.framework.utils;

public class NameUtil {
	
	/**
	 * 首字母大写
	 * @param str
	 * @return
	 */
	public static String firstToUp(String str){
		if(null != str && !"".equals(str)){
			char[] chars = str.toCharArray();
			if(chars[0] >= 'a' && chars[0] <= 'z'){
				chars[0] = (char) (chars[0] - 32);
				return new String(chars);
			}
		}
		return str;
	}
	
	/**
	 * 首字母小写
	 * @param str
	 * @return
	 */
	public static String firstToLow(String str){
		if(null != str && !"".equals(str)){
			char[] chars = str.toCharArray();
			if(chars[0] >= 'A' && chars[0] <= 'Z'){
				chars[0] = (char) (chars[0] + 32);
				return new String(chars);
			}
		}
		return str;
	}
	
	/**
	 * 去下划线
	 * @param str
	 * @return
	 */
	public static String delUnderLine(String str){
		if(null != str && !"".equals(str)){
			String[] strs = str.split("_");
			String result = strs[0];
			for(int i = 1; i < strs.length; i++)
				result += firstToUp(strs[i]);
			return result;
		}
		return str;
	}
	
	/**
	 * 包名转路径名
	 * @param str
	 * @return
	 */
	public static String packToPath(String str){
		if (null != str && !"".equals(str)) {
			return str.replaceAll("\\.", "/") + "/";
		}
		return str + "/";
	}
}
