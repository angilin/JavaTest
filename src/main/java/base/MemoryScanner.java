package base;

import java.util.ArrayList;
import java.util.List;

/**
 * 
  * 内存查看
  * @author angilin
  *
 */
public class MemoryScanner {
	public static void main(String [] args) throws Exception {
		int mb = 1024*1024;
		Runtime runtime = Runtime.getRuntime();
		System.out.println("##### Heap utilization statistics [MB] #####");
		List testList = new ArrayList();
		for(int i =0; i<3;i++){
			//看上去像等待回收生命周期结束的对象，但增加临时对象后却对内存没影响
			Thread.sleep(1000);
			for(int i1 =0; i1<5000; i1++){
				String[] bytes = new String[1000];
				for(int j=0;j<1000;j++){
					bytes[j] = new String("1");
					//String a = new String("12");
					//a.substring(0);
				}
				testList.add(bytes);
			}
			//http://www.importnew.com/1305.html  计算java对象内存
			//http://mercyblitz.iteye.com/blog/710998   深入java对象大小
			//一个String数组，头8byte+长度头4byte，1000个空String对象，???每个只有引用（long型内存地址）4byte，引用为null，循环500次，为20MB-
			//空Long，4	Long=1L,4  Long=111111L,28	Long=111111111111L,28
			//			Long=new Long(1),28	Long=new Long(111111),28	Long=new Long(111111111111),28
			//空long,8	long=1L,8	long=111111L,8	long=111111111111L,8
			//空Byte,4	Byte=1,4
			//			Byte=Byte.valueOf("1"),20
			//空byte,1	byte=1,1
			//空String,4		String="",4		String="1",4	
			//				String=new String(""),36	String=new String("1"),36
			System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / mb+"M");
			System.out.println("average:" + (runtime.totalMemory() - runtime.freeMemory()) / 5000000);
		}
		System.out.println("Free Memory:" + runtime.freeMemory() / mb+"M");
		System.out.println("Total Memory:" + runtime.totalMemory() / mb+"M");
		System.out.println("Max Memory:" + runtime.maxMemory()/mb+"M");
	}
	
	/**
	 * get JVM used memory
	 * @return MB
	 */
	public static long getUsedMemory(){
		int mb = 1024*1024;
		Runtime runtime = Runtime.getRuntime();
		return (runtime.totalMemory() - runtime.freeMemory()) / mb;
	}
}
