package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Pattern;

public class Test {

	//http://www.zhihu.com/question/20552606
	//在 Java 中使用中文作为变量名和函数名称有什么缺点？ 
	private String 中文 = "111";
	
	public String get中文() {
		return 中文;
	}

	public void set中文(String 中文) {
		this.中文 = 中文;
	}

	public static void main(String [] args) throws Exception {
		/*int mb = 1024*1024;
		Runtime runtime = Runtime.getRuntime();
		System.out.println("##### Heap utilization statistics [MB] #####");
		List testList = new ArrayList();
		for(int i =0; i<10;i++){
			Thread.sleep(1000);
			for(int i1 =0; i1<5000; i1++){
				testList.add(new String[1000]);
			}
			System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / mb+"M");
		}
		System.out.println("Free Memory:" + runtime.freeMemory() / mb+"M");
		System.out.println("Total Memory:" + runtime.totalMemory() / mb+"M");
		System.out.println("Max Memory:" + runtime.maxMemory()/mb+"M");*/
		
		
		
		//http://www.importnew.com/9162.html
		/* Integer.valueOf(String)确有一个不同寻常的行为。
		 valueOf会返回一个Integer（整型）对象，当被处理的字符串在-128和127（包含边界）之间时，返回的对象是预先缓存的。
		 这就是为什么Integer.valueOf("127")==Integer.valueOf("127")会返回true-127这个整型对象是被缓存的（所以两次valueOf返回的是同一个对象）
		Integer.valueOf("128")==Integer.valueOf("128")返回false是因为128没有被缓存，所以每次调用，都会生成一个新的整型对象，因此两个128整型对象是不同的对象。*/

		
		System.out.println("select    *    from".replaceAll("\\s+", " "));
		
		
		
		
		
		Integer a = 100;

		Integer b = 100;

		System.out.println(a == b) ;
		System.out.println(Integer.valueOf("127")==Integer.valueOf("127")); 
		Integer c = 128;

		Integer d = 128;

		System.out.println(c == d) ;
		System.out.println(c.equals(d)) ;

		BigDecimal b1 = new BigDecimal(0);
		BigDecimal b2 = new BigDecimal(0.0000);
		System.out.println(b1.equals(b2));
		System.out.println(b1.compareTo(b2));

	}
}
