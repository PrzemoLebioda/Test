package com.comida.sia.fundamentals.port.acquirer.corpoevents.splits;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SplitEventData extends WaterMark{
	
	public SplitEventData() {
		super(Direction.ASCENDING);
	}

	private String tickerSymbol;
	private String effective_date;
	private String split_factor;
	
	
	@Override
	public String calculateLevel() {
		try {
			return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(effective_date)), 10);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(effective_date);
	}
	
	public void enrich(String additionalData) {
		this.tickerSymbol = additionalData;
	}

	@Override
	public String toString() {
		return "SplitEventData [tickerSymbol=" + tickerSymbol + ", effective_date=" + effective_date + ", split_factor="
				+ split_factor + "]";
	}
	
	
}
