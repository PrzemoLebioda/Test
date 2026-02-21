 package com.comida.sia.indicators.domain.model.retailsales;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class RetailSalesSynchronizationAdminAssembler {
	
	public RetailSalesSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, RetailSalesTranslator, RetailSales> assemblyRetailSalesMonthlySyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::setDomainEventBuilder, new RetailSalesMonthlySynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::setTranslator, new RetailSalesTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::new)
										.with(Register<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, RetailSalesTranslator, RetailSales>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
