package com.comida.sia.exchangequote.port.acquirer;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExchangeQuotationEntry extends WaterMark{
	private String timestamp;
	private String open;
	private String high;
	private String low;
	private String close;
	private String vloume;
	private String adjustedClose;
	private String dividendAmount;
	private String splitCoefficient;
	
	public ExchangeQuotationEntry() {
		super(Direction.ASCENDING);
	}

	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(timestamp);
	}

	@Override
	public String calculateLevel() {
		String level = "";
		try {
			level = calculateQuotationMark();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return level;
	}
	
	private String calculateQuotationMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(timestamp)), 10);
	}

	@Override
	public String toString() {
		return "ExchangeQuotationEntry [timestamp=" + timestamp + ", open=" + open + ", high=" + high + ", low=" + low
				+ ", close=" + close + ", vloume=" + vloume + ", adjustedClose=" + adjustedClose + ", dividendAmount="
				+ dividendAmount + ", splitCoefficient=" + splitCoefficient + ", getOrder()=" + getOrder() + "]";
	}
	
}
