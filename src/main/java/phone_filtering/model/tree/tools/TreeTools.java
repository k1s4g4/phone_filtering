package phone_filtering.model.tree.tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


import phone_filtering.model.tree.Node;

public class TreeTools {
	
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

    public static List<List<Node<String>>> getPaths(Node<String> head) {
        if(head == null) {
            return new ArrayList<>();
        } else {
            return getPaths0(head);
        }
    }
    
    
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
