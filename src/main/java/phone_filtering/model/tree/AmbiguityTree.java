package phone_filtering.model.tree;

import java.util.List;

import phone_filtering.model.tree.tools.TreePaths;

public class AmbiguityTree {
	private Node<String> root;
	private List<List<Node<String>>> paths;
	
	
	public AmbiguityTree(List<String> parts) {
		//generate tree from parts
		buildTree(parts);
		paths=TreePaths.getPaths(root);
	}
	
	private void buildTree(List<String> parts) {
		int partLength;
		while((partLength=parts.get(0).length())>0) {
			if(partLength==1) {
				//addToTreeOneNode
				
				//deleteThisPartFromList
			}else if(partLength==2){
				//AddToTreeSomeNodes
				//delete whatever is needed from parts
			}else {
				//AddToTreeSomeNodes
				//delete whatever is needed from parts
			}
		}
	}
	
	
	private void addToLeafs(List<Node> children) {
		
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



