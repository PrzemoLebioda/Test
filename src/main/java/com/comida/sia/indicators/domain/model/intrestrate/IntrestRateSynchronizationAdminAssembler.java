package com.comida.sia.indicators.domain.model.intrestrate;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class IntrestRateSynchronizationAdminAssembler {

	public IntrestRateSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate> assemblyIntrestRateMonthlySyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setDomainEventBuilder, new IntrestRateMonthlySynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setTranslator, new IntrestRateTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::new)
										.with(Register<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate> assemblyIntrestRateWeeklySyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setDomainEventBuilder, new IntrestRateWeeklySynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setTranslator, new IntrestRateTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::new)
										.with(Register<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate> assemblyIntrestRateDailySyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setDomainEventBuilder, new IntrestRateDailySynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setTranslator, new IntrestRateTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::new)
										.with(Register<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, IntrestRateTranslator, IntrestRate>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
