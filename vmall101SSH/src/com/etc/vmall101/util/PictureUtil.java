package com.etc.vmall101.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PictureUtil {
	public static int getImgWidth(String url){
		int width = -1;
		try {
			BufferedImage image = ImageIO.read(new File(url));
			width = image.getWidth();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return width;
	}
	
	public static int getImgHeigh(String url){
		int height = -1;
		try {
			BufferedImage image = ImageIO.read(new File(url));
			height = image.getHeight();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return height;
	}
}
