package com.iostream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 文件间的拷贝
 * @author 浮生若梦
 *
 */
public class FIleCopy {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		byte[] buffer = new byte[1024 * 4];
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream("F:/stamp.bmp");
			fos = new FileOutputStream("F:/stamp2.bmp");
			int ch = 0;
			while((ch=fis.read(buffer))!= -1){
				fos.write(buffer, 0, ch);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
