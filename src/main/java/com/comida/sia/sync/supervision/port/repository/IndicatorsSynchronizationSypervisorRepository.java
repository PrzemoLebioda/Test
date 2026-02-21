package com.comida.sia.sync.supervision.port.repository;

import com.comida.sia.sync.supervision.domain.model.indicators.IndicatorsSynchronizationSupervisor;

public interface IndicatorsSynchronizationSypervisorRepository {	
	void store(IndicatorsSynchronizationSupervisor supervisor);
	void update(IndicatorsSynchronizationSupervisor supervisor);
	
	IndicatorsSynchronizationSupervisor get();
}
