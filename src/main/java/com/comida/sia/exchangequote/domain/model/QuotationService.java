package com.comida.sia.exchangequote.domain.model;

import java.util.List;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.EmptyListException;
import com.comida.sia.sharedkernel.messaging.Notifier;

public class QuotationService<T extends ExchangeQuote> {
	private String tickerSymbol;
	private List<T> exchangeQuotesList;
	private Notifier notifier;
	
	public QuotationService(
			String tickerSymbol,
			List<T> exchangeQuotesList,
			Notifier notifier) throws EmptyListException {
		
		setTickerSymbol(tickerSymbol);
		setExchangeQuotesList(exchangeQuotesList);
		setNotifier(notifier);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in ordrer to create quotation service");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setExchangeQuotesList(List<T> exchangeQuotesList) throws EmptyListException {
		AssertionConcern.assertNotEmpty(exchangeQuotesList, "Exchange quotes list must not be empty in order to create quotation service");
		this.exchangeQuotesList = exchangeQuotesList;
	}
	
	private void setNotifier(Notifier notifier) {
		AssertionConcern.assertNotNull(notifier, "Notifier is mandatory in order to create quotation service");
		this.notifier = notifier;
	}
	
	public void notifyFirstQuotationDate() {
		ExchangeQuote firstExchangeQuote = exchangeQuotesList.get(0);
		
		if(firstExchangeQuote != null) {
			notifier.notify(buildEvent(firstExchangeQuote));
		}
	}
	
	private FirstQuotationSynchronizedDomainEvent buildEvent(ExchangeQuote firstExchangeQuote) {	
		return new FirstQuotationSynchronizedDomainEvent(
				tickerSymbol,
				firstExchangeQuote.getQuotationTime());
	}
}
