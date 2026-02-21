package com.comida.sia.fundamentals.port.repository;

import com.comida.sia.fundamentals.domain.model.corpoevents.dividend.CorporateDividendEvent;

public interface CorporateDividendEventRepository {
	void store(CorporateDividendEvent corporateDividendEvent);
	void update(CorporateDividendEvent corporateDividendEvent);

}
