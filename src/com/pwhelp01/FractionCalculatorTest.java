/**
 * Software and Programming II
 * Coursework: sp2-cw2-2014
 * 
 * Submitted by: Pete Whelpton (pwhelp01)
 * Due Date: 03/11/2014
 * Lecturer: Keith Mannock
 */

package com.pwhelp01;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Fraction Calculator
 * 
 * @author Pete Whelpton
 *
 */
public class FractionCalculatorTest {

	private FractionCalculator fc;
	
	
	/**
	 * Instantiate a FractionCalculator for each test
	 */
	@Before
	public void setup() {
		
		this.fc = new FractionCalculator();										// Create new FractionCalculator object
		
	}
	
	
	/**
	 * Tear down the Fraction Calculator after each test
	 */
	@After
	public void tearDown() {
		
		this.fc = null;															// Destroy FractionCalculator object
		
	}
	
	
	/**
	 * Test the Absolute method of the evaluate() function
	 */
	@Test
	public void evaluateTestAbs() {	
		
		String inputString = "abs";												// 
		
		Fraction frac1 = new Fraction(4, -5);									// Test a negative fraction
		Fraction frac2 = new Fraction(0, 1);									// Test when fraction = 0
		Fraction frac3 = new Fraction()
		
		Fraction expectedResult = new Fraction(4, 5);
		
		Fraction actualResult = (this.fc.evaluate(frac1, inputString));
		
		assertTrue (expectedResult.equals(actualResult));
		
	}
	
	
	@Test
	public void evaluateTestClear() {
		
		String inputString = "clear";
		Fraction frac1 = new Fraction(4, -5);
		Fraction expectedResult = new Fraction(0, 1);
		Fraction actualResult = (fc.evaluate(frac1, inputString));
		
		assertTrue (expectedResult.equals(actualResult));
		
	}
	
	
	@Test(expected=ArithmeticException.class)
	public void evaluateBinaryException() {
		
		String inputString = "5 + + 5";
		Fraction frac1 = new Fraction(4, -5);
		Fraction actualResult = (fc.evaluate(frac1, inputString));
		
		
	}
	
	
	@Test
	public void isBinary() {
		
		FractionCalculator fc = new FractionCalculator();
		
		String input = "+";
		System.out.println(fc.isBinaryOperator(input));
		
	}

}
