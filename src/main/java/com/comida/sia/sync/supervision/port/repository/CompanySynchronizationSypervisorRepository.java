package com.comida.sia.sync.supervision.port.repository;

import java.util.List;

import com.comida.sia.sync.supervision.domain.model.company.CompanySynchronizationSupervisor;

public interface CompanySynchronizationSypervisorRepository {
	void store(CompanySynchronizationSupervisor supervisor);
	void update(CompanySynchronizationSupervisor supervisor);
	
	CompanySynchronizationSupervisor get(String tickerSymbol);
	List<CompanySynchronizationSupervisor> getAll();
}
