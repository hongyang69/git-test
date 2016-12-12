package com.tsty.encrypt.desaespbs;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
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

import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.crypto.io.CipherIOException;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

/**
 * 
 * 对称加密算法(可以适用于软件和硬件)
 * 	--加密秘钥 = 解密秘钥
 * 	--初等加密算法
 * 	--DES算法(Data Encryption Standard) 数据加密标准
 * 		--延伸出3DES算法
 * 	--AES算法
 * 	--PBE算法
 * 	--IDEA算法
 * 
 *
 */

public class TestDES {
	private static final String src = "test security DES";
	
	public static void main(String[] args) {
		jdkDES();
		bcDES();
	}

	private static void jdkDES() {
		try {
			//生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			
			//KEY转换
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.err.println("JDK DES encrypt: " + Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.err.println("JDK DES decrypt: " + new String(result));
			
			
			
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
	
	private static void bcDES() {
		try {
			Security.addProvider(new BouncyCastleProvider());	//使用BC的Provider
			
			//生成KEY
			KeyGenerator keyGenerator = KeyGenerator.getInstance("DES","BC");//加入“BC”参数，表示使用BC的
			keyGenerator.init(56);
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] bytesKey = secretKey.getEncoded();
			
			//KEY转换
			DESKeySpec desKeySpec = new DESKeySpec(bytesKey);
			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey convertSecretKey = secretKeyFactory.generateSecret(desKeySpec);
			
			//加密
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
			byte[] result = cipher.doFinal(src.getBytes());
			System.err.println("BC DES encrypt: " + Hex.encodeHexString(result));
			
			//解密
			cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
			result = cipher.doFinal(result);
			System.err.println("BC DES decrypt: " + new String(result));
			
			
			
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
