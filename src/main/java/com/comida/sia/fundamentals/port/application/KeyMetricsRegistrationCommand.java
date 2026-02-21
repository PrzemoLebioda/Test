package com.comida.sia.fundamentals.port.application;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KeyMetricsRegistrationCommand {
	private String tickerSymbol;
	
	public KeyMetricsRegistrationCommand() {
		super();
	}
}
