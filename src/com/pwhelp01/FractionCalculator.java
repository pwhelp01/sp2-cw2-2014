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
	private Fraction value; 													// Hold the current value in the calculator
	private String operation; 													// Remembers an operation to perform
	private boolean active; 													//
	
	
	/* Initialisation block */
	{
		this.value = new Fraction(0,1);											// Set default value of calculator to zero
		this.operation = "";													// No operation recorded yet
		this.active = true;
	}
	
	
	/* Getters and Setters */
	public Fraction getValue() {
		return this.value;
	}
	
	public void setValue(Fraction frac) {
		this.value = frac;
	}
	
	public boolean getActive() {
		return this.active;
	}
	
	
	
	/* Methods */
	
	public static void main(String[] args) {
		
		/* Declare variables */
		FractionCalculator fc = new FractionCalculator();						// Create new instance of FractionCalculator
		
		/* Print welcome message */
		System.out.println("----------------------------------------------");
		System.out.println("Fraction Calculator: Peter Whelpton (pwhelp01)");
		System.out.println("----------------------------------------------");
		
		
		/* Processing loop */


			do{
				
				try{
					System.out.println();												// Line space
					String input = fc.getUserInput();									
					Fraction result = (fc.evaluate(fc.getValue(), input));
					System.out.println(" = " + result);
					fc.setValue(result);
				}
				catch (Exception e) {
					
					System.out.print("Error: ");
					if(e.getMessage() != null) {
						System.out.print(e.getMessage() + "\n");
					}
					
					fc.reset();
				}

			}while(fc.getActive() == true);
			
			/* End program */
			System.out.println("\n---------------------");
			System.out.println("Goodbye");

	}
	
	
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
		System.out.print("Please enter an expression to be evaluated "
				+ "(Current value is " + this.getValue()+ "): ");					// Prompt user for input
		inputString = in.nextLine();											// Read in whole line of user input
		//in.close();															// Close scanner to avoid resource leak
		
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
	 * @throws IllegalArgumentException
	 */
	public Fraction evaluate(Fraction frac, String inputString) 
			throws ArithmeticException, NumberFormatException, 
			IllegalArgumentException  {
		
		String[] exp = splitString(inputString);
		
		for(String item : exp) {
				
			String str = this.identifyFraction(item);
			
				switch(str){
				/* Unary operations */	
					case "\n":
						break;
					case "a": case "A": case "abs":
						frac = frac.absValue();
						break;
					case "n": case "N": case "neg":
						frac = frac.negate();
						break;
					case "c": case "C": case "clear":
						frac = new Fraction(0, 1);
						break;
					case "q": case "Q": case "quit":
						this.active = false;
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
							break;
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
							break;
						}
						default:
							throw new IllegalArgumentException("Invalid input");
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
	
	
	/**
	 * Resets calculator to it's inital state
	 */
	private void reset() {
		
		this.value = new Fraction(0,1);											// Set default value of calculator to zero
		this.operation = "";													// No operation recorded yet
		
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
