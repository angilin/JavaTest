package URLEncode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 
 * http传输时url编码转换
 * html页面用，字符串  原文和 Unicode形式（\\uxxxx）互相转换
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
		*/
		
		//中文转\\uxxxx格式
		String s = "简介abc aaa";
		String tt = gbEncoding(s);
		System.out.println(decodeUnicode(tt));
		

		
		System.out.println(URLEncoder.encode("测试人员", "UTF-8"));
		
		System.out.println(URLEncoder.encode("南", "UTF-8"));
		System.out.println(URLDecoder.decode("%E5%8D%98", "UTF-8"));
		System.out.println(URLEncoder.encode("単", "UTF-8"));
		
		System.out.println(URLEncoder.encode("北", "UTF-8"));
		System.out.println(URLDecoder.decode("%E5%8C%98", "UTF-8"));
		System.out.println(URLEncoder.encode("匘", "UTF-8"));
		
		
		
		
		
		String aaa = ";/?:@&=+$,";
		System.out.println(URLEncoder.encode(aaa, "UTF-8"));
		aaa = "-_.!~*'()\"\\";
		System.out.println(URLEncoder.encode(aaa, "UTF-8"));
		
		
		
		aaa="中文";
		aaa = URLDecoder.decode(URLEncoder.encode(aaa, "UTF-8"),"ISO-8859-1");
		System.out.println(new String(aaa.getBytes("ISO-8859-1"), "UTF-8"));
		System.out.println(URLDecoder.decode(URLEncoder.encode(aaa,"ISO-8859-1"),"UTF-8"));
		
		//2步得到%E4%B8%AD%E6%96%87，看来对于这种encodeUTFdecodeISO的，在进行decodeUTF和decodeGBK没有影响
		System.out.println(URLDecoder.decode(URLEncoder.encode(URLDecoder.decode(aaa,"UTF-8"),"ISO-8859-1"),"UTF-8"));
		System.out.println(URLDecoder.decode(URLEncoder.encode(URLDecoder.decode(aaa,"GBK"),"ISO-8859-1"),"UTF-8"));
		
		//%C3%A4%C2%B8%C2%AD%C3%A6%C2%96%C2%87      encodeUTF	decodeGBKencodeUTF	decodeISOencodeUTF  这些就无法一次性还原了
		
		
		
		checkStringEncoding(aaa);
		
		//checkStringEncoding("%E4%B8%AD%E6%96%87");
		
		//checkStringEncoding("%C3%A4%C2%B8%C2%AD%C3%A6%C2%96%C2%87");
		
	}
	
	
	private static String[] charset = {"UTF-8","GBK","ISO-8859-1"};
	
	/**
	 * 在不知道原文的情况下，用于检测输入字符串被哪种方式转换过，目前只支持【编码解码，解码编码，编码，解码】
	 * @param s
	 */
	public static void checkStringEncoding(String s) {
		System.out.println("origin: "+s);
		try{
			for(int i=0;i<charset.length;i++){
				for(int j=0;j<charset.length;j++){
					if(!charset[i].equals(charset[j])){
						System.out.println("encode:"+ charset[i]+",decode:"+ charset[j]+": "+URLDecoder.decode(URLEncoder.encode(s, charset[i]),charset[j]));
						System.out.println("decode:"+ charset[i]+",encode:"+ charset[j]+": "+URLEncoder.encode(URLDecoder.decode(s, charset[i]),charset[j]));
					}
				}
				System.out.println("encode:"+ charset[i]+": "+URLEncoder.encode(s, charset[i]));
				System.out.println("decode:"+ charset[i]+": "+URLDecoder.decode(s, charset[i]));
			}
			System.out.println();
		}
		catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}	
	
	/**
	 * 将字符串转为Unicode形式（\\uxxxx）
	 * @param gbString
	 * @return
	 */
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

	/**
	 * 将unicode形式（\\uxxxx）的字符串转为原文
	 * @param dataStr
	 * @return
	 */
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
