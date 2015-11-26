package base;

//排序算法
public class SortAlgorithm {

	public static void main(String[] args) {

		int[] array = {511,132,33,21,313,31,43,22};

		sortquick(array,0,array.length-1);
		for(int a: array){
			System.out.println(a);
		}
	}
	
	
	public static void sortquick(int[] array,int start,int end){
		if(start>=end)
			return;
		int key = array[start];
		int i = start;
		int j = end;
		while(i<j){
			while(array[j]>key && j>=i){
				j--;
			}
			if(j!=i){
				array[i] = array[j];
				array[j] = key;
			}
			while(array[i]<key && j>=i){
				i++;
			}
			if(j!=i){
				array[j] = array[i];
				array[i] = key;
			}
		}
		sortquick(array,start,i-1);
		sortquick(array,i+1,end);
	}

//	快速排序
	public static void sort_quick(int[] numbers, int left, int right)
    {
        if (left < right)
        {
            int middle = numbers[(left + right) / 2];
            int i = left - 1;
            int j = right + 1;
            while (true)
            {
                while (numbers[++i] < middle) ;

                while (numbers[--j] > middle) ;

                if (i >= j)
                    break;

                int number = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = number;
            }

            sort_quick(numbers, left, i - 1);
            sort_quick(numbers, j + 1, right);
        }
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//归并排序
	public static int[] sort_merge(int[] a)
    {
        if (a.length <= 1)
        {
            return a;
        }
        int mid = a.length / 2;
        int[] left = new int[mid];
        int[] right = new int[a.length-mid];

        //以下兩個循環把lst分為左右兩個List
        for (int i = 0; i < mid; i++)
        {
            left[i]=(a[i]);
        }
        for (int j = mid; j < a.length; j++)
        {
            right[j-mid]=a[j];
        }
        left = sort_merge(left);
        right = sort_merge(right);
        return Two_Way_Merge_Sort(left, right);
    }
	
	//归并排序子方法
	public static int[] Two_Way_Merge_Sort(int[] A, int[] B) {
		int[] C = new int[A.length + B.length];
		int k = 0;
		int i = 0;
		int j = 0;
		while (i < A.length && j < B.length) {
			if (A[i] < B[j])
				C[k++] = A[i++];
			else
				C[k++] = B[j++];
		}
		while (i < A.length)
			C[k++] = A[i++];
		while (j < B.length)
			C[k++] = B[j++];
		return C;
	}

	
	


    //起泡排序
	public static void sort_bubble(int[] array){
	    int temp=0;
	    for(int i=0;i<array.length;i++){
	        for(int j=array.length-1;j>i;j--){
	        	if(array[j]<=array[j-1]){
		    		temp=array[j];
		    		array[j]=array[j-1];
		    		array[j-1]=temp;
		    	}
	        }
	    }
    }
    
    
    //插入排序，替代冒泡排序作为最基础的排序
    public static void sort_insert(int[] array){
	    int temp=0;
	    for(int i=1;i<array.length;i++){
	    	temp = array[i];
	    	int j=i;
	    	while(j>0 && temp<array[j-1]){
	    		array[j]=array[j-1];
	    		j--;
	    	}
	    	array[j]=temp;
	    }
    }
    
    //插入排序，带二分查找
    public static void sort_insert_divide_search(int[] array){
	    int temp=0;
	    for(int i=1;i<array.length;i++){
	    	temp = array[i];
	    	int j=i;
	    	int searchIndex = divide_search(array,temp,0,j-1);
	    	while(j>searchIndex){
	    		array[j]=array[j-1];
	    		j--;
	    	}
	    	array[j]=temp;
	    }
    }
    
    //二分查找，插入用，返回值为放在array[int]之前，可能出现大于array长度的数字，同值下时插入在后面
    private static int divide_search(int[] array,int s, int start, int end){
    	int mid = 0;
    	while(start<=end){
    		mid = (start+end)/2;
    		if(array[mid]>s){
    			end = mid-1;
    		}
    		else if(array[mid]<s){
    			start = mid+1;
    		}
    		else{
    			start = mid+1;
    			break;
    		}
    	}
    	return start;
    }

    //选择排序
    public static void sort_select(int[] array){
    	for(int i=0;i<array.length;i++){
    		int k=i;
    		for(int j=i+1;j<array.length;j++){
    			if(array[j]<array[k]){
    				k=j;
    			}
    		}
    		if(k!=i){
	    		int temp=array[k];
	    		array[k]=array[i];
	    		array[i]=temp;
    		}
    	}	
    }
    
    
    
	

}
