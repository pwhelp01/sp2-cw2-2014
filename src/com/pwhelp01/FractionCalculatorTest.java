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
	public void evaluateTestAbs() throws EndOfInputException{	
		
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
	@Test public void evaluateTestAdd() throws EndOfInputException {
		
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
	 * Test the subtract function of the evaluate() method
	 * 
	 * @throws EndOfInputException
	 */
	@Test
	public void evaluateSubtractTest() throws EndOfInputException {
		
		String inputString1 = "17/22 - 1";										// Test subtracting two positive numbers (one fraction and one int)
		String inputString2 = "-13/19 - 4/5";									// Test one negative fraction and one positive fraction
		String inputString3 = "64/72 - 0/1";									// Test trying to subtract zero
		String inputString4 = "13/99 - 4 - 16/17";								// Test multiple subtraction operations
		
		Fraction expectedResult1 = new Fraction(-5, 22);
		Fraction expectedResult2 = new Fraction(-141, 95);
		Fraction expectedResult3 = new Fraction(8, 9);
		Fraction expectedResult4 = new Fraction(-8095, 1683);
		
		Fraction actualResult1 = fc.evaluate(fc.getValue(), inputString1);
		Fraction actualResult2 = fc.evaluate(fc.getValue(), inputString2);
		Fraction actualResult3 = fc.evaluate(fc.getValue(), inputString3);
		Fraction actualResult4 = fc.evaluate(fc.getValue(), inputString4);
		
		assertTrue(expectedResult1.equals(actualResult1));
		assertTrue(expectedResult2.equals(actualResult2));
		assertTrue(expectedResult3.equals(actualResult3));
		assertTrue(expectedResult4.equals(actualResult4));
	}
	
	
	/**
	 * Test the divide function of the evaluate() method
	 * 
	 * @throws EndOfInputException
	 */
	@Test
	public void evaluateDivideTest() throws EndOfInputException {
		
		String inputString1 = "42/50 / 2";										// Test dividing two positive numbers (one fraction and one int)
		String inputString2 = "-18/20 / 1/2";									// Test one negative fraction and one positive fraction
		String inputString3 = "40/66 / 3 / 2/3";								// Test multiple division operations
		
		Fraction expectedResult1 = new Fraction(21, 50);
		Fraction expectedResult2 = new Fraction(-9, 5);
		Fraction expectedResult3 = new Fraction(10, 33);
		
		Fraction actualResult1 = fc.evaluate(fc.getValue(), inputString1);
		Fraction actualResult2 = fc.evaluate(fc.getValue(), inputString2);
		Fraction actualResult3 = fc.evaluate(fc.getValue(), inputString3);
		
		assertTrue(expectedResult1.equals(actualResult1));
		assertTrue(expectedResult2.equals(actualResult2));
		assertTrue(expectedResult3.equals(actualResult3));

	}
	
	
	/**
	 * Test the multiply function of the evaluate() method
	 * 
	 * @throws EndOfInputException
	 */
	@Test
	public void evaluateMultiplyTest() throws EndOfInputException {
		
		String inputString1 = "42/50 * 2";										// Test multiplying two positive numbers (one fraction and one int)
		String inputString2 = "-18/20 * 1/2";									// Test one negative fraction and one positive fraction
		String inputString3 = "40/66 * 3 * 2/3";								// Test multiple multiplication operations
		
		Fraction expectedResult1 = new Fraction(42, 25);
		Fraction expectedResult2 = new Fraction(-9, 20);
		Fraction expectedResult3 = new Fraction(40, 33);
		
		Fraction actualResult1 = fc.evaluate(fc.getValue(), inputString1);
		Fraction actualResult2 = fc.evaluate(fc.getValue(), inputString2);
		Fraction actualResult3 = fc.evaluate(fc.getValue(), inputString3);
		
		assertTrue(expectedResult1.equals(actualResult1));
		assertTrue(expectedResult2.equals(actualResult2));
		assertTrue(expectedResult3.equals(actualResult3));

	}
	
	
	/**
	 * Test a whole expression
	 * 
	 * @throws EndOfInputException
	 */
	@Test
	public void evaulateWholeExpressionTest() throws EndOfInputException {
		
		String inputString1 = "3/4 + 1/-3 * 7 / 5";								// Test a whole expression
		String inputString2 = "1/2 - 3/4 * abs";
		String inputString3 = "7/8 neg +";										// Test another whole expression split across two lines
		
		Fraction expectedResult1 = new Fraction(7, 12);
		Fraction expectedResult2 = new Fraction(-7, 8);
		
		Fraction actualResult1 = fc.evaluate(fc.getValue(), inputString1);
		Fraction frac = fc.evaluate(fc.getValue(), inputString2);
		Fraction actualResult2 = fc.evaluate(frac, inputString3);
		
		assertTrue(expectedResult1.equals(actualResult1));
		assertTrue(expectedResult2.equals(actualResult2));

	}
	
	
	/**
	 * Test the clear function of the evaluate() method
	 */
	@Test
	public void evaluateTestClear() throws EndOfInputException {
		
		Fraction frac1 = new Fraction(4, -5);									// Test with a negative fraction
		Fraction frac2 = new Fraction(55, Integer.MAX_VALUE);					// Test with an extreme value
		Fraction frac3 = new Fraction(0, 1);									// Test with zero
		
		Fraction expectedResult = new Fraction(0, 1);							// All results should return 0
		
		String inputString = "clear";											// We're testing the "clear" functionality
		Fraction actualResult1 = (fc.evaluate(frac1, inputString));
		
		inputString = "c";														// Test using "c" also works
		Fraction actualResult2 = (fc.evaluate(frac2, inputString));
		
		inputString = "C";
		Fraction actualResult3 = (fc.evaluate(frac3, inputString));				// Test using "C" also works
		
		assertTrue(expectedResult.equals(actualResult1));						// Should equal 0
		assertTrue(expectedResult.equals(actualResult2));						// Should equal 0
		assertTrue(expectedResult.equals(actualResult3));						// Should equal 0
		
	}
	
	
	/**
	 * Test the having two consecutive binary operators raises an exception
	 * 
	 * @throws IllegalArgumentException
	 */
	@Test(expected=IllegalArgumentException.class)
	public void consecutiveOperatorsException() throws EndOfInputException {
		
		String inputString = "4/5 + * 7";										// Test two consecutive binary operators
		
		Fraction frac = fc.evaluate(fc.getValue(), inputString);
		
	}
	

	/**
	 * Test that quit / q / Q etc. throws an exception
	 * @throws EndOfInputException
	 */
	@Test(expected=EndOfInputException.class)
	public void quitException() throws EndOfInputException {
		
		String inputString = "6/2 + 1/3 quit - 9/12 fdafda";					// Check quit terminates, even if there is another error afterwards
		
		Fraction frac = fc.evaluate(fc.getValue(), inputString);
		
	}
	
	@Test(expected=EndOfInputException.class)
	public void qException() throws EndOfInputException {
		
		String inputString = "6/2 + 1/3 q - 9/12 fdafda";						// Check quit terminates, even if there is another error afterwards
		
		Fraction frac = fc.evaluate(fc.getValue(), inputString);
		
	}
	
	@Test(expected=EndOfInputException.class)
	public void QException() throws EndOfInputException {
		
		String inputString = "6/2 + 1/3 Q - 9/12 fdafda";						// Check quit terminates, even if there is another error afterwards
		
		Fraction frac = fc.evaluate(fc.getValue(), inputString);
		
	}
	
	
	/**
	 * Test that any invalid input (gibberish etc.) throws an appropriate
	 * exception
	 */
	@Test(expected=IllegalArgumentException.class)
	public void invalidInputException() throws EndOfInputException {
		
		String inputString = "4/5 fdas + 9/1";									// Check that the gibberish causes an exception to be thrown
		
		Fraction frac = fc.evaluate(fc.getValue(), inputString);
		
	}
	
	
}
