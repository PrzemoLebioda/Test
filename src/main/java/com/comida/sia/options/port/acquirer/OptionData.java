package com.comida.sia.options.port.acquirer;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionData extends WaterMark {

	private String contractId;
	private String symbol;
	private String expiration;
	private String strike;
	private String type;
	private String last;
	private String mark;
	private String bid;
	private String bidSize;
	private String ask;
	private String askSize;
	private String volume;
	private String openInterest;
	private String date;
	private String impliedVolatility;
	private String delta;
	private String gamma;
	private String theta;
	private String vega;
	private String rho;
	
	public OptionData() {
		super(Direction.ASCENDING);
	}
	
	public OptionData(String contractId, String symbol, String date, String expiration, String strike, String type) {
		super(Direction.ASCENDING);
		this.contractId = contractId;
		this.symbol = symbol;
		this.date = date;
		this.expiration = expiration;
		this.strike = strike;
		this.type = type;
	}
	
	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(date);
	}
	
	@Override
	public String calculateLevel() {
		String level = "";
		try {
			level = calculateDateMark() + 
					calculateExpirationMark() + 
					calculateStrikeMark() +
					calculateTypeMark();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return level;
	}
	
	private String calculateDateMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(date)), 10);
	}
	
	private String calculateExpirationMark() throws ParseException {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(expiration)), 10);
	}
	
	private String calculateStrikeMark() {
		return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getNumberFrom(strike).longValue()), 7);
	}
	
	private String calculateTypeMark() {
		return TranslationConcern.fillLeadingZeros(type, 4);
	}

	@Override
	public String toString() {
		return "OptionData [contractId=" + contractId + ", symbol=" + symbol + ", expiration=" + expiration
				+ ", strike=" + strike + ", type=" + type + ", last=" + last + ", mark=" + mark + ", bid=" + bid
				+ ", bidSize=" + bidSize + ", ask=" + ask + ", askSize=" + askSize + ", volume=" + volume
				+ ", openInterest=" + openInterest + ", date=" + date + ", impliedVolatility=" + impliedVolatility
				+ ", delta=" + delta + ", gamma=" + gamma + ", theta=" + theta + ", vega=" + vega + ", rho=" + rho
				+ "]";
	}
}
