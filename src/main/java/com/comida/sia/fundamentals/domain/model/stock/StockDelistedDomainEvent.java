package com.comida.sia.fundamentals.domain.model.stock;

import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;

import lombok.Getter;

public class StockDelistedDomainEvent implements SubjectedPayload{

	@Getter private String tickerSymbol;
	@Getter private Date delistedDate;
	@Getter private AssetType assetType;
	
	public StockDelistedDomainEvent(String tickerSymbol, Date delistedDate, AssetType assetType) {
		setTickerSymbol(tickerSymbol);
		setDelistedDate(delistedDate);
		setAssetType(assetType);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "ticker symbol must be provided in order to create stock delisted domain event");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setDelistedDate(Date delistedDate) {
		AssertionConcern.assertNotNull(delistedDate, "Delisted date must be provided in order to create stock delisted domain event");
		this.delistedDate = delistedDate;
	}
	
	private void setAssetType(AssetType assetType) {
		AssertionConcern.assertNotNull(assetType, "Assert type must be provided in order to create stock delisted domain event");
		this.assetType = assetType;
	}

	@Override
	public Subject getSubject() {
		return Subject.STOCK_DELISTED;
	}

	@Override
	public String getClassName() {
		return this.getClass().getName();
	}

}
