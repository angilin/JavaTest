package test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
	
	String aa ="1";
	
	static List<? extends Number> list;
	
	public static void main(String[] args){
		System.out.println(1111);
		
		String aa ="b";
		list = new ArrayList<Long>();
		
		int a=1;
		int b=3;
		double c = (double)a/b;
		System.out.println(c);
		
		BigDecimal loanOverdue = BigDecimal.valueOf(a);
		BigDecimal loanRepayAll = BigDecimal.valueOf(b);
		BigDecimal overdueRate = loanOverdue.multiply(new BigDecimal(100)).divide(loanRepayAll,2,4);
		System.out.println(overdueRate);
	}
}
