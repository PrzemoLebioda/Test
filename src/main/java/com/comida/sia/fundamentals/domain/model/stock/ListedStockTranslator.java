package com.comida.sia.fundamentals.domain.model.stock;

import java.text.ParseException;

import com.comida.sia.fundamentals.port.acquirer.stock.StockData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class ListedStockTranslator implements ModelTranslator<StockData, Stock> {
	private Notifier notifier;
	
	public ListedStockTranslator(Notifier notifier) {
		setNotifier(notifier);
	}
	
	private void setNotifier(Notifier notifier) {
		AssertionConcern.assertNotNull(notifier, "Notifier is mandatory in order to prepare stock domain object");
		this.notifier = notifier;
	}
	
	@Override
	public Stock translate(StockData source) throws ParseException {
		if(isTranslatable(source)) {
			Stock stock = new Stock(source.getSymbol(), source.getName());
			stock.setAssetType(AssetType.get(source.getAssetType()));
			stock
				.withNotifier(notifier)
				.list(TranslationConcern.getDateFrom(source.getIpoDate()));
			
			return stock;
		} else {
			return null;
		}
		
	}

	private Boolean isTranslatable(StockData source) {
		if(source.getSymbol() != null &&  !source.getSymbol().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}
