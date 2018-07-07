package utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import info.impPath;

/**
 * AESCode:AES加密解密算法
 * 
 * @author 蜂鸟Swift
 *
 */
public class AESCode {
	public static String getAESWord(String content) throws Exception {
		byte[] encode = encrypt(content, info.impPath.AES_key);
		String code = parseByte2HexStr(encode);
		return code;
	}

	public static String getOrgWord(String code) throws Exception {
		if (code == null || code.equals("")) {
			return "";
		}
		byte[] decode = parseHexStr2Byte(code);
		byte[] decryptResult = decrypt(decode, impPath.AES_key);
		return new String(decryptResult, "UTF-8");
	}

	// 加密
	private static byte[] encrypt(String content, String password) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(password.getBytes()));
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		// 创建密码器
		Cipher cipher = Cipher.getInstance("AES");
		byte[] byteContent = content.getBytes("UTF-8");
		// 初始化
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] result = cipher.doFinal(byteContent);
		return result;
	}

	// 解密
	private static byte[] decrypt(byte[] content, String password) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(password.getBytes()));
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] result = cipher.doFinal(content);
		return result;
	}

	// 将二进制转换成16进制
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	// 将16进制转换为二进制
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	// 测试集
	public static void main(String[] args) throws Exception {
		String orgWord = "蜂鸟图书馆#SwiftV1.0#Powered By:Swift#";
		String aesWord = getAESWord(orgWord);
		System.out.println(aesWord);
		System.out.println(getOrgWord(aesWord));
	}
}
