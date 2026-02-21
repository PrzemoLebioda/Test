package com.comida.sia.sharedkernel.observator;

public interface EventListener {
	<T> void handle(Event<T> event);
}
