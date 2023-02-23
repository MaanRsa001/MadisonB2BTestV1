package com.maan.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.maan.common.LogManager;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Encrypter {
	private static final String ALGO = "AES";
	private static final String	UNICODE_FORMAT= "UTF-8";
	private static final String AES_KEY = "1234567812345675";
	public String encrypt(String Data) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(AES_KEY.getBytes(UNICODE_FORMAT), ALGO);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, keySpec);
		byte[] encVal = c.doFinal(Data.getBytes());
		String encryptedValue = new BASE64Encoder().encode(encVal);
		return encryptedValue;
	}

	public String decrypt(String encryptedData) throws Exception {
		SecretKeySpec keySpec = new SecretKeySpec(AES_KEY.getBytes(UNICODE_FORMAT), ALGO);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, keySpec);
		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	public static void main(String[] args) {
		try {
			System.out.println(new Encrypter().encrypt("1c5b3ca1de614fbf9356452812e583393d90e4332330444e8dd4c687b857ff618d337a23443b4bf2a90b0ed241c8c8d60a1e265f028c4a10bcdc23362809e34a2c1948b5d8564c23a430b305369025584fb38ed0c783419ca8b60726a9338d5d44bc59fa3b8a45c7ab766f855c4e10632a75beafb0da42f989531a0da0e2aca3"));
			//System.out.println(new Encrypter().decrypt("qBU1+CI2kVRszrb/C6Hcng=="));
		} catch(Exception exception) {
			LogManager.debug(exception);
		}
	}
}
