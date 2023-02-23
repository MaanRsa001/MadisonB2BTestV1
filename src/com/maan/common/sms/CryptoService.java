package com.maan.common.sms;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;

import com.maan.common.util.Config;

public class CryptoService {
	
	private static final String FORMAT = "ISO-8859-1";
	
	public static String encrypt(String unencryptedString) throws Exception {

        String encryptedString = null;
        Config.getCipher().init(Cipher.ENCRYPT_MODE, Config.getKey());
        byte[] plainText = unencryptedString.getBytes(FORMAT);
        byte[] encryptedText = Config.getCipher().doFinal(plainText);
        encryptedString = DatatypeConverter.printBase64Binary(encryptedText);
        return encryptedString;
    }
	
	public static String decrypt(String encryptedString)  throws Exception {

        String decryptedText = null;
        Config.getCipher().init(Cipher.DECRYPT_MODE, Config.getKey());
        byte[] encryptedText = DatatypeConverter.parseBase64Binary(encryptedString.replace(" ", "+"));
        byte[] plainText = Config.getCipher().doFinal(encryptedText);
        decryptedText = new String(plainText);

        return decryptedText;
    }
	
	public static void main(String[] args) {
		try {
			String encrypt = CryptoService.encrypt("chassisno=KMHSU81E3HU655078~civilid=91975864~mobileno=93306457");
			System.out.println("encrypt="+encrypt);
			//YzLFr3kcUqNuCgyYsvBRDMejjXy8jjcztnHhF3gOwS/fxj ukHb8Lm1dlCAPTIShsx1VJAsAiuw=
			System.out.println("decrypt="+CryptoService.decrypt("YzLFr3kcUqP30nNXeEIB8AgmaWwWd0bnkvWMc5NPp1FlvoyGl5vHx6DjV/+Sq9VYLHqcxwk3Aq8DLS9i7jORnw=="));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
