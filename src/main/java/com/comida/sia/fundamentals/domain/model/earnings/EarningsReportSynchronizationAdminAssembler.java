package com.comida.sia.fundamentals.domain.model.earnings;

import com.comida.sia.fundamentals.port.acquirer.earnings.EarningsAnnualReportData;
import com.comida.sia.fundamentals.port.acquirer.earnings.EarningsQuarterlyReportData;
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

public class EarningsReportSynchronizationAdminAssembler {
	
	private String tickerSymbol;
	
	public EarningsReportSynchronizationAdminAssembler(String tickerSymbol) {
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport> assemblyEarningsAnnualReportSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::new)
				.with(SynchronizationAdmin<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::setNotifier, notifier)
				.with(SynchronizationAdmin<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::setDomainEventBuilder, new EarningsAnnualReportSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::setTranslator, new EarningsAnnualReportTranslator())
				.with(SynchronizationAdmin<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::new)
							.with(SynchronizationWorker<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::setRegister, 
									GenericBuilder.of(Register<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::new)
										.with(Register<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::setPeriod, syncState.getPeriod())
										.with(Register<EarningsAnnualReportData, EarningsAnnualReportTranslator, EarningReport>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<EarningsAnnualReportData>::new)
													.with(AscendDiscriminator<EarningsAnnualReportData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport> assemblyEarningsQuarterReportSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		AssertionConcern.assertEquals(PeriodType.INFINITY, syncState.getPeriodType(), "Incompatibile period types");
		AssertionConcern.assertEquals(Direction.ASCENDING, syncState.getDirection(), "Incompatibile sorting direction");
		
		return GenericBuilder.of(SynchronizationAdmin<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::new)
				.with(SynchronizationAdmin<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::setNotifier, notifier)
				.with(SynchronizationAdmin<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::setDomainEventBuilder, new EarningsQuarterReportSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::setTranslator, new EarningsQuarterReportTranslator())
				.with(SynchronizationAdmin<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::new)
							.with(SynchronizationWorker<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::setRegister, 
									GenericBuilder.of(Register<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::new)
										.with(Register<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::setPeriod, syncState.getPeriod())
										.with(Register<EarningsQuarterlyReportData, EarningsQuarterReportTranslator, EarningReport>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<EarningsQuarterlyReportData>::new)
												.with(AscendDiscriminator<EarningsQuarterlyReportData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
												.build())
										.build())
							.build())
				.build();
	}
}
