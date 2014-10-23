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
	
	private Fraction value = new Fraction(0, 1);
	private String operation = "";
	private String[] UNARY_OPERATORS = {"a", "A", "abs", "n", "N", 
										"neg", "c", "C", "clear"};
	private String[] BINARY_OPERATORS = {"+", "-", "/", "*"};
	
	public String getUserInput() {
		
		/* Declare local variables */
		String inputString = "";
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please enter an expression to be evaluated: ");
		inputString = in.next();
		inputString = inputString.trim();										// Trim any trailing whitespace
		in.close();																// Close scanner to avoid resource leak
		
		return inputString;
		
	}
	
	
	public Fraction evaluate(Fraction frac, String inputString) throws ArithmeticException {
		
		String[] exp = splitString(inputString);
		
		for(String item : exp) {
			
			System.out.print(item + "");
			
			if(isUnaryOperator(item)) {
				
				switch(item){
					case "a": case "A": case "abs":
						frac = frac.absValue();
						break;
					case "n": case "N": case "neg":
						frac = frac.negate();
						break;
					case "c": case "C": case "clear":
						frac = new Fraction(0, 1);
						break;
				}
			}
			else if(!isBinaryOperator(item)) {

				if(this.operation != "") {
					System.out.println("Exception Reached");
					throw new ArithmeticException("Error: Invalid syntax. Cannot have two consecutive operators");
				}
				else{
					System.out.println("?");
					this.operation = item;
				}
			}
			
		}
		
		return frac;
		
	}
	

	public String[] splitString(String inputString) {
		
		String[] split = inputString.split("\\s");
		
		return split;
	}
	
	
	public Fraction isFraction(String item) {
		
		String[] split = item.split("/");
		
		int num = Integer.parseInt(split[0]);
		int denom = Integer.parseInt(split[1]);
		
		Fraction frac = new Fraction(num, denom);
		
		return frac;
		
	}
	
	
	private boolean isUnaryOperator(String op) {
		
		boolean rv = false;
		
		for(String s : this.UNARY_OPERATORS) {
			if(s == op) {
				rv = true;
			}
		}
		
		return rv;
		
	}
	
	public boolean isBinaryOperator(String op) {
		
		boolean rv = false;
		
		for(String s : this.BINARY_OPERATORS) {
			if(s == op) {
				rv = true;
			}
		}
		
		return rv;
		
	}
}
