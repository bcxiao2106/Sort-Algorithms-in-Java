public class QuickSort extends SortArray{
	
	public QuickSort(int[] intArr){
		this.intArr = intArr;
	}
	
	public static int partition(int[] intArr, int startIdx, int endIdx){
		int pivot = intArr[endIdx];
		int pivotIdx = startIdx;
		int temp = 0;
		for(int i = startIdx; i < endIdx; i++){
			if(intArr[i] <= pivot){
				temp = intArr[i];
				intArr[i] = intArr[pivotIdx];
				intArr[pivotIdx] = temp;
				pivotIdx++;
			} 
		}
		temp = intArr[pivotIdx];
		intArr[pivotIdx] = pivot;
		intArr[endIdx] = temp;
		
		return pivotIdx;
	}
	
	public void sort(){
		sort(this.intArr, 0, this.intArr.length-1);
	}
	
	public static void sort(int[] intArr, int startIdx, int endIdx){
		if(startIdx >= endIdx) return;
		int pivotIdx = partition(intArr, startIdx, endIdx);
		sort(intArr, startIdx, pivotIdx - 1);
		sort(intArr, pivotIdx, endIdx);
	}
}
