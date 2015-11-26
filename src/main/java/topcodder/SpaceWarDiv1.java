package topcodder;

public class SpaceWarDiv1 {
	/**
	 * http://community.topcoder.com/stat?c=problem_statement&pm=12604&rd=15502
	 * int[] magicalGirlStrength 表示N个魔法少女的力量值
	 * int[] enemyStrength 表示M种魔女的力量值
	 * long[] enemyCount表示M种魔女的每种数量
	 * 当魔法少女的力量大于等于魔女力量时，就能战胜魔女
	 * 每个魔法少女战斗一次后，黑化度+1，如果让魔法少女的最大黑化度最小的情况下，战胜所有魔女...
	 * 
	 */
	public static void main(String[] args) throws Exception{
		int[] a1 = {2, 3, 5}; 
		int[] b1 = {1, 3, 4};
		long[] c1 = {2, 9, 4};
		System.out.println(minimalFatigue(a1, b1, c1));
		
		int[] a2 = {2, 3, 5}; 
		int[] b2 = {1, 1, 2};
		long[] c2 = {2, 9, 4};
		System.out.println(minimalFatigue(a2, b2, c2));
		
		int[] a3 = {14, 6, 22}; 
		int[] b3 = {8,33};
		long[] c3 = {9,1};
		System.out.println(minimalFatigue(a3, b3, c3));
		
		int[] a4 = {869, 249, 599, 144, 929, 748, 665, 37, 313, 99, 33, 437, 308, 137, 665, 834, 955, 958, 613, 417}; 
		int[] b4 = {789, 57, 684, 741, 128, 794, 542, 367, 937, 739, 568, 872, 127, 261, 103, 763, 864, 360, 618, 307};
		long[] c4 =  {20626770196420l, 45538527263992l, 52807114957507l, 17931716090785l, 65032910980630l, 88711853198687l, 26353250637092l,
				 61272534748707l, 89294362230771l, 52058590967576l, 60568594469453l, 23772707032338l, 43019142889727l, 39566072849912l,
				 78870845257173l, 68135668032761l, 36844201017584l, 10133804676521l, 6275847412927l, 37492167783296l};
		System.out.println(minimalFatigue(a4, b4, c4));
	}
	
	/**
	 * 输入参数限制
	-	magicalGirlStrength will contain between 1 and 50 elements, inclusive.
	-	Each element of magicalGirlStrength will be between 1 and 10,000, inclusive.
	-	enemyStrength and enemyCount will each contain between 1 and 50 elements, inclusive.
	-	enemyStrength and enemyCount will contain the same number of elements.
	-	Each element of enemyStrength will be between 1 and 10,000, inclusive.
	-	Each element of enemyCount will be between 1 and 100,000,000,000,000 (10^14), inclusive.
	
	 * @param magicalGirlStrength
	 * @param enemyStrength
	 * @param enemyCount
	 * @return
	 */
	public static long minimalFatigue(int[] magicalGirlStrength, int[] enemyStrength, long[] enemyCount){
		sortI1(magicalGirlStrength, null);
		sortI1(enemyStrength, enemyCount);
		if(enemyStrength[enemyStrength.length-1]>magicalGirlStrength[magicalGirlStrength.length-1]){
			return -1;
		}
		long[] result = new long[magicalGirlStrength.length];
		for(int i=0;i<result.length;i++)
			result[i]=0;
		

		int j = magicalGirlStrength.length-1;
		for(int i=enemyStrength.length-1;i>=0;i--){
			while(j>=0 && magicalGirlStrength[j]>=enemyStrength[i]){
				j--;
			}
			long value = enemyCount[i];
			//平衡从j+1到magicalGirlStrength.length-1的result的值
			balance(j+1,result, value);
		}
		return result[result.length-1];
	}
	
	private static void balance(int k,long[] result, long value){
		long max = result[result.length-1];
		for(int i=result.length-2;i>=k;i--){
			if(result[i]<max){
				if(value<=max-result[i]){
					result[i] +=value;
					value = 0;
				}
				else{
					value = value - (max-result[i]);
					result[i]=max;
				}
			}
		}
		if(value>0){
			int count  = result.length-k;
			long y = value/count;
			long m = value%count;
			for(int i=result.length-1;i>=k;i--){
				result[i] += y;
				if(result.length-1-i<m){
					result[i]++;
				}
			}
		}
	}
	
//  插入排序
    private static void sortI1(int[] array,long[] array2){
	    int temp=0;
	    long temp2=0;
	    for(int i=1;i<array.length;i++){
	    	temp = array[i];
	    	if(array2!=null){
	    		temp2 = array2[i];
	    	}
	    	int j=i;
	    	while(j>0 && temp<array[j-1]){
	    		array[j]=array[j-1];
	    		if(array2!=null){
	    			array2[j]=array2[j-1];
		    	}
	    		j--;
	    	}
	    	array[j]=temp;
	    	if(array2!=null){
	    		array2[j]=temp2;
	    	}
	    }
    }
    
}
