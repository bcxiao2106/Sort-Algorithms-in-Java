
public class BinarySearch extends SortArray{

	public BinarySearch(int[] intArr){
		this.intArr = intArr;
	}
	
	public int search(int left, int right, int KEY){
		if(left > right) return -1;
		int m = (left + right) / 2;
		if(KEY == this.intArr[m]) return m;
		if(KEY < this.intArr[m]) {
			return search(left,m-1,KEY);
		} else {
			return search(m+1,right,KEY);
		}
	}
}
