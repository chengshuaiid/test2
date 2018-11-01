package com.etc.vmall101.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static void addCookie(String name,String value,int days,HttpServletResponse response){
		Cookie cookie = new Cookie(name, value);

		//设置cookie最大年龄---持久化Cookie
		cookie.setMaxAge(days*24*60*60);
		response.addCookie(cookie);
		
	}
	public static String queryCookie(String name,HttpServletRequest request){
		Cookie[] cookies = request.getCookies();
		if(cookies!=null){
			for(Cookie cookie : cookies){
				if(cookie.getName().equals(name)){
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	public static void deleteCookie(String name,HttpServletResponse response){
		//创建一个同名Cookie
		Cookie cookie = new Cookie(name, "");

		//设置cookie最大年龄为0
		cookie.setMaxAge(0);
		
		//发送Cookie
		response.addCookie(cookie);
	}
}
