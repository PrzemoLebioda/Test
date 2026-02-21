package com.comida.sia.sharedkernel.cashe;

public class ObsoleteCashFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3355129397915184605L;
	
	public ObsoleteCashFileException(String message) {
		super(message);
	}
	
	public ObsoleteCashFileException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
