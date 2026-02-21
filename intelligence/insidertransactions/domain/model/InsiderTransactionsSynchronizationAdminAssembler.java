package com.comida.sia.intelligence.insidertransactions.domain.model;

import com.comida.sia.intelligence.insidertransactions.port.acquirer.InsiderTransactionData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class InsiderTransactionsSynchronizationAdminAssembler {

	private String tickerSymbol;
	
	public InsiderTransactionsSynchronizationAdminAssembler(String tickerSymbol) {
		super();
		setTickerSymbol(tickerSymbol);
	}

	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create insider ransactions report synchronization admin");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction> assemblyInsiderTransactionSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::new)
				.with(SynchronizationAdmin<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::setNotifier, notifier)
				.with(SynchronizationAdmin<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::setDomainEventBuilder, new InsiderTransactionsSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::setTranslator, new InsiderTransactionTranslator())
				.with(SynchronizationAdmin<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::new)
							.with(SynchronizationWorker<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::setRegister, 
									GenericBuilder.of(Register<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::new)
										.with(Register<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::setPeriod, syncState.getPeriod())
										.with(Register<InsiderTransactionData, InsiderTransactionTranslator, InsiderTransaction>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<InsiderTransactionData>::new)
													.with(AscendDiscriminator<InsiderTransactionData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
