package phone_filtering.model.tree.tools;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import phone_filtering.model.tree.Node;

public class TreePaths {
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
}
