package URLEncode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class WebTransfer {

	
	public static void main(String[] args){
		
		//String s = "浜ゆ槗鎴愬姛";
		//String s = "涓婃捣娴︿笢鍙戝睍閾惰";
		String s = "{\"ret_code\":\"3007\",\"ret_msg\":\"[user_id]鏌ヨ涓嶅瓨鍦 }";
			
		/*try {
			System.out.println(new String (s.getBytes("gbk"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		decode(encode(s,"GBK"),"UTF-8");
		
		String s1 = "{\"ret_code\":\"3007\",\"ret_msg\":\"[user_id]查询不存在\"}";
		s1 = decode(encode(s1,"UTF-8"),"GBK");
		decode(encode(s1,"GBK"),"UTF-8");
		
		//encode(s,"GBK");
		
		/*String s=  "中文a+b";
		s = encode(s,"UTF-8");
		s = decode(s,"GBK");
		try {
			System.out.println(new String (s.getBytes("gbk"),"utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
				
		
	}
	
	public static String encode(String s, String encode){
		try {
			s = URLEncoder.encode(s, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(s);
		return s;
	}
	
	public static String decode(String s, String encode){
		try {
			s = URLDecoder.decode(s, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(s);
		return s;
	}
}
