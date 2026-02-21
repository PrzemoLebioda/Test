package com.comida.sia.fundamentals.port.repository;

import java.util.Date;

import com.comida.sia.fundamentals.domain.model.corpoevents.earnings.CorporateEarningEvent;

public interface CorporateEarningsEventRepository {
	public void store(CorporateEarningEvent event);
	public void update(CorporateEarningEvent event);
	
	public CorporateEarningEvent get(String tickerSymbol, Date fiscalDateEnding);
}
