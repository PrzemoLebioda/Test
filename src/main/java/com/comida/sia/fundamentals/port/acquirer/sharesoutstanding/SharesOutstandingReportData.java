package com.comida.sia.fundamentals.port.acquirer.sharesoutstanding;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharesOutstandingReportData extends WaterMark {
	
	private String tickerSymbol;
	private String date;
	private String shares_outstanding_diluted;
	private String shares_outstanding_basic;
	
	public SharesOutstandingReportData() {
		super(Direction.ASCENDING);
	}
	
	@Override
	public String calculateLevel() {
		try {
			return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(date)), 10);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(date);
	}
	
	public void enrich(String additionalData) {
		this.tickerSymbol = additionalData;	
	}
	
	@Override
	public String toString() {
		return "SharesOutstandingReportData [tickerSymbol=" + tickerSymbol + ", date=" + date
				+ ", shares_outstanding_diluted=" + shares_outstanding_diluted + ", shares_outstanding_basic="
				+ shares_outstanding_basic + "]";
	}
}
