package com.tsty.java.keywords;

/**
 * 代码出自博客链接：http://zengzhaoshuai.iteye.com/blog/1132288
 * 
 * transient说明一个属性是临时的，不会被序列化。
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TransientDemo implements Serializable {
	/** 
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private transient String name;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		String path = "object.txt";
		File file = new File(path);
		TransientDemo transientDemo = new TransientDemo();
		transientDemo.setName("姓名");
		transientDemo.setPassword("密码");
		ObjectOutput output = new ObjectOutputStream(new FileOutputStream(file));
		output.writeObject(transientDemo);
		ObjectInput input = new ObjectInputStream(new FileInputStream(file));
		TransientDemo demo = (TransientDemo) input.readObject();
		System.out.println(demo.getName() + demo.getPassword());//输出内容是：null密码 (因为属性name是transient，不能被序列化)

	}

}
