package com.comida.sia.sync.supervision.port.application.exchangequote;

import java.util.Date;

public interface InterdayExchangeQuotesSynchronizationSupervisorService extends ExchangeQuotesSynchronizationSupervisorService {
	public void establishStartSynchronizationTime(String tickerSymbol, Date startSyncDate);
}
