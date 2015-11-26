package base;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

/**
 *  为什么处理有序数组比无序数组快
 *  http://blog.jobbole.com/68023/
 *  CPU的分支预测器 http://zh.wikipedia.org/wiki/%E5%88%86%E6%94%AF%E9%A0%90%E6%B8%AC
  * ClassName: SortedArraySpeedTest
  * Description: TODO
  * @author angilin
  * @date 2014-6-5 下午1:36:35
  *
 */
public class SortedArraySpeedTest {

	
	public static void main(String[] args){

		testSpeed(true);
		//testSpeed(false);
	}
	
	
	private static void testSpeed(Boolean isSorted){
		
		int arraySize = 100000000;
		Random r = new Random();
		int[] array = new int[arraySize];
		for(int i=0;i<array.length;i++){
			array[i] = (int)(r.nextDouble()*100);
		}
		
		if(isSorted){
			Arrays.sort(array);
		}
		
		long start = new Date().getTime();
		int count = 0;
		long sum = 0;
		for(int i=0;i<array.length;i++){
			if(array[i]>=50){
				count++;
				sum+=array[i];
			}
		}
		long end = new Date().getTime();
		if(isSorted){
			System.out.println("sorted:"+(end-start)+"ms, count:"+count+", start:"+start+", end:"+end);
		}
		else{
			System.out.println("unsorted:"+(end-start)+"ms, count:"+count+", start:"+start+", end:"+end);
		}
	}
	
	
}
