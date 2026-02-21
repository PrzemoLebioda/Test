package com.comida.sia.fundamentals.domain.model.corpoevents.splits;

import com.comida.sia.fundamentals.port.acquirer.corpoevents.splits.SplitEventData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.period.PeriodType;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class CorporateSplitEventSynchronizationAdminAssembler {
	private String tickerSymbol;
	
	public CorporateSplitEventSynchronizationAdminAssembler(String tickerSymbol) {
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent> assemblyCorporateSplitEventSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		AssertionConcern.assertEquals(PeriodType.INFINITY, syncState.getPeriodType(), "Incompatibile period types");
		AssertionConcern.assertEquals(Direction.ASCENDING, syncState.getDirection(), "Incompatibile sorting direction");
		
		return GenericBuilder.of(SynchronizationAdmin<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::new)
				.with(SynchronizationAdmin<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::setNotifier, notifier)
				.with(SynchronizationAdmin<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::setDomainEventBuilder, new CorporateSplitEventSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::setTranslator, new CorporateSplitEventTranslator())
				.with(SynchronizationAdmin<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::new)
							.with(SynchronizationWorker<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::setRegister, 
									GenericBuilder.of(Register<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::new)
										.with(Register<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::setPeriod, syncState.getPeriod())
										.with(Register<SplitEventData, CorporateSplitEventTranslator, CorporateSplitEvent>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<SplitEventData>::new)
													.with(AscendDiscriminator<SplitEventData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
