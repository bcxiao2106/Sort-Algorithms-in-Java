public class SelectionSort extends SortArray{
	
	public SelectionSort(int[] intArr){
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
		int pValue = findMedian(intArr, startIdx, endIdx);
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
	
	public int findMedian(int[] intArr, int startIdx, int endIdx){
		if(startIdx == endIdx || startIdx == endIdx - 1){
			return intArr[startIdx];
		}
		InsertionSort is = new InsertionSort(intArr);
		for(int i = startIdx, j = startIdx; i <= endIdx; i=i+5, j++){
			if(endIdx - i >= 4){
				is.sort(intArr, i, i+4);
				this.swap(intArr, i+2, j);
			} else if(i == endIdx){
				this.swap(intArr, i, j);
			} else if(endIdx - i < 4){
				is.sort(intArr,i,endIdx);
				this.swap(intArr, i + (endIdx - i + 1) / 2, j);
			}
		}
		this.compareCount += is.compareCount;
		return findMedian(intArr, startIdx, startIdx + (endIdx - startIdx) / 5 + 1);
	}

}
