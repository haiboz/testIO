package com.iostream;

import java.io.File;
import java.io.IOException;

public class FileExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		creatFile();
	}

	private static void creatFile() {
		File file = new File("F:/test/test.txt");
		try {
			//当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件
			file.createNewFile();  
//			file.mkdirs();//创建路径指定的目录 包括不存在的父目录
			System.out.println("此分区大小："+file.getTotalSpace()/(1024*1024*1024)+"G");
			System.out.println("文件名字："+file.getName());
			System.out.println("文件父目录："+file.getParent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
