/**
 * Software and Programming II
 * Coursework: sp2-cw2-2014
 * 
 * Submitted by: Pete Whelpton (pwhelp01)
 * Due Date: 03/11/2014
 * Lecturer: Keith Mannock
 */

package com.pwhelp01;

import java.util.Scanner;

public class FractionCalculator {
	
	/* Attributes */
	private Fraction value; 
	private String operation; 
	private boolean active; 
	
	
	/* Initialisation block */
	{
		value = new Fraction(0,1);
		operation = "";
		active = false;
	}
	
	
	/* Getters and Setters */
	public Fraction getValue() {
		return this.value;
	}
	
	
	/* Methods */
	/**
	 * Prompt user to type an expression to be evaluated and return the result
	 * 
	 * @return user input
	 */
	public String getUserInput() {
		
		/* Declare local variables */
		String inputString = "";												// Variable to store user input
		Scanner in = new Scanner(System.in);									// Create new Scanner object to read user input
		
		/* Prompt user for input and read it */
		System.out.print("Please enter an expression to be evaluated: ");		// Prompt user for input
		inputString = in.next();												// Read in whole line of user input
		inputString = inputString.trim();										// Trim any trailing whitespace
		in.close();																// Close scanner to avoid resource leak
		
		/* Return result */
		return inputString;														// Return user input
		
	}
	
	
	/**
	 * 
	 * @param frac
	 * @param inputString
	 * @return
	 * @throws ArithmeticException
	 * @throws NumberFormatException
	 */
	public Fraction evaluate(Fraction frac, String inputString) 
			throws ArithmeticException, NumberFormatException {
		
		String[] exp = splitString(inputString);
		
		for(String item : exp) {
				
			String str = this.identifyFraction(item);
			
				switch(str){
				/* Unary operations */	
				case "a": case "A": case "abs":
						frac = frac.absValue();
						break;
					case "n": case "N": case "neg":
						frac = frac.negate();
						break;
					case "c": case "C": case "clear":
						frac = new Fraction(0, 1);
						break;
					case "+": case "-": case "*": case "/":
						this.operation = item;
						break;
					case "integer":
						item += "/1";
					case "fraction":
						Fraction newFrac = createFraction(item);
						if(this.operation.equals("")) {
							frac = newFrac;
						}
						else {
							switch(this.operation){
								/* Binary operations */
								case "+":
									frac = frac.add(newFrac);
									this.operation = "";
									break;
								case "-":
									frac = frac.subtract(newFrac);
									this.operation = "";
									break;
								case "*":
									frac = frac.multiply(newFrac);
									this.operation = "";
									break;
								case "/":
									frac = frac.divide(newFrac);
									this.operation = "";
									break;
							}
						}

			}
			
		}
		
		return frac;
		
	}
	

	/**
	 * Helper function to split the input string into tokens based on whitespace
	 * 
	 * @param inputString string to be split
	 * @return array containing the individual tokens
	 */
	private String[] splitString(String inputString) {
		
		String[] split = inputString.split("\\s");								// Split string up based on whitespace and store in an array
		
		return split;															// return result
	}
	
	
	/**
	 * Creates a fraction from a string in x/y format
	 * 
	 * @param item string to be tested
	 * @return true is string is in fraction format, false if not a valid fraction
	 */
	private Fraction createFraction(String item) throws NumberFormatException {
		
		String[] split = item.split("/");
		
		int num = Integer.parseInt(split[0]);
		int denom = Integer.parseInt(split[1]);
		
		Fraction frac = new Fraction(num, denom);
		
		return frac;
		
	}
	
	
	private String identifyFraction(String str) {
		
		if(str.matches("-?\\d+")) {
			return "integer";
		}
		else if(str.matches("-?\\d+\\/{1}-?\\d+")){
			return("fraction");
		}
		else {
			return str;
		}
		
		
	}
	

	

}
