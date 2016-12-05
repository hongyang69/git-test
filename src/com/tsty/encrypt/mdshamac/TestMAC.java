package com.tsty.encrypt.mdshamac;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.encoders.Hex;

/**
 * MCA(Message Authentication Code)
 * HMAC(keyed_Hash Message Authentication Code),含有秘钥的散列函数算法
 * 
 * 融合MC、SHA
 *		--MD系列：HmacMD2(128位)、HmacMD4(128位)、HmacMD5(128位)
 *		--SHA系列：HmacSHA1(160位)、HmacSHA224(224位)、HmacSHA256(256位)、HmacSHA384(384位)、HmacSHA512(512位)
 * 应用如SecureCRT
 *
 */

public class TestMAC {
	
	private static String src = "test security mac";
	
	public static void main(String[] args) {
		jdkHmacMD5();
		bcHmacMD5();
	}
	
	private static void jdkHmacMD5(){
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5"); //初始化keyGenerator
			SecretKey secretKey = keyGenerator.generateKey();//产生秘钥
			//byte[] key = secretKey.getEncoded();//获得秘钥(随机生成)
			
			byte[] key = org.apache.commons.codec.binary.Hex.decodeHex(new char[]{'a','a','a','a','a','a','a','a','a','a'});
			
			
			SecretKey restoreSecreteKey = new SecretKeySpec(key, "HmacMD5");//还原秘钥
			Mac mac = Mac.getInstance(restoreSecreteKey.getAlgorithm());//实例化MAC
			mac.init(restoreSecreteKey);//初始化MAC
			byte[] hmacMD5Bytes =  mac.doFinal(src.getBytes());
			System.err.println("JDK HmacMD5: " + Hex.toHexString(hmacMD5Bytes));
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	//初始化keyGenerator
		catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void bcHmacMD5(){
		HMac hMac = new HMac(new MD5Digest());
		hMac.init(new KeyParameter(Hex.decode("aaaaaaaaaa")));
		hMac.update(src.getBytes(),0,src.getBytes().length);
		
		byte[] hmacMD5Bytes = new byte[hMac.getMacSize()];	//执行摘要
		hMac.doFinal(hmacMD5Bytes, 0);
		System.err.println("BC HmacMD5: " + Hex.toHexString(hmacMD5Bytes));
	}
}
