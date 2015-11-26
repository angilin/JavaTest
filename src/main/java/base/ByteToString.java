package base;

import java.io.UnsupportedEncodingException;

public class ByteToString {

		public static void main(String[] args){
			//F7C00CC3C6EB42889E4DB4E3E8D00CF4
			byte[] b = new byte[]{
					(byte) 0xF7 ,
					(byte) 0xC0 ,
					(byte) 0x0C ,
					(byte) 0xC3 ,
					(byte) 0xC6 ,
					(byte) 0xEB ,
					(byte) 0x42 ,
					(byte) 0x88 ,
					(byte) 0x9E ,
					(byte) 0x4D , 
					(byte) 0xB4 , 
					(byte) 0xE3 , 
					(byte) 0xE8 , 
					(byte) 0xD0 , 
					(byte) 0x0C , 
					(byte) 0xF4 
					
					};
			try {
				System.out.println(new String(b,"GBK"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
