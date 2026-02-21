package com.comida.sia.sharedkernel.messaging;

public interface TaggedPayload extends Payload{
	public String getTag();
}
