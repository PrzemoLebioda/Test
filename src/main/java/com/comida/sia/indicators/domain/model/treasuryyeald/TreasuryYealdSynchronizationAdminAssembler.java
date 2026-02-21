package com.comida.sia.indicators.domain.model.treasuryyeald;

import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily02YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily03MonthSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily05YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily07YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily10YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdDaily30YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly02YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly03MonthSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly05YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly07YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly10YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdMonthly30YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly02YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly03MonthSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly05YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly07YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly10YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.domain.model.treasuryyeald.events.TreasuryYealdWeekly30YearSynchronizedDomainEventBuilder;
import com.comida.sia.indicators.port.acquirer.treasuryyeald.TreasuryYealdEntry;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class TreasuryYealdSynchronizationAdminAssembler {
	
	public TreasuryYealdSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdDaily02YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdDaily02YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdDaily03MonthSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdDaily03MonthSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdDaily05YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdDaily05YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdDaily07YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdDaily07YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdDaily10YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdDaily10YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdDaily30YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdDaily30YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdMonthly02YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdMonthly02YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdMonthly03MonthSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdMonthly03MonthSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdMonthly05YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdMonthly05YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdMonthly07YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdMonthly07YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdMonthly10YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdMonthly10YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdMonthly30YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdMonthly30YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdWeekly02YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdWeekly02YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdWeekly03MonthSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdWeekly03MonthSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdWeekly05YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdWeekly05YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdWeekly07YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdWeekly07YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdWeekly10YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdWeekly10YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald> assemblyTreasuryYealdWeekly30YearSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setNotifier, notifier)
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDomainEventBuilder, new TreasuryYealdWeekly30YearSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setTranslator, new TreasuryYealdTranslator())
				.with(SynchronizationAdmin<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
							.with(SynchronizationWorker<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setRegister, 
									GenericBuilder.of(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::new)
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setPeriod, syncState.getPeriod())
										.with(Register<TreasuryYealdEntry, TreasuryYealdTranslator, TreasuryYeald>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<TreasuryYealdEntry>::new)
													.with(AscendDiscriminator<TreasuryYealdEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
