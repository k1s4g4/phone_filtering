package phone_filtering.tools;

import java.util.List;

import phone_filtering.model.PossibleNumber;

public class Output {
	
	/*This class was made as a component of a console application
	 *Its purpose is to print tables of data with the right horizontal alignment 
	 * */	
	
	private static final String MY_TAB="    ";
	
	public static void printPossibleNumbers(List<PossibleNumber> possibleNumbers) {
		printTable(new String[] {"","",""},listToTable(possibleNumbers));
	}
	
	private static void printTable(String[] headers,String[][] table) {
		int[] maximums=new int[headers.length];
		for(int i=0; i< headers.length ;i++) {
			maximums[i]=headers[i].length();
			for(int j=0;j<table.length;j++) {
				if(maximums[i]<table[j][i].length()) {
					maximums[i]=table[j][i].length();
				}	
			}
		}
		//Print headers line
		String headersLine="";
		for(int j=0;j<headers.length;j++) {
			
				String spaces="";
				for(int k=0;k<maximums[j]-headers[j].length()-9;k++) {
					spaces+=" ";
				}
				headersLine+=headers[j]+spaces+MY_TAB;	
			
		}
		System.out.println(headersLine);
		//Print table lines
		for(int i=0;i<table.length;i++) {
			
			String line="";
			for(int j=0;j<headers.length;j++) {
				if(j==headers.length-1) {
					line+=table[i][j];
				}else {
					String spaces="";
					for(int k=0;k<maximums[j]-table[i][j].length();k++) {
						spaces+=" ";
					}
					line+=table[i][j]+spaces+MY_TAB;	
					
				}
			}
			System.out.print("     ");
			System.out.println(line);
			
		}
		System.out.println("");
	}
	
	private static String[][] listToTable(List<PossibleNumber> possibleNumbers){
		String[][] table=new String[possibleNumbers.size()][3];
		for(int i=0;i<possibleNumbers.size();i++) {
			table[i][0]="Interpretation "+(i+1)+":";
			table[i][1]=possibleNumbers.get(i).getValue();
			table[i][2]="[phone number:"+(possibleNumbers.get(i).isValid()?"VALID":"INVALID")+"]";
		}
		return table;
	}
	
	
}
