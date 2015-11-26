package URLEncode;

import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 * http传输时的编码转换
 * 
 * @author angilin
 * 
 */
public class UrlOrHtmlStringConvert {

	public static void main(String[] args) throws Exception {
		/*// UTF编码，页面间param传参使用
		System.out.println(URLDecoder.decode(
				"%E5%BC%80%E5%8F%91%E5%B7%A5%E7%A8%8B%E5%B8%88desu", "UTF-8"));
		// unicode编码，页面使用，FEFF为big endian unicode标识
		System.out.println(URLDecoder.decode("%FE%FF%75%28+%FE%FF%62%37%7E%C4",
				"Unicode"));
		System.out.println(URLDecoder.decode("\uFEFF\u7528\u6237\u7EC4",
				"Unicode"));

		System.out.println(URLEncoder.encode("用 户组", "Unicode"));
		System.out.println(URLEncoder.encode("组 名", "Unicode"));
		
		System.out.println("aaa:"+URLDecoder.decode("%E6%94%AF%E4%BB%98%E6%88%90%E5%8A%9F",
				"UTF-8"));
		
		System.out.println("aaa:"+URLDecoder.decode("%25E6%2594%25AF%25E4%25BB%2598%25E6%2588%2590%25E5%258A%259F",
				"UTF-8"));
		System.out.println("aaa:"+URLEncoder.encode(URLEncoder.encode("支付成功", "UTF-8"), "UTF-8"));
		
		
		//中文转\\uxxxx格式
		String s = "简介abc aaa";
		String tt = gbEncoding(s);
		System.out.println(decodeUnicode(tt));*/
		
		
		System.out.println(URLEncoder.encode("南", "UTF-8"));
		System.out.println(URLDecoder.decode("%E5%8D%98", "UTF-8"));
		System.out.println(URLEncoder.encode("単", "UTF-8"));
		
		System.out.println(URLEncoder.encode("北", "UTF-8"));
		System.out.println(URLDecoder.decode("%E5%8C%98", "UTF-8"));
		System.out.println(URLEncoder.encode("匘", "UTF-8"));
		
		
		
		String aaa = "@see �������һ���ֽ�����?���������ֽ������MD5����ַ�?";
		System.out.println(URLEncoder.encode(aaa, "UTF-8"));
		System.out.println(URLDecoder.decode(URLEncoder.encode(aaa, "UTF-8"),"GBK"));
	}

	public static String gbEncoding(final String gbString) {
		char[] utfBytes = gbString.toCharArray();
		String unicodeBytes = "";
		for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
			String hexB = Integer.toHexString(utfBytes[byteIndex]);
			if (hexB.length() <= 2) {
				hexB = "00" + hexB;
			}
			unicodeBytes = unicodeBytes + "\\u" + hexB;
		}
		System.out.println("unicodeBytes is: " + unicodeBytes);
		return unicodeBytes;
	}

	public static String decodeUnicode(final String dataStr) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while (start > -1) {
			end = dataStr.indexOf("\\u", start + 2);
			String charStr = "";
			if (end == -1) {
				charStr = dataStr.substring(start + 2, dataStr.length());
			} else {
				charStr = dataStr.substring(start + 2, end);
			}
			char letter = (char) Integer.parseInt(charStr, 16);
			buffer.append(new Character(letter).toString());
			start = end;
		}
		return buffer.toString();
	}

}
