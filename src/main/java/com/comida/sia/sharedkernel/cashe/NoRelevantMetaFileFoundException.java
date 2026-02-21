package com.comida.sia.sharedkernel.cashe;

public class NoRelevantMetaFileFoundException extends CacheDataNotExistsException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8550718758750450436L;
	
	public NoRelevantMetaFileFoundException() {
		super("No relevant index file has been found");
	}
	
	public NoRelevantMetaFileFoundException(String message) {
		super(message);
	}
	
	public NoRelevantMetaFileFoundException(Throwable throwable) {
		super("No relevant index file has been found", throwable);
	}

	public NoRelevantMetaFileFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
