package base;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 泛型列表转特定类型数组
 * @author hjl
 *
 */
public class ListToArray {
	public static void main(String args[]) throws Exception {
		
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("bbb");
		String[] a = collection2Array(list);
		System.out.println(Arrays.toString(a));
		
		List<Long> list2 = new ArrayList<Long>();
		list2.add(1L);
		list2.add(2L);
		Long[] b = collection2Array(list2);
		System.out.println(Arrays.toString(b));
		
		List<Long> list3 = new ArrayList<Long>();
		list3.add(null);
		Long[] c = collection2Array(list3);
		System.out.println(Arrays.toString(c));
		
		List<Long> list4 = new ArrayList<Long>();
		Long[] d = collection2Array(list4);
		System.out.println(Arrays.toString(d));
		
		collection2Array(null);
		System.out.println();
	}
	
	
	public static <T> T[] collection2Array(Collection<T> c){
		if(c == null || c.isEmpty()){
			return null;
		}
		T t = null;
		Iterator<T> it = c.iterator();
		while(it.hasNext() && t==null){
			t=it.next();
		}
		if(t==null){
			return null;
		}
		return c.toArray((T[])Array.newInstance(t.getClass(), c.size()));
	}
	
	public static <T> T[] collection2Array(Collection<T> c, Class<? extends T> clazz){
		if(c == null){
			return null;
		}
		return c.toArray((T[])Array.newInstance(clazz, c.size()));
	}


}