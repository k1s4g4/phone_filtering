package phone_filtering.model;

import phone_filtering.tools.Validator;

//This class is used only for holding the possible telephone values and validate each one
public class PossibleNumber {
	private String value;
	private boolean valid;
	
	public PossibleNumber(String value) {
		this.value=value;
		valid=Validator.numberIsValid(value);
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
