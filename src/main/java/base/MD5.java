package base;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.apache.commons.codec.binary.Base64;

/**
 * https://www.ibm.com/developerworks/cn/java/l-security/ 
 * JAVA 上加密算法的实现用例
 * 
 * @author hjl
 * 
 */
public class MD5 {

	public static void main(String args[]) throws Exception {
		System.out.print("输入需要加密内容：");
		Scanner cin = new Scanner(System.in);
		while (cin.hasNext()) {
			String str = cin.nextLine();
			System.out.println("BASE64编码");
			System.out.println(encrypte(str));
			System.out.println(encrypteOrigin(str));
			System.out.println(e(str));
			System.out.println("不进行BASE64编码");
			System.out.println(getMD5Str(str, true));
			System.out.println(getMD5Str(str, false));
			System.out.println(testDigest(str));
		}
		testDigest();
	}

	public static String e(String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
			md.reset();
			// 默认为iso-8859-1，对于非iso-8859-1字符集，使用iso和utf-8得到的结果不同
			md.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] hash = md.digest();
		Base64 encoder = new Base64();
		return encoder.encodeToString(hash);
	}

	/**
	 * 16位和32位加密结果，16位是取32位结果的9-24位
	 * 加密后的byte为16位，值从-128到127，每个byte可以转化为2位16进制数，最后得到32位结果
	 * 
	 * @param str
	 * @param is32
	 * @return
	 */
	public static String getMD5Str(String str, Boolean is32) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.reset();

			messageDigest.update(str.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			// 如果byte转换结果只有一位，前一位补齐0
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		if (is32) {
			return md5StrBuff.toString();
		} else {
			return md5StrBuff.toString().substring(8, 24);
		}
	}

	/**
	 * 加密方法
	 * 
	 * @param plainText
	 * @return
	 */
	public static String encrypteOrigin(String plainText) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(plainText.getBytes());
		byte[] b = md.digest();
		Base64 encoder = new Base64();
		return encoder.encodeToString(b);
	}

	/**
	 * 加密方法
	 * 
	 * @param plainText
	 * @return
	 */
	public static String encrypte(String plainText) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(plainText.getBytes());
		byte[] b = md.digest();
		Base64 encoder = new Base64();
		return filter(encoder.encodeToString(b));
	}

	/**
	 * 去掉字符串的换行符号 base64编码数据时，如果得到的字符串较长，则其中会有回车和换行符号，需要去掉
	 */
	private static String filter(String str) {
		String output = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			int asc = str.charAt(i);
			if (asc != 10 && asc != 13)
				sb.append(str.subSequence(i, i + 1));
		}
		output = new String(sb);
		return output;
	}

	
	
	
	
	public static String testDigest(String plainText) {
		String result = "";
		try {
			java.security.MessageDigest alg=java.security.MessageDigest.getInstance("MD5");
//			java.security.MessageDigest alga = java.security.MessageDigest
//					.getInstance("SHA-1");
			alg.update(plainText.getBytes());
			byte[] digesta = alg.digest();
			result = byte2hex(digesta);
			
		} catch (java.security.NoSuchAlgorithmException ex) {
			System.out.println("非法摘要算法");
		}
		return result;
	}
	
	
	/**
	 * ibm例子
	 *
	 */
	public static void testDigest() {
		try {
			String myinfo = "我的测试信息";
			// java.security.MessageDigest
			// alg=java.security.MessageDigest.getInstance("MD5");
			java.security.MessageDigest alga = java.security.MessageDigest
					.getInstance("SHA-1");
			alga.update(myinfo.getBytes());
			byte[] digesta = alga.digest();
			System.out.println("本信息摘要是 :" + byte2hex(digesta));
			// 通过某中方式传给其他人你的信息 (myinfo) 和摘要 (digesta) 对方可以判断是否更改或传输正常
			java.security.MessageDigest algb = java.security.MessageDigest
					.getInstance("SHA-1");
			algb.update(myinfo.getBytes());
			if (java.security.MessageDigest.isEqual(digesta, algb.digest())) {
				System.out.println("信息检查正常");
			} else {
				System.out.println("摘要不相同");
			}
		} catch (java.security.NoSuchAlgorithmException ex) {
			System.out.println("非法摘要算法");
		}
	}

	public static String byte2hex(byte[] b) // 二行制转字符串
	{
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

}
