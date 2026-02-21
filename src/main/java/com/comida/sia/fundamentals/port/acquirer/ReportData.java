package com.comida.sia.fundamentals.port.acquirer;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ReportData extends WaterMark{
	protected String fiscalDateEnding;
	
	public ReportData(Direction sortingOrder) {
		super(sortingOrder);
	}
	
	@Override
	public String calculateLevel() {
		try {
			return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(fiscalDateEnding)), 10);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(fiscalDateEnding);
	}
	
	public abstract void enrich(String additionalData); 

	@Override
	public String toString() {
		return "ReportData [fiscalDateEnding=" + fiscalDateEnding + "]";
	}
}
