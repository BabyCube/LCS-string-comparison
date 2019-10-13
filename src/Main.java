import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub

			Scanner input = new Scanner(System.in); //the scanner for selecting the data
			System.out.println("Select the data type: 1 - low error, 2 - high error");
			int dataType = input.nextInt();
			System.out.println("Select the size of data: 1 - 10, 2 - 50, 3 - 100, 4 - 500, 5 - 1000");
			int dataSize = input.nextInt();
			System.out.println("Select the type of output: 1 - length of LCS, 2 - edit distance, 3 - LCS, 4 - optimal edit operations");
			int outputType = input.nextInt();

			
			Scanner dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorlow-l10.txt")); //the default data list
			Scanner sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorlow-l10.nsol.txt")); //the default source list
			if(dataType == 1 && dataSize == 1){
				dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorlow-l10.txt")); 
				sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorlow-l10.nsol.txt"));
			}else if(dataType == 1 && dataSize  == 2){
				dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorlow-l50.txt"));
				sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorlow-l50.nsol.txt"));
			}else if(dataType == 1 && dataSize == 3){
				dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorlow-l100.txt"));
				sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorlow-l100.nsol.txt"));
			}else if(dataType == 1 && dataSize == 4){
				dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorlow-l500.txt"));
				sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorlow-l500.nsol.txt"));
			}else if(dataType == 1 && dataSize == 5){
				dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorlow-l1000.txt"));
				sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorlow-l1000.nsol.txt"));
			}else if(dataType == 2 && dataSize == 1){
				dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorhigh-l10.txt"));
				sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorhigh-l10.nsol.txt"));
			}else if(dataType == 2 && dataSize  == 2){
				dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorhigh-l50.txt"));
				sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorhigh-l50.nsol.txt"));
			}else if(dataType == 2 && dataSize == 3){
				dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorhigh-l100.txt"));
				sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorhigh-l100.nsol.txt"));
			}else if(dataType == 2 && dataSize == 4){
				dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorhigh-l500.txt"));
				sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorhigh-l500.nsol.txt"));
			}else if(dataType == 2 && dataSize == 5){
				dataList = new Scanner(new File("CollectionSeqs/listSeqs-errorhigh-l1000.txt"));
				sourceList = new Scanner(new File("CollectionSeqs/listSeqs-consensustest-errorhigh-l1000.nsol.txt"));
			}
			
			if(outputType == 1){LCS_Length(dataList, sourceList);}
			if(outputType == 2){EditDistance(dataList, sourceList);}
			if(outputType == 3){LCS(dataList, sourceList);}
			if(outputType == 4){OptimalEdit(dataList, sourceList);}		
			
	}

	private static void OptimalEdit(Scanner dataList, Scanner sourceList){
		// TODO Auto-generated method stub
		String stringArray[] = new String[10];
		 stringArray[0] = dataList.next(); //load each string in the file into variables String in Java
		 stringArray[1] = dataList.next();
		 stringArray[2] = dataList.next();
		 stringArray[3] = dataList.next();
		 stringArray[4] = dataList.next();
		 stringArray[5] = dataList.next();
		 stringArray[6] = dataList.next();
		 stringArray[7] = dataList.next();
		 stringArray[8] = dataList.next();
		 stringArray[9] = dataList.next();
		String sourceArray[] = new String[10];
		sourceArray[0] = sourceList.next();
		sourceArray[1] = sourceList.next();
		sourceArray[2] = sourceList.next();
		sourceArray[3] = sourceList.next();
		sourceArray[4] = sourceList.next();
		sourceArray[5] = sourceList.next();
		sourceArray[6] = sourceList.next();
		sourceArray[7] = sourceList.next();
		sourceArray[8] = sourceList.next();
		sourceArray[9] = sourceList.next();
		 
		long startTimeH = System.currentTimeMillis(); //the time counter: record the starting time of the entire process
		 
		 for(int i = 1; i <= 10; i++){
			 
				 System.out.println("The edit distance for " + i + "th string comparing with the source: ");
				 long startTime = System.currentTimeMillis(); //the time counter: record the starting time
				 //notice that there is no need to do the initialization since the default for each cell is 0
				 String str1 = stringArray[i - 1]; //load the target string
				 String str2 = sourceArray[i - 1];
				 
				 
				 int trackingArray[][] = new int[str1.length() + 1][str2.length() + 1]; //create the dynamic programming table
				 char charArray1[] = new char[str1.length() + 1]; //load the each letter in the string into the array
				 char charArray2[] = new char[str2.length() + 1];
				 		 
				 for(int k = 1; k <= str1.length(); k++){
					 charArray1[k] = str1.charAt(k - 1); //the loading process
				 }
				 for(int k = 1; k <= str2.length(); k++){
					 charArray2[k] = str2.charAt(k - 1); //the loading process
				 }
				 
				 for(int p = 0; p <= str1.length(); p++){
					 trackingArray[p][0] = p; //the initialization process
				 }
				 for(int q = 0; q <= str2.length(); q++){
					 trackingArray[0][q] = q;
				 }
				 
				 for(int p = 1; p <= str1.length(); p++){ //the comparing process
					 for(int q = 1; q<= str2.length(); q++){
						 if(charArray1[p] == charArray2[q]){ //if two letters are the same, then the edit distance remain unchanged
							 trackingArray[p][q] = trackingArray[p-1][q-1];
							 }else{
								 trackingArray[p][q] = Math.min(1+trackingArray[p-1][q], Math.min(1+trackingArray[p][q-1], 1+trackingArray[p-1][q-1])); 
								 //Two letters are different, three possibilities: insert in x, delete in x, substitution
							 }
					 }
				 }
				 
				 //now the table has been created, the task is to find the minimum operation by tracing back the table
				 int currentLocation1 = str1.length();
				 int currentLocation2 = str2.length();
				 Stack<String> operationStack = new Stack<String>();
				 String operationString;
				 

				 
				 while(currentLocation1 > 0 && currentLocation2 > 0){
					 if(charArray1[currentLocation1] == charArray2[currentLocation2]){
						 
						 currentLocation1--; //decrement the location pointer. there is no change needed since it is the same
						 currentLocation2--;
						 continue;
						 
					 }else{ 
						 
						 //the case when two current characters are not equal, changes need to be made! Three cases!
						 char currentCharacter = charArray1[currentLocation1];
						 
						 if(trackingArray[currentLocation1 - 1][currentLocation2 - 1] <= trackingArray[currentLocation1][currentLocation2 - 1] &&
								 trackingArray[currentLocation1 - 1][currentLocation2 - 1] <= trackingArray[currentLocation1- 1][currentLocation2] ){
							 
							 operationString = "Substitute letter "+ charArray1[currentLocation1] + " at " + currentLocation1 + " with " + charArray2[currentLocation2];
							 operationStack.push(operationString);
							 currentLocation1--; //decrement the location pointer
							 currentLocation2--;
							 continue;
							 
						 }else if(trackingArray[currentLocation1][currentLocation2 - 1] <= trackingArray[currentLocation1 - 1][currentLocation2]){
							 
							 
							 operationString = "Insert letter " + charArray2[currentLocation2] + " after " + currentLocation1;
							 operationStack.push(operationString);
							 currentLocation2--;
							 continue;
							 
						 }else if(trackingArray[currentLocation1 - 1][currentLocation2] < trackingArray[currentLocation1][currentLocation2 - 1]){
							 
							 operationString = "Delete letter " + charArray1[currentLocation1] + " at " + currentLocation1;
							 operationStack.push(operationString);
							 currentLocation1--;
							 continue;
							 
						 }
					 }
				 }
				 
				 if(charArray1[1] != charArray2[1]){
					 operationString = "Substitute letter "+ charArray1[1] + " at " + currentLocation1 + " with " + charArray2[1];
					 operationStack.push(operationString);
				 }
				 
				 while(!operationStack.isEmpty()){
					 System.out.println(operationStack.pop());
				 }
				 
				 
				 System.out.println("The edit distance  " + trackingArray[str1.length()][str2.length()]);
				 long endTime = System.currentTimeMillis(); //the time counter: record the ending time
				 long timeRunning = endTime - startTime;
				 System.out.println("The running time is " + timeRunning + " ms " + "\n");
				 
		 }
		 
		 long endTimeH = System.currentTimeMillis(); //the time counter: record the ending time of the entire process
		 long timeRunningH = endTimeH - startTimeH;
		 System.out.println("The running time of Edit Distance Operation is " + timeRunningH + " ms ");
		
	}

	private static void LCS(Scanner dataList, Scanner sourceList) {
		// TODO Auto-generated method stub
		String stringArray[] = new String[10];
		 stringArray[0] = dataList.next(); //load each string in the file into variables String in Java
		 stringArray[1] = dataList.next();
		 stringArray[2] = dataList.next();
		 stringArray[3] = dataList.next();
		 stringArray[4] = dataList.next();
		 stringArray[5] = dataList.next();
		 stringArray[6] = dataList.next();
		 stringArray[7] = dataList.next();
		 stringArray[8] = dataList.next();
		 stringArray[9] = dataList.next();
		String sourceArray[] = new String[10];
		sourceArray[0] = sourceList.next();
		sourceArray[1] = sourceList.next();
		sourceArray[2] = sourceList.next();
		sourceArray[3] = sourceList.next();
		sourceArray[4] = sourceList.next();
		sourceArray[5] = sourceList.next();
		sourceArray[6] = sourceList.next();
		sourceArray[7] = sourceList.next();
		sourceArray[8] = sourceList.next();
		sourceArray[9] = sourceList.next();
		
		 
		 long startTimeH = System.currentTimeMillis(); //the time counter: record the starting time of the entire process
		 
		 for(int i = 1; i <= 10; i++){
			 
				 System.out.print("The LCS_length for " + i + "th string comparing with the source: ");
				 long startTime = System.currentTimeMillis(); //the time counter: record the starting time
				 //notice that there is no need to do the initialization since the default for each cell is 0
				 String str1 = stringArray[i - 1]; //load the target string
				 String str2 = sourceArray[i - 1];
				 	 
				 
				 int trackingArray[][] = new int[str1.length() + 1][str2.length() + 1]; //create the dynamic programming table
				 char charArray1[] = new char[str1.length() + 1]; //load the each letter in the string into the array
				 char charArray2[] = new char[str2.length() + 1];
				 		 
				 for(int k = 1; k <= str1.length(); k++){
					 charArray1[k] = str1.charAt(k - 1); //the loading process
				 }
				 for(int k = 1; k <= str2.length(); k++){
					 charArray2[k] = str2.charAt(k - 1); //the loading process
				 }
				 
				 for(int p = 1; p <= str1.length(); p++){ //the comparing process
					 for(int q = 1; q<= str2.length(); q++){
						 if(charArray1[p] == charArray2[q]){ //if two letters are the same, then 1 + LCS(p-1)(q-1)
							 trackingArray[p][q] = 1 + trackingArray[p-1][q-1];
							 }else{
								 trackingArray[p][q] = Math.max(trackingArray[p-1][q], trackingArray[p][q-1]); //Two letters are different, take the max between LCS(p-1)(q) and LCS(p)(q-1)
							 }
					 }
				 }
				 
				 //now the table has been constructed, need to trace back to get LCS
				 String LCS = ""; //LCS string initialization
				 int currentLocation1 = str1.length();
				 int currentLocation2 = str2.length();
				 
				 while(currentLocation1 > 0 && currentLocation2 > 0){
					 if(charArray1[currentLocation1] == charArray2[currentLocation2]){
						 char currentChar = charArray1[currentLocation1];
						 LCS = Character.toString(currentChar) + LCS;
						 
						 currentLocation1--; //decrement the location pointer
						 currentLocation2--;
						 continue;
					 }else{ //the case when two current characters are not equal
						 if(trackingArray[currentLocation1 - 1][currentLocation2] < trackingArray[currentLocation1][currentLocation2 - 1]){
							 currentLocation2--; 
							 continue;
						 }else{
							 currentLocation1--;
							 continue;
						 }
					 }
				 }
				 
				 System.out.println("The LCS in the current selected Strings is " + LCS);
				 long endTime = System.currentTimeMillis(); //the time counter: record the ending time
				 long timeRunning = endTime - startTime;
				 System.out.println("The running time is " + timeRunning + "ms");
				 
			 }
		 
		 long endTimeH = System.currentTimeMillis(); //the time counter: record the ending time of the entire process
		 long timeRunningH = endTimeH - startTimeH;
		 System.out.println("The running time of LCS is " + timeRunningH + "ms");
	}

	private static void EditDistance(Scanner dataList, Scanner sourceList) {
		// TODO Auto-generated method stub
		String stringArray[] = new String[10];
		 stringArray[0] = dataList.next(); //load each string in the file into variables String in Java
		 stringArray[1] = dataList.next();
		 stringArray[2] = dataList.next();
		 stringArray[3] = dataList.next();
		 stringArray[4] = dataList.next();
		 stringArray[5] = dataList.next();
		 stringArray[6] = dataList.next();
		 stringArray[7] = dataList.next();
		 stringArray[8] = dataList.next();
		 stringArray[9] = dataList.next();
		String sourceArray[] = new String[10];
		sourceArray[0] = sourceList.next();
		sourceArray[1] = sourceList.next();
		sourceArray[2] = sourceList.next();
		sourceArray[3] = sourceList.next();
		sourceArray[4] = sourceList.next();
		sourceArray[5] = sourceList.next();
		sourceArray[6] = sourceList.next();
		sourceArray[7] = sourceList.next();
		sourceArray[8] = sourceList.next();
		sourceArray[9] = sourceList.next();
		
 long startTimeH = System.currentTimeMillis(); //the time counter: record the starting time of the entire process
		 
		 for(int i = 1; i <= 10; i++){
			 
				 System.out.print("The edit distance for " + i + "th string comparing with the source: ");
				 long startTime = System.currentTimeMillis(); //the time counter: record the starting time
				 //notice that there is no need to do the initialization since the default for each cell is 0
				 String str1 = stringArray[i - 1]; //load the target string
				 String str2 = sourceArray[i - 1];
				 
				 
				 int trackingArray[][] = new int[str1.length() + 1][str2.length() + 1]; //create the dynamic programming table
				 char charArray1[] = new char[str1.length() + 1]; //load the each letter in the string into the array
				 char charArray2[] = new char[str2.length() + 1];
				 		 
				 for(int k = 1; k <= str1.length(); k++){
					 charArray1[k] = str1.charAt(k - 1); //the loading process
				 }
				 for(int k = 1; k <= str2.length(); k++){
					 charArray2[k] = str2.charAt(k - 1); //the loading process
				 }
				 
				 for(int p = 0; p <= str1.length(); p++){
					 trackingArray[p][0] = p; //the initialization process
				 }
				 for(int q = 0; q <= str2.length(); q++){
					 trackingArray[0][q] = q;
				 }
				 
				 for(int p = 1; p <= str1.length(); p++){ //the comparing process
					 for(int q = 1; q<= str2.length(); q++){
						 if(charArray1[p] == charArray2[q]){ //if two letters are the same, then the edit distance remain unchanged
							 trackingArray[p][q] = trackingArray[p-1][q-1];
							 }else{
								 trackingArray[p][q] = Math.min(1+trackingArray[p-1][q], Math.min(1+trackingArray[p][q-1], 1+trackingArray[p-1][q-1])); 
								 //Two letters are different, three possibilities: insert in x, delete in x, substitution
							 }
					 }
				 }
				 
				 System.out.println("The edit distance  " + trackingArray[str1.length()][str2.length()]);
				 long endTime = System.currentTimeMillis(); //the time counter: record the ending time
				 long timeRunning = endTime - startTime;
				 System.out.println("The running time is " + timeRunning + "ms");
				 
		 }
		 
		 long endTimeH = System.currentTimeMillis(); //the time counter: record the ending time of the entire process
		 long timeRunningH = endTimeH - startTimeH;
		 System.out.println("The running time of finding Edit Distance is " + timeRunningH + "ms");
		
	}
	
	private static void LCS_Length(Scanner dataList, Scanner sourceList){
		// TODO Auto-generated method stub
		String stringArray[] = new String[10];
		 stringArray[0] = dataList.next(); //load each string in the file into variables String in Java
		 stringArray[1] = dataList.next();
		 stringArray[2] = dataList.next();
		 stringArray[3] = dataList.next();
		 stringArray[4] = dataList.next();
		 stringArray[5] = dataList.next();
		 stringArray[6] = dataList.next();
		 stringArray[7] = dataList.next();
		 stringArray[8] = dataList.next();
		 stringArray[9] = dataList.next();
		String sourceArray[] = new String[10];
		sourceArray[0] = sourceList.next();
		sourceArray[1] = sourceList.next();
		sourceArray[2] = sourceList.next();
		sourceArray[3] = sourceList.next();
		sourceArray[4] = sourceList.next();
		sourceArray[5] = sourceList.next();
		sourceArray[6] = sourceList.next();
		sourceArray[7] = sourceList.next();
		sourceArray[8] = sourceList.next();
		sourceArray[9] = sourceList.next();
		
		 
		 long startTimeH = System.currentTimeMillis(); //the time counter: record the starting time of the entire process
		 
		 for(int i = 1; i <= 10; i++){
			 
				 System.out.print("The LCS_length for " + i + "th string comparing with the source: ");
				 long startTime = System.currentTimeMillis(); //the time counter: record the starting time
				 //notice that there is no need to do the initialization since the default for each cell is 0
				 String str1 = stringArray[i - 1]; //load the target string
				 String str2 = sourceArray[i - 1];
				 
				 
				 int trackingArray[][] = new int[str1.length() + 1][str2.length() + 1]; //create the dynamic programming table
				 char charArray1[] = new char[str1.length() + 1]; //load the each letter in the string into the array
				 char charArray2[] = new char[str2.length() + 1];
				 		 
				 for(int k = 1; k <= str1.length(); k++){
					 charArray1[k] = str1.charAt(k - 1); //the loading process
				 }
				 for(int k = 1; k <= str2.length(); k++){
					 charArray2[k] = str2.charAt(k - 1); //the loading process
				 }
				 
				 for(int p = 1; p <= str1.length(); p++){ //the comparing process
					 for(int q = 1; q<= str2.length(); q++){
						 if(charArray1[p] == charArray2[q]){ //if two letters are the same, then 1 + LCS(p-1)(q-1)
							 trackingArray[p][q] = 1 + trackingArray[p-1][q-1];
							 }else{
								 trackingArray[p][q] = Math.max(trackingArray[p-1][q], trackingArray[p][q-1]); //Two letters are different, take the max between LCS(p-1)(q) and LCS(p)(q-1)
							 }
					 }
				 }
				 
				 System.out.println("The length of LCS  " + trackingArray[str1.length()][str2.length()]);
				 long endTime = System.currentTimeMillis(); //the time counter: record the ending time
				 long timeRunning = endTime - startTime;
				 System.out.println("The running time is " + timeRunning + "ms");
				 
		 }
		 
		 long endTimeH = System.currentTimeMillis(); //the time counter: record the ending time of the entire process
		 long timeRunningH = endTimeH - startTimeH;
		 System.out.println("The running time of finding the length of LCS is " + timeRunningH + "ms");
	}
	
	

}
