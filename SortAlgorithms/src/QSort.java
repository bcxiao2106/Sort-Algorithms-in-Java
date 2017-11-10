import java.util.Random;

public class QSort extends SortArray{
	
	public QSort(int[] intArr){
		this.intArr = intArr;
	}

	public void sort(){
		sort(this.intArr,0,this.intArr.length-1);
	}
	
	public void sort(int[] intArr, int left, int right){
		if(left >= right) return; //finished
		Random rdm = new Random();
		int pIdx = rdm.nextInt(right - left + 1) + left;
		//move pivot to left most
		int temp = intArr[left];
		intArr[left] = intArr[pIdx];
		intArr[pIdx] = temp;
		int pivot = intArr[left];
		int l = left + 1;
		int r = right;
		while(l <= r){
			while( l <= r && this.compare(intArr[l], pivot) <= 0 ) l++;//stop at an element >= pivot
			while( l <= r && this.compare(intArr[r], pivot) >= 0) r--;//stop at an element <= pivot
			if(l < r){
				//swap intArr[l] and intArr[r]
				temp = intArr[l];
				intArr[l] = intArr[r];
				intArr[r] = temp;
				l++;
				r--;
			}
		}
		//swap intArr[left] and intArr[r], intArr[r] is the pivot
		temp = intArr[left];
		intArr[left] = intArr[r];
		intArr[r] = temp;
		//recursive calls
		sort(intArr, left, r-1);
		sort(intArr, r+1, right);
	}
}
