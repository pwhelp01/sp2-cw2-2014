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
    
    
    /**
     * Compares two fractions for equality of numerator / denominator
     * 
     * @param o fraction to compare against
     * @return true if both fractions are equal, false if not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;												// Check the object reference - if they are the same, the fractions must be equal!
        if (o == null || getClass() != o.getClass()) return false;				// If the object is null, the fractions can't be equal!

        Fraction fraction = (Fraction) o;										// Instantiate a new Fraction from the object passed in

        if (getDenominator() != fraction.getDenominator()) return false;		// Fractions are always simplified on creation, so if denominators do not match, nor do fractions
        if (getNumerator() != fraction.getNumerator()) return false;			// Check numerators match.  If not, fractions are not equal

        return true;															// If none of the above conditions are false, fractions must be equal
        
    }
    
    
    /**
     * Override the default hashcode
     * 
     * Don't really understand what this does!
     */
    @Override
    public int hashCode() {
        int result = numerator;
        result = 31 * result + denominator;
        return result;
    }

    
    /**
     * Multiply two fractions
     * 
     * @param frac the fraction to multiply by
     * @return new Fraction of the result
     * @throws ArithmeticException if an overflow of int datatype would occur
     */
    public Fraction multiply(Fraction frac) throws ArithmeticException{

        long num = (long)this.getNumerator() * (long)frac.getNumerator();		// Perform calculation and upcast new numerator
        long denom = (long)this.getDenominator() * (long)frac.getDenominator();	// Perform calculation and upcast new denominator
        
        this.testRange(num);													// Test num and throw exception if out of range of int datatype
    	this.testRange(denom);													// Test denom and throw exception if out of range of int datatype
    	
        return new Fraction((int)num, (int)denom);								// Return result as new Fraction
        
    }
    
    
    /**
     * Add two fractions 
     * 
     * @param frac `the fraction to add to
     * @return new Fraction of the result 
     * @throws ArithmeticException if an overflow of int datatype would occur
     */
    public Fraction add(Fraction frac) throws ArithmeticException{
    	
    	long num = ((long)this.getNumerator() * (long)frac.getDenominator())
    			+ ((long)this.getDenominator() * (long)frac.getNumerator());	// Had to change to long datatype as possible to overflow int 
    	long denom = (long)this.getDenominator() * (long)frac.getDenominator();	// Had to use long datatype as possible to overflow int
    	
    	this.testRange(num);													// Test num and throw exception if out of range of int datatype
    	this.testRange(denom);													// Test denom and throw exception if out of range of int datatype
    	
    	return new Fraction((int)num, (int)denom);								// Cast back to int datatype and return result
    	
    }

    
    /**
     * Find the greatest common denominator between two numbers
     * 
     * Keith wrote this - don't really know how it works...
     * 
     * @param a first number
     * @param b second number
     * @return greatest common denominator
     */
    private int myGcd(int a, int b) {
        while (b != 0) {														
            int t = b;
            b = a % b;
            a = t;
        }  
        return a;
    }

    
    /**
     * Subtract one fraction from another
     * 
     * @param frac the fraction to subtract
     * @return new Fraction of the result
     * @throws ArithmeticException if an overflow of the int datatype would occur
     */
	public Fraction subtract(Fraction frac) throws ArithmeticException{

		long num = ((long)this.getNumerator() * (long)frac.getDenominator())
    			- ((long)this.getDenominator() * (long)frac.getNumerator());	// Use long datatype in case of int overflow
		long denom = (long)this.getDenominator() * (long)frac.getDenominator(); // Use long datatype in case of int overflow
		
    	this.testRange(num);													// Test num and throw exception if out of range of int datatype
    	this.testRange(denom);													// Test denom and throw exception if out of range of int datatype
		
		return new Fraction((int)num, (int)denom);								// Cast back to int datatype and return result
	}
	
	
	/**
	 * Divide fraction by another
	 * 
	 * @param frac the fraction to divide by
	 * @return new Fraction of the result
	 * @throws ArithmeticException if an overflow of the int datatype would occur
	 */
	public Fraction divide(Fraction frac) throws ArithmeticException{
		
		long num = ((long)this.getNumerator() * (long)frac.getDenominator());	// Calculate new numerator and upcast to long
		long denom = ((long)this.getDenominator() * (long)frac.getNumerator());	// Calculate new denominator and upcast to long
		
		this.testRange(num);													// Test num and throw exception if out of range of int datatype
    	this.testRange(denom);													// Test denom and throw exception if out of range of int datatype
		
		return new Fraction((int)num, (int)denom);								// Cast back to int datatype and return result
	}
	
	
	/**
	 * Return the absolute (positive) value of a fraction
	 * 
	 * @return absolute value of fraction
	 * @throws ArithmeticException 
	 */
	public Fraction absValue() {
		
		int num = this.getNumerator();											// Get the numerator
		int denom = this.getDenominator();										// Get the denominator
		
		if(num < 0) {															// If the numerator is negative,
			num *= -1;															// flip it to be positive
		}
		if(denom < 0) {															// If the denominator is negative,
			denom *= -1;														// flip it to be positive
		}
		
		return new Fraction(num, denom);										// Return result as new Fraction
		
	}
	
	
	/**
	 * Negate (flip) the sign of the fracion
	 * 
	 * @return negated Fraction
	 */
	public Fraction negate() {
		
		 int num = this.getNumerator() * -1;									// As the constructor ensures only the numerator is signed,
		 int denom = this.getDenominator();										// we only need to flip the numerator
		 
		 return new Fraction(num, denom);										// Return result as new Fraction

		
	}
	
	
	/**
	 * Helper method to check if the value of a long would 
	 * overflow the int datatype
	 * 
	 * @param l value to check for an int overflow
	 * @throws ArithmeticException if l would overflow int datatype
	 */
	private void testRange(long l) throws ArithmeticException {
		
		if((l < Integer.MIN_VALUE) || (l > Integer.MAX_VALUE)) {				// If value is outside the range of the int datatype,
			throw new ArithmeticException("Overflow of type int");				// throw and Arithmetic Exception
		}	

	}
	
	
}
