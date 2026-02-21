package com.comida.sia.sharedkernel.cashe;

/**
 * Exception is raised when data in cache file is 
 */
public class ObsoleteCashDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2787681007834233137L;

	public ObsoleteCashDataException(String message) {
		super(message);
	}
	
	public ObsoleteCashDataException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
