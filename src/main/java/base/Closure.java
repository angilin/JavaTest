package base;

import java.util.ArrayList;
import java.util.List;

public class Closure {

	
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();
		String a = "";
		StringBuilder b = new StringBuilder();
		int c =3;
		test(list, a, b, c);
		
		System.out.println(list.size());
		System.out.println(a);
		System.out.println(b.toString());
		System.out.println(c);
	}
	
	
	private static void test(List<String> list, String a, StringBuilder b, int c){
		String f = "test";
		list.add(f);
		a=f;
		b.append(f);
		c=4;
	}
}
