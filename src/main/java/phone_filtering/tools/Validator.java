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
}
