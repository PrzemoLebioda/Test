package com.comida.sia.indicators.domain.model.cpi;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class CpiSynchronizationAdminAssembler {

	public CpiSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex> assemblyCpiMonthlySyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setDomainEventBuilder, new CpiMonthlySynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setTranslator, new ConsumerPriceIndexTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::new)
										.with(Register<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex> assemblyCpiSemiannualSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setDomainEventBuilder, new CpiSemiannualSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setTranslator, new ConsumerPriceIndexTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::new)
										.with(Register<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, ConsumerPriceIndexTranslator, ConsumerPriceIndex>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
