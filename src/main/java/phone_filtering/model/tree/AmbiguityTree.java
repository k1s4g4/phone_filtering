package phone_filtering.model.tree;

import java.util.ArrayList;
import java.util.List;

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
		int partsLength;
		while((partsLength=parts.size())>0) {
			String currentPart=parts.get(0);
			int partLength=currentPart.length();
			List<Node<String>> children=new ArrayList<>();
			
			if(partLength==1) {
				children.add(new Node<String>(currentPart));
				parts.remove(0);
			}else if(partLength==2){
				
				if(currentPart.charAt(1)=='0') {
					if(partsLength>1) {
						String nextPart=parts.get(1);
						if(nextPart.length()>1) {
							children.add(new Node<String>(currentPart));
							parts.remove(0);
						}else {
							if(currentPart.equals("10") && nextPart.equals("1") || nextPart.equals("2")) {
								children.add(new Node<String>(currentPart));
								parts.remove(0);
							}else {
								if(nextPart.equals("0")) {
									children.add(new Node<String>(currentPart));
									parts.remove(0);
								}else {
									children.add(new Node<String>(currentPart+""+nextPart));
									children.add(new Node<String>(currentPart.substring(0,1)+nextPart));
									parts.remove(0);
									parts.remove(0);
								}
								
							}
						}
					}else {
						children.add(new Node<String>(currentPart));
					}
				}else {
					if(currentPart.equals("11") || currentPart.equals("12")) {
						children.add(new Node<String>(currentPart));
						parts.remove(0);
					}else {
						children.add(new Node<String>(currentPart.substring(0, 1)+"0"+currentPart.substring(1,2)));
						children.add(new Node<String>(currentPart));
						parts.remove(0);
					}
				}
			}else {
				String hundreds=currentPart.substring(0,1);
				String tens=currentPart.substring(1,2);
				String units=currentPart.substring(2,3);
				if(units.equals("0")) {
					if(tens.equals("0")) {
						if(partsLength>1) {
							String nextPart=parts.get(1);
							if(nextPart.length()>2) {
								children.add(new Node<String>(currentPart));
								parts.remove(0);
							}else {
								if(nextPart.length()>1) {
									String nextPartUnits=nextPart.substring(1, 2);
									String nextPartTens=nextPart.substring(0, 1);
									if(nextPartUnits.equals("0")) {
										if(partsLength>2) {
											String afterNextPart=parts.get(2);
											if(afterNextPart.length()>1) {
												children.add(new Node<String>(hundreds+nextPartTens+"0"));
												children.add(new Node<String>(hundreds+"00"+nextPartTens+"0"));
												parts.remove(0);
												parts.remove(0);
											}else {
												if(nextPartTens.equals("1") && afterNextPart.equals("1") || afterNextPart.equals("2")) {
													children.add(new Node<String>(hundreds+"00"+nextPartTens+afterNextPart));
													children.add(new Node<String>(hundreds+nextPartTens+afterNextPart));
													parts.remove(0);
													parts.remove(0);
													parts.remove(0);
												}else {
													if(afterNextPart.equals("0")) {
														children.add(new Node<String>(hundreds+nextPartTens+"0"));
														children.add(new Node<String>(hundreds+"00"+nextPartTens+"0"));
														parts.remove(0);
														parts.remove(0);
													}else {
														children.add(new Node<String>(hundreds+"00"+nextPartTens+"0"+afterNextPart));
														children.add(new Node<String>(hundreds+"00"+nextPartTens+afterNextPart));
														children.add(new Node<String>(hundreds+nextPartTens+"0"+afterNextPart));
														children.add(new Node<String>(hundreds+nextPartTens+afterNextPart));
														parts.remove(0);
														parts.remove(0);
														parts.remove(0);
													}
												}
											}
										}else {
											children.add(new Node<String>(hundreds+nextPartTens+"0"));
											children.add(new Node<String>(hundreds+"00"+nextPartTens+"0"));
											parts.remove(0);
											parts.remove(0);
										}
									}else {
										if(nextPart.equals("11") || nextPart.equals("12")) {
											children.add(new Node<String>(hundreds+"00"+nextPart));
											children.add(new Node<String>(hundreds+nextPart));
											parts.remove(0);
											parts.remove(0);
										}else {
											children.add(new Node<String>(hundreds+"00"+nextPartTens+"0"+nextPartUnits));
											children.add(new Node<String>(hundreds+"00"+nextPartTens+nextPartUnits));
											children.add(new Node<String>(hundreds+nextPartTens+"0"+nextPartUnits));
											children.add(new Node<String>(hundreds+nextPartTens+nextPartUnits));
											parts.remove(0);
											parts.remove(0);
										}
									}
								}else {
									if(nextPart.equals("0")) {
										children.add(new Node<String>(currentPart));
										parts.remove(0);
									}else {
										children.add(new Node<String>(hundreds+tens+"0"+nextPart));
										children.add(new Node<String>(hundreds+tens+nextPart));
										parts.remove(0);
										parts.remove(0);
									}
								}
							}
						}
					}else {
						if(partsLength>1) {
							String nextPart=parts.get(1);
							if(nextPart.length()>1) {
								children.add(new Node<String>(currentPart));
								parts.remove(0);
							}else {
								if(tens.equals("1") && nextPart.equals("1") || nextPart.equals("2")){
									children.add(new Node<String>(currentPart));
									parts.remove(0);
								}else {
									children.add(new Node<String>(hundreds+tens+"0"+nextPart));
									children.add(new Node<String>(hundreds+tens+"0"+nextPart));
									parts.remove(0);
									parts.remove(0);
								}
							}
						}else {
							children.add(new Node<String>(currentPart));
							parts.remove(0);
						}
					}
				}else {
					if(tens.equals("0")) {
						children.add(new Node<String>(currentPart));
						children.add(new Node<String>(hundreds+tens+"0"+units));
						parts.remove(0);
					}else {
						if(currentPart.substring(1,3).equals("11") || currentPart.substring(1,3).equals("12")) {
							children.add(new Node<String>(hundreds+"00"+tens+units));
							children.add(new Node<String>(currentPart));
							parts.remove(0);
						}else {
							children.add(new Node<String>(hundreds+"00"+tens+"0"+units));
							children.add(new Node<String>(hundreds+"00"+tens+units));
							children.add(new Node<String>(hundreds+tens+"0"+units));
							children.add(new Node<String>(currentPart));
							parts.remove(0);
						}
					}
				}
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



