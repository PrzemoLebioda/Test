package com.comida.sia.indicators.domain.model.payroll;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class NonfarmPayrollSynchronizationAdminAssembler {
	
	public NonfarmPayrollSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll> assemblyNonfarmPayrollMonthlySyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::new)
				.with(SynchronizationAdmin<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::setNotifier, notifier)
				.with(SynchronizationAdmin<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::setDomainEventBuilder, new NonfarmPayrollMonthlySynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::setTranslator, new NonfarmPayrollTranslator())
				.with(SynchronizationAdmin<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::new)
							.with(SynchronizationWorker<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::setRegister, 
									GenericBuilder.of(Register<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::new)
										.with(Register<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::setPeriod, syncState.getPeriod())
										.with(Register<IndicatorsDataEntry, NonfarmPayrollTranslator, NonfarmPayroll>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<IndicatorsDataEntry>::new)
													.with(AscendDiscriminator<IndicatorsDataEntry>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
}
