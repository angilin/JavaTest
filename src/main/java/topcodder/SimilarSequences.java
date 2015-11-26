package topcodder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class SimilarSequences {

	
	
	public static void main(String[] args) throws Exception{
		int[] a = {1,1};
		System.out.println(count(a,3));
		
		int[] b = {1,2};
		System.out.println(count(b,2));
		
		//int[] c = {999999999};
		//System.out.println(count(c,1000000000));
		
		int[] d = {1, 2, 3, 4, 5};
		System.out.println(count(d,5));
	}


	public static int count(int[] seq, int bound){
		HashSet<TestList> set = new HashSet<TestList>();
		for(int i=0;i<seq.length;i++){
			for(int j=1;j<=bound;j++){
				for(int d=0;d<=seq.length;d++){
					TestList testList = new TestList();
					for(int k=0;k<seq.length;k++){
						if(k!=i){
							testList.add(seq[k]);
						}
					}
					if(d!=i){
						testList.add(d>i?d-1:d,j);
						set.add(testList);	
					}
				}
			}
		}
		Iterator<TestList> it = set.iterator();
		while(it.hasNext()){
			System.out.println(it.next().toString());
		}
		return set.size();
	}
	
	private static class TestList{
		private ArrayList<Integer> list= new ArrayList<Integer>();
		
		public void add(int a){
			list.add(a);
		}
		
		public void add(int index, int a){
			list.add(index,a);
		}
		
		public ArrayList<Integer> getList(){
			return list;
		}
		
		public void setList(ArrayList<Integer> l){
			list = l;
		}
		
		public int hashCode() 
		{ 
			int num = 0;
			for(int i=0;i<list.size();i++){
				num += list.get(i);
			}
			return num; 
		} 
		
		public boolean equals(Object o) 
		{ 
			TestList s=(TestList)o; 
			if(list.size()==s.getList().size()){
				for(int i=0;i<list.size();i++){
					if(!list.get(i).equals(s.getList().get(i))){
						return false;
					}
				}
				return true;
			}
			return false;
		} 
		
		public String toString(){
			Integer[] a = new Integer[list.size()];
			return Arrays.toString(list.toArray(a));
		}
		
	}

}
