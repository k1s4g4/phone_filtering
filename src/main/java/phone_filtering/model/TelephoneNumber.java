package phone_filtering.model;

import java.util.ArrayList;
import java.util.List;

import phone_filtering.model.tree.AmbiguityTree;
import phone_filtering.tools.Separator;

public class TelephoneNumber {
	private AmbiguityTree ambiguities;
	
	public TelephoneNumber(String input) {
		ambiguities=new AmbiguityTree(Separator.inputToParts(Separator.separateZeros(input)));
	}

	public AmbiguityTree getAmbiguities() {
		return ambiguities;
	}

	public void setAmbiguities(AmbiguityTree ambiguities) {
		this.ambiguities = ambiguities;
	}
	
	public List<PossibleNumber> getNumberList(){
		List<PossibleNumber> numbers=new ArrayList<>();
		List<String> list=ambiguities.getPathList();
		for(String s:list) {
			numbers.add(new PossibleNumber(s));
		}
		return numbers;
	}

}
