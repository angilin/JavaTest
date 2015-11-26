package base;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.TreeSet;

import base.struct.RBTree;


public class CompileError {
	
	public static void main(String [] args) throws Exception {
		
		//字符串相等测试
		String a = "aaa";
		String b = "aaa";
		String c = "aa"+"a";
		String d = new String("aaa");
		String e = new String(a);
		String f = a.substring(0, 3);
		System.out.println(a==b);
		System.out.println(a==c);
		System.out.println(a==d);
		System.out.println(a==e);
		System.out.println(a==f);
		
		//c1的类型
		byte a1=1;
		double b1=2.3;
		//? c1 = (short)a1/b1; 
		
		//未初始化变量的使用
		System.out.println(bbb(i1));
		//System.out.println(bbb(ia1[0]));
		
		//数据类型错误
		//错误 char c = "a";
		
		//对象引用
		StringBuffer s1 = new StringBuffer("x");
		StringBuffer s2 = new StringBuffer("y");
		changeStringBuffer(s1,s2);
		System.out.println(s1+","+s2);
		
		//基类和接口
		TreeSet treeSet = new TreeSet();//SortedSet
		Hashtable table = new Hashtable();//Map
		HashMap map = new HashMap();//Map
	}
	
	private static void changeStringBuffer(StringBuffer s1, StringBuffer s2){
		s1.append(s2);
		s2=s1;
	}
	
	private	static int i1;
	private static int[] ia1;
	
	public static int bbb(int a){
		return a++;
	}
	

	/*同名同参方法错误
	 * public int aaa(){
		return 1;
	}
	public void aaa(){
		
	}*/
	
	//内部类能有哪些前缀和能否继承类实现接口
	public class aaacc extends RBTree{
		
	}
	
}
