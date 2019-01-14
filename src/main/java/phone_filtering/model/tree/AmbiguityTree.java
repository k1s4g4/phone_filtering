package phone_filtering.model.tree;

import java.util.ArrayList;
import java.util.List;

import phone_filtering.model.tree.tools.AmbiguityHandler;
import phone_filtering.model.tree.tools.TreeTools;

public class AmbiguityTree {
	private Node<String> root;
	private List<List<Node<String>>> paths;
	
	public AmbiguityTree(List<String> parts) {
		//generate tree from parts
		root=new Node<String>("");
		buildTree(parts);
		paths=TreeTools.getPaths(root);
	}
	
	private void buildTree(List<String> parts) {
		@SuppressWarnings("unused")
		int partsLength;
		while((partsLength=parts.size())>0) {
			String currentPart=parts.get(0);
			int partLength=currentPart.length();
			List<Node<String>> children=new ArrayList<>();
			if(partLength==1) {
				children=AmbiguityHandler.oneDigit(parts);
			}else if(partLength==2){
				children=AmbiguityHandler.twoDigit(parts);
			}else {
				children=AmbiguityHandler.threeDigit(parts);
			}
			TreeTools.addToLeafs(root, children);
		}
	}
	
	public Node<String> getRoot() {
		return root;
	}

	public void setRoot(Node<String> root) {
		this.root = root;
	}

	public List<List<Node<String>>> getPaths() {
		return paths;
	}

	public void setPaths(List<List<Node<String>>> paths) {
		this.paths = paths;
	}
	
	
}



