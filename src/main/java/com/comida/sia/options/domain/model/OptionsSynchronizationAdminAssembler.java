package com.comida.sia.options.domain.model;

import com.comida.sia.options.port.acquirer.OptionData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sharedkernel.period.PeriodType;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class OptionsSynchronizationAdminAssembler {
	private String tickerSymbol;
	
	public OptionsSynchronizationAdminAssembler(String tickerSymbol) {
		setTickerSymbol(tickerSymbol);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory");
		this.tickerSymbol = tickerSymbol;
	}
	
	public SynchronizationAdmin<OptionData, OptionTranslator, Option> assemblyOptionsSyncAdmin(SynchronizationStateDto syncState, Notifier notifier){
		AssertionConcern.assertNotNull(syncState, "Synchronization state must be provided");
		
		AssertionConcern.assertEquals(PeriodType.INFINITY, syncState.getPeriodType(), "Incompatibile period types");
		AssertionConcern.assertEquals(Direction.ASCENDING, syncState.getDirection(), "Incompatibile sorting direction");
		
		return GenericBuilder.of(SynchronizationAdmin<OptionData, OptionTranslator, Option>::new)
				.with(SynchronizationAdmin<OptionData, OptionTranslator, Option>::setTickerSymbol, tickerSymbol)
				.with(SynchronizationAdmin<OptionData, OptionTranslator, Option>::setNotifier, notifier)
				.with(SynchronizationAdmin<OptionData, OptionTranslator, Option>::setDomainEventBuilder, new OptionsSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<OptionData, OptionTranslator, Option>::setTranslator, new OptionTranslator())
				.with(SynchronizationAdmin<OptionData, OptionTranslator, Option>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<OptionData, OptionTranslator, Option>::new)
							.with(SynchronizationWorker<OptionData, OptionTranslator, Option>::setRegister, 
									GenericBuilder.of(Register<OptionData, OptionTranslator, Option>::new)
										.with(Register<OptionData, OptionTranslator, Option>::setPeriod, syncState.getPeriod())
										.with(Register<OptionData, OptionTranslator, Option>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<OptionData>::new)
													.with(AscendDiscriminator<OptionData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
