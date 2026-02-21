package com.comida.sia.sync.supervision.port.application.exchangequote;

import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;

public interface ExchangeQuotesSynchronizationSupervisorService {
	public void register(String tickerSymbol, AssetType assetType);
	public void deactivateSynchronization(String tickerSymbol, AssetType assetType);
	public void orderSynchronization();
	public void orderSynchronization(String tickerSymbol);
	//public void sheduleSynchronization(String tickerSymbol, Date planedEarningsReleaseDate);
	public void calculateCurrentSyncState(
			Subject subject,
			String tickerSymbol,
			SynchronizationSummary summary);
}
