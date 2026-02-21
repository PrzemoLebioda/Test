package com.comida.sia.fundamentals.port.repository;

import com.comida.sia.fundamentals.domain.model.stock.Stock;

public interface StockRepository {
	void store(Stock stock);
	void update(Stock stock);
	
	Stock get(String tickerSymbol);

}
