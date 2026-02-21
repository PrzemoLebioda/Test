package com.comida.sia.sharedkernel.observator;

import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;

import lombok.Getter;

public abstract class Event <T> extends AssertionConcern{
	@Getter private Date eventDate;
	@Getter private String eventType;
	@Getter private T payload;
	
	public Event(String eventType, T payload) {
		setEventType(eventType);
		setEventDate();
		setPayload(payload);
	}
	
	private void setEventType(String eventType) {
		assertNotEmpty(eventType, "Event type must be provided");
		this.eventType = eventType;
	}
	
	private void setEventDate() {
		this.eventDate = new Date();
	}
	
	private void setPayload(T payload) {
		assertNotNull(payload, "Payload of the event must be provided");
		this.payload = payload;
	}
}
