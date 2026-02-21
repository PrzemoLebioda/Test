package com.comida.sia.sharedkernel;

public class EmptyListException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -175578051622375318L;

	public EmptyListException(String message) {
		super(message);
	}
	
	public EmptyListException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
