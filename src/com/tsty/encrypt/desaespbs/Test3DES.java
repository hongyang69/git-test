package com.tsty.encrypt.desaespbs;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.io.CipherIOException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

/**
 * 
 * 对称加密算法 -- 3重DES（Triple DES或DESede）
 * 	--1. 违反柯克霍夫原则
 * 	--2. 安全问题
 * 3重DES的好处：
 * 	--1. 秘钥长度增强
 * 	--2. 迭代次数提高
 *
 */

public class Test3DES {
	private static final String src = "test security 3DES";
	
	public static void main(String[] args) {
		jdk3DES();
		bc3DES();
	}

	private static void jdk3DES() {
		try {
			//生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
//			keyGenerator.init(168); //长度必须是112或168
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			
			//KEY转换
			DESedeKeySpec desedeKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey convertSecretKey = secretKeyFactory.generateSecret(desedeKeySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.err.println("JDK 3DES encrypt: " + Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.err.println("JDK 3DES decrypt: " + new String(result));
			
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void bc3DES() {
		try {
			Security.addProvider(new BouncyCastleProvider());	//使用BC的Provider
			
			//生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede","BC");//加入“BC”参数，表示使用BC的
//			keyGenerator.init(168);	//必须是112或者168
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			
			//KEY转换
			DESedeKeySpec desedeKeySpec = new DESedeKeySpec(bytesKey);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
			SecretKey convertSecretKey = secretKeyFactory.generateSecret(desedeKeySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.err.println("BC 3DES encrypt: " + Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.err.println("BC 3DES decrypt: " + new String(result));
			
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
