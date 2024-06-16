package com.exceptionHandling;

public class InvalidInvestmentAmountException extends RuntimeException {
	
    /**
	 * 
	 */
//	private static final long serialVersionUID = 1L;

	public InvalidInvestmentAmountException(String message) {
    	
        super(message);
    }
}