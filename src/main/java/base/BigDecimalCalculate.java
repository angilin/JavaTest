package base;

import java.math.BigDecimal;

/**
 * 数字运算精度问题
 * @author angilin
 *
 */
public class BigDecimalCalculate {

	
	public static void main(String[] args){
		
		String a = "1984";
		int a1 = (Integer.parseInt(a)/5)*5;
		System.out.println(a1);
		
		//只有通过String生成的BigDecimal，才不会有精度问题
		//BigDecimal.valueOf等同于new BigDecimal(Double.toString(double))
		//等于先把double转String
		
		BigDecimal d1 = BigDecimal.valueOf(Double.valueOf("0.05"));
		BigDecimal d2 = BigDecimal.valueOf(Double.valueOf("0.01"));
		System.out.println(d1.add(d2));
		//加法会生成新的BigDecimal对象
		System.out.println(d1); 
		
		//直接用double生成BigDecimal，运算有问题
		d1=new BigDecimal(0.05);
		d2=new BigDecimal(0.01);
		System.out.println(d1.add(d2));
		
		d1 = BigDecimal.valueOf(new Double(0.05));
		d2 = BigDecimal.valueOf(new Double(0.01));
		System.out.println(d1.add(d2));
		
		//用Double生成BigDecimal，运算有问题
		d1 = new BigDecimal(new Double(0.05));
		d2 = new BigDecimal(new Double(0.01));
		System.out.println(d1.add(d2));
		
		//用Double生成BigDecimal，运算有问题
		d1 = new BigDecimal(Double.valueOf(0.05));
		d2 = new BigDecimal(Double.valueOf(0.01));
		System.out.println(d1.add(d2));
		
		d1 = new BigDecimal(new Double(0.05).toString());
		d2 = new BigDecimal(new Double(0.01).toString());
		System.out.println(d1.add(d2));
		
		d1 = new BigDecimal(Double.toString(0.05));
		d2 = new BigDecimal(Double.toString(0.01));
		System.out.println(d1.add(d2));
		
		//double运算有问题
		System.out.println(0.05+0.01); 
		System.out.println(123.3/100);
		System.out.println(1.11+1.11112);
		
		
		
		//非BigDecimal的精度测试
		System.out.println(1.1/100);
		System.out.println(1.1/(double)100);
		System.out.println((double)(1.1/100));
		System.out.println((float)1.1/(long)100);
		float f1 = (float)1.1/(long)100;
		double b1 = (float)1.1/(long)100;
		//float f2 = (float)1.1/(double)100;
		double b2 = (float)1.1/(double)100;
		
		
	}
	
}
