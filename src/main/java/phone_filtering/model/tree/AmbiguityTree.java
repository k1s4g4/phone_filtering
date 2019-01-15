package phone_filtering.model.tree;

import java.util.ArrayList;
import java.util.List;

import phone_filtering.model.tree.tools.AmbiguityHandler;
import phone_filtering.model.tree.tools.TreeTools;

public class AmbiguityTree {
	
	// classic tree data structure implementation.
	// a tree has its root node and every node has its children 
	private Node<String> root;
	
	// paths are saved in a member variable and calculated once
	// instead of calculating them every time is needed
	private List<List<Node<String>>> paths;
	
	public AmbiguityTree(List<String> parts) {
		root=new Node<String>("");
		buildTree(parts);
		paths=TreeTools.getPaths(root);
	}
	
	// here the structure of the tree is created 
    // 'parts' variable is processed and a layer is added to the tree
	// and the parts that were used to create every layer are removed from the list
	// when the list is empty the tree is ready
	private void buildTree(List<String> parts) {
		@SuppressWarnings("unused")
		int partsLength;
		while((partsLength=parts.size())>0) {
			List<Node<String>> children=new ArrayList<>();
			children=AmbiguityHandler.handle(parts);
			TreeTools.addToLeafs(root, children);
		}
	}
	
	// all nodes are merged into one String for every path
	public List<String> getPathList() {
		return TreeTools.pathsToList(paths);
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



