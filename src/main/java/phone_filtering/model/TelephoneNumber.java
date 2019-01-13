package phone_filtering.model;

import phone_filtering.model.tree.AmbiguityTree;

public class TelephoneNumber {
	private AmbiguityTree ambiguities;
	private String input;
	
	public TelephoneNumber(String input) {
		this.input=input;
	}

	public AmbiguityTree getAmbiguities() {
		return ambiguities;
	}

	public void setAmbiguities(AmbiguityTree ambiguities) {
		this.ambiguities = ambiguities;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}
	

}
