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
 * Custom exception thrown when user indicates that they want to deactivate 
 * the calculator
 * 
 * @author pete
 *
 */
public class EndOfInputException extends Exception {

	public EndOfInputException() {
		
	}

	public EndOfInputException(String arg0) {
		super(arg0);
		
	}

	public EndOfInputException(Throwable arg0) {
		super(arg0);
		
	}

	public EndOfInputException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public EndOfInputException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
