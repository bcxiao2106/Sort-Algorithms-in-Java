import java.util.ArrayList;

public class RadixSort {
	private char[][] charArr;
	private int[] pointerArr;
	private int maxLength = 21;
	
	public RadixSort(char[][] charArr, int[] pointerArr){
		this.charArr = charArr;
		this.pointerArr = pointerArr;
	}
	
	public void sort(){
		//initial the alphaBetaList
		ArrayList<ArrayList<Integer>> alphaBetaList = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 27; i++){
			alphaBetaList.add(new ArrayList<Integer>());
		}
		//initial: store the last char indices into ArrayList
		for(int i = 0; i < charArr.length; i++){
			if(charArr[i] == null) break;
			char tempChar = charArr[i][maxLength - 1];
			if(tempChar == 32){
				tempChar = 64;
			}
			//alphaBetaList.get(charArr[i][maxLength - 1] - 65).add(i);
			//System.out.println(i + " at charArr");
			alphaBetaList.get(tempChar - 64).add(i);
		}
		generatePointerArray(alphaBetaList);
		sort(maxLength - 2, pointerArr);
	}
	
	public void sort(int idx, int[] pointerArr){
		if(idx < 0) return;//end the recursive call
		
		//initial the alphaBetaList
		ArrayList<ArrayList<Integer>> alphaBetaList = new ArrayList<ArrayList<Integer>>();
		for(int i = 0; i < 27; i++){
			alphaBetaList.add(new ArrayList<Integer>());
		}
		
		//store indices into ArrayList base on the current 'char'
		for(int j = 0; j < pointerArr.length; j++){
			//System.out.println(charArr[this.pointerArr[j]][idx]);
			char tempChar = charArr[this.pointerArr[j]][idx];
			if(tempChar == 32){
				tempChar = 64;
			}
			//alphaBetaList.get(charArr[this.pointerArr[j]][idx] - 65).add(this.pointerArr[j]);
			alphaBetaList.get(tempChar - 64).add(this.pointerArr[j]);
		}
		
		//Generate Pointer array based on the new alphaBetaList
		generatePointerArray(alphaBetaList);

		//recursive call
		sort(idx-1, this.pointerArr);
	}
	
	//Generate Pointer array based on the new alphaBetaList
	public void generatePointerArray(ArrayList<ArrayList<Integer>> alphaBetaList){
		int p = 0;
		for(int i = 0; i < alphaBetaList.size(); i++){
			for(int j = 0; j < alphaBetaList.get(i).size(); j++){
				this.pointerArr[p] = alphaBetaList.get(i).get(j);
				p++;
			}
		}
	}
	
	//save result to file
	public void saveToFile(String fileName){
		FileProcessor.writeToFile(this.pointerArr, this.charArr, fileName);
	}
}
