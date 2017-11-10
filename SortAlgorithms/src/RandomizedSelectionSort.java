import java.util.Random;

public class RandomizedSelectionSort extends SortArray{

	public RandomizedSelectionSort(int[] intArr){
		this.intArr = intArr;
	}
	
	public int find(int k){
		if(this.intArr.length < 25){
			InsertionSort is = new InsertionSort(this.intArr);
			is.sort();
			this.compareCount += is.compareCount;
			return this.intArr[k-1];
		} else {
			return find(this.intArr, 0, this.intArr.length - 1, k);
		}
	}
	
	public int find(int[] intArr, int startIdx, int endIdx, int k){
		if(endIdx <= startIdx){
			return intArr[endIdx];
		}
		Random rdm = new Random();
		int n = endIdx - startIdx + 1;
		int pIdx = rdm.nextInt(n) + startIdx;
		int pValue = intArr[pIdx];
		int lessPointer = startIdx;
		int greaterPointer = endIdx;
		//partition[L,E,G]
		for(int i = startIdx; i <= greaterPointer; i++){
			int compResult = this.compare(intArr[i], pValue);
			if(compResult < 0){
				this.swap(intArr, i, lessPointer);
				lessPointer++;
			} else if(compResult > 0){
				this.swap(intArr, i, greaterPointer);
				greaterPointer--;
				i--;
			}
		}

		if(k <= lessPointer - startIdx){//L
			return find(intArr, startIdx, lessPointer - 1, k);//recursive call
		} else if(k <= greaterPointer - startIdx + 1){//E
			return intArr[lessPointer];
		} else {//G
			if(greaterPointer > startIdx){
				return find(intArr, greaterPointer + 1, endIdx, k + startIdx - greaterPointer - 1);//recursive call
			} else {
				return find(intArr, greaterPointer + 1, endIdx, k - 1);//recursive call
			}
		}
	}
}
