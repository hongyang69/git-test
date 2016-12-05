package com.tsty.encrypt.mdshamac;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.test.Test;

import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.xml.internal.ws.encoding.xml.XMLMessage.MessageDataSource;

/**
 * MD(Message Digest)  (消息摘要)
 * SHA(Secure Hash Algorithm) (安全三列算法)
 * MAC(Message Authentication Code) (消息认证码)
 * 
 * 这三个算法：
 * 	1）验证数据完整性
 *  2）数字签名核心算法
 *
 */

/**
 *  MD5
 * 	MD家族（128位摘要信息）
 * 		-- MD2、MD4 
 * 
 * 
 * 不论是哪一种MD算法，它们都需 要获得一个随机长度的信息并产生一个128位的信息摘要。
 * 如果将这个128位的二进制摘要信息换算成十六进制，可以得到一个32位的字符串，
 * 故我们见到的 大部分MD5算法的数字指纹都是32位十六进制的字符串。


 *
 */

public class TestMD{
	private static String src = "test security md";
	
	public static void main(String[] args) {
		jdkMD2();
		jdkMD4();//jdk没有实现MD4，需要用，可以在jre的ext包中加入bc.jar包，并且在java.security文件增加security.provider.11=org.bouncycastle.jce.provider.BouncyCastleProvider
		jdkMD5();
		bcMD4UsingJDKAndBc(); //这种方式在JRE中增加jar包，并且不需要修改java.security文件
		bcMD4();
		bcMD5();
		ccMD2();
		ccMD5();
		
	}
	private static void jdkMD2(){
		try {
			MessageDigest md = MessageDigest.getInstance("MD2");
			byte[] md2Bytes = md.digest(src.getBytes());
			System.err.println("JDK impelments MD2: " + org.apache.commons.codec.binary.Hex.encodeHexString(md2Bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void jdkMD5(){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] md5Bytes = md.digest(src.getBytes());
			System.err.println("JDK impelments MD5: " + org.apache.commons.codec.binary.Hex.encodeHexString(md5Bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void jdkMD4(){
		/**
		 *  */
		try {
			MessageDigest md = MessageDigest.getInstance("MD4");
			byte[] md4Bytes = md.digest(src.getBytes());
			System.err.println("JDK impelments MD4: " + org.apache.commons.codec.binary.Hex.encodeHexString(md4Bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void bcMD4UsingJDKAndBc() {
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest md4 = MessageDigest.getInstance("MD4");
			byte[] md4Bytes = md4.digest(src.getBytes());
			System.err.println("JDKAndBc implements MD4: " + Hex.toHexString(md4Bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void bcMD4(){
		Digest digest = new MD4Digest();
		digest.update(src.getBytes(),0,src.getBytes().length);
		byte[] md4Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(md4Bytes, 0);
		System.err.println("BC MD4: " + Hex.toHexString(md4Bytes));
	}
	
	private static void bcMD5(){
		Digest digest = new MD5Digest();
		digest.update(src.getBytes(),0,src.getBytes().length);
		byte[] md5Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(md5Bytes, 0);
		System.err.println("BC MD5: " + Hex.toHexString(md5Bytes));
	}
	private static void ccMD2() {
		System.err.println("CC MD2: " + org.apache.commons.codec.binary.Hex.encodeHexString(DigestUtils.md2(src)));
	}
	
	private static void ccMD5() {
		System.err.println("CC MD5: " + org.apache.commons.codec.binary.Hex.encodeHexString(DigestUtils.md5(src)));
	}
}
