package phone_filtering.tools;

public class Validator {
	
	private static final String VALID_INPUT_REGEX="(\\d{1,3}\\s)*\\d{1,3}\\s?";
	
	// Checks if input is some sets of 1-3 digits separated by one space character
	public static boolean inputIsValid(String input) {
		if(input.matches(VALID_INPUT_REGEX)) {
			return true;
		}
		return false;
	}
	
	
	//checks if number has 10 or 14 digits and if yes checks if numbers is starting with
	// to find either 2 or 69 and if yes number is valid
	public static boolean numberIsValid(String number) {
			boolean valid=false;
			if(number.length()==14) {
				if(number.substring(0, 4).equals("0030")){
					if(number.substring(4,5).equals("2") || number.substring(4,6).equals("69")) {
						valid=true;
					}
				}
			}else if(number.length()==10) {
				if(number.substring(0,1).equals("2") || number.substring(0,2).equals("69")) {
					valid=true;
				}
			}
			return valid;
	}
}
