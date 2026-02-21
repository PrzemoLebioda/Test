package com.comida.sia.fundamentals.domain.model.corpoevents.earnings;

import com.comida.sia.fundamentals.port.acquirer.corpoevents.earnings.EarningEventData;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class CorporateEarningEventSynchronizationAdminAssembler {
	
	public SynchronizationAdmin<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent> assemblyCorporateEarningEventSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::new)
				.with(SynchronizationAdmin<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::setNotifier, notifier)
				.with(SynchronizationAdmin<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::setDomainEventBuilder, new EarningsCalendarSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::setTranslator, new CorporateEarningEventTranslator(notifier))
				.with(SynchronizationAdmin<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::new)
							.with(SynchronizationWorker<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::setRegister, 
									GenericBuilder.of(Register<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::new)
										.with(Register<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::setPeriod, syncState.getPeriod())
										.with(Register<EarningEventData, CorporateEarningEventTranslator, CorporateEarningEvent>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<EarningEventData>::new)
													.with(AscendDiscriminator<EarningEventData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
