package com.tsty.base64;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/** 
 * 柯克霍夫原则：数据的安全基于密钥而不是算法的保密。即系统的安全取决于密钥，对密钥保密，对算法公开。  --- 现代密码学设计的基本原则。
 * 
 * 散列函数用来验证数据的完整性 (特点：长度不受限制；哈希值容易计算;散列运算过程不可逆)
 * 散列函数相关的算法：（消息摘要算法MD5等；SHA--安全散列算法；MAC--消息认证码算法）
 * 
 * JDK自带的加密包
 * java.security  的包是关于-- 消息摘要
 * java.crypto    的包是关于-- 安全消息摘要，消息认证(鉴别)码
 * java.net.ssl   的报是关于 -- 安全套接字
 * 
 * 
 * --第三方扩展包 BC和CC
 * Bouncy Castle （比JDK提供更高强度的算法）
 * 	--两种支持方案： 1）配置 2）调用
 * 
 * Commons Codec 
 * 	 --apache
 *   --Base64、二进制、十六进制、字符集编码
 *   --Url编码/解码
 *   
 *   
 *   Base64算法
 *   	--产生：邮件的“历史问题”
 *   	--定义：基于64个字符的编码算法
 *   	--关于RFC 2045	相当于Base64的
 *   	--衍生：Base16、Base32、Url Base64
 *   	--Base64算法与加解密算法  (违反了柯克霍夫原则,)
 * */

public class TestBase64 {
	
	private static final String SRC_STRING = "test security base64";
	
	public static void main(String[] args) {
		try {
			Class.forName("com.tsty.base64.TestBase64");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		jdkBase64();
		commonsCodecBase64();
		bouncyCastleBase64();
	}
	
	private static void jdkBase64() {
		try {
			BASE64Encoder encoder = new BASE64Encoder();
			String encode = encoder.encode(SRC_STRING.getBytes());
			System.err.println("encode:" + encode);
			BASE64Decoder decoder = new BASE64Decoder();
			System.err.println("decode:" + new String(decoder.decodeBuffer(encode)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void commonsCodecBase64() {
			byte[] encodeBytes = Base64.encodeBase64(SRC_STRING.getBytes());
			System.err.println("encode:" + new String(encodeBytes));
			byte[] decodeBytes = Base64.decodeBase64(encodeBytes);
			System.err.println("decode:" + new String(decodeBytes));
		
	}
	
	private static void bouncyCastleBase64() {
		byte[] encodeBytes = org.bouncycastle.util.encoders.Base64.encode(SRC_STRING.getBytes());
		System.err.println("encode:" + new String(encodeBytes));
		byte[] decodeBytes = org.bouncycastle.util.encoders.Base64.decode(encodeBytes);
		System.err.println("decode:" + new String(decodeBytes));
		
		byte[] encode = org.bouncycastle.util.encoders.Base64.decode(SRC_STRING);
}
	
}
