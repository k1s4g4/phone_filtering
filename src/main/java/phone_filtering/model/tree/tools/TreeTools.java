package phone_filtering.model.tree.tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import phone_filtering.model.tree.Node;

public class TreeTools {
	// to get the paths of the tree recursion is used
	//this method calls the method below and a list of lists starts from the leafs
	//and in every list in list of lists every parent of every leaf is added to the first position
	public static List<List<Node<String>>> getPaths(Node<String> head) {
        if(head == null) {
            return new ArrayList<>();
        } else {
            return getPaths0(head);
        }
    }
    private static List<List<Node<String>>> getPaths0(Node<String> pos) {
        List<List<Node<String>>> retLists = new ArrayList<>();

        if(pos.getChildren().size() == 0) {
            List<Node<String>> leafList = new LinkedList<>();
            leafList.add(pos);
            retLists.add(leafList);
        } else {
            for (Node<String> node : pos.getChildren()) {
                List<List<Node<String>>> nodeLists = getPaths0(node);
                for (List<Node<String>> nodeList : nodeLists) {
                    nodeList.add(0, pos);
                    retLists.add(nodeList);
                }
            }
        }

        return retLists;
    }

    //This method adds some children to every leaf of the tree
    public static void addToLeafs(Node<String> node,List<Node<String>> children) {
    	if(node.getChildren().isEmpty()) {
    		List<Node<String>> copyOfChildren=new ArrayList<>();
        	for(Node<String> child:children) {
        		copyOfChildren.add(new Node<String>(child.getData()));
        	}
    		node.addChildren(copyOfChildren);
    	}else {
    		
    		for(Node<String> nodeChild:node.getChildren()) {
    			addToLeafs(nodeChild,children);
    		}
    	}
    }
    
    // Collects strings of all nodes in every path to one list of strings
    // those strings are the numbers with all the ambiguities
    public static List<String> pathsToList(List<List<Node<String>>> paths) {
    	List<String> stringPaths=new ArrayList<>();
    	for(List<Node<String>> path:paths) {
    		StringBuilder temp=new StringBuilder();
    		for(Node<String> node:path) {
    			temp.append(node.getData());
    		}
    		stringPaths.add(temp.toString());
    	}
    	return stringPaths;
    }
}
