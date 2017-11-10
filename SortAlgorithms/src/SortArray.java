import java.util.ArrayList;
import java.util.Random;

public class SortArray {
	public int[] getIntArr() {
		return intArr;
	}

	public void setIntArr(int[] intArr) {
		this.intArr = intArr;
	}

	public int getCompareCount() {
		return compareCount;
	}

	public void setCompareCount(int compareCount) {
		this.compareCount = compareCount;
	}

	protected int intArr[];
	protected int compareCount;
	
	public void print(){
		for(int i = 0; i < this.intArr.length; i++){
			System.out.print(this.intArr[i] + " ");
		}
		System.out.println();
	}
	
	public void print(int startIdx, int endIdx){
		for(int i = startIdx; i < endIdx + 1; i++){
			System.out.print(this.intArr[i] + " ");
		}
		System.out.println();
	}
	
	public static void print(int intArr[]){
		for(int i = 0; i < intArr.length; i++){
			System.out.print(intArr[i] + " ");
		}
		System.out.println();
	}
	
	public int compareCounts(){
		return this.compareCount;
	}
	
	public int compare(int intA, int intB){
		this.compareCount++;
		return intA - intB;
	}
	
	public static void reverse(int intArr[]){
		for(int i = 0; i <= intArr.length/2; i++){
			int temp = intArr[i];
			intArr[i] = intArr[intArr.length - i - 1];
			intArr[intArr.length - i - 1] = temp;
		}
	}
	
	public void swap(int[] intArr, int i, int j){
		int temp = intArr[i];
		intArr[i] = intArr[j];
		intArr[j] = temp;
	}
	
	public static ArrayList<int[]> generateArray(int min, int max, int length, int copy, char arrayType, boolean printArray){
		if(max - min + 1 < length || copy <= 0) return null;
		int[] tempArr = new int[length];
		ArrayList<int[]> resultArrList = new ArrayList<int[]>();
		int i = 0, j = 0;
		Random rdm = new Random();
		while(i < length){
			int temp = rdm.nextInt(max) + min;
			tempArr[i] = temp;
			i++;
		}
		if(arrayType == 'S' || arrayType == 'V'){
			BubbleSort bs = new BubbleSort(tempArr);
			bs.sort();
		}
		if(arrayType == 'V'){
			reverse(tempArr);
		}
		if(printArray){
			System.out.print("Generated Array: ");
			print(tempArr);
		}
		System.out.println("");
		for(j = 0; j < copy; j++){
			int[] tempCopy = new int[length];
			for(i = 0; i < tempArr.length; i++){
				tempCopy[i] = tempArr[i];
			}
			resultArrList.add(tempCopy);
		}
		return resultArrList;
	}
}
