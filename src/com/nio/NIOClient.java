package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * NIO客户端
 * @author 浮生若梦
 *
 */
public class NIOClient {
	
	//通道管理器
	private Selector selector;
	
	
	/**
	 * 初始化一个通道，并对该通道做一些初始化的工作
	 * @param ip 连接的服务器ip
	 * @param port 连接的服务器的端口
	 * @throws IOException 
	 */
	public void initClient(String ip,int port) throws IOException{
		//获得一个通道
		SocketChannel channel = SocketChannel.open();
		//将该通道设置为非阻塞
		channel.configureBlocking(false);
		//获得一个通道管理器
		this.selector = Selector.open();
		//客户端连接服务器 其实方法执行并没有实现连接 需要在listen()方法中调用
		//chanell.finishConnect()完成连接
		channel.connect(new InetSocketAddress(ip, port));
		//将通道管理器和该通道绑定 并为该通道注册SelectionKey.CONNECT事件
		channel.register(selector, SelectionKey.OP_CONNECT);
	}
	
	
	/**
	 * 采用轮询的方式监听selector上是否有需要处理的事件 如果有 则处理
	 * @throws IOException 
	 */
	@SuppressWarnings("rawtypes")
	public void listen() throws IOException{
		//轮询访问selector
		while(true){
			selector.select();
			//获得selector中选中的项的迭代器
			Iterator iterator = this.selector.selectedKeys().iterator();
			while(iterator.hasNext()){
				SelectionKey key = (SelectionKey)iterator.next();
				//删除已使用的key 避免再次使用
				iterator.remove();
				//连接事件发生
				if(key.isConnectable()){
					SocketChannel channel = (SocketChannel)key.channel();
					//如果正在连接 则完成连接
					if(channel.isConnectionPending()){
						channel.finishConnect();
					}
					//设置成非阻塞
					channel.configureBlocking(false);
					//这里可以给服务区发送信息
					channel.write(ByteBuffer.wrap(new String("clint send a message to server").getBytes()));
					//在和服务端连接成功后 为了能接收服务端信息 给通道设置读权限
					channel.register(this.selector, SelectionKey.OP_READ);
				}else if(key.isReadable()){//获得了可读的事件
					read(key);
				}
			}
		}
	}
	
	
	/**
	 * 处理读取服务端发来的消息的事件
	 * @param key
	 * @throws IOException 
	 */
	private void read(SelectionKey key) throws IOException {
		//客户端可读取信息 获取事件发生的socket通道
		SocketChannel channel = (SocketChannel)key.channel();
		//创建读取的缓存区
		ByteBuffer buffer = ByteBuffer.allocate(10);
		channel.read(buffer);
		byte[] data = buffer.array();
		String msg = new String(data).trim();
		System.out.println("服务端接受的信息:"+msg);
		ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());
		//将消息写回客户端
		channel.write(outBuffer);
	}


	/**
	 * 启动客户端测试
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		NIOClient client = new NIOClient();
		client.initClient("localhost", 8000);
		client.listen();
	}

}
