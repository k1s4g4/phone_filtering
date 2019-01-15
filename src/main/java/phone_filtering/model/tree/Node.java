package phone_filtering.model.tree;

import java.util.ArrayList;
import java.util.List;

public class Node<T> {
	 //every Node has an Object as data
	 // in this case String is selected as the only field that needs to be combined
	 private T data = null;
	 
	 //every Node has children of type Node. self-reference  
	 private List<Node<T>> children = new ArrayList<>();
	 
	 
	 public Node(T data) {
		 this.data = data;
	 }
	 
	 public Node<T> addChild(Node<T> child) {
		 this.children.add(child);
		 return child;
	 }
	 
	 public void addChildren(List<Node<T>> children) {
		 this.children.addAll(children);
	 }
	 
	 public List<Node<T>> getChildren() {
		 return children;
	 }
	 
	 public T getData() {
		 return data;
	 }
	 
	 public void setData(T data) {
		 this.data = data;
	 }
	 
	 public String toString() {
		 return data.toString();
	 }
	}
