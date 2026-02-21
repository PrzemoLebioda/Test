package com.comida.sia.fundamentals.domain.model.balancesheet;

import com.comida.sia.fundamentals.port.acquirer.balancesheet.BalanceSheetReportData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class BalanceSheetReportSynchronizationAdminAssembler {
	private String tickerSymbol;

	public BalanceSheetReportSynchronizationAdminAssembler(String tickerSymbol){
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport> assemblyBalanceSheetAnnualReportSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::new)
				.with(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::setNotifier, notifier)
				.with(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::setDomainEventBuilder, new BalanceSheetAnnualReportSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::setTranslator, new BalanceSheetAnnualReportTranslator())
				.with(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::new)
							.with(SynchronizationWorker<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::setRegister, 
									GenericBuilder.of(Register<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::new)
										.with(Register<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::setPeriod, syncState.getPeriod())
										.with(Register<BalanceSheetReportData, BalanceSheetAnnualReportTranslator, BalanceSheetReport>::setDiscriminator,
											GenericBuilder.of(AscendDiscriminator<BalanceSheetReportData>::new)
												.with(AscendDiscriminator<BalanceSheetReportData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
												.build())
										.build())
							.build())
				.build();
	}
	

	public SynchronizationAdmin<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport> assemblyBalanceSheetQuarterReportSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){

		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
				
		return GenericBuilder.of(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::new)
				.with(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::setNotifier, notifier)
				.with(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::setDomainEventBuilder, new BalanceSheetQuarterReportSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::setTranslator, new BalanceSheetQuarterReportTranslator())
				.with(SynchronizationAdmin<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::new)
							.with(SynchronizationWorker<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::setRegister, 
									GenericBuilder.of(Register<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::new)
										.with(Register<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::setPeriod, syncState.getPeriod())
										.with(Register<BalanceSheetReportData, BalanceSheetQuarterReportTranslator, BalanceSheetReport>::setDiscriminator,
											GenericBuilder.of(AscendDiscriminator<BalanceSheetReportData>::new)
											.with(AscendDiscriminator<BalanceSheetReportData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
												.build())
										.build())
							.build())
				.build();
	}
}
