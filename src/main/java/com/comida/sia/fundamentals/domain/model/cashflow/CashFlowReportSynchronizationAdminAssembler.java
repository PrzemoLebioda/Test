package com.comida.sia.fundamentals.domain.model.cashflow;

import com.comida.sia.fundamentals.port.acquirer.cashflow.CashFlowReportData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class CashFlowReportSynchronizationAdminAssembler {
	private String tickerSymbol;

	public CashFlowReportSynchronizationAdminAssembler(String tickerSymbol){
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport> assemblyCashFlowAnnualReportSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::new)
				.with(SynchronizationAdmin<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::setNotifier, notifier)
				.with(SynchronizationAdmin<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::setDomainEventBuilder, new CashFlowAnnualReportSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::setTranslator, new CashFlowAnnualReportTranslator())
				.with(SynchronizationAdmin<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::new)
							.with(SynchronizationWorker<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::setRegister, 
									GenericBuilder.of(Register<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::new)
										.with(Register<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::setPeriod, syncState.getPeriod())
										.with(Register<CashFlowReportData, CashFlowAnnualReportTranslator, CashFlowReport>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<CashFlowReportData>::new)
													.with(AscendDiscriminator<CashFlowReportData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport> assemblyCashFlowQuarterReportSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::new)
				.with(SynchronizationAdmin<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::setNotifier, notifier)
				.with(SynchronizationAdmin<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::setDomainEventBuilder, new CashFlowQuarterReportSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::setTranslator, new CashFlowQuarterReportTranslator())
				.with(SynchronizationAdmin<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::new)
							.with(SynchronizationWorker<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::setRegister, 
									GenericBuilder.of(Register<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::new)
										.with(Register<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::setPeriod, syncState.getPeriod())
										.with(Register<CashFlowReportData, CashFlowQuarterReportTranslator, CashFlowReport>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<CashFlowReportData>::new)
													.with(AscendDiscriminator<CashFlowReportData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
