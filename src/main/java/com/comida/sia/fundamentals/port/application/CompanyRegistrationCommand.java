package com.comida.sia.fundamentals.port.application;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyRegistrationCommand {
	
	private String tickerSymbol;
	
	
	public CompanyRegistrationCommand() {
		super();
	}
	
	
}
