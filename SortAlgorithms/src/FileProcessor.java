import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileProcessor {

	public static char[][] readFile(String fileName){
		
		char[][] charArr = new char[1000][21];
		char[][] charArrResult = null;
       
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            int lineCount = 0;
            while((line = bufferedReader.readLine()) != null) {
            	if(lineCount >= 1000){
            		System.out.println("\t[ERROR]: The total lines of the input file can not be more than 1000.");
            		return null;
            		//break;
            	}
                if(line.length() > 21){
                	System.out.println("\t[ERROR]: The length of "+ line + " is bigger than 21.");
                	return null;
                	//break;
                }
                if(line.length() < 21){
                	String spaces = "";
                	for(int i = 0; i <= 21 - line.length(); i++){
                		spaces += ' ';
                	}
                	line += spaces;
                }
                charArr[lineCount] = line.toCharArray();
                //System.out.println(charArr[lineCount]);
                lineCount++;
            }   

            // Always close files.
            bufferedReader.close();  
            charArrResult = new char[lineCount][21];
            for(int i = 0; i < lineCount; i++){
            	charArrResult[i] = charArr[i];
            }
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        return charArrResult;
	}
	
	public static void writeToFile(int[] idxArr, char[][] charArr, String fileName){
		try {
            // Assume default encoding.
            FileWriter fileWriter =
                new FileWriter(fileName);

            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            // Note that write() does not automatically
            // append a newline character.
            for(int i = 0; i < idxArr.length; i++){
            	if(charArr[idxArr[i]] == null) break;
            	bufferedWriter.write(charArr[idxArr[i]]);
            	if(i < idxArr.length - 1){//check if it is the last line
            		bufferedWriter.newLine();
            	}
            }

            // Always close files.
            bufferedWriter.close();
            System.out.println("\t[INFO]: Result saved : " + fileName);
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
        }
	}
}
