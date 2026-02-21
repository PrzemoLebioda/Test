package com.comida.sia.fundamentals.domain.model.corpoevents.earnings;

import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;

import lombok.Getter;

@Getter
public class EarningsCalendarUpdatedDomainEvent implements SubjectedPayload{

	private String tickerSymbol;
	private Date nextReportExpectedDate; 
	
	public EarningsCalendarUpdatedDomainEvent(String tickerSymbol, Date reportDate) {
		setTickerSymbol(tickerSymbol);
		setNextReportExpectedDateReportDate(reportDate);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is necessary in order to create earnings calendar update event");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setNextReportExpectedDateReportDate(Date reportDate) {
		AssertionConcern.assertNotNull(reportDate, "Report Date is necessary in order to create earnings update event");
		this.nextReportExpectedDate = reportDate;
	}
	
	@Override
	public String getClassName() {
		return this.getClass().getName();
	}

	@Override
	public Subject getSubject() {
		return Subject.CALENDAR_EARNINGS_UPDATED;
	}
	
}
