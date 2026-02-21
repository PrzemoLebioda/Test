package com.comida.sia.sharedkernel.messaging;

public interface Notifier {
	public <P extends SubjectedPayload> void notify(P domainEvent);
}
