package phone_filtering.tools;

import java.util.ArrayList;
import java.util.List;

public class Separator {
	
	/*This class transforms the input from the initial state to 
	to the form that is needed for building the ambiguity tree.
	
	 1. All zeros that is at first place of 2 or 3 digit number
		are separated from the number with a space
	 2.	After that the string become a List with all the numbers but no spaces
	 */
	
	public static List<String> separate(String input) {
		return Separator.inputToParts(Separator.separateZeros(input));
	}
	
	// Check for existence of zero at hundreds or decades position 
	// and separates zero with the rest of the number with a space
	public static String separateZeros(String input) {
		StringBuilder separated=new StringBuilder();
		String[] array=input.split("\\s");
		for(String str:array) {
			if(str.charAt(0)=='0' && str.length()==3) {
				String first=str.substring(0,1);
				String rest=str.substring(1,str.length());
				separated.append(first+" ");
				if(rest.charAt(0)=='0') {
					separated.append(rest.substring(0, 1)+" "+rest.substring(1,rest.length())+" ");
				}else {
					separated.append(rest+" ");
				}
			}else if(str.charAt(0)=='0' && str.length()==2){
				separated.append(str.substring(0,1)+" "+str.substring(1,str.length())+" ");
			}else{
				separated.append(str+" ");
			}
		}
		return separated.toString().trim();
		
	}
	
	// Takes the "zero"-separated input and gives back a List of Strings
	// by cutting input everywhere is space character
	public static List<String> inputToParts(String input){
		String[] array=input.split("\\s");
		List<String> parts=new ArrayList<String>();
		for(String part:array) {
			parts.add(part);
		}
		return parts;
	}
}
