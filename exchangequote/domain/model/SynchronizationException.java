package com.comida.sia.exchangequote.domain.model;

public class SynchronizationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6500349920479495231L;
	
	public SynchronizationException(String errorMessage) {
		super(errorMessage);
	}

}
