package base;

public class Double2Int {

	
	public static void main(String[] args){
		double2int(1234.6);
	}
	
	public static int double2int(double d){
		Double c = d+6755399441055744.0;
		
		System.out.println(c.byteValue());
		return 1;
	}
}
