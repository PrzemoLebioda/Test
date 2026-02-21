package com.comida.sia.sharedkernel.messaging;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;

import lombok.Getter;

public class Notification<T extends SubjectedPayload> implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4940696964075815514L;
	
	@Getter private UUID id;
	@Getter private Date occurredAt;
	@Getter private Subject subject;
	@Getter private T payload;
	
	@Deprecated
	public Notification(UUID id, Subject subject, T payload) {
		setId(id);
		setOccuranceAtTime();
		setSubject(subject);
		setPayload(payload);
	}
	
	public Notification(UUID id, T payload) {
		setId(id);
		setOccuranceAtTime();
		setSubject(payload.getSubject());
		setPayload(payload);
	}
	
	private void setId(UUID id) {
		AssertionConcern.assertNotNull(id, "Id must be provided in order to create new notivfcation");
		this.id = id;
	}
	
	private void setOccuranceAtTime() {
		this.occurredAt = new Date();
	}
	
	private void setPayload(T payload) {
		AssertionConcern.assertNotNull(payload, "Payload is mandatory in order to create notification");
		this.payload = payload;
	}
	
	private void setSubject(Subject subject) {
		AssertionConcern.assertNotNull(subject, "Subject is mandatory in order to create notification");
		this.subject = subject;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, occurredAt, payload);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Notification<?> other = (Notification<?>) obj;
		return Objects.equals(id, other.id) && Objects.equals(occurredAt, other.occurredAt)
				&& Objects.equals(payload, other.payload);
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", occurredAt=" + occurredAt + ", payload=" + payload + "]";
	}
	
}
