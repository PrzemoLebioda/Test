package com.comida.sia.sync.supervision.domain.model;

public class NotEligibleException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3233438891230257711L;

	public NotEligibleException(String message) {
		super(message);
	}
	
	public NotEligibleException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
