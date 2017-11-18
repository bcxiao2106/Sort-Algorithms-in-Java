import java.util.ArrayList;

public class SortII {
	
	private static ArrayList<String> resultArrayLst;
/*
	public static void main(String[] args) {
		resultArrayLst = new ArrayList<String>();
		String result = String.format("%-4s%-39s%-12s%-12s%-12s%-12s%-20s", "SN", "ALGORITHM", "N", "K", "VALUE[K]", "KEY_COMPs", "RUNNING_TIME(ns)");
		String sep = String.format("%-111s", "---------------------------------------------------------------------------------------------------------------");
		resultArrayLst.add(result);
		resultArrayLst.add(sep);
		
		System.out.println("======== n=10,000, k=5,000 Random Generated Order ========");
		executeSort(10000,'R',5000,false,false);
		
		System.out.println("======== n=100,000, k=50,000 Random Generated Order ========");
		executeSort(100000,'R',50000,false,false);
		
		System.out.println("======== n=1,000,000, k=500,000 Random Generated Order ========");
		executeSort(1000000,'R',500000,false,false);
		
		printResult();
	}
	/*FUNCTION: executeSort(int length, char arrayType, int k, boolean printOriginalArray, boolean printResult)
	1. length: integer, the length of the array; 
	2. arrayType['R': Randomly Generated] ['S': Sorted] ['V': Reversely Sorted]
	3. k, the index of the target element
	4. printOriginalArray: [ture: print the original array] [false: not to print the original array]
	5. printResult: [ture: print the result] [false: not to print the result]
 * *
 */
public static void executeSort(int length, char arrayType, int k, boolean printOriginalArray, boolean printResult){

	String arrTypeTxt = "";
	if(arrayType == 'R') arrTypeTxt = "Random Generated Order";
	if(arrayType == 'S') arrTypeTxt = "Sorted";
	if(arrayType == 'V') arrTypeTxt = "Reversely Sorted";
	ArrayList<int[]> resultArrLst = SortArray.generateArray(1, length * 2, length, 3, arrayType, printOriginalArray);
	
	//SELECT 1: Quick Sort
	QSort qs = new QSort(resultArrLst.get(0));
	long startTime = System.nanoTime();
	qs.sort();
	long stopTime = System.nanoTime();
	long elapsedTime = stopTime - startTime;
	System.out.println("\tAlgorithm SELECT1: " + length + ", " + k + ", " + qs.intArr[k-1] + ", " + qs.compareCount);
	saveResult("SELECT 1: Quick Sort", length, k, qs.intArr[k-1], qs.compareCount, elapsedTime);
	
	//SELECT 2: Randomized Selection
	RandomizedSelectionSort rss = new RandomizedSelectionSort(resultArrLst.get(1));
	startTime = System.nanoTime();
	int result = rss.find(k);
	stopTime = System.nanoTime();
	elapsedTime = stopTime - startTime;
	System.out.println("\tAlgorithm SELECT2: " + length + ", " + k + ", " + result + ", " + rss.compareCount);
	saveResult("SELECT 2: Randomized Selection", length, k, result, rss.compareCount, elapsedTime);
	
	//SELECT 3: Selection (Median)
	SelectionSort ss = new SelectionSort(resultArrLst.get(2));
	startTime = System.nanoTime();
	result = ss.find(k);
	stopTime = System.nanoTime();
	elapsedTime = stopTime - startTime;
	System.out.println("\tAlgorithm SELECT3: " + length + ", " + k + ", " + result + ", " + ss.compareCount);
	System.out.println();
	saveResult("ELECT 3: Selection (Median)", length, k, result, ss.compareCount, elapsedTime);
}

public static void saveResult(String algorithm, int nValue, int k, int kValue, int keyComps, long elapsedTime){
	String result = String.format("%-39s%-12d%-12d%-12d%-12d%-20d", algorithm, nValue, k, kValue, keyComps, elapsedTime);
	resultArrayLst.add(result);
}

public static void printResult(){
	for(int i = 0; i < resultArrayLst.size(); i++){
		String str = resultArrayLst.get(i);
		if(i > 1){
			String sn = String.format("%-4d", i-1);
			str = sn + str;
		}
		System.out.println(str);
	}
}
}