package com.ryan.commons.util.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * AES算法加密解密工具类
 * 
 * @author Alex
 * 
 * @since 2014-10-08
 */
public class AES256Encryption {

	private static Logger logger = LoggerFactory.getLogger(AES256Encryption.class);
	
	private static final String KEY_ALGORITHM = "AES";
	private static final String CHARSET = "UTF-8";
	/**
	 * 加密、解密算法/工作模式/填充方式
	 * 
	 * JAVA6 支持PKCS5PADDING填充方式 Bouncy castle支持PKCS7Padding填充方式
	 * 
	 */
	private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
	/**
	 * 密钥填充字符28位
	 */
	private static final String DEFAULT_CONSTANT = "0000000000000000000000000000";

	static Cipher cipher;
	
	private static void initCipher(){
		// 实例化 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现		 
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		try {
			cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
		} catch (NoSuchAlgorithmException e) {
			logger.error("获取加密实例Cipher失败！", e.getMessage());
		} catch (NoSuchProviderException e) {
			logger.error("获取加密实例Cipher失败！", e.getMessage());
		} catch (NoSuchPaddingException e) {
			logger.error("获取加密实例Cipher失败！", e.getMessage());
		}
	}

	/**
	 * 根据密文+密钥解密
	 * 
	 * @param encryptStr	
	 * 					密文
	 * @param authCode		
	 * 					密钥
	 * @return string
	 * 				解密后字符串
	 * @throws Exception
	 */
	public static String decryptByStr(String encryptStr, String authCode) throws Exception{
		byte [] encryptBytes = parseHexStr2Byte(encryptStr);
		byte [] decryptBytes = decrypt(encryptBytes, authCode);		
		return new String(decryptBytes);
	}
	
	/**
	 * 加密数据
	 * 
	 * @param data
	 *            待加密数据
	 * @param password
	 *            密钥
	 * @return byte[] 加密后的数据
	 */
	public static byte[] encrypt(byte[] data, String password) throws Exception {
		// 还原密钥
		Key k = generateKey(password);
		if(cipher == null){
			initCipher();
		}
		// 初始化，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}
	/**
	 * 加密数据
	 */
	public static String encrypt(String minwen, String password) throws Exception {
		byte[] data = encrypt(minwen.getBytes(), password);
		return parseByte2HexStr(data);
	}

	/**
	 * 解密数据
	 * 
	 * @param data
	 *            待解密数据
	 * @param password
	 *            密钥
	 * @return byte[] 解密后的数据
	 */
	public static byte[] decrypt(byte[] data, String password) throws Exception {
		// 欢迎密钥
		Key k = generateKey(password);
		if(cipher == null){
			initCipher();
		}
		// 初始化，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, k);
		// 执行操作
		return cipher.doFinal(data);
	}

	/**
	 * 转换密钥
	 * 
	 * @param password
	 *            二进制密钥
	 * @return Key 密钥
	 * 
	 */
	public static Key generateKey(String password) throws Exception {

		byte[] data = null;

		if (password == null) {
			password = "0000";
		}

		data = (password + DEFAULT_CONSTANT).getBytes(CHARSET);

		return new SecretKeySpec(data, KEY_ALGORITHM);
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2),
					16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {

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

	/**
	 * @param args
	 * @throws java.io.UnsupportedEncodingException
	 * @throws Exception
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "{\"mCode\":\"M0101\",\"uuid\":\"sdfhdfhfj645758fhfgj\",\"version\":\"2.0\",\"imei\":\"347658679dfhfgjvgkbhljl\",\"extMap\":{\"test\":\"test\"}}";
		System.out.println("原文：" + str);

		// 初始化密钥
		try {
			long start = System.currentTimeMillis();
			// 加密数据
			byte[] data = AES256Encryption.encrypt(str.getBytes(), "0000");
			System.out.println(System.currentTimeMillis() - start + " 加密后："
					+ parseByte2HexStr(data));

			byte[] data1 = AES256Encryption.encrypt(str.getBytes(), "0000");
			System.out.println(System.currentTimeMillis() - start + " 加密后："
					+ parseByte2HexStr(data1));

			byte[] data2 = AES256Encryption.encrypt(str.getBytes(), "0000");
			System.out.println(System.currentTimeMillis() - start + " 加密后："
					+ parseByte2HexStr(data2));

			long start1 = System.currentTimeMillis();
			// 解密数据
			try{
			data = AES256Encryption
					.decrypt(
							parseHexStr2Byte("7289bfac5284deb8e0647cc82dbfafeeb75c0fdf6321a5fda429530296394a34c1177ec893c5201aa5b9c083e185ac47a04b89da2eed83846c56f7d18e986893566e5a85616e5b7535da244a0a5aeeb609f86d2b3a859b984d23f9afc4f3afcea471f27f5e47caa52a1d431409348ceabead1fb72931b00b722851a329bb9d934dcd279c13414b3c88f9d898919dd9053a91e31c37d27b24f83b1a0f549b4179b09cdcd8d393b9dc76cf0d5bdf2714f0ff13339b992fdf10d66943a24dc1d50e588f5ca4a85bb61bd7ae2b0ccdaf11e0"),
							"0000");
			System.out.println(System.currentTimeMillis() - start1 + "解密后："
					+ new String(data));
			}catch(java.security.InvalidKeyException e){
				System.out.println("秘钥格式错误");
			}
			
			try{
			data1 = AES256Encryption
					.decrypt(
							parseHexStr2Byte("C99327793182FE9DEDC700E402283195499E058A4E20D322D405AE47F38FDEB725C7A7F470F6BD970F2754A3129DF65F25C7A7F470F6BD970F2754A3129DF65F25C7A7F470F6BD970F2754A3129DF65F71DACC717C01B597373883D5DD57CDFD"),
							"0000");
			System.out.println(System.currentTimeMillis() - start1 + "解密后："
					+ new String(data1));
			}catch(javax.crypto.BadPaddingException e){
				System.out.println("密钥错误");
			}
			
			try{
			data2 = AES256Encryption
					.decrypt(
							parseHexStr2Byte("C99327793182FE9DEDC700E402283195499E058A4E20D322D405AE47F38FDEB725C7A7F470F6BD970F2754A3129DF65F25C7A7F470F6BD970F2754A3129DF65F25C7A7F470F6BD970F2754A3129DF65F71DACC717C01B597373883D5DD57CDFD"),
							"0000");
			System.out.println(System.currentTimeMillis() - start1 + "解密后："
					+ new String(data2));
			}catch(javax.crypto.IllegalBlockSizeException e){
				System.out.println("解密失败-密文无效");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}