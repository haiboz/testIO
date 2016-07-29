package com.iostream;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;

/**
 * 合并流 
 * 有些情况下，当我们需要从多个输入流中向程序读入数据。
 * 此时，可以使用合并流，将多个输入流合并成一个SequenceInputStream流对象。
 * SequenceInputStream会将与之相连接的流集组合成一个输入流并从第一个输入流开始读取，
 * 直到到达文件末尾，接着从第二个输入流读取，依次类推，直到到达包含的最后一个输入流的文件末尾为止。
 * 合并流的作用是将多个源合并合一个源。其可接收枚举类所封闭的多个字节流对象
 * @author 浮生若梦
 *
 */
public class TestSequenceInputStream {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		doSeauence();
	}

	private static void doSeauence() {
		//声明合并流
		SequenceInputStream sis = null;
		//声明输出流
		BufferedOutputStream bos = null;
		//构建流集合
		Vector<InputStream> vector = new Vector<InputStream>();
		try {
			vector.addElement(new FileInputStream("F:/test/test.txt"));
			vector.addElement(new FileInputStream("F:/test/test1.txt"));
			vector.addElement(new FileInputStream("F:/test/test2.txt"));
			Enumeration<InputStream> e = vector.elements();
			sis = new SequenceInputStream(e);
			bos = new BufferedOutputStream(new FileOutputStream("F:/test/test4.txt"));
			//写入数据
			int ch = 0;
			byte[] b = new byte[4 * 1024];
			try {
				while((ch = sis.read(b)) != -1){
					bos.write(b, 0, ch);
					bos.flush();
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}finally{
				try {
					sis.close();
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
