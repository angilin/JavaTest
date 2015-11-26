package base;

import java.util.Date;

/**
 * 
  * 测试正则表达式
  * @author angilin
  *
 */
public class StringReplace {

	//http://zh.wikipedia.org/zh-cn/%E6%AD%A3%E5%88%99%E8%A1%A8%E8%BE%BE%E5%BC%8F
	//正则表达式规则
	public static void main(String args[]) throws Exception {
		
		System.out.println("0101".charAt(1)=='1');
		
		
		
		
		System.out.println(new Date(1427962881068l));
		
		
		String a = "${text.result.rows}";
		System.out.println(a.replaceAll("\\$\\{text.result.rows\\}", "1"));
		
		//. 匹配除“\n”之外的任何单个字符。要匹配包括“\n”在内的任何字符，请使用像“(.|\n)”的模式。 
		String hiveSql= "select t.* from aaa t where \n --aaaaa \n t.aaaaa=1";
		System.out.println(hiveSql);
		System.out.println(hiveSql.replaceAll("--.*\n", ""));
		
		String ddd= "select t.* from aaa t where \r\n --aaaaa \r\n t.aaaaa=1";
		System.out.println(ddd);

		
		String aaa = "{37672,6913,1864098,1430860,10,1467108,154125,1842105,39852,161799,2420716,290899,2161171,1754032,1723983,1753524,2051430,24443,43802,248099,2155062,1863624,1568252,1950402,312523,1753024,1872139,992366,2203106,152409,2890415,2248062,2854709,2154693,1516273,1879380,7731703,2179426,2293573,2292864,1264600,2191478,2378461}";
		System.out.println(aaa.split(",").length);
		
		Date d = new Date(1*60000);
		System.out.println(d.getTime());
	
	}
}
