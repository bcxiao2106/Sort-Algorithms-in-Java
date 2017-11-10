
public class MergeSort extends SortArray {
	
	private int[] intArrA;
	private int[] intArrB;
	
	public MergeSort(int[] intArrA){
		this.intArr = intArrA;
	}
	
	public MergeSort(int[] intArrA, int[] intArrB){
		this.intArrA = intArrA;
		this.intArrB = intArrB;
		this.intArr = new int[intArrA.length + this.intArrB.length];
	}
	
	public void partition(int[] intArrA, int startIdx, int endIdx){	
		if(startIdx >= endIdx) return;
		int midIdx = startIdx + (endIdx - startIdx) / 2;//find the middle index
		partition(intArrA, startIdx, midIdx);//recursive call to process left side
		partition(intArrA, midIdx + 1, endIdx);//recursive call to process right side
		merge(intArrA, startIdx, midIdx, endIdx);//merge
	}
	
	public void merge(int[] intArr, int startIdx, int midIdx, int endIdx){
		int[] leftPartArr = new int[midIdx - startIdx + 1];//left half array
		int[] rightPartArr = new int[endIdx - midIdx];//right half array
		int[] tempResult = new int[endIdx - startIdx + 1];//array to store result
		int i = 0, j = 0;
		//initial left half array
		for(i = 0, j = startIdx; i < leftPartArr.length; i++, j++){
			leftPartArr[i] = intArr[j];
		}
		//initial right half array
		for(i = 0, j = midIdx + 1; i < rightPartArr.length; i++, j++){
			rightPartArr[i] = intArr[j];
		}
		//sort
		sort(leftPartArr, rightPartArr, tempResult);
		for(i = 0, j = startIdx; i < tempResult.length; i++, j++){
			intArr[j] = tempResult[i];
		}
	}
	
	public void sort(){
		partition(this.intArr, 0, this.intArr.length-1);
	}
	
	public void sort(int[] intArrA, int[] intArrB, int[] intArr){
		int i = 0, j = 0, k = 0;
		int lenA = intArrA.length;
		int lenB = intArrB.length;
		
		while(i < lenA && j < lenB){//compare and merge
			if(this.compare(intArrA[i], intArrB[j]) <= 0){
				intArr[k] = intArrA[i];
				i++;
			} else {
				intArr[k] = intArrB[j];
				j++;
			}
			k++;
		}
		
		while(i < lenA){//add rest elements
			intArr[k] = intArrA[i];
			i++;
			k++;
		}
		
		while(j < lenB){//add rest elements
			intArr[k] = intArrB[j];
			j++;
			k++;
		}
	}
}
