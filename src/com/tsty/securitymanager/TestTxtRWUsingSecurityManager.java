package com.tsty.securitymanager;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 *当你想对Java程序进行权限控制时，可以考虑启用SecurityManager安全管理器，并配置XX.policy文件来达到你想的效果。（想对安全管理器进一步了解，可以阅读《深入Java虚拟机》）
1.启动SecurityManager开关
默认情况下，JVM是不启动安全检查的，所以要想让程序在沙箱中运行，必须将开关打开。打开的方式有两种，一种是在启动运行中追加JVM参数 -Djava.security.manager，还有一种方式是在程序中直接设置：System.setSecurityManager(new SecurityManager());，这两种方式是等价的。

 */
public class TestTxtRWUsingSecurityManager {
	/**
	 *
	 * 功能：Java读取txt文件的内容
	 *
	 * 步骤：1：先获得文件句柄
	 *
	 * 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	 *
	 * 3：读取到输入流后，需要读取生成字节流
	 *
	 * 4：一行一行的输出。readline()。
	 *
	 * 备注：需要考虑的是异常情况
	 *
	 * @param filePath
	 */
	public static void readTxtFile(String filePath) {
		try {
//			String encoding = "GBK";
			String encoding = "UTF-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					System.out.println(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}

	public static void main(String argv[]){
		SecurityManager securityManager = System.getSecurityManager();
		System.err.println(securityManager);
		
        String filePath = "/Users/ningguangyuan/Desktop/1.txt";
//		 String filePath = "TestResources/1.txt";
        readTxtFile(filePath);
    }

}
