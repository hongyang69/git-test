package com.tsty.encrypt.mdshamac;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.KeyStore.PrivateKeyEntry;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/** 
 * 消息摘要算法-SHA （MD的继承者）
 * --安全散列算法
 * --固定长度摘要信息
 * SHA共有5中算法（在MD4的基础上引进而来的）
 * SHA-1(摘要长度160)是一种，另外四种统称为SHA-2（SHA-224（摘要长度224）、SHA-256（摘要长度256）、SHA-384（摘要长度384）、SHA-512（摘要长度512））
 * 
 * **/

public class TestSHA {

	private static String src = "test security sha";
	
	public static void main(String[] args) {
		jdkSHA1();
		bcSHA1();
		bcSHA224();
		bcSHA224UsingJDK();
		ccSHA1();
	}
	
	private static void jdkSHA1(){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(src.getBytes());
			System.err.println("JDK SHA-1: " + Hex.encodeHexString(md.digest()));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	private static void bcSHA1(){
		Digest digest = new SHA1Digest();
		digest.update(src.getBytes(),0,src.getBytes().length);
		byte[] sha1Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(sha1Bytes, 0);
		System.err.println("BC SHA-1: " + org.bouncycastle.util.encoders.Hex.toHexString(sha1Bytes));
	} 
	
	private static void bcSHA224(){
		Digest digest = new SHA224Digest();
		digest.update(src.getBytes(),0,src.getBytes().length);
		byte[] sha224Bytes = new byte[digest.getDigestSize()];
		digest.doFinal(sha224Bytes, 0);
		System.err.println("BC SHA-224: " + org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes));
	} 
	
	private static void bcSHA224UsingJDK() {
		try {
			Security.addProvider(new BouncyCastleProvider());
			MessageDigest md = MessageDigest.getInstance("SHA-224");
			byte[] sha224Bytes = md.digest(src.getBytes());
			System.err.println("BC and JDK SHA224: " + org.bouncycastle.util.encoders.Hex.toHexString(sha224Bytes));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	private static void ccSHA1(){
		System.err.println("CC SHA-1_1: " + DigestUtils.sha1Hex(src.getBytes()) );
		System.err.println("CC SHA-1_2: " + DigestUtils.sha1Hex(src) );
	}
}
