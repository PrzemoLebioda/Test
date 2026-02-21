package com.comida.sia.exchangequote.port.acquirer;

public interface InterdayExchangeQuotesAcquirer {
	public ExchangeQuotesResult gatherInterdayExchangeQuoteFor(String symbol, String appliedPeriod);
}
