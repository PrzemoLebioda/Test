package com.comida.sia.fundamentals.domain.model.corpoevents.dividend;

import com.comida.sia.fundamentals.port.acquirer.corpoevents.dividend.DividendEventData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class CorporateDividendEventSynchronizationAdminAssembler {
	private String tickerSymbol;

	public CorporateDividendEventSynchronizationAdminAssembler(String tickerSymbol){
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent> assemblyCorporateDividendEventSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::new)
				.with(SynchronizationAdmin<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::setNotifier, notifier)
				.with(SynchronizationAdmin<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::setDomainEventBuilder, new CorporateDividendEventSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::setTranslator, new CorporateDividendEventTranslator())
				.with(SynchronizationAdmin<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::new)
							.with(SynchronizationWorker<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::setRegister, 
									GenericBuilder.of(Register<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::new)
										.with(Register<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::setPeriod, syncState.getPeriod())
										.with(Register<DividendEventData, CorporateDividendEventTranslator, CorporateDividendEvent>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<DividendEventData>::new)
													.with(AscendDiscriminator<DividendEventData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
		
	}
}
