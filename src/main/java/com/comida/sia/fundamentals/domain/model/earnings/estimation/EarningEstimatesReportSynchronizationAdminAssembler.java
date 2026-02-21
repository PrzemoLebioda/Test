package com.comida.sia.fundamentals.domain.model.earnings.estimation;

import com.comida.sia.fundamentals.port.acquirer.earnings.estimation.EarningEstimatesReportData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class EarningEstimatesReportSynchronizationAdminAssembler {
	private String tickerSymbol;
	
	public EarningEstimatesReportSynchronizationAdminAssembler(String tickerSymbol) {
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create earning estimates report synchronization admin");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport> assemblyEarningEstimatesReportSynchronizationAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::new)
				.with(SynchronizationAdmin<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::setNotifier, notifier)
				.with(SynchronizationAdmin<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::setDomainEventBuilder, new EarningEstimatesReportSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::setTranslator, new EarningEstimatesReportTranslator())				
				.with(SynchronizationAdmin<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::new)
							.with(SynchronizationWorker<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::setRegister, 
									GenericBuilder.of(Register<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::new)
										.with(Register<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::setPeriod, syncState.getPeriod())
										.with(Register<EarningEstimatesReportData, EarningEstimatesReportTranslator, EarningEstimatesReport>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<EarningEstimatesReportData>::new)
													.with(AscendDiscriminator<EarningEstimatesReportData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
