package com.comida.sia.sharedkernel.cashe;

public class LackOfNewDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7095264230770546622L;
	
	public LackOfNewDataException(String message) {
		super(message);
	}

	public LackOfNewDataException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
