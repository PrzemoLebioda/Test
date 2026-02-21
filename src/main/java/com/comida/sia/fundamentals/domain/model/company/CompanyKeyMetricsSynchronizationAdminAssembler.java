package com.comida.sia.fundamentals.domain.model.company;

import com.comida.sia.fundamentals.port.acquirer.company.CompanyDetailsData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class CompanyKeyMetricsSynchronizationAdminAssembler {
	private String tickerSymbol;
	
	public CompanyKeyMetricsSynchronizationAdminAssembler(String tickerSymbol) {
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics> assemblyCompanyKeyMetricsSynchronizationAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		return GenericBuilder.of(SynchronizationAdmin<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::new)
				.with(SynchronizationAdmin<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::setNotifier, notifier)
				.with(SynchronizationAdmin<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::setDomainEventBuilder, new CompanyKeyMetricsSynchronizedDomainEventBuilder())
				//.with(CompanyKeyMetricsSynchronizationAdmin::setScope, SynchronizationScope.COMPANY_KEY_METRICS.getName())
				.with(SynchronizationAdmin<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::setTranslator, new CompanyKeyMetricsTranslator())
				.with(SynchronizationAdmin<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::new)
							.with(SynchronizationWorker<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::setRegister, 
									GenericBuilder.of(Register<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::new)
										.with(Register<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::setPeriod, syncState.getPeriod())
										.with(Register<CompanyDetailsData, CompanyKeyMetricsTranslator, CompanyKeyMetrics>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<CompanyDetailsData>::new)
													.with(AscendDiscriminator<CompanyDetailsData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
