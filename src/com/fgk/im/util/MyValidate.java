package com.fgk.im.util;

/**
 * 工具类：提供常用的验证函数
 * @author Administrator
 *
 */
public class MyValidate {
	
	/**
	 * 验证输入的参数是否有为空的值
	 * @param params
	 * @return fasle 输入的字符串全部非空 | true 输入的参数存在为空的字符串
	 */
	public static boolean isNullOrEmpty(String... params){
		
		boolean flag = false;
		for (String str : params) {
			
			if (str == null || str.length() <=0) {
				
				flag = true;
			}
		}
		
		return flag;
	} 
}
