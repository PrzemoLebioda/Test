package com.comida.sia.indicators.domain.model.gdp;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class GdpSynchronizationAdminAssembler {

	public GdpSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct> assemblyGdpAnnualSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setDomainEventBuilder, new GdpAnnualSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setTranslator, new GrossDomesticProductTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::new)
										.with(Register<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct> assemblyGdpQuarterSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setDomainEventBuilder, new GdpQuarterSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setTranslator, new GrossDomesticProductTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::new)
										.with(Register<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, GrossDomesticProductTranslator, GrossDomesticProduct>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita> assemblyGdpPerCapitaAnnualSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setDomainEventBuilder, new GdpPerCapitaAnnualSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setTranslator, new GrossDomesticProductPerCapitaTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::new)
										.with(Register<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita> assemblyGdpPerCapitaQuarterSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setDomainEventBuilder, new GdpPerCapitaQuarterSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setTranslator, new GrossDomesticProductPerCapitaTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::new)
										.with(Register<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, GrossDomesticProductPerCapitaTranslator, GrossDomesticProductPerCapita>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
