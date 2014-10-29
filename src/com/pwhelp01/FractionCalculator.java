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
	private boolean active; 													// Maintain state on whether to process expressions
	
	
	/* Initialisation block */
	{
		this.value = new Fraction(0,1);											// Set default value of calculator to zero
		this.operation = "";													// No operation recorded yet
		this.active = true;														// Calculator is actice
	}
	
	
	/* Getters and Setters */
	/**
	 * Get the current Fraction value stored in the calculator
	 * 
	 * @return calculator's current value
	 */
	public Fraction getValue() {
		return this.value;
	}
	
	/**
	 * Set the current value stored in the calculator
	 * 
	 * @param frac new value to be assigned to the calculator
	 */
	public void setValue(Fraction frac) {
		this.value = frac;
	}
	
	/**
	 * Get the status of the calculator (whether it should continue evaluating
	 * expressions
	 * 
	 * @return status of the calculator
	 */
	public boolean getActive() {
		return this.active;
	}
	
	/**
	 * Set the status of the calculator (whether it should continue evaluating
	 * expressions
	 * 
	 * @param status
	 */
	public void setActive(boolean status) {
		this.active = status;
	}
	
	
	
	/* Methods */
	/**
	 * Main method
	 * <p>
	 * Evaluate fraction calculations as input by the user
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		/* Declare variables */
		FractionCalculator fc = new FractionCalculator();						// Create new instance of FractionCalculator
		
		/* Print welcome message */
		System.out.println("---------------------------------------------");
		System.out.println("Fraction Calculator: Pete Whelpton (pwhelp01)");
		System.out.println("---------------------------------------------");
		
		
		/* Processing loop */
		do{																		// Do loop, as we must loop AT LEAST once
			
			/* What should happen! */
			try{
				
				System.out.println();											// Line space
				String input = fc.getUserInput();								// Prompt user and get input string				
				Fraction result = (fc.evaluate(fc.getValue(), input));			// Evaluate input string and get fraction result
				System.out.println(" = " + result);								// Print result on screen for user
				fc.setValue(result);											// Store the result in the calculator ready for next input line
				
			}
			
			/* User wants to quit */
			catch (EndOfInputException e) {										// User has input 'quit' or similar
				fc.setActive(false);											// Change state of calculator to stop processing input
			}
			
			/* An error has occurred */
			catch (Exception e) {												// If an exception has been raised that we can recover from:
				
				System.out.print("Error: ");									// Inform user an error has occurred
				if(e.getMessage() != null) {									// Check if the exception has a message...
					System.out.print(e.getMessage() + "\n");					// If it does, display it to the user
				}
				
				fc.reset();														// Reset calculator to original state and go back to prompting for user input
			}

		}while(fc.getActive() == true);											// Keep looping until user indicates that they want to stop
		
		
		/* End program */
		System.out.println("\n---------------------");							// Line break
		System.out.println("Goodbye");											// Goodbye message

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
				+ "(Current value is " + this.getValue()+ "): ");				// Prompt user for input
		inputString = in.nextLine();											// Read in whole line of user input
		//in.close();															// Close scanner to avoid resource leak (this crashed the program?!)
		
		/* Return result */
		return inputString;														// Return user input
		
	}
	
	
	/**
	 * Evaluate a string of fraction arithmetic and return the result as a
	 * fraction
	 * <p>
	 * Uses a pretty ugly switch() statement - but by this time I was too tired
	 * to figure out anything more elegant... do values in a switch() statement
	 * count as MAGIC NUMBERS?  Should constants be used instead somehow?
	 * 
	 * @param frac starting value 
	 * @param inputString string of fractions / operators (e.g. 1/3 + 7/8 - 2)
	 * @return fraction object representation of the result
	 * @throws ArithmeticException 
	 * @throws NumberFormatException 
	 * @throws IllegalArgumentException
	 * @throws EndOfInputException 
	 */
	public Fraction evaluate(Fraction frac, String inputString) 
			throws ArithmeticException, NumberFormatException, 
			IllegalArgumentException, EndOfInputException  {
		
		String[] exp = splitString(inputString);								// Split string and store tokens in an array
		
		for(String item : exp) {												// Loop through each token one by one
				
			String str = this.identifyFraction(item);							// Identify if token is a fraction / integer
			
				switch(str){													// Check what to do with each line
				/* Unary operations */	
					case "\n":													// If newline (does this get a token?!) do nothing
						break;												
					case "a": case "A": case "abs":								// If a / A / abs
						frac = frac.absValue();									// return absoulte value of stored fraction
						break;
					case "n": case "N": case "neg":								// If n / N / neg
						frac = frac.negate();									// negate stored fraction
						break;
					case "c": case "C": case "clear":							// If c / C / clear
						frac = new Fraction(0, 1);								// set stored fraction to zero
						break;
					case "q": case "Q": case "quit":							// If q / Q / quit
						throw new EndOfInputException("User quit application");	// Throw a custom exception to terminate the program
					case "+": case "-": case "*": case "/":						// If it is an operator...
						if(this.operation != "") {								// check if an operator is already stored,
							throw new IllegalArgumentException("Two sequential"
									+ " operators " + this.operation + " " +
									item + " is not permitted");				// if it is throw an error
						}
						this.operation = item;									// otherwise, store the operation
						break;
					case "integer":
						item += "/1";											// FALLS THROUGH!  Check if the token is an integer, and if so make it a fraction
					case "fraction":											// if the token is a fraction
						Fraction newFrac = createFraction(item);				// create a new fraction instance
						if(this.operation.equals("")) {							// check if a binary operation is stored
							frac = newFrac;										// if not, set the stored fraction to the fraction we just created
							break;
						}
						else {													// If a binary operation is stored
							switch(this.operation){								// we need to perform the operation on the two fractions (stored and new)
								/* Binary operations */
								case "+":										
									frac = frac.add(newFrac);					// The the two fractions
									this.operation = "";						// and clear the stored operation
									break;
								case "-":
									frac = frac.subtract(newFrac);				// Subtract the two fractions
									this.operation = "";						// and clear the stored operation
									break;
								case "*":
									frac = frac.multiply(newFrac);				// Multiply the two fractions
									this.operation = "";						// and clear the stored operation
									break;
								case "/":
									frac = frac.divide(newFrac);				// Divide the two fractions
									this.operation = "";						// and clear the stored operation
									break;
							}
							break;
						}
						default:												// If the token is not recognised,
							throw new IllegalArgumentException("Invalid input."
									+ " " + item  + " is not a valid operator");// throw an exception to inform the user
			}
			
		}
		
		return frac;															// Return the answer to the calling method
		
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
	 * @throws NumberFormatException if parseInt() fails due to num / denom not
	 * being a valid integer 
	 */
	private Fraction createFraction(String item) throws NumberFormatException {
		
		String[] split = item.split("/");										// Split string into tokens 
		
		int num = Integer.parseInt(split[0]);									// First token is going to be the numerator
		int denom = Integer.parseInt(split[1]);									// Second token is going to be the denominator
		
		return new Fraction(num, denom);										// Create new fraction and return it
		
	}
	
	
	/**
	 * Resets calculator to it's initial state
	 */
	private void reset() {
		
		this.value = new Fraction(0,1);											// Set default value of calculator to zero
		this.operation = "";													// No operation recorded yet
		this.active = true;														// Make sure we keep evaluating operations
		
	}
	
	
	/**
	 * Helper function to check an input token and identify (via regular
	 * expression) if it is an integer or a fraction
	 * 
	 * @param str the string token to be tested
	 * @return "integer" or "fraction" 
	 */
	private String identifyFraction(String str) {
		
		if(str.matches("-?\\d+")) {												// Check if the token is an integer
			return "integer";													// If it is, return "integer"
		}
		else if(str.matches("-?\\d+\\/{1}-?\\d+")){								// Check if the token is a fraction
			return("fraction");													// If it is, return "fraction"
		}
		else {																	// Otherwise,
			return str;															// just return the original input
		}		
		
	}

}
