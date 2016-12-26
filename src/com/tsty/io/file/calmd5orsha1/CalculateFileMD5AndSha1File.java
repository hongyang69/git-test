package com.tsty.io.file.calmd5orsha1;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class CalculateFileMD5AndSha1File {
	public static void main(String[] args) {
		String pathname = "/Users/ningguangyuan/Desktop/SynchronizedDemo.java";
		String fileMD5 = getFileMD5(new File(pathname));
		System.err.println(fileMD5);
		String pathname2 = "/Users/ningguangyuan/Desktop/SynchronizedDemo.java";
		String file2MD5 = getFileMD5(new File(pathname2));
		System.err.println(file2MD5);
		String pathname3 = "/Users/ningguangyuan/Desktop/hello.js";
		String file3MD5 = getFileMD5(new File(pathname3));
		System.err.println(file3MD5);
	}

	// 计算文件的 MD5 值
	public static String getFileMD5(File file) {
		if (!file.isFile()) {
			return null;
		}
		MessageDigest digest = null;
		FileInputStream in = null;
		byte buffer[] = new byte[8192];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = new FileInputStream(file);
			while ((len = in.read(buffer)) != -1) {
				digest.update(buffer, 0, len);
			}
			BigInteger bigInt = new BigInteger(1, digest.digest());
			return bigInt.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				in.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 计算文件的 SHA-1 值
	public static String getFileSha1(File file) {
	    if (!file.isFile()) {
	        return null;
	    }
	    MessageDigest digest = null;
	    FileInputStream in = null;
	    byte buffer[] = new byte[8192];
	    int len;
	    try {
	        digest =MessageDigest.getInstance("SHA-1");
	        in = new FileInputStream(file);
	        while ((len = in.read(buffer)) != -1) {
	            digest.update(buffer, 0, len);
	        }
	        BigInteger bigInt = new BigInteger(1, digest.digest());
	        return bigInt.toString(16);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        try {
	            in.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
}
