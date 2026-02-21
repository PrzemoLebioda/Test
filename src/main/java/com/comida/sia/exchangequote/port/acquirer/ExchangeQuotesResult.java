package com.comida.sia.exchangequote.port.acquirer;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ExchangeQuotesResult {
	private String symbol;
	private List<ExchangeQuotationEntry> quotations; 
	
	public ExchangeQuotesResult() {
		super();
	}
}
