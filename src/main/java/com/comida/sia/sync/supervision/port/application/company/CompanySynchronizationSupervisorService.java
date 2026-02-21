package com.comida.sia.sync.supervision.port.application.company;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SynchronizationSummary;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;

public interface CompanySynchronizationSupervisorService {
	public void register(String tickerSymbol, Date ipoDate, AssetType assetType) throws ParseException;
	public void deactivateSynchronization(String tickerSymbol, Date ipoDate, AssetType assetType) throws ParseException;
	public void orderSynchronization();
	public void orderSynchronization(String tickerSymbol) ;
	public void sheduleSynchronization(String tickerSymbol, Date planedEarningsReleaseDate);
	public void calculateCurrentSyncState(
			Subject subject,
			String tickerSymbol,
			SynchronizationSummary summary);
}
