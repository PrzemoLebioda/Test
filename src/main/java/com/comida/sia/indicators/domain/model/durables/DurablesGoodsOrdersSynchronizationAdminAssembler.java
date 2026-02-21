package com.comida.sia.indicators.domain.model.durables;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class DurablesGoodsOrdersSynchronizationAdminAssembler {
	
	public DurablesGoodsOrdersSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders> assemblyDurablesMonthlySyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::setDomainEventBuilder, new DurableGoodsOrdersMonthlySynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::setTranslator, new DurableGoodsOrdersTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::new)
										.with(Register<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, DurableGoodsOrdersTranslator, DurableGoodsOrders>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
