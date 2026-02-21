package com.comida.sia.fundamentals.port.acquirer.stock;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StockData extends WaterMark{
	private String symbol;
	private String name;
	private String exchange;
	private String assetType;
	private String ipoDate;
	private String delistingDate;
	private String status;
	
	public StockData() {
		super(Direction.ASCENDING);
	}

	@Override
	public String calculateLevel() {
		String activeStatus = "Active";
		String delistedStatus = "Delisted";
		
		try {
			
			if(activeStatus.equals(status)) {
				return calculateIpoDateMark() + calculateSymbolMark();
			}
			
			if(delistedStatus.equals(status)) {
				return calculateDelistingDateMark() + calculateSymbolMark();
			}
						
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	private String calculateIpoDateMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(ipoDate)), 10);
	}
	
	private String calculateDelistingDateMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(delistingDate)), 10);
	}
	
	private String calculateSymbolMark() {
		return TranslationConcern.fillLeadingZeros(symbol, 10);
	}

	@Override
	public Date occuranceTime() throws ParseException {
		String activeStatus = "Active";
		String delistedStatus = "Delisted";
		
		if(activeStatus.equals(status)) {
			return TranslationConcern.getDateFrom(this.ipoDate);
		}
		
		if(delistedStatus.equals(status)) {
			return TranslationConcern.getDateFrom(this.delistingDate);
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "StockData [symbol=" + symbol + ", name=" + name + ", exchange=" + exchange + ", assetType=" + assetType
				+ ", ipoDate=" + ipoDate + ", delistingDate=" + delistingDate + ", status=" + status
				+ ", calculateLevel()=" + calculateLevel() + ", getOrder()=" + getOrder() + "]";
	}

}
