package com.comida.sia.indicators.port.acquirer;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.indicators.domain.model.Interval;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicatorsDataEntry extends WaterMark {
	
	private Interval interval;
	private String date;
	private String value;
	
	public IndicatorsDataEntry() {
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
			level = calculateEntryDateMarkMark();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return level;
	}
	
	private String calculateEntryDateMarkMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(date)), 10);
	}

	@Override
	public String toString() {
		return "IndicatorsDataEntry [interval=" + interval + ", date=" + date + ", value=" + value + ", getOrder()="
				+ getOrder() + "]";
	}
	
}
