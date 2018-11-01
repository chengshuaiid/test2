package com.etc.vmall101.util;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

public class StringUtil {

	//转换关键字---将source中的每一个keyword替换成加红的keyword  
	public static String convertKeyword(String source, String keyword){
		
			if(!"".equals(keyword)){
				return source.replace(keyword, "<font color=red><b>" + keyword + "</b></font>");
			}else{
				return source;
			}
			
	}
	
	//转换日期时间---将数据库中的datetime转换成指定的pattern的格式
	public static String convertDatetime(Date datetime, String pattern){
		
		DateFormat df = new SimpleDateFormat(pattern);
		
		return df.format(datetime);
	}
	
	public static String convertDateTime(Date datetime){
		Date now = new Date();
		int s = (int) (now.getTime()-datetime.getTime())/1000;
		if(s<=60){
			return "刚刚发布";
		}else if(s<=60*60){
			return s/60+"分钟前发布";
		}else if(s<=60*60*3){
			return s/60/60+"小时前发布";
		}else if(s<=60*60*24){
			return convertDatetime(datetime,"HH时mm分")+"发布";
		}else{
			return convertDatetime(datetime,"MM月dd日 HH时mm分")+"发布";
		}
		
	}
	
	public static String fileType(String fileName,ServletContext serCon){
		int index = fileName.lastIndexOf(".");
		String exension =  fileName.substring(index+1);
		String coinName = exension+".png";
		File file = new File(serCon.getRealPath("/img/icon")+"/"+coinName);
		if(file.exists()){
			return exension+".png";
		}else{
			return "default.gif";
		}
	}
	
	public static String fileSize(Integer size){
		if(size<1024){
			return size+"B";
		}else if(size<1024*1024 && size>1024){
			return (size/1024)+"K";
		}else{
			return (size/1024/1024)+"M";
		}
	}
	
	public static String getImgName(String name){
		String[] arrs = name.split("#");
		for(String arr:arrs){
			if(arr.startsWith("m")){
				return arr;
			}
		}
		return null;
	}
	
	//取大图
		public static String[] getBigPhoto(String imgString){
			String[] photos=imgString.split("#");
			String[] bigPhotos=new String[5];
			int j=0;
			for(int i=0;i<photos.length;i++){
				if(photos[i].startsWith("b")){
					bigPhotos[j]=photos[i];
					j++;
				}
			}
			
			return bigPhotos;
		}
		
		
		//取中图
		public static String[] getMiddlePhoto(String imgString){
			String[] photos=imgString.split("#");
			String[] middlePhotos=new String[5];
			int j=0;
			for(int i=0;i<photos.length;i++){
				if(photos[i].startsWith("m")){
					middlePhotos[j]=photos[i];
					j++;
				}
			}
			return middlePhotos;
		}  
		
		//取小图
		public static String[] getSmallPhoto(String imgString){
			String[] photos=imgString.split("#");
			String[] smallPhotos=new String[5];
			int j=0;
			for(int i=0;i<photos.length;i++){
				if(photos[i].startsWith("s")){
					smallPhotos[j]=photos[i];
					j++;
				}
			}
			return smallPhotos;
		}  
		
		//取手机颜色种类
		public static String[] getKindColor(String kindCorlorString){
			String[] kindColors=kindCorlorString.split("#");
			return kindColors;
		}
		
		//取手机制式种类
		public static String[] getKindStand(String kindStandString){
			String[] kindStands=kindStandString.split("#");
			return kindStands;
		}
}
