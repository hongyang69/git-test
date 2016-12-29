package com.tsty.binary;

import java.util.Arrays;

/**
 * 数据类型 转化字节 
 * 例：8143 （00000000 00000000 00011111 11001111）
 * 		=> byte[] b = [-49,31,0,0];
 * 		第一个（低端）字节：8143 >> 
 *
 */

public class TestBinary {
	public static void main(String[] args) {
		byte[] intBytes = int2Bytes(8143);
		System.err.println(intBytes[0] + "," + intBytes[1] + "," + intBytes[2] + "," + intBytes[3]);
		
		System.err.println(bytes2Int(intBytes));
		
		byte bt = (byte) 0xcf;
		System.err.println(bt);
		System.err.println(bt & 0xff);//为何&操作之后，就-49就变为207了呢
		
		byte[] longBytes = long2Bytes(9000l);
		System.err.println(longBytes[0] + "," + longBytes[1] + "," + longBytes[2] + "," + longBytes[3] + ","
				+ longBytes[4] + "," + longBytes[5] + "," + longBytes[6] + "," + longBytes[7]);
		System.err.println(bytes2Long(longBytes));
		
		//字符串与字节数组
		String str = "每日一练";
		byte[] strBytes = str.getBytes();
		
		System.err.println(new String(strBytes));
	}
	
	//int转化为byte[]
	private static byte[] int2Bytes(int num){
		byte[] bytes = new byte[4];
//		bytes[0] = (byte) ((int)(num >> 0 * 8) & 0xff);
//		bytes[1] = (byte) ((int)(num >> 1 * 8) & 0xff);
//		bytes[2] = (byte) ((int)(num >> 2 * 8) & 0xff);
//		bytes[3] = (byte) ((int)(num >> 3 * 8) & 0xff);
		for (int i = 0 ; i < bytes.length; i++) {
			bytes[i] = (byte)((int)(num >> i * 8) & 0xff);
		}
		return bytes;
	} 
	
	//转化byte[]为int
	private static int bytes2Int(byte[] bytes){
//		int rs0 = (int)((bytes[0] & 0xff) << 0 * 8);
//		int rs1 = (int)((bytes[1] & 0xff) << 1 * 8);
//		int rs2 = (int)((bytes[2] & 0xff) << 2 * 8);
//		int rs3 = (int)((bytes[3] & 0xff) << 3 * 8);
//		return rs0 + rs1 + rs2 + rs3;
		int result = 0;
		for (int i = 0; i < bytes.length; i++) {
			result += (int)((bytes[i] & 0xff) << i * 8);
		}
		return result;
	} 
	
	//long转化为byte[]
	private static byte[] long2Bytes(long num){
		byte[] bytes = new byte[8];
//			
		for (int i = 0 ; i < bytes.length; i++) {
			bytes[i] = (byte)((int)(num >> i * 8) & 0xff);
		}
		return bytes;
	} 
		
	//转化byte[]为long
	private static long bytes2Long(byte[] bytes){
		int result = 0;
		for (int i = 0; i < bytes.length; i++) {
			result += (long)((bytes[i] & 0xff) << i * 8);
		}
		return result;
	} 
}
