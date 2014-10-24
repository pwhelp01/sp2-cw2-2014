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
 * Represents part of a whole or a number of equal parts
 * 
 * @author Keith Mannock
 * @author Pete Whelpton
 *
 */
public class Fraction {
	
	/* Attributes */
    private int numerator;
    private int denominator;

    /* Methods*/
    /**
     * Constructor for a new fraction.  Fraction will be simplified.
     * 
     * @param num numerator
     * @param denom denominator
     * @throws ArithmeticException if the denom is zero or equal to
     * Integer.MIN_VALUE
     */
    public Fraction(int num, int denom) throws ArithmeticException {
        
    	/* Validate input parameters */
    	
    	//Check for divide by zero error
    	if (denom == 0) {														// Denom = 0, would cause Divide By Zero error
            throw new ArithmeticException("Invalid fraction with "
            		+ "denominator 0"); 										// this should use exceptions
        }
    	
    	// Discovered during unit testing that Integer.MIN_VALUE
    	// causes problems with simplifying and negating as it is -2147483648
    	// and can't be negated as the Max value = +2147483647
    	if(num == Integer.MIN_VALUE || denom == Integer.MIN_VALUE) {			// Check if parameters are on or beyond boundries for type int
    		throw new ArithmeticException("Parameter out of range: must be "
    				+ "between -2147483647 and 2147483647");					// Parameters out of range so throw exception
    	}
    	
    	
    	/* Create Fraction */
        int gcd = myGcd(num, denom);											// Calculate greatest common denominator
        
        // PDW: Always move the sign to the numerator to make checking
        // for negatives / negating easier
        int newNum = num / gcd;													// Simplify the numerator
        int newDenom = denom / gcd;												// Simplify the denominator

        if(newDenom < 0 && newNum >= 0) {										// Then, if the denominator is negative,
        	newNum *= -1;														// flip the numerator
        	newDenom *= -1;														// and flip the denominator
        }
        
        // Set instance attributes
        setNumerator(newNum);													// Assign the simplified num to the instance variable
        setDenominator(newDenom);												// Assign the simplified denom to the instance variable
        
    }

    
    /**
     * Returns a string representation of the fraction in the format
     * num/denom
     * 
     * @return fraction as a string num/denom
     */
    @Override
    public String toString() {
        
    	/* Return a whole number if fraction can be simplified as such */
    	if(this.getDenominator() == 1) {										// Check if fraction is a whole number
    		return "" + this.getNumerator();									// If it is, return just the numerator
    	}
    	else {																	// If not, 
    		return "" + getNumerator() + '/' + getDenominator();				// return the fraction in the format num/denom
    	}
    }
    
    /* Getters and setters */
    /**
     * Get the fraction's numerator
     * @return numerator
     */
    public int getNumerator() {
        return numerator;
    }
    
    /**
     * Set the fraction's numerator
     * @param num numerator
     */
    public void setNumerator(int num) {
        numerator = num;
    }
    
    /**
     * Get the fraction's denominator
     * @return denominator
     */
    public int getDenominator() {
        return denominator;
    }
    
    /**
     * Set the fraction's denominator
     * @param num denominator
     */
    public void setDenominator(int num) {
        denominator = num;
    }
    
    
    /**
     * Compares two fractions for equality of numerator / denominator
     * 
     * @param o fraction to compare against
     * @return true if both fractions are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fraction fraction = (Fraction) o;

        if (getDenominator() != fraction.getDenominator()) return false;
        if (getNumerator() != fraction.getNumerator()) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    
    public Fraction multiply(Fraction frac) throws ArithmeticException{

        long num = (long)this.getNumerator() * (long)frac.getNumerator();
        long denom = (long)this.getDenominator() * (long)frac.getDenominator();
        
        this.testRange(num);													// Test num and throw exception if out of range of int datatype
    	this.testRange(denom);													// Test denom and throw exception if out of range of int datatype
    	
        return new Fraction((int)num, (int)denom);
        
    }
    
    public Fraction add(Fraction frac) throws ArithmeticException{
    	
    	long num = ((long)this.getNumerator() * (long)frac.getDenominator())
    			+ ((long)this.getDenominator() * (long)frac.getNumerator());	// Had to change to long datatype as possible to overflow int 
    	long denom = (long)this.getDenominator() * (long)frac.getDenominator();	// Had to use long datatype as possible to overflow int
    	
    	this.testRange(num);													// Test num and throw exception if out of range of int datatype
    	this.testRange(denom);													// Test denom and throw exception if out of range of int datatype
    	
    	return new Fraction((int)num, (int)denom);								// Cast back to int datatype and return result
    	
    }

    private int myGcd(int a, int b) {
        while (b != 0) {														
            int t = b;
            b = a % b;
            a = t;
        }  
        return a;
    }

	public Fraction subtract(Fraction frac) throws ArithmeticException{

		long num = ((long)this.getNumerator() * (long)frac.getDenominator())
    			- ((long)this.getDenominator() * (long)frac.getNumerator());	// Use long datatype in case of int overflow
		long denom = (long)this.getDenominator() * (long)frac.getDenominator(); // Use long datatype in case of int overflow
		
    	this.testRange(num);													// Test num and throw exception if out of range of int datatype
    	this.testRange(denom);													// Test denom and throw exception if out of range of int datatype
		
		return new Fraction((int)num, (int)denom);								// Cast back to int datatype and return result
	}
	
	
	public Fraction divide(Fraction frac) throws ArithmeticException{
		
		long num = ((long)this.getNumerator() * (long)frac.getDenominator());
		long denom = ((long)this.getDenominator() * (long)frac.getNumerator());
		
		this.testRange(num);													// Test num and throw exception if out of range of int datatype
    	this.testRange(denom);													// Test denom and throw exception if out of range of int datatype
		
		return new Fraction((int)num, (int)denom);								// Cast back to int datatype and return result
	}
	
	public Fraction absValue() throws ArithmeticException{
		
		int num = this.getNumerator();
		int denom = this.getDenominator();
		
		if(num < 0) {
			num *= -1;
		}
		if(denom < 0) {
			denom *= -1;
		}
		
		return new Fraction(num, denom);
		
	}
	
	public Fraction negate() {
		
		 int num = this.getNumerator() * -1;
		 int denom = this.getDenominator();
		 
		 return new Fraction(num, denom);

		
	}
	
	
	public void testRange(long l) throws ArithmeticException {
		
		if((l < Integer.MIN_VALUE) || (l > Integer.MAX_VALUE)) {
			throw new ArithmeticException("Overflow of type int");
		}	

	}
	
	
}
