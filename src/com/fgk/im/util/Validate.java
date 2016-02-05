package com.fgk.im.util;

public class Validate {
	
	public static void NotNull(Object... params) throws Exception{
		
		for (Object object : params) {
			if (object == null) {
					throw new Exception("用户名或者密码为空");
				}
				
			}
		}
		

}
