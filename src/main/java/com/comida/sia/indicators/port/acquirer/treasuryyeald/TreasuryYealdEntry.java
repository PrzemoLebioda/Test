package com.comida.sia.indicators.port.acquirer.treasuryyeald;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.indicators.domain.model.treasuryyeald.Interval;
import com.comida.sia.indicators.domain.model.treasuryyeald.Maturity;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreasuryYealdEntry extends WaterMark{
	
	private Interval interval;
	private Maturity maturity;
	private String date;
	private String value;
	
	public TreasuryYealdEntry() {
		super(Direction.ASCENDING);
	}

	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(date);
	}

	@Override
	public String calculateLevel() {
		String level = "";
		try {
			level = calculateEntryDateMark();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return level;
	}
	
	private String calculateEntryDateMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(date)), 10);
	}
	
	@Override
	public String toString() {
		return "TreasuryYealdEntry [interval=" + interval + ", maturity=" + maturity + ", date=" + date + ", value="
				+ value + ", getOrder()=" + getOrder() + "]";
	}
	
}
