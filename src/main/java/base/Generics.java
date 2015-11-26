package base;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 泛型测试
 */
public class Generics<T extends Number> {

	public List<?> list1;
	
	public List<T> list2;
	
	public T[] array1;
	
	public T[] array2;
	
	public static void main(String[] args){
		Generics<Integer> g = new Generics<Integer>();
		
		g.list1 = new ArrayList<String>();
		((List<String>)g.list1).add("aaa");
		System.out.println(g.list1.get(0));
		
		g.list2 = new ArrayList<Integer>();
		
		Generics.test1("bc");
		g.test2(1);
		g.test3(Arrays.asList(11.1,22.2));
		
		g.array2 = new Integer[10];
		System.out.println(g.array2.length);
		System.out.println(g.array2[0]);
		
		g.createArray1(Integer.class, 10);
		
	}
	
	
	//静态方法必须在方法上声明泛型，无法依靠类上的泛型声明
	public static <T> void test1(T t){
		System.out.println(t.getClass());
	}
	
	//非静态方法可以在类上声明泛型
	public void test2(T t){
		System.out.println(t.getClass());
	}
	
	//输出的类型为?的类型，而非Number
	public void test3(List<? extends Number> list){
		System.out.println(list.get(0).getClass());
		Number n = list.get(1);
		System.out.println(n);
	}
	
	public void createArray1(int capacity){
		//无法直接对初始化泛型数组
		//array1 =  new T[capacity];
		
		//运行时报出ClassCastException
		//array1 = (T[])new Object[capacity];
	}
	
	//创建泛型数组
	public void createArray1(Class<T> tclass, int capacity) {
		array1 = (T[])Array.newInstance(tclass, capacity);
		System.out.println(array1.length);
		System.out.println(array1[0]);
	}
}
