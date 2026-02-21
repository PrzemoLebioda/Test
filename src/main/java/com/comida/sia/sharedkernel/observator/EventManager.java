package com.comida.sia.sharedkernel.observator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.EmptyListException;

public class EventManager extends AssertionConcern{
	private Map<String, List<EventListener>> listeners;
	
	public EventManager() {
		
	}
	
	public void registerEventType(String eventType) {
		getListeners().put(eventType, new ArrayList<EventListener>());
	}
	
	public void subscribe(String eventType, EventListener listener) throws EmptyListException {
		List<EventListener> eventListeners = getListeners().get(eventType);
		assertNotEmpty(eventListeners, "Event type must be registered");
		
		eventListeners.add(listener);
	}
	
	public void unsubscribe(String eventType, EventListener listener) throws EmptyListException {
		List<EventListener> eventListeners = getListeners().get(eventType);
		assertNotEmpty(eventListeners, "Event type must be registered");
		
		eventListeners.remove(listener);
	}
	
	public <T> void notify(Event<T> event) {
		List<EventListener> eventListeners = getListeners().get(event.getEventType());
		
		for(EventListener eventListener : eventListeners) {
			eventListener.handle(event);
		}
	}
	
	private Map<String, List<EventListener>> getListeners(){
		if(this.listeners == null) {
			this.listeners = new HashMap<>();
		}
		
		return this.listeners;
	}
}
