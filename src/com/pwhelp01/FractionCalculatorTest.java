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
	 * Test the Absolute function of the evaluate() method
	 */
	@Test
	public void evaluateTestAbs() {	
		
		Fraction frac1 = new Fraction(4, -5);									// Test a negative fraction
		Fraction frac2 = new Fraction(0, 1);									// Test when fraction = 0
		Fraction frac3 = new Fraction(-24, -2);									// Check when both num and denom and signed
		Fraction frac4 = new Fraction(17, 44);									// Check absolute doesn't accidentally flip positive fractions
		
		Fraction expectedResult1 = new Fraction(4, 5);							// Should simplify to 4/5
		Fraction expectedResult2 = new Fraction(0, 1);							// Should simplify to 0
		Fraction expectedResult3 = new Fraction(12, 1);							// Should simplify to 12/1
		Fraction expectedResult4 = new Fraction(17, 44);						// Should simplify to 17/44
		
		String inputString = "abs";												// We're testing the absolute functionality
		Fraction actualResult1 = (this.fc.evaluate(frac1, inputString));		
		Fraction actualResult2 = (this.fc.evaluate(frac2, inputString));		
		
		inputString = "a";														// Test 'a' also works
		Fraction actualResult3 = (this.fc.evaluate(frac3, inputString));
		
		inputString = "A";														// Test 'A' also works							
		Fraction actualResult4 = (this.fc.evaluate(frac4, inputString));
		
		assertTrue(expectedResult1.equals(actualResult1));
		assertTrue(expectedResult2.equals(actualResult2));
		assertTrue(expectedResult3.equals(actualResult3));
		assertTrue(expectedResult4.equals(actualResult4));
		
	}
	
	
	/**
	 * Test the add function of the evaluate() method
	 */
	@Test public void evaluateTestAdd() {
		
		String inputString1 = "4 + 1";											// Test adding two integers
		String inputString2 = "6 + 1 + 4";										// Test adding three integers
		String inputString3 = "1/4 + 5/6";										// Test adding two fractions
		String inputString4 = "3/4 + 9 + -1/4";									// Test mix of fraction and integers including negative fraction
		String inputString5 = "0 + 0/7 + 9/8";									// Test using zero
		
		Fraction expectedResult1 = new Fraction(5, 1);
		Fraction expectedResult2 = new Fraction(11, 1);
		Fraction expectedResult3 = new Fraction(13, 12);
		Fraction expectedResult4 = new Fraction(19, 2);
		Fraction expectedResult5 = new Fraction(9, 8);
		
		
		Fraction actualResult1 = fc.evaluate(fc.getValue(), inputString1);
		Fraction actualResult2 = fc.evaluate(fc.getValue(), inputString2);
		Fraction actualResult3 = fc.evaluate(fc.getValue(), inputString3);
		Fraction actualResult4 = fc.evaluate(fc.getValue(), inputString4);
		Fraction actualResult5 = fc.evaluate(fc.getValue(), inputString5);
		
		assertTrue(expectedResult1.equals(actualResult1));
		assertTrue(expectedResult2.equals(actualResult2));
		assertTrue(expectedResult3.equals(actualResult3));
		assertTrue(expectedResult4.equals(actualResult4));
		assertTrue(expectedResult5.equals(actualResult5));
		
	}
	
	
	/**
	 * Test the clear function of the evaluate() method
	 */
	@Test
	public void evaluateTestClear() {
		
		Fraction frac1 = new Fraction(4, -5);
		Fraction frac2 = new Fraction(55, Integer.MAX_VALUE);
		Fraction frac3 = new Fraction(0, 1);
		
		Fraction expectedResult = new Fraction(0, 1);
		
		String inputString = "clear";
		Fraction actualResult1 = (fc.evaluate(frac1, inputString));
		
		inputString = "c";
		Fraction actualResult2 = (fc.evaluate(frac2, inputString));
		
		inputString = "C";
		Fraction actualResult3 = (fc.evaluate(frac3, inputString));
		
		assertTrue(expectedResult.equals(actualResult1));
		assertTrue(expectedResult.equals(actualResult2));
		assertTrue(expectedResult.equals(actualResult3));
		
	}
	

}
