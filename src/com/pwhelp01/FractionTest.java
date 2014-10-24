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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the Fraction Calculator
 * 
 * @author Keith Mannock
 * @author Pete Whelpton
 *
 */
public class FractionTest {
    
	/**
	 * Test that the sign (-) is only ever applied to the numerator
	 */
	@Test
	public void testSign() {
		
		Fraction frac1 = new Fraction(-1, 20);									// Test when num is negative and denom is positive
		Fraction frac2 = new Fraction(3,-7);									// Test when num is positive and denom is negative
		Fraction frac3 = new Fraction(-1,-2);									// Test when both num and denom are negative
		Fraction frac4 = new Fraction(24, 56);									// Test when both num and denom are positive
		Fraction frac5 = new Fraction(1, Integer.MIN_VALUE + 1);				// Test an extreme value
		
		Fraction expectedResult1 = new Fraction(-1, 20);						// frac1 should simplify to -1/20
		Fraction expectedResult2 = new Fraction(-3, 7);							// frac2 should simplify to -3/7
		Fraction expectedResult3 = new Fraction(1, 2);							// frac3 should simplify to 1/2
		Fraction expectedResult4 = new Fraction(3, 7);							// frac4 should simplify to 3/7
		Fraction expectedResult5 = new Fraction(-1, 2147483647);				// frac5 should simplify to -1, 2147483647
		
		assertEquals(expectedResult1, frac1);									// Test frac1
		assertEquals(expectedResult2, frac2);									// Test frac2
		assertEquals(expectedResult3, frac3);									// Test frac3
		assertEquals(expectedResult4, frac4);									// Test frac4
		assertEquals(expectedResult5, frac5);									// Test frac5
		
	}
	
	
	/**
	 * Test denom of zero throws an exception
	 * 
	 * @throws ArithmeticException
	 */
	@Test(expected=ArithmeticException.class)
	public void divideByZeroTest() throws ArithmeticException{
		
        Fraction frac1 = new Fraction(1, 0);									// Should throw exception
        
	}
	
	
	/**
	 * Test for overflow exceptions
	 * <p>
	 * Testing showed that when adding / subtracting fractions with
	 * large numerators / denominators, it was possible to overflow the int
	 * datatype.  Java does not throw an error - it simply 'wraps round',
	 * resulting in incorrect results.
	 * 
	 * Tried to resolve the isse by using the 'Upcasting' solution described 
	 * here: https://www.securecoding.cert.org/confluence/display/java/NUM00-J.+Detect+or+prevent+integer+overflow
	 * 
	 */
	@Test(expected=ArithmeticException.class)
	public void overflowAddTest() throws ArithmeticException {
		
		Fraction frac1 = new Fraction(1, Integer.MAX_VALUE);					// Create a fraction 1 / 2147483647
		Fraction frac2 = new Fraction(10, Integer.MAX_VALUE);					// Create another fraction 10 / 2147483647
		
		Fraction frac3 = frac1.add(frac2);										// Add the fractions - should cause an overflow exception to be thrown
			
	}
	
	@Test(expected=ArithmeticException.class)
	public void overflowSubtractTest() throws ArithmeticException {
		
		Fraction frac1 = new Fraction(1, Integer.MAX_VALUE);					// Create a fraction 1 / 2147483647
		Fraction frac2 = new Fraction(10, Integer.MAX_VALUE);					// Create another fraction 10 / 2147483647
		
		Fraction frac3 = frac1.subtract(frac2);									// Subtract the fractions - should cause an overflow exception to be thrown
			
	}
	
	@Test(expected=ArithmeticException.class)
	public void overflowMultiplyTest() throws ArithmeticException {
		
		Fraction frac1 = new Fraction(1, Integer.MAX_VALUE);					// Create a fraction 1 / 2147483647
		Fraction frac2 = new Fraction(10, Integer.MAX_VALUE);					// Create another fraction 10 / 2147483647
		
		Fraction frac3 = frac1.multiply(frac2);									// Multiply the fractions - should cause an overflow exception to be thrown
			
	}
	
	@Test(expected=ArithmeticException.class) 
	public void overflowDivideTest() throws ArithmeticException {
		
		Fraction frac1 = new Fraction(1, Integer.MAX_VALUE);					// Create a fraction 1 / 2147483647
		Fraction frac2 = new Fraction(10, Integer.MAX_VALUE);					// Create another fraction 10 / 2147483647
		
		Fraction frac3 = frac1.divide(frac2);									// Divide the fractions - should cause an overflow exception to be thrown
		
	}
	
	
	/**
	 * Test the toString method returns the correct simplified fraction,
	 * including returning whole numbers
	 */
	@Test
	public void toStringTest()  {
		
		Fraction frac1 = new Fraction(1, 1);									// Test with whole number
		Fraction frac2 = new Fraction(0, 1);									// Test when fraction is zero
		Fraction frac3 = new Fraction(24, 72);									// Test with positive number
		Fraction frac4 = new Fraction(-4, 10);									// Test a negative fraction
		Fraction frac5 = new Fraction(10, -5);									// Test negative whole number
		Fraction frac6 = new Fraction(1, Integer.MAX_VALUE);					// Test with extreme number
		
		String expectedResult1 = "1";											// Change to toString() should return whole number
		String expectedResult2 = "0";											// Fraction should return whole number 0
		String expectedResult3 = "1/3";											// Fraction should get simplified
		String expectedResult4 = "-2/5";										// Fraction should get simplified
		String expectedResult5 = "-2";											// Should return a whole negative number
		String expectedResult6 = "1/2147483647";								// Should return the boundary number of the integer datatype
		
		String actualResult1 = frac1.toString();								// frac1 should simplify to 1
		String actualResult2 = frac2.toString();								// frac2 should simplify to 0
		String actualResult3 = frac3.toString();								// frac3 should simplify to 1/3
		String actualResult4 = frac4.toString();								// frac4 should simplify to -2/5
		String actualResult5 = frac5.toString();								// frac5 should simplify to -2
		String actualResult6 = frac6.toString();								// frac6 should not simplify from 1/2147483647
		
		assertEquals(expectedResult1, actualResult1);							// Test frac1
		assertEquals(expectedResult2, actualResult2);							// Test frac2
		assertEquals(expectedResult3, actualResult3);							// Test frac3
		assertEquals(expectedResult4, actualResult4);							// Test frac4
		assertEquals(expectedResult5, actualResult5);							// Test frac5
		assertEquals(expectedResult6, actualResult6);							// Test frac6
		
	}
	
	
	/**
	 * Test we can / cannot instantiate Fraction with parameters on / outside
	 * the int datatypes boundaries.  Integer.MIN_VALUE causes problems with 
	 * simplifying and negating. (Due to the datatype holding 1 more 
	 * negative than positive number)
	 * 
	 * @throws ArithmeticException
	 */
	@Test
	public void maxNum() {
		
		Fraction frac1 = new Fraction(Integer.MAX_VALUE, 100);					// Test when num is at max value, should not throw exception
		
	}
	
	@Test(expected=ArithmeticException.class)
	public void minNum() throws ArithmeticException {
		
		Fraction frac1 = new Fraction(Integer.MIN_VALUE, 100);					// Test when num is at min value, should throw exception
		
	}
	
	@Test
	public void maxDenom() throws ArithmeticException {
		
		Fraction frac1 = new Fraction(1, Integer.MAX_VALUE);					// Test when denom is at max value, should not throw exception
		
	}
	
	@Test(expected=ArithmeticException.class)
	public void minDenom() throws ArithmeticException {
		
		Fraction frac1 = new Fraction(1, Integer.MIN_VALUE);					// Test when denom is at min value, should throw exception
		
	}
	
	
	/**
	 * Test multiply() method
	 * 
	 */
	@Test
	public void muliplyTest() {
		
		Fraction frac1 = new Fraction(3, 1);									// Test with positive fraction (that simplifies to a whole number)
		Fraction frac2 = new Fraction(3, 5);									// Test with another positive fraction
		Fraction frac3 = new Fraction(-3, 7);									// Test with negative fraction
		Fraction frac4 = new Fraction(4, -9);									// Test with another negative fraction
		Fraction frac5 = new Fraction(-3, -5);									// Test with double negative (positive) fraction
		
		Fraction expectedResult1 = new Fraction(9, 5);							// Should simplify to 9/5
		Fraction expectedResult2 = new Fraction(-9,35);							// Should simplify to -9/35
		Fraction expectedResult3 = new Fraction(4, 21);							// Should simplify to 4/21
		Fraction expectedResult4 = new Fraction(-4,15);							// Should simplify to -4/15
		Fraction expectedResult5 = new Fraction(9, 5);							// Should simplify to 9/5
		
		Fraction actualResult1 = frac1.multiply(frac2);							// frac1 * frac2
		Fraction actualResult2 = frac2.multiply(frac3);							// frac2 * frac3
		Fraction actualResult3 = frac3.multiply(frac4);							// frac3 * frac4
		Fraction actualResult4 = frac4.multiply(frac5);							// frac4 * frac5
		Fraction actualResult5 = frac5.multiply(frac1);							// frac5 * frac1
		
		assertTrue(expectedResult1.equals(actualResult1));						// Test frac1 * frac2
		assertTrue(expectedResult2.equals(actualResult2));						// Test frac2 * frac3
		assertTrue(expectedResult3.equals(actualResult3));						// Test frac3 * frac4
		assertTrue(expectedResult4.equals(actualResult4));						// Test frac4 * frac5
		assertTrue(expectedResult5.equals(actualResult5));						// Test frac5 * frac1
		
	}
	
	
	/**
	 * Test equals() method
	 */
	@Test
	public void equalsTest() {
       
        assertTrue(new Fraction(1, 2).equals(new Fraction(1, 2)));				// Test a straight forward equals		
        assertTrue(new Fraction(1, 2).equals(new Fraction(3, 6)));				// Test an equals operation involving simplifying 
        assertTrue(new Fraction(-1, 2).equals(new Fraction(1, -2)));			// Test where negative two fractions are simplified
        assertTrue(new Fraction(-1, -2).equals(new Fraction(1, 2)));			// Test where a double negative fraction should be simplified to positive
        assertFalse(new Fraction(10, 3).equals(new Fraction(99, 3)));			// Test method isn't returning true for everything!
        
    }
	
	
	/**
	 * Test add() method
	 * 
	 */
	@Test
	public void addTest() {
		
		Fraction frac1 = new Fraction(8, 12);									// Test with a positive fraction
		Fraction frac2 = new Fraction(4, 12);									// Test with another positive fraction
		Fraction frac3 = new Fraction(-4, 7);									// Test where num is negative
		Fraction frac4 = new Fraction(19, -22);									// Test where denom is negative
		Fraction frac5 = new Fraction(-7, -10);									// Test where num and denom and both negative
		
		Fraction expectedResult1 = new Fraction(1, 1);							// Should simplify to 1
		Fraction expectedResult2 = new Fraction(-5, 21);						// Should simplify to -5, 21
		Fraction expectedResult3 = new Fraction(-221, 154);						// Should simplify to -221, 154
		Fraction expectedResult4 = new Fraction(-9, 55);						// Should simplify to -9, 55
		
		Fraction actualResult1 = frac1.add(frac2);								// frac1 + frac2
		Fraction actualResult2 = frac2.add(frac3);								// frac2 + frac3
		Fraction actualResult3 = frac3.add(frac4);								// frac3 + frac4
		Fraction actualResult4 = frac4.add(frac5);								// frac4 + frac5
		
		assertTrue(expectedResult1.equals(actualResult1));						// Test frac1 + frac2
		assertTrue(expectedResult2.equals(actualResult2));						// Test frac2 + frac3
		assertTrue(expectedResult3.equals(actualResult3));						// Test frac3 + frac4
		assertTrue(expectedResult4.equals(actualResult4));						// Test frac4 + frac4

	}
	
	
	/**
	 * Test the subract() method
	 * 
	 */
	@Test
	public void subtractTest() {
		
		Fraction frac1 = new Fraction(12, 18);									// Test with a positive fraction
		Fraction frac2 = new Fraction(3, 7);									// Test with another positive fraction
		Fraction frac3 = new Fraction(-88, 102);								// Test where num is negative
		Fraction frac4 = new Fraction(69, -55);									// Test where denom is negative
		Fraction frac5 = new Fraction(-3, -9);									// Test where both num and denom is negative
		
		Fraction expectedResult1 = new Fraction(5, 21);							// Should simplify to 5/21
		Fraction expectedResult2 = new Fraction(461, 357);						// Should simplify to 461/357
		Fraction expectedResult3 = new Fraction(1099, 2805);					// Should simplify to 1099/2085
		Fraction expectedResult4 = new Fraction(-262, 165);						// Should simplify to -262, 165
		
		Fraction actualResult1 = (frac1.subtract(frac2));						// frac1 - frac2
		Fraction actualResult2 = (frac2.subtract(frac3));						// frac2 - frac3
		Fraction actualResult3 = (frac3.subtract(frac4));						// frac3 - frac4
		Fraction actualResult4 = (frac4.subtract(frac5));						// frac4 - frac5
		
		assertTrue(expectedResult1.equals(actualResult1));						// Test frac1 - frac2
		assertTrue(expectedResult2.equals(actualResult2));						// Test frac2 - frac3
		assertTrue(expectedResult3.equals(actualResult3));						// test frac3 - frac4
		assertTrue(expectedResult4.equals(actualResult4));						// Test frac4 - frac5
		
	}
	
	
	/**
	 * Test divide() method
	 * 
	 */
	@Test
	public void divideTest() {
		
		Fraction frac1 = new Fraction(2, 6);									// Test with positive fraction
		Fraction frac2 = new Fraction(1, 4);									// Test with another positive fraction
		Fraction frac3 = new Fraction(-875, 109);								// Test where num is negative
		Fraction frac4 = new Fraction(99, -999);								// Test where denom is negative
		Fraction frac5 = new Fraction(-45, -87);								// Test where both num and denom are negative
		
		Fraction expectedResult1 = new Fraction(4, 3);							// Should simplify to 4/3
		Fraction expectedResult2 = new Fraction(-109, 3500);					// Should simplify to -109/3500
		Fraction expectedResult3 = new Fraction(97125, 1199);					// Should simplify to 97125/1199
		Fraction expectedResult4 = new Fraction(-319, 1665);					// Should simplify to -319/1665
		
		Fraction actualResult1 = (frac1.divide(frac2));							// frac1 / frac2
		Fraction actualResult2 = (frac2.divide(frac3));							// frac2 / frac3
		Fraction actualResult3 = (frac3.divide(frac4));							// frac3 / frac4
		Fraction actualResult4 = (frac4.divide(frac5));							// frac4 / frac5
		
		assertTrue(expectedResult1.equals(actualResult1));						// Test frac1 / frac2
		assertTrue(expectedResult2.equals(actualResult2));						// Test frac2 / frac3
		assertTrue(expectedResult3.equals(actualResult3));						// Test frac3 / frac4
		assertTrue(expectedResult4.equals(actualResult4));						// Test frac4 / frac5
		
	}
	
	
	/**
	 * Test absValue() method
	 * 
	 */
	@Test
	public void absValueTest() {
		
		Fraction frac1 = new Fraction(4, -5);									// Test where denom is negative
		Fraction frac2 = new Fraction(-1, 3);									// Test where num is negative
		Fraction frac3 = new Fraction(-99, -101);								// Test where num and denom are negative
		Fraction frac4 = new Fraction(-3, 1);									// Test where fraction can simplify to a whole number
		Fraction frac5 = new Fraction(1, Integer.MIN_VALUE + 1);				// Test an extreme value
		
		Fraction expectedResult1 = new Fraction(4, 5);							// Resulting Fraction should be positive
		Fraction expectedResult2 = new Fraction(1, 3);							// Resulting Fraction should be positive
		Fraction expectedResult3 = new Fraction(99, 101);						// Resulting Fraction should be positive
		Fraction expectedResult4 = new Fraction(3, 1);							// Resulting Fraction should be positive
		Fraction expectedResult5 = new Fraction(1, 2147483647);					// Resulting Fraction should be positive
		
		Fraction actualResult1 = frac1.absValue();								// Get absolute value of frac1
		Fraction actualResult2 = frac2.absValue();								// Get absolute value of frac2
		Fraction actualResult3 = frac3.absValue();								// Get absolute value of frac3
		Fraction actualResult4 = frac4.absValue();								// Get absolute value of frac4
		Fraction actualResult5 = frac5.absValue();								// Get absolute value of frac5
		
		assertTrue(expectedResult1.equals(actualResult1));						// Test absValue(frac1) returns correct result
		assertTrue(expectedResult2.equals(actualResult2));						// Test absValue(frac2) returns correct result
		assertTrue(expectedResult3.equals(actualResult3));						// Test absValue(frac3) returns correct result
		assertTrue(expectedResult4.equals(actualResult4));						// Test absValue(frac4) returns correct result
		assertTrue(expectedResult5.equals(actualResult5));						// Test absValue(frac5) returns correct result
	}

	
	/**
	 * Test negate() method
	 * 
	 */
	@Test
	public void negateTest() {
		
		Fraction frac1 = new Fraction(-1, 2);									// Test where num is negative
		Fraction frac2 = new Fraction(7,999);									// Test where fraction is positive
		Fraction frac3 = new Fraction(17, -56);									// Test where denom is negative
		Fraction frac4 = new Fraction(-10, -82);								// Test where both num and denom are negative (so fraction is positive)
		Fraction frac5 = new Fraction(1, Integer.MIN_VALUE + 1);				// Test extreme value
		Fraction frac6 = new Fraction(1, Integer.MAX_VALUE);					// Test extreme value
		
		Fraction expectedResult1 = new Fraction(1, 2);							// Fraction should be flipped to positive
		Fraction expectedResult2 = new Fraction(-7, 999);						// Fraction should be flipped to negative
		Fraction expectedResult3 = new Fraction(17, 56);						// Fraction should be flipped to positive
		Fraction expectedResult4 = new Fraction(-5, 41);						// Fraction should be flipped to negative
		Fraction expectedResult5 = new Fraction(1, 2147483647);					// Fraction should be flipped to positive
		Fraction expectedResult6 = new Fraction(-1, 2147483647);				// Fraction should be flipped to negative
		
		Fraction actualResult1 = frac1.negate();								// Flip frac1
		Fraction actualResult2 = frac2.negate();								// Flip frac2
		Fraction actualResult3 = frac3.negate();								// Flip frac3
		Fraction actualResult4 = frac4.negate();								// Flip frac4
		Fraction actualResult5 = frac5.negate();								// Flip frac5
		Fraction actualResult6 = frac6.negate();								// Flip frac6
			
		assertEquals(expectedResult1, actualResult1);							// Test negate(frac1) returns correct result
		assertEquals(expectedResult2, actualResult2);							// Test negate(frac2) returns correct result
		assertEquals(expectedResult3, actualResult3);							// Test negate(frac3) returns correct result
		assertEquals(expectedResult4, actualResult4);							// Test negate(frac4) returns correct result
		assertEquals(expectedResult5, actualResult5);							// Test negate(frac5) returns correct result
		assertEquals(expectedResult6, actualResult6);							// Test negate(frac6) returns correct result
		
	}
}
