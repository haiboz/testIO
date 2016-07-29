package com.iostream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.iostream.common.Student;


/**
 * 读写对象：被读写的对象必须要实现serializalbe
 * @author 浮生若梦
 *
 */
public class ObjectStresm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		String path = "F:/test/student.txt";
		try {
			oos = new ObjectOutputStream(new FileOutputStream(path));
			ois = new ObjectInputStream(new FileInputStream(path));
			oos.writeObject(new Student("zhangsan",18));
			oos.writeObject(new Student("lisi",20));
			oos.writeObject(new Student("wangwu",22));
			for(int i=0;i<3;i++){
				System.out.println(ois.readObject());
			}
		} catch (FileNotFoundException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				oos.close();
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	

}
