package com.comida.sia.indicators.domain.model.unemployment;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class UnemploymentRateSynchronizationAdminAssembler {

	public UnemploymentRateSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate> assemblyUnemploymentRateMonthlySyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::setDomainEventBuilder, new UnemploymentRateMonthlySynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::setTranslator, new UnemploymentRateTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::new)
										.with(Register<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, UnemploymentRateTranslator, UnemploymentRate>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
