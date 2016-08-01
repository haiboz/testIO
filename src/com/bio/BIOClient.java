package com.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 同步阻塞IO的客户端
 * @author 浮生若梦
 *
 */
public class BIOClient {
	//默认的端口号
	private static int DEFAULT_SERVER_PORT = 12345;
	
	//默认的服务器ip
	private static String DEFAULT_SERVER_IP = "127.0.0.1";
	
	public static void send(String expression){
	    send(DEFAULT_SERVER_PORT,expression);
	}
	
	public static void send(int port ,String expression){
		
		System.out.println("表达式是："+expression);
		Socket socket = null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		
		try {
            socket = new Socket(DEFAULT_SERVER_IP, DEFAULT_SERVER_PORT);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            writer.println(expression);
            System.out.println("结果为："+reader.readLine());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            //清理
            try {
                reader.close();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
	}
	
}
