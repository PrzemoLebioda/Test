package com.comida.sia.fundamentals.domain.model.stock;

import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;

import lombok.Getter;

public class StockListedDomainEvent implements SubjectedPayload {
	@Getter private String tickerSymbol;
	@Getter private AssetType assetType;
	@Getter private Date ipoDate;
	
	public StockListedDomainEvent(String tickerSymbol, Date ipoDate, AssetType assetType) {
		setTickerSymbol(tickerSymbol);
		setIpoDate(ipoDate);
		setAssetType(assetType);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "ticker symbol must be provided in order to create stock listed domain event");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setIpoDate(Date ipoDate) {
		AssertionConcern.assertNotNull(ipoDate, "IPO date must be provided in order to create stock listed domain event");
		this.ipoDate = ipoDate;
	}
	
	private void setAssetType(AssetType assetType) {
		AssertionConcern.assertNotNull(assetType, "Assert type must be provided in order to create stock listed domain event");
		this.assetType = assetType;
	}
	
	@Override
	public Subject getSubject() {
		return Subject.STOCK_LISTED;
	}

	@Override
	public String getClassName() {
		return this.getClass().getName();
	}

}
