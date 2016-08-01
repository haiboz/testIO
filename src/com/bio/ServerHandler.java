package com.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//import com.anxpp.io.utils.Calculator; 

/**
 * 客户端消息处理线程
 * @author 浮生若梦
 *
 */
public class ServerHandler implements Runnable{
	
	private Socket socket;
	
	public ServerHandler(Socket socket){
		this.socket = socket;
	}
	
	@Override
	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(),true);
			String expression;
			String result;
			while(true){
				//通过BufferedReader读取一行
				//如果已经读取到输入流的最后一行 返回null 退出循环
				//如果得到非空值就计算结果并返回
				if((expression = in.readLine()) == null){
					break;
				}
				System.out.println("服务器收到消息："+expression);
				
				result = "计算后:"+expression+" end!";
				
				out.println(result);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//一些必要的清理工作
			try {
				in.close();
				out.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
