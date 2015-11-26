package base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * 读入控制台数据
 * @author hjl
 *
 */
public class SystemIn {
	
	public static void main(String args[]) throws Exception {
		
		
		
		Scanner cin = new Scanner(System.in);
		String s = cin.next();
		System.out.println(s.split(",").length);
		
		
		/*int a = cin.nextInt(), b = cin.nextInt();
		System.out.println(a + b);
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		String str = reader.readLine();
		System.out.println(str);*/
	}
}
