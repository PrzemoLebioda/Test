package com.comida.sia.indicators.domain.model.inflation;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class InflationSynchronizationAdminAssembler {
	
	public InflationSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, InflationTranslator, Inflation> assemblyInflationAnnualSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, InflationTranslator, Inflation>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, InflationTranslator, Inflation>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, InflationTranslator, Inflation>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, InflationTranslator, Inflation>::setDomainEventBuilder, new InflationAnnualSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, InflationTranslator, Inflation>::setTranslator, new InflationTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, InflationTranslator, Inflation>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, InflationTranslator, Inflation>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, InflationTranslator, Inflation>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, InflationTranslator, Inflation>::new)
										.with(Register<IndicatorsDataEntry, InflationTranslator, Inflation>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, InflationTranslator, Inflation>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
