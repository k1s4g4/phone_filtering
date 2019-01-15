package phone_filtering.model.tree.tools;

import java.util.ArrayList;
import java.util.List;

import phone_filtering.model.tree.Node;

public class AmbiguityHandler {
	
	/*
	 * This class is responsible for taking parts of the input,
	 *  handling the case of ambiguity that arise from each input,
	 *  and return the right amount and data of children that must be added
	 *  to the ambiguity tree.
	 *  Also in each case the amount of parts that
	 *  are used is not constant.
	 * */
	
	
	//the only method that is public in this class is handle()
	//the others methods are private and handle() calls them
	//depending on the number of digits of the first part
	public static List<Node<String>> handle(List<String> parts) {
		int digits=parts.get(0).length();
		if(digits==1) {
			return oneDigit(parts);
		}else if(digits==2) {
			return twoDigit(parts);
		}else {
			return threeDigit(parts);
		}
	}
	
	/* 1-DIGIT
	 * When the first part is one-digit number there are no ambiguities
	 * only one child must be added to the leafs of the tree
	 * and only one part must be removed because only one part is used
	 * */
	private static List<Node<String>> oneDigit(List<String> parts) {
		List<Node<String>> children=new ArrayList<>();
		add(children,parts.get(0));
		remove(parts,1);
		return children;
	}
	
	
	/* 2-DIGIT
	 * The first condition in 2 digit numbers is if units are zero.
	 * If yes then next part is examined 
	 *    if next part is Not(2-digit or 3-Digit) and Not(zero)  Not(makes 11 or 12 with previous) 
	 *    	add ambiguities that exist between next and previous to children and remove two from list	
	 *    else
	 *    	then just add only first part to children and remove one 
	 * If no then check if it is 11 or 12
	 * 	  if yes then add number to children and remove one.
	 * 	  if no then add ambiguities to children and remove one
	 * */
	private static List<Node<String>> twoDigit(List<String> parts) {
		List<Node<String>> children=new ArrayList<>();
		String currentPart=parts.get(0);
		int partsLength=parts.size();
		if(currentPart.charAt(1)=='0') {
			if(partsLength>1) {
				String nextPart=parts.get(1);
				if(		!(nextPart.length()>1) &&
						!(currentPart.equals("10") && (nextPart.equals("1") || nextPart.equals("2"))) && 
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
	/*3-DIGIT
	 *The first condition in 2 digit numbers is if units are zero.
	 * If units are zero then 
	 * 	  if numbers has no tens
	 * 		 if number is not last
	 *       	if next is 3-digit
	 *       		add number to children and remove one
	 *          if next is 2 digit
	 *              if next has zero units
	 *                 if next number is not last
	 *                    if Not(number after next is 3 or 2 digit) and Not (next and after next number make 11 or 12)
	 *                       add two and remove two
	 *                    else
	 *                       add four and remove three
	 *              if next has not zero units
	 *                 if next is 11 or 12
	 *                    add four and remove two
	 *                 else
	 *                    add two and remove two 
	 *          if next is 1 digit
	 *              if is zero add previous to tree and remove one
	 *              if not zero add two ambiguities and remove two
	 *    if numbers has tens 
	 *       if number is not last
	 *          if Not(next number is 2 or 3 digit) and Not (next number makes 11 or twelve with previous)
	 *             add four ambiguities and remove two
	 *          else
	 *             add two and remove one (next is not used)
	 * If units are not zero then
	 *    if number has tens and Not(ends in 11 or 12 )
	 *       add four ambiguities and remove one.
	 *    else 
	 *       add two ambiguities and remove one
	 * */
	private static List<Node<String>> threeDigit(List<String> parts) {
		List<Node<String>> children=new ArrayList<>();
		Part current=new Part(parts.get(0));
		int partsLength=parts.size();
		if(current.units.equals("0")) {
			if(current.tens.equals("0")) {
				if(partsLength>1) {
					Part next=new Part(parts.get(1));
					if(next.three) {
						add(children,current.full());
						remove(parts,1);
					}else {
						if(!next.one) {
							if(next.units.equals("0")) {
								if(partsLength>2) {
									Part afterNext=new Part(parts.get(2));
									boolean nextOverOneDigit=!afterNext.one;
									boolean elevenTwelve=next.tens.equals("1") && (afterNext.full().equals("1") || afterNext.full().equals("2"));
									boolean afterNextZero=afterNext.full().equals("0");
									if(!elevenTwelve && !afterNextZero && !nextOverOneDigit) {
										add(children,
												current.hundreds+"00"+next.tens+"0"+afterNext.full(),
												current.hundreds+"00"+next.tens+afterNext.full(),
												current.hundreds+next.tens+"0"+afterNext.full(),
												current.hundreds+next.tens+afterNext.full());
										remove(parts,3);
									}else {
										add(children,
												current.hundreds+"00"+next.tens+"0"+(nextOverOneDigit?"":afterNext.full()),
												current.hundreds+next.tens+"0"+(nextOverOneDigit?"":afterNext.full()));
										remove(parts,2);
										if(!nextOverOneDigit)remove(parts,1);
									}
								}else {
									add(children,
											current.hundreds+next.tens+"0",
											current.hundreds+"00"+next.tens+"0");
									remove(parts,2);
								}
							}else {
								if(next.full().equals("11") || next.full().equals("12")) {
									add(children,
											current.hundreds+"00"+next.full(),
											current.hundreds+next.full());
									remove(parts,2);
								}else {
									add(children,
											current.hundreds+"00"+next.tens+"0"+next.units,
											current.hundreds+"00"+next.tens+next.units,
											current.hundreds+next.tens+"0"+next.units,
											current.hundreds+next.tens+next.units);
									remove(parts,2);
								}
							}
						}else {
							if(next.full().equals("0")) {
								add(children,current.full());
								remove(parts,1);
							}else {
								add(children,
										current.hundreds+current.tens+"0"+next.full(),
										current.hundreds+current.tens+next.full());
								remove(parts,2);
							}
						}
					}
				}else {
					add(children,current.full());
					remove(parts,1);
				}
			}else {
				if(partsLength>1) {
					Part next=new Part(parts.get(1));
					if( !(next.full().length()>1) && !(current.tens.equals("1") && next.full().equals("1") || next.full().equals("2"))) {
						add(children,
								current.hundreds+"00"+current.tens+"0"+next.full(),
								current.hundreds+"00"+current.tens+next.full(),
								current.hundreds+current.tens+"0"+next.full(),
								current.hundreds+current.tens+next.full());
						remove(parts,2);
					}else {
						add(children,
								current.hundreds+"00"+current.tens+"0",
								current.hundreds+current.tens+"0");
						remove(parts,1);
					}
				}else {
					add(children,current.full());
					remove(parts,1);
				}
			}
		}else {
			boolean zeroTens=current.tens.equals("0");
			boolean elevenTwelve=current.full().substring(1,3).equals("11") || current.full().substring(1,3).equals("12");
			if( !zeroTens && !elevenTwelve ){
				add(children,
						current.hundreds+"00"+current.tens+"0"+current.units,
						current.hundreds+"00"+current.tens+current.units,
						current.hundreds+current.tens+"0"+current.units,
						current.full());
				remove(parts,1);
			}else {
				add(children,
						current.full(),
						current.hundreds+(zeroTens?"":"00")+current.tens+(elevenTwelve?"":"0")+current.units);
				remove(parts,1);
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
			parts.size();
			parts.remove(0);
		}
	}
	
}
class Part{
	boolean one;
	boolean two;
	boolean three;
	String hundreds;
	String tens;
	String units;
	Part(String part) {
		if(part.length()==3) {
			three=true;
			hundreds=part.substring(0, 1);
			tens=part.substring(1, 2);
			units=part.substring(2,3);
		}else if(part.length()==2) {
			two=true;
			hundreds="";
			tens=part.substring(0, 1);
			units=part.substring(1,2);
		}else {
			one=true;
			hundreds="";
			tens="";
			units=part.substring(0,1);
		}
	}
	
	String full() {
		return hundreds+tens+units;
	}
}
