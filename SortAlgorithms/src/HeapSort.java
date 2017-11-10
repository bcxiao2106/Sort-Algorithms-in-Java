
public class HeapSort extends SortArray{

	public HeapSort(int[] intArr){
		this.intArr = intArr;
	}
	
	public void build(){
		biuldHeap(this.intArr, 0, this.intArr.length);
		for(int i = this.intArr.length; i > 0; i--){
			deleMin(this.intArr, i);
		}
	}
	
	public void biuldHeap(int[] intArr, int r, int n){
		if(2 * r + 1 >= n) return;
		biuldHeap(intArr, 2 * r + 1, n);
		biuldHeap(intArr, 2 * r + 2, n);
		pushDown(intArr,r,n);
	}
	
	public void pushDown(int[] intArr, int r, int n){
		if(2 * r + 1 >= n) return;
		int s = 0;
		int temp = 0;
		if(2 * r + 1 == n-1 || this.compare(intArr[2 * r + 1], intArr[2 * r + 2]) > 0 ){
			s = 2 * r + 1;
		} else {
			s = 2 * r + 2;
		}
		if(this.compare(intArr[r], intArr[s]) <= 0){
			temp = intArr[r];
			intArr[r] = intArr[s];
			intArr[s] = temp;
			pushDown(intArr, s, n);
		}
	}
	
	public void deleMin(int[] intArr, int n){
		if(n <= 0 ) return;
		int temp = intArr[0];
		intArr[0] = intArr[n-1];
		intArr[n-1] = temp;
		pushDown(intArr, 0, n-1);
		//deleMin(intArr, n-1);
	}
}