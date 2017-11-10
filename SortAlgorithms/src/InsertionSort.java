public class InsertionSort extends SortArray{

	public InsertionSort(int[] intArr){
		this.intArr = intArr;
	}
	
	public void sort(){
		//sort(this.intArr,this.intArr.length);
		//sort(this.intArr);
		sort(this.intArr,0,this.intArr.length-1);
	}
	
	public void sort(int[] intArr, int n){
		if(n == 1) return;
		sort(intArr, n - 1);//recursive call
		int j = n - 1;
		while(j > 0 && this.compare(intArr[j-1], intArr[j]) > 0){
			int temp = intArr[j];
			intArr[j] = intArr[j-1];
			intArr[j-1] = temp;
			j--;
		}
	}
	
	public void sort(int[] intArr, int startIdx, int endIdx){
		if(startIdx == endIdx) return;
		sort(intArr, startIdx, endIdx - 1);//recursive call
		int j = endIdx;
		while(j > startIdx && this.compare(intArr[j-1], intArr[j]) > 0){
			int temp = intArr[j];
			intArr[j] = intArr[j-1];
			intArr[j-1] = temp;
			j--;
		}
	}
	
	public void sort(int[] intArr){
		for(int i = 1; i < intArr.length; i++){
			for(int j = i; j > 0; j--){
				if(this.compare(intArr[j-1], intArr[j]) > 0){
					int temp = intArr[j];
					intArr[j] = intArr[j-1];
					intArr[j-1] = temp;
				}
			}
		}
	}
}
