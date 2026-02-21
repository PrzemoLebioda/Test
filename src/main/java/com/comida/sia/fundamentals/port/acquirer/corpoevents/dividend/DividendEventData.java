package com.comida.sia.fundamentals.port.acquirer.corpoevents.dividend;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DividendEventData extends WaterMark{
	private String tickerSymbol;
	private String ex_dividend_date;
	private String declaration_date;
	private String record_date;
	private String payment_date;
	private String amount;
	
	public DividendEventData() {
		super(Direction.ASCENDING);
	}

	@Override
	public String calculateLevel() {
		try {
			return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(ex_dividend_date)), 10);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(ex_dividend_date);
	}
	
	public void enrich(String additionalData) {
		this.tickerSymbol = additionalData;
	}

	@Override
	public String toString() {
		return "DividendEventData [tickerSymbol=" + tickerSymbol + ", ex_dividend_date=" + ex_dividend_date
				+ ", declaration_date=" + declaration_date + ", record_date=" + record_date + ", payment_date="
				+ payment_date + ", amount=" + amount + "]";
	}
	
}
