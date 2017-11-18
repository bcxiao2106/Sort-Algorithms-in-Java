import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SortIII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scnr = new Scanner(System.in);
		System.out.println("==== LSD (Least-Significant-Digit-First) radixsort. CS610 bx34 ====\n");
		
		System.out.println("Please specify the input file (default = f.txt):");
		String inputFileName = scnr.nextLine();
		if(inputFileName == null || inputFileName.equals("")){
			inputFileName = "f.txt";
		}
		
		System.out.println("Please specify the output file (default = g.txt):");
		String outputFileName = scnr.nextLine();
		if(outputFileName == null || outputFileName.equals("")){
			outputFileName = "g.txt";
		}
		
		char[][] charArr = FileProcessor.readFile(inputFileName);
		if(charArr == null){
			return;
		}
		System.out.println("\t[INFO]: Total number of strings: " + charArr.length);
		RadixSort rs = new RadixSort(charArr, new int[charArr.length]);
		rs.sort();
		rs.saveToFile(outputFileName);
		
	}
}
