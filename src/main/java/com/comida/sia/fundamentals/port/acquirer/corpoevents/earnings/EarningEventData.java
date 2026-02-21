package com.comida.sia.fundamentals.port.acquirer.corpoevents.earnings;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EarningEventData extends WaterMark {
	private String symbol;
	private String reportDate;
	private String fiscalDateEnding;
	private String estimate;
	private String currency;
	
	public EarningEventData() {
		super(Direction.ASCENDING);
	}
	
	@Override
	public String calculateLevel() {
		String level = "";
		try {
			level = calculateReportDateMark() + 
					calculateFiscalDateEndingMark() + 
					calculateSymbolMark();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return level;
	}

	private String calculateSymbolMark() {
		return TranslationConcern.fillLeadingZeros(symbol, 6);
	}
	
	private String calculateFiscalDateEndingMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(fiscalDateEnding)), 10);
	}
	
	private String calculateReportDateMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(reportDate)), 10);
	}
	
	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(reportDate);
	}

	@Override
	public String toString() {
		return "EarningEventData [symbol=" + symbol + ", reportDate=" + reportDate
				+ ", fiscalDateEnding=" + fiscalDateEnding + ", estimate=" + estimate + ", currency=" + currency + "]";
	}
}
