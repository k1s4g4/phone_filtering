package phone_filtering.model.tree.tools;

import java.util.ArrayList;
import java.util.List;

import phone_filtering.model.tree.Node;

public class AmbiguityHandler {
	
	public static List<Node<String>> oneDigit(List<String> parts) {
		List<Node<String>> children=new ArrayList<>();
		add(children,parts.get(0));
		remove(parts,1);
		return children;
	}
	
	public static List<Node<String>> twoDigit(List<String> parts) {
		List<Node<String>> children=new ArrayList<>();
		String currentPart=parts.get(0);
		int partsLength=parts.size();
		if(currentPart.charAt(1)=='0') {
			if(partsLength>1) {
				String nextPart=parts.get(1);
				if(		!(nextPart.length()>1) &&
						!(currentPart.equals("10") && nextPart.equals("1") || nextPart.equals("2")) && 
						!(nextPart.equals("0"))) 
				{
					add(children,
							currentPart+""+nextPart,
							currentPart.substring(0,1)+nextPart);
					remove(parts,2);
				}else {
					add(children,currentPart);
					remove(parts,1);
				}
			}else {
				add(children,currentPart);
				remove(parts,1);
			}
		}else {
			if(currentPart.equals("11") || currentPart.equals("12")) {
				add(children,currentPart);
				remove(parts,1);
			}else {
				add(children,
						currentPart.substring(0, 1)+"0"+currentPart.substring(1,2),
						currentPart);
				remove(parts,1);
			}
		}

		return children;
	}
	
	public static List<Node<String>> threeDigit(List<String> parts) {
		List<Node<String>> children=new ArrayList<>();
		String currentPart=parts.get(0);
		String hundreds=currentPart.substring(0,1);
		String tens=currentPart.substring(1,2);
		String units=currentPart.substring(2,3);
		int partsLength=parts.size();
		if(units.equals("0")) {
			if(tens.equals("0")) {
				if(partsLength>1) {
					String nextPart=parts.get(1);
					if(nextPart.length()>2) {
						add(children,currentPart);
						remove(parts,1);
					}else {
						if(nextPart.length()>1) {
							String nextPartUnits=nextPart.substring(1, 2);
							String nextPartTens=nextPart.substring(0, 1);
							if(nextPartUnits.equals("0")) {
								if(partsLength>2) {
									String afterNextPart=parts.get(2);
									if(afterNextPart.length()>1) {
										add(children,
												hundreds+nextPartTens+"0",
												hundreds+"00"+nextPartTens+"0");
										remove(parts,2);
									}else {
										if(nextPartTens.equals("1") && afterNextPart.equals("1") || afterNextPart.equals("2")) {
											add(children,
													hundreds+"00"+nextPartTens+"0"+afterNextPart,
													hundreds+nextPartTens+"0"+afterNextPart);
											remove(parts,3);
										}else {
											if(afterNextPart.equals("0")) {
												add(children,
														hundreds+nextPartTens+"0",
														hundreds+"00"+nextPartTens+"0");
												remove(parts,2);
											}else {
												add(children,
														hundreds+"00"+nextPartTens+"0"+afterNextPart,
														hundreds+"00"+nextPartTens+afterNextPart,
														hundreds+nextPartTens+"0"+afterNextPart,
														hundreds+nextPartTens+afterNextPart);
												remove(parts,3);
											}
										}
									}
								}else {
									add(children,
											hundreds+nextPartTens+"0",
											hundreds+"00"+nextPartTens+"0");
									remove(parts,2);
								}
							}else {
								if(nextPart.equals("11") || nextPart.equals("12")) {
									add(children,
											hundreds+"00"+nextPart,
											hundreds+nextPart);
									remove(parts,2);
								}else {
									add(children,
											hundreds+"00"+nextPartTens+"0"+nextPartUnits,
											hundreds+"00"+nextPartTens+nextPartUnits,
											hundreds+nextPartTens+"0"+nextPartUnits,
											hundreds+nextPartTens+nextPartUnits);
									remove(parts,2);
								}
							}
						}else {
							if(nextPart.equals("0")) {
								add(children,currentPart);
								remove(parts,1);
							}else {
								add(children,
										hundreds+tens+"0"+nextPart,
										hundreds+tens+nextPart);
								remove(parts,2);
							}
						}
					}
				}else {
					add(children,currentPart);
					remove(parts,1);
				}
			}else {
				if(partsLength>1) {
					String nextPart=parts.get(1);
					if(nextPart.length()>1) {
						add(children,
								hundreds+"00"+tens+"0",
								hundreds+tens+"0");
						remove(parts,1);
					}else {
						if(tens.equals("1") && nextPart.equals("1") || nextPart.equals("2")){
							add(children,
									hundreds+"00"+tens+"0",
									hundreds+tens+"0");
							remove(parts,1);
						}else {
							add(children,
									hundreds+"00"+tens+"0"+nextPart,
									hundreds+"00"+tens+nextPart,
									hundreds+tens+"0"+nextPart,
									hundreds+tens+nextPart);
							remove(parts,2);
						}
					}
				}else {
					add(children,currentPart);
					remove(parts,1);
				}
			}
		}else {
			if(tens.equals("0")) {
				add(children,
						currentPart,
						hundreds+tens+"0"+units);
				remove(parts,1);
			}else {
				if(currentPart.substring(1,3).equals("11") || currentPart.substring(1,3).equals("12")) {
					add(children,
							hundreds+"00"+tens+units,
							currentPart);
					remove(parts,1);
				}else {
					add(children,
							hundreds+"00"+tens+"0"+units,
							hundreds+"00"+tens+units,
							hundreds+tens+"0"+units,
							currentPart);
					remove(parts,1);
				}
			}
		}

		return children;
	}
	
	
	private static void add(List<Node<String>> children,String... values) {
		for(String value:values) {
			children.add(new Node<String>(value));
		}
	}
	private static void remove(List<String> parts,int removeAmount) {
		for(int i=0; i<removeAmount; i++) {
			parts.remove(0);
		}
	}
	
}
