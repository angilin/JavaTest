package base.struct;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MyMap implements Map {
	private int maxLength;
	private double loadFactor;
	private Entry[] table;
	private int size = 0;
	
	public MyMap(){
		maxLength = 16;
		loadFactor = 0.75;
		table = new Entry[maxLength];
	}

	
	public int size() {
		return size;
	}

	
	public boolean isEmpty() {
		return size==0;
	}

	
	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public Object get(Object key) {
		int hash = key.hashCode();
		int index = hash % maxLength;
		Entry entry = table[index];
		while(entry!=null){
			if(key.equals(entry.key) && hash==entry.hash){
				return entry.value;
			}
			entry = entry.next;
		}
		return null;
	}

	
	public Object put(Object key, Object value) {
		int hash = key.hashCode();
		int index = hash % maxLength;
		Entry entry = table[index];
		while(entry!=null){
			if(key.equals(entry.key) && hash==entry.hash){
				Object oldValue = entry.value;
				entry.value = value;
				return oldValue;
			}
			entry = entry.next;
		}
		table[index] = new Entry(hash, key, value, entry);
		size++;
		//计算是否要扩容
		return null;
	}

	
	public Object remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void putAll(Map m) {
		// TODO Auto-generated method stub
		
	}

	
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	
	public Set keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Collection values() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Set entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	
	private class Entry<K,V> implements Map.Entry<K,V>{
		int hash;
		K key;
		V value;
		Entry<K,V> next;
		
		public Entry(int hash,K key,V value,Entry<K,V> next){
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}

		
		public K getKey() {
			return key;
		}

		
		public V getValue() {
			return value;
		}

		
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value; 
			return oldValue;
		}
		
	}
	
	
	
	public static void main(String[] args){
		MyMap map = new MyMap();
		map.put(1, "a");
		map.put(2, "b");
		System.out.println(map.get(1));
		System.out.println(map.get(2));
	}
}
