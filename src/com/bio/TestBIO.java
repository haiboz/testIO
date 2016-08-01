package com.bio;

import java.io.IOException;
import java.util.Random;

import org.omg.Messaging.SyncScopeHelper;

/**
 * 测试BIO
 * 类TestBIO.java的实现描述：TODO 类实现描述 
 * @author wb-zhaohaibo 2016年8月1日 下午5:31:44
 */
public class TestBIO {

    @SuppressWarnings("static-access")
    public static void main(String[] args) {
        
        new Thread(new Runnable(){
            //运行服务器
            @Override
            public void run() {
                try {
                    BIOServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        }).start();
        //休眠已避免客户端先与服务器运行
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Random random = new Random(System.currentTimeMillis());
        new Thread(new Runnable(){
            //客户端运行
            @Override
            public void run() {
                while(true){
                    String expression = "test bio client!";
                    BIOClient.send(expression);
                }
            }
        }).start();
        int sTime = random.nextInt(1000);
        System.out.println("程序休眠："+sTime);
        try {
            Thread.currentThread().sleep(sTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

}
