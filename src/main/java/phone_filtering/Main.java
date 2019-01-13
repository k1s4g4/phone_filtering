package phone_filtering;

import java.util.Scanner;

import phone_filtering.tools.Separator;
import phone_filtering.tools.Validator;

public class Main {
	private static Scanner sc;
	private static String input;
	
	public static void main(String[] args) {
		
		
		sc=new Scanner(System.in);
		
		while(!Validator.inputIsValid(input=getInput().trim())) {
			printDirectionsForValidInput();
		}
		sc.close();
	}
	
	private static String getInput() {
		System.out.println("Please enter a telephone number:");
		return sc.nextLine();
	}
	
	private static void printDirectionsForValidInput() {
		System.out.println("Your input is not valid");
		System.out.println("Valid telephone number must be some groups of 1 to 3 digits seperated by one space character");
	}
	

}
