package com.comida.sia.sharedkernel.cashe;

public class EmptyCasheException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1127488699481079330L;
	
	public EmptyCasheException(String message) {
		super(message);
	}
	
	public EmptyCasheException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
