package com.comida.sia.sharedkernel.cashe;

public class CacheDataNotExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4431398770642464260L;
	
	CacheDataNotExistsException(String message){
		super(message);
	}

	CacheDataNotExistsException(String message, Throwable throwable){
		super(message, throwable);
	}
}
