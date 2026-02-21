package com.comida.sia.sharedkernel.messaging;

public interface NotificationBuilder<N extends Notification<P>, P extends SubjectedPayload> {
	public N build(P payload);
}
