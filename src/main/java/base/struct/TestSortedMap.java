package base.struct;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 有序map
 * @author angilin
 *
 */
public class TestSortedMap {
	
	public static void main(String[] args){
		
		//keySet有序，插入数据的顺序
		pringMapKeySet(new LinkedHashMap<String, String>());
		//keySet无序
		pringMapKeySet(new HashMap<String, String>());
		//keySet有序，按key自动排序
		pringMapKeySet(new TreeMap<String, String>());
		
		//同步map的方法
		Collections.synchronizedMap(new HashMap<String, String>());		
	}
	
	
	public static void pringMapKeySet(Map<String, String> map){
		map.put("3", "ccc");
		map.put("1", "aaa");
		map.put("2", "bbb");
		for(Iterator<String> it = map.keySet().iterator();it.hasNext();){
			System.out.print(it.next()+",");
		}
		System.out.println();
	}
}
