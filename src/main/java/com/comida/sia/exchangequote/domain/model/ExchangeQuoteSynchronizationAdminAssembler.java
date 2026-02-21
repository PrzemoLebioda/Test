package com.comida.sia.exchangequote.domain.model;

import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationEntry;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class ExchangeQuoteSynchronizationAdminAssembler {
	private String tickerSymbol;

	public ExchangeQuoteSynchronizationAdminAssembler(String tickerSymbol){
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted> assemblyExchangeQuoteDailyAdjustedSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::new)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::setNotifier, notifier)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::setDomainEventBuilder, new ExchangeQuoteDailyAdjustedSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::setTranslator, new ExchangeQuoteDailyAdjustedTranslator())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::new)
							.with(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::setRegister, 
									GenericBuilder.of(Register<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::new)
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::setPeriod, syncState.getPeriod())
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteDailyAdjustedTranslator, ExchangeQuoteDailyAdjusted>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<ExchangeQuotationEntry>::new)
													.with(AscendDiscriminator<ExchangeQuotationEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted> assemblyExchangeQuoteWeeklyAdjustedSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::new)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::setNotifier, notifier)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::setDomainEventBuilder, new ExchangeQuoteWeeklyAdjustedSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::setTranslator, new ExchangeQuoteWeeklyAdjustedTranslator())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::new)
							.with(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::setRegister, 
									GenericBuilder.of(Register<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::new)
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::setPeriod, syncState.getPeriod())
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteWeeklyAdjustedTranslator, ExchangeQuoteWeeklyAdjusted>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<ExchangeQuotationEntry>::new)
													.with(AscendDiscriminator<ExchangeQuotationEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted> assemblyExchangeQuoteMonthlyAdjustedSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::new)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::setNotifier, notifier)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::setDomainEventBuilder, new ExchangeQuoteMonthlyAdjustedSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::setTranslator, new ExchangeQuoteMonthlyAdjustedTranslator())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::new)
							.with(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::setRegister, 
									GenericBuilder.of(Register<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::new)
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::setPeriod, syncState.getPeriod())
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjustedTranslator, ExchangeQuoteMonthlyAdjusted>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<ExchangeQuotationEntry>::new)
													.with(AscendDiscriminator<ExchangeQuotationEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min> assemblyExchangeQuoteInterday60SyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::new)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::setNotifier, notifier)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::setDomainEventBuilder, new ExchangeQuoteInterday60MinSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::setTranslator, new ExchangeQuoteInterday60MinTranslator())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::new)
							.with(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::setRegister, 
									GenericBuilder.of(Register<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::new)
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::setPeriod, syncState.getPeriod())
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteInterday60MinTranslator, ExchangeQuoteInterday60Min>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<ExchangeQuotationEntry>::new)
													.with(AscendDiscriminator<ExchangeQuotationEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min> assemblyExchangeQuoteInterday30SyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::new)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::setNotifier, notifier)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::setDomainEventBuilder, new ExchangeQuoteInterday30MinSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::setTranslator, new ExchangeQuoteInterday30MinTranslator())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::new)
							.with(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::setRegister, 
									GenericBuilder.of(Register<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::new)
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::setPeriod, syncState.getPeriod())
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteInterday30MinTranslator, ExchangeQuoteInterday30Min>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<ExchangeQuotationEntry>::new)
													.with(AscendDiscriminator<ExchangeQuotationEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min> assemblyExchangeQuoteInterday15SyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::new)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::setNotifier, notifier)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::setDomainEventBuilder, new ExchangeQuoteInterday15MinSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::setTranslator, new ExchangeQuoteInterday15MinTranslator())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::new)
							.with(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::setRegister, 
									GenericBuilder.of(Register<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::new)
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::setPeriod, syncState.getPeriod())
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteInterday15MinTranslator, ExchangeQuoteInterday15Min>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<ExchangeQuotationEntry>::new)
													.with(AscendDiscriminator<ExchangeQuotationEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min> assemblyExchangeQuoteInterday05SyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::new)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::setNotifier, notifier)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::setDomainEventBuilder, new ExchangeQuoteInterday05MinSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::setTranslator, new ExchangeQuoteInterday05MinTranslator())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::new)
							.with(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::setRegister, 
									GenericBuilder.of(Register<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::new)
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::setPeriod, syncState.getPeriod())
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteInterday05MinTranslator, ExchangeQuoteInterday05Min>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<ExchangeQuotationEntry>::new)
													.with(AscendDiscriminator<ExchangeQuotationEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min> assemblyExchangeQuoteInterday01SyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::new)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::setNotifier, notifier)
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::setDomainEventBuilder, new ExchangeQuoteInterday01MinSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::setTranslator, new ExchangeQuoteInterday01MinTranslator())
				.with(SynchronizationAdmin<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::new)
							.with(SynchronizationWorker<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::setRegister, 
									GenericBuilder.of(Register<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::new)
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::setPeriod, syncState.getPeriod())
										.with(Register<ExchangeQuotationEntry, ExchangeQuoteInterday01MinTranslator, ExchangeQuoteInterday01Min>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<ExchangeQuotationEntry>::new)
													.with(AscendDiscriminator<ExchangeQuotationEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
