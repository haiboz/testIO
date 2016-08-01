package com.bio;

/**
 * 同步阻塞IO的客户端
 * @author 浮生若梦
 *
 */
public class Client {
	//默认的端口号
	private static int DEFAULT_SERVER_PORT = 12345;
	
	//默认的服务器ip
	private static String DEFAULT_SERVER_IP = "127.0.0.1";
	
	public static void send(int port ,String expression){
		
		System.out.println("表达式是："+expression);
		
	}
	
}
