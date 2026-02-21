package com.comida.sia.fundamentals.domain.model.sharesoutstanding;

import com.comida.sia.fundamentals.port.acquirer.sharesoutstanding.SharesOutstandingReportData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class SharesOutstandingReportSynchronizationAdminAssembler {
	private String tickerSymbol;
	
	public SharesOutstandingReportSynchronizationAdminAssembler(String tickerSymbol) {
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create shares outstanding report synchronization admin");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport> assemblySharesOutstandingReportSynchronizationAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::new)
				.with(SynchronizationAdmin<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::setNotifier, notifier)
				.with(SynchronizationAdmin<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::setDomainEventBuilder, new SharesOutstandingReportSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::setTranslator, new SharesOutstandingReportTranslator())				
				.with(SynchronizationAdmin<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::new)
							.with(SynchronizationWorker<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::setRegister, 
									GenericBuilder.of(Register<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::new)
										.with(Register<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::setPeriod, syncState.getPeriod())
										.with(Register<SharesOutstandingReportData, SharesOutstandingReportTranslator, SharesOutstandingReport>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<SharesOutstandingReportData>::new)
												.with(AscendDiscriminator<SharesOutstandingReportData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
												.build())
										.build())
							.build())
				.build();
	}
}
