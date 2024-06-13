package com.exceptionHandling;

public class InvalidInvestmentAmountException extends RuntimeException {
	
    public InvalidInvestmentAmountException(String message) {
    	
        super(message);
    }
}