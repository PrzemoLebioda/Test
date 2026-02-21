package com.comida.sia.fundamentals.domain.model.income;

import com.comida.sia.fundamentals.port.acquirer.income.IncomeStatementReportData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class IncomeStatementReportSynchronizationAdminAssembler {

	private String tickerSymbol;
	
	public IncomeStatementReportSynchronizationAdminAssembler(String tickerSymbol) {
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create income statement report synchronization admin");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport> assemblyIncomeStatementAnnualReportSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::new)
				.with(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::setNotifier, notifier)
				.with(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::setDomainEventBuilder, new IncomeStatementAnnualReportSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::setTranslator, new IncomeStatementAnnualReportTranslator())				
				.with(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::new)
							.with(SynchronizationWorker<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::setRegister, 
									GenericBuilder.of(Register<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::new)
										.with(Register<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::setPeriod, syncState.getPeriod())
										.with(Register<IncomeStatementReportData, IncomeStatementAnnualReportTranslator, IncomeStatementReport>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IncomeStatementReportData>::new)
													.with(AscendDiscriminator<IncomeStatementReportData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport> assemblyIncomeStatementQuarterReportSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::new)
				.with(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::setNotifier, notifier)
				.with(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::setDomainEventBuilder, new IncomeStatementQuarterReportSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::setTranslator, new IncomeStatementQuarterReportTranslator())
				.with(SynchronizationAdmin<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::new)
							.with(SynchronizationWorker<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::setRegister, 
									GenericBuilder.of(Register<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::new)
										.with(Register<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::setPeriod, syncState.getPeriod())
										.with(Register<IncomeStatementReportData, IncomeStatementQuarterReportTranslator, IncomeStatementReport>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IncomeStatementReportData>::new)
												.with(AscendDiscriminator<IncomeStatementReportData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
												.build())
										.build())
							.build())
				.build();
	}
}
