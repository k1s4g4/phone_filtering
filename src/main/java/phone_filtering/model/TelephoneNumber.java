package phone_filtering.model;

import java.util.ArrayList;
import java.util.List;

import phone_filtering.model.tree.AmbiguityTree;
import phone_filtering.tools.Separator;

public class TelephoneNumber {
	private AmbiguityTree ambiguityTree;  // all the ambiguities are saved in a tree
	private List<PossibleNumber> possibleNumbers; // the possible numbers are extracted as paths of the tree
	
	public TelephoneNumber(String input) {
		ambiguityTree=new AmbiguityTree(Separator.separate(input));
		generatePossibleNumbers();
	}
	
	// in this method all paths of the ambiguity tree are extracted and transformed into possible numbers
	private void generatePossibleNumbers() {
		List<String> list=ambiguityTree.getPathList();
		possibleNumbers=new ArrayList<>();
		for(String s:list) {
			possibleNumbers.add(new PossibleNumber(s));
		}
	}

	public AmbiguityTree getAmbiguityTree() {
		return ambiguityTree;
	}

	public void setAmbiguityTree(AmbiguityTree ambiguities) {
		this.ambiguityTree = ambiguities;
	}
	
	public List<PossibleNumber> getPossibleNumbers() {
		return possibleNumbers;
	}

	public void setPossibleNumbers(List<PossibleNumber> possibleNumbers) {
		this.possibleNumbers = possibleNumbers;
	}

	
	
}
