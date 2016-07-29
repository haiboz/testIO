package com.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 同步阻塞式通信服务端
 * 同步阻塞模型开发中，ServerSocket负责绑定IP地址，启动监听端口;
 * Socket负责发起连接操作。连接成功后，双方通过输入和输出流进行同步阻塞式通信
 * @author 浮生若梦
 *
 */
public class BIOServer {
	
	//默认端口号
	private static int DEFAULT_PORT = 12345;
	//单例的ServerSocket
	private static ServerSocket serverSocket; 
	//根据传入的参数设置监听端口 如果没有传入 调用一下方法并使用默认端口
	public static void start() throws IOException{
		start(DEFAULT_PORT);
	}
	//这个方法不会被大量调用 直接用同步方法 不需要太考虑效率
	private synchronized static void start(int port) throws IOException{
		if(serverSocket != null ){
			return ;
		}
		try{
			//通过构造函数创建ServerSocket
			//如果端口合法且空闲 服务端就监听成功
			serverSocket = new ServerSocket(port);
			System.out.println("服务端已启动，端口号："+port);
			Socket socket;
			//通过无线循环监听客户端
			//如果没有客户端接入 蒋阻塞再accept操作上
			while(true){
				socket = serverSocket.accept();
				//当有新的客户端接入时，会执行下面的代码
				//然后创建新的线程处理这条socket链路
				new Thread(new ServerHandler(socket)).start();
			}
		}finally{
			//一些必要的清理工作
			if(serverSocket != null){
				System.out.println("服务已关闭");
				serverSocket.close();
				serverSocket = null;
			}
		}
		
		
	}
	
	
	
	
}
