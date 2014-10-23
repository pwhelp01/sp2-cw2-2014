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
 */
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int num, int denom) throws ArithmeticException {
        if (denom == 0) {
            throw new ArithmeticException("Invalid fraction with denominator 0"); // this should use exceptions
        }
        int gcd = myGcd(num, denom);
        
        /* PDW: Always move the sign to the numerator to make checking
         * for negatives / negating easier */
        
        int newNum = num / gcd;
        int newDenom = denom / gcd;

        
        /* PDW: Always move the sign to the numerator to make checking
         * for negatives / negating easier */
        if(newDenom < 0 && newNum > 0) {										// If the denominator is negative,
        	newNum *= -1;														// flip the numerator
        	newDenom *= -1;														// and flip the denominator
        }
        
        setNumerator(newNum);
        setDenominator(newDenom);
    }

    @Override
    public String toString() {
        
    	if(this.getDenominator() == 1) {
    		return "" + this.getNumerator();
    	}
    	else {
    		return "" + getNumerator() + '/' + getDenominator();
    	}
    }

    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int num) {
        numerator = num;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int num) {
        denominator = num;
    }

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

        int num = this.getNumerator() * frac.getNumerator();
        int denom = this.getDenominator() * frac.getDenominator();
        return new Fraction(num, denom);
    }
    
    public Fraction add(Fraction frac) throws ArithmeticException{
    	
    	int num = (this.getNumerator() * frac.getDenominator())
    			+ (this.getDenominator() * frac.getNumerator());
    	int denom = this.getDenominator() * frac.getDenominator();
    	return new Fraction(num, denom);
    	
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

		int num = (this.getNumerator() * frac.getDenominator())
    			- (this.getDenominator() * frac.getNumerator());
		int denom = this.getDenominator() * frac.getDenominator();
		return new Fraction(num, denom);
	}
	
	public Fraction divide(Fraction frac) throws ArithmeticException{
		
		int num = (this.getNumerator() * frac.getDenominator());
		int denom = (this.getDenominator() * frac.getNumerator());
		return new Fraction(num, denom);
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
	

	
	
}
