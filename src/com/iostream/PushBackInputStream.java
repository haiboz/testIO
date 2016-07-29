package com.iostream;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * 回退流测试
 * PushbackInputStream类继承了FilterInputStream类是iputStream类的修饰者。
 * 提供可以将数据插入到输入流前端的能力(当然也可以做其他操作)。
 * 简而言之PushbackInputStream类的作用就是能够在读取缓冲区的时候提前知道下一个字节是什么，
 * 其实质是读取到下一个字符后回退的做法，这之间可以进行很多操作，这有点向你把读取缓冲区的
 * 过程当成一个数组的遍历，遍历到某个字符的时候可以进行的操作，当然，如果要插入，能够插入
 * 的最大字节数是与推回缓冲区的大小相关的，插入字符肯定不能大于缓冲区吧
 * @author 浮生若梦
 *
 */
public class PushBackInputStream {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		String str = "hello,I'm haibozhao.";
		//申明回退流对象
		PushbackInputStream push = null;
		//申明字节数组对象
		ByteArrayInputStream bat = null;
		
		bat = new ByteArrayInputStream(str.getBytes());
		//创建回退流对象  将拆解的字节数组传入
		push = new PushbackInputStream(bat);
		int ch = 0;
		while((ch = push.read()) != -1){//逐字节读取 如果读取完  返回-1
			if(ch == 'I'){
				push.unread(ch);//回到ch的位置
				push.read();//继续读取
				System.out.println("回退：{"+ch+"}位置");
			}else{
				System.out.println(str);
			}
		}
	}

}
