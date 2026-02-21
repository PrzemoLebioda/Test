package com.comida.sia.sync.supervision.domain.model;

public class NotSupportedSynchronizationScope extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7275880366561687973L;
	
	public NotSupportedSynchronizationScope(String message) {
		super(message);
	}
	
	public NotSupportedSynchronizationScope(Throwable throwable) {
		super("Not supported synchronization scope", throwable);
	}
	
	public NotSupportedSynchronizationScope(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public NotSupportedSynchronizationScope(SynchronizationScope scope) {
		super("Not supported synchronization scope " + scope);
	}
	
	public NotSupportedSynchronizationScope(SynchronizationScope scope, Throwable throwable) {
		super("Not supported synchronization scope " + scope, throwable);
	}
}
