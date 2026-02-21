package com.comida.sia.intelligence.insidertransactions.port.repository;

import java.util.List;

import com.comida.sia.intelligence.insidertransactions.domain.model.InsiderTransaction;


public interface InsiderTransactionRepository {
	
	void store(InsiderTransaction insiderTransaction);
	void update(InsiderTransaction insiderTransaction);
	
	List<InsiderTransaction> getFor(String tickerSybmol);
}
