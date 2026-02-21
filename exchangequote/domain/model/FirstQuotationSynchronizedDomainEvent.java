package com.comida.sia.exchangequote.domain.model;

import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;

import lombok.Getter;

public class FirstQuotationSynchronizedDomainEvent implements SubjectedPayload {
	@Getter private String tickerSymbol;
	@Getter private Date firstQuotationTime;
	
	public FirstQuotationSynchronizedDomainEvent(String tickerSymbol, Date firstQuotationTime) {
		setTickerSymbol(tickerSymbol);
		setFirstQuotationDate(firstQuotationTime);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create domain event");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setFirstQuotationDate(Date firstQuotationTime) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create domain event");
		this.firstQuotationTime = firstQuotationTime;
	}
	
	@Override
	public String getClassName() {
		return this.getClass().getName();
	}
	
	@Override
	public Subject getSubject() {
		return Subject.FIRST_QUITATION_SYNCED;
	}
	
	
}
