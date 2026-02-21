package com.comida.sia.fundamentals.port.repository;

import com.comida.sia.fundamentals.domain.model.corpoevents.splits.CorporateSplitEvent;

public interface CorporateSplitEventRepository {
	void store(CorporateSplitEvent corporateSplitEvent);
	void update(CorporateSplitEvent corporateSplitEvent);
}
