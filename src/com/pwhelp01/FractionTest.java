/**
 * Software and Programming II
 * Coursework: sp2-cw2-2014
 * 
 * Submitted by: Pete Whelpton (pwhelp01)
 * Due Date: 03/11/2014
 * Lecturer: Keith Mannock
 */

package com.pwhelp01;

/**
 * Created by keith for the second coursework assignment.
 *
 * You need to recode this as a series of JUnit tests
 */

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		
		Fraction expectedResult1 = new Fraction(-1, 20);						// frac1 should simplify to -1/20
		Fraction expectedResult2 = new Fraction(3, -7);							// frac2 should simplify to -3/7
		Fraction expectedResult3 = new Fraction(1, 2);							// frac3 should simplify to 1/2
		Fraction expectedResult4 = new Fraction(3, 7);							// frac4 should simplify to 3/7
		
		assertEquals(expectedResult1, frac1);									// Test frac1
		assertEquals(expectedResult2, frac2);									// Test frac2
		assertEquals(expectedResult3, frac3);									// Test frac3
		assertEquals(expectedResult4, frac4);									// Test frac4
		
	}
	
	
	@Test(expected=ArithmeticException.class)
	public void divideByZeroTest() throws ArithmeticException{
		// test divide by zero - should print an error and exit
        new Fraction(1, 0);
	}
	
	@Test
	public void toStringTest() throws ArithmeticException {
		
		Fraction frac1 = new Fraction(1, 1);									
		
		String expectedResult1 = "1";											// Change to toString() should return whole number
		
		String actualResult1 = frac1.toString();								
		
		assertEquals(expectedResult1, actualResult1);							// Test frac1
	}
	
	@Test
	public void muliplyTest() throws ArithmeticException{
		
		Fraction frac1 = new Fraction(1, 2);
		Fraction frac2 = new Fraction(3, 5);
		Fraction expectedResult = new Fraction(3, 10);
		Fraction actualResult = frac1.multiply(frac2);
		
		assertTrue (expectedResult.equals(actualResult));
	}
	
	
	@Test
	public void equalsTest() throws ArithmeticException{
        // test equals
        assertTrue (new Fraction(1, 2).equals(new Fraction(1, 2)));
        assertTrue (new Fraction(1, 2).equals(new Fraction(3, 6)));
        assertTrue (new Fraction(-1, 2).equals(new Fraction(1, -2)));
        assertTrue (new Fraction(-1, -2).equals(new Fraction(1, 2)));
    }
	
	
	@Test
	public void addTest() throws ArithmeticException {
		
		Fraction frac1 = new Fraction(8, 12);
		Fraction frac2 = new Fraction(4, 12);
		Fraction expectedResult = new Fraction(1, 1);
		Fraction actualResult = frac1.add(frac2);
		
		assertTrue (expectedResult.equals(actualResult));

	}
	
	@Test
	public void subtractTest() throws ArithmeticException{
		
		Fraction frac1 = new Fraction(12, 18);
		Fraction frac2 = new Fraction(3, 7);
		Fraction expectedResult = new Fraction(5, 21);
		Fraction actualResult = (frac1.subtract(frac2));
		
		assertTrue (expectedResult.equals(actualResult));
	}
	
	@Test
	public void divideTest() throws ArithmeticException{
		
		Fraction frac1 = new Fraction(2, 6);
		Fraction frac2 = new Fraction(1, 4);
		Fraction expectedResult = new Fraction(4, 3);
		Fraction actualResult = (frac1.divide(frac2));
		
		assertTrue (expectedResult.equals(actualResult));
	}
	
	@Test
	public void absValueTest() throws ArithmeticException{
		
		Fraction frac1 = new Fraction(4, -5);
		Fraction expectedResult = new Fraction(4, 5);
		Fraction actualResult = (frac1.absValue());
		
		assertTrue (expectedResult.equals(actualResult));
	}

	@Test
	public void negateTest() throws ArithmeticException{
		
		Fraction frac1 = new Fraction(-1, 2);
		Fraction expectedResult1 = new Fraction(1, 2);
		Fraction actualResult1 = frac1.negate();
		
		assertEquals(expectedResult1, actualResult1);
		
	}
}
