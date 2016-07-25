package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO 服务端
 * @author 浮生若梦
 *
 */
public class NIOServer {
	
	//通道管理器
	private Selector selector;
	
	/**
	 * 获得一个ServerSocket通道 并对该通道做一些初始化工作
	 * @param port 绑定的端口号
	 * @throws IOException
	 */
	public void initServer(int port) throws IOException{
		//获得一个ServerSocket通道
		ServerSocketChannel serverChnnel = ServerSocketChannel.open();
		//设置通道为非阻塞
		serverChnnel.configureBlocking(false);
		//将该通道对应的serverSocket绑定到port端口
		serverChnnel.socket().bind(new InetSocketAddress(port));
		//获得一个通道管理器
		this.selector = Selector.open();
		//将通道管理器和该通道绑定，并未该通道注册SelectionKey.OP_ACCEPT事件
		//注册该事件后，当该事件到达时，select.select()会返回
		//如果该事件一直未抵达，select.select()会一直阻塞
		serverChnnel.register(selector, SelectionKey.OP_ACCEPT);
		
	}
	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件 如果有 则处理
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public void listen() throws IOException{
		System.out.println("服务端启动成功");
		//轮询访问selector
		while(true){
			//当注册的事件抵达时，方法返回，否则，该方法会一直阻塞
			selector.select();
			//获得selector中选中的项的迭代器，选中的项为注册的事件
			Iterator iterator = this.selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey key = (SelectionKey)iterator.next();
				//移除已选的key防止再次使用
				iterator.remove();
				//客户端请求连接事件
				if(key.isAcceptable()){
					ServerSocketChannel server = (ServerSocketChannel)key.channel();
					//获得和客户端连接的通道
					SocketChannel channel = server.accept();
					//设置成非阻塞
					channel.configureBlocking(false);
					//这里可以给客户端发送信息
					channel.write(ByteBuffer.wrap(new String("服务器给客户端发了消息").getBytes()));
					//在客户端和服务器连接成功后，为了能接受客户端的信息，需要给通道设置读的权限
					channel.register(this.selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){//获得了可读事件
					read(key);
				}
			}
		}
	}
	/**
	 * 处理读取客户端发来的信息的事件
	 * @param key
	 * @throws IOException 
	 */
	private void read(SelectionKey key) throws IOException {
		//服务器可读取信息 获取事件发生的socket通道
		SocketChannel channel = (SocketChannel)key.channel();
		//创建读取的缓存区
		ByteBuffer buffer = ByteBuffer.allocate(10);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("服务端接受的信息:"+msg);
		ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
		//将消息写回服务端
		channel.write(outBuffer);
	}
	
	/**
	 * 启动服务端测试
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException{
		NIOServer server = new NIOServer();
		server.initServer(8000);
		server.listen();
	}
	
	
	
	
	
}
