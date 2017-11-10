import java.util.ArrayList;

public class Sort {
	
	private static double timeComplexity32 = 32 * Math.log(32);
	private static double timeComplexity1024 = 1024 * Math.log(1024);
	private static double timeComplexity32k = 32768 * Math.log(32768);
	private static double timeComplexity1m = Math.pow(2, 20) * Math.log(Math.pow(2, 20));
	private static ArrayList<String> resultArrayLst;
/*
	public static void main(String[] args) {
		/*
		 * FUNCTION: executeSort(int length, char arrayType, boolean printOriginalArray, boolean printResult)
		1. length: integer, the length of the array; 
		2. arrayType['R': Randomly Generated] ['S': Sorted] ['V': Reversely Sorted]
		3. printOriginalArray: [ture: print the original array] [false: not to print the original array]
		4. printResult: [ture: print the result] [false: not to print the result]
		* *
		*/
		/*
		resultArrayLst = new ArrayList<String>();
		String result = String.format("%-12s%-8s%-26s%-18s%-18s%-18s%-15s", "ALGORITHM", "N_VALUE", "ARRAY_TYPE", "COMPARISON_COUNT", "RUNNING_TIME(ns)", "nlog(n)", "CONSTANT_FACTOR");
		resultArrayLst.add(result);
		System.out.println("============ n=32, Random Generated Order ============");
		executeSort(32, 'R', true, true);
		
		System.out.println("============ n=32, Sorted ============");
		executeSort(32, 'S', true, true);
		
		System.out.println("============ n=32, Reversely Sorted ============");
		executeSort(32, 'V', true, true);
		
		System.out.println("============ n=2^10, Random Generated Order ============");
		executeSort(1024, 'R', false, false);
		
		System.out.println("============ n=2^15, Random Generated Order ============");
		executeSort(32768, 'R', false, false);
		
		System.out.println("============ n=2^20, Random Generated Order ============");
		executeSort(1048576, 'R', false, false);
		
		printResult();
		
		int[] intArr = {-6,-5,-3,2,4,5,7,8};
		BinarySearch bs = new BinarySearch(intArr);
		System.out.println(bs.search(0, intArr.length, 2));
		bs.print();
		
		String aa= "abc.htm";
		System.out.println(aa.split("\\.")[0]);
	}
*/	
	/*FUNCTION: executeSort(int length, char arrayType, boolean printOriginalArray, boolean printResult)
		1. length: integer, the length of the array; 
		2. arrayType['R': Randomly Generated] ['S': Sorted] ['V': Reversely Sorted]
		3. printOriginalArray: [ture: print the original array] [false: not to print the original array]
		4. printResult: [ture: print the result] [false: not to print the result]
	 * *
	 */
	public static void executeSort(int length, char arrayType, boolean printOriginalArray, boolean printResult){
		double timeComplexity = 0.0;
		if(length == 32){
			timeComplexity = timeComplexity32;
		} else if (length == 1024){
			timeComplexity = timeComplexity1024;
		} else if(length == 32768){
			timeComplexity = timeComplexity32k;
		} else if(length == 1048576){
			timeComplexity = timeComplexity1m;
		}
		String arrTypeTxt = "";
		if(arrayType == 'R') arrTypeTxt = "Random Generated Order";
		if(arrayType == 'S') arrTypeTxt = "Sorted";
		if(arrayType == 'V') arrTypeTxt = "Reversely Sorted";
		ArrayList<int[]> resultArrLst = SortArray.generateArray(1, length * 2, length, 3, arrayType, printOriginalArray);
		
		//Merge Sort
		MergeSort ms = new MergeSort(resultArrLst.get(0));
		long startTime = System.nanoTime();
		ms.sort();
		long stopTime = System.nanoTime();
		System.out.println("MERGE_SORT(n="+length+" "+arrTypeTxt+")\n\t- Total number of COMPAREs: " + ms.compareCounts());
		if(printResult) {
			System.out.print("\t- Result: ");
			ms.print();
		}
		long elapsedTime = stopTime - startTime;
		String constantFactor = String.format("%.2f", ms.compareCounts()/timeComplexity);
		System.out.println("\t- Total Elapsed time(ns): " + elapsedTime);
		System.out.println("\t- Constant Factor: " + constantFactor);
		saveResult("Merge Sort", length, arrTypeTxt, ms.compareCounts(), elapsedTime, timeComplexity, constantFactor);
		
		//Quick Sort
		QSort qs = new QSort(resultArrLst.get(1));
		startTime = System.nanoTime();
		qs.sort();
		stopTime = System.nanoTime();
		System.out.println("QUICK_SORT(n="+length+" "+arrTypeTxt+")\n\t- Total number of COMPAREs: " + qs.compareCounts());
		if(printResult) {
			System.out.print("\t- Result: ");
			qs.print();
		}
		elapsedTime = stopTime - startTime;
		constantFactor = String.format("%.2f", qs.compareCounts()/timeComplexity);
		System.out.println("\t- Total Elapsed time(ns): " + elapsedTime);
		System.out.println("\t- Constant Factor: " + constantFactor);
		saveResult("Quick Sort", length, arrTypeTxt, qs.compareCounts(), elapsedTime, timeComplexity, constantFactor);
	
		//Heap Sort
		HeapSort hs = new HeapSort(resultArrLst.get(2));
		startTime = System.nanoTime();
		hs.build();
		stopTime = System.nanoTime();
		System.out.println("HEAP_SORT(n="+length+" "+arrTypeTxt+")\n\t- Total number of COMPAREs: " + hs.compareCounts());
		if(printResult) {
			System.out.print("\t- Result: ");
			hs.print();
		}
		elapsedTime = stopTime - startTime;
		constantFactor = String.format("%.2f", hs.compareCounts()/timeComplexity);
		System.out.println("\t- Total Elapsed time(ns): " + elapsedTime);
		System.out.println("\t- Constant Factor: " + constantFactor);
		saveResult("Heap Sort", length, arrTypeTxt, hs.compareCounts(), elapsedTime, timeComplexity, constantFactor);
		System.out.println();
	}
	
	public static void saveResult(String algorithm, int nValue, String arrTypeTxt, int compCounts, long elapsedTime, double timeComplexity, String constantFactor){
		String result = String.format("%-12s%-8d%-26s%-18d%-18d%-18f%-15s", algorithm, nValue, arrTypeTxt, compCounts, elapsedTime, timeComplexity, constantFactor);
		//System.out.println(result);
		resultArrayLst.add(result);
	}
	
	public static void printResult(){
		for(int i = 0; i < resultArrayLst.size(); i++){
			System.out.println(resultArrayLst.get(i));
		}
	}
}