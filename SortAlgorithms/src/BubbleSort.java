public class BubbleSort extends SortArray{
	
	public BubbleSort(int[] intArr){
		this.intArr = intArr;
	}
	
	public void sort(){
		this.sort(this.intArr.length);
	}
	
	public void sort(int n){
		if(n == 1) return;
		for(int i = 0; i < n-1; i++){
			if(intArr[i] > intArr[i+1]){//compare
				//move the bigger element backward
				int temp = intArr[i];
				intArr[i] = intArr[i+1];
				intArr[i+1] = temp;
			}
		}
		sort(n-1);//recursive call
	}

	public int[] getIntArr() {
		return intArr;
	}

	public void setIntArr(int[] intArr) {
		this.intArr = intArr;
	}
}
