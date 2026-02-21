package com.comida.sia.options.domain.model;

import java.text.ParseException;

import com.comida.sia.options.port.acquirer.OptionData;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class OptionTranslator implements ModelTranslator<OptionData, Option>{
	
	public OptionTranslator() {
		super();
	}

	@Override
	public Option translate(OptionData source) throws ParseException {
		Option option = new Option.Builder(source.getContractId(), source.getDate(), source.getSymbol())
				.setExpiration(source.getExpiration())
				.setStrike(source.getStrike())
				.setType(source.getType())
				.setLast(source.getLast())
				.setMark(source.getMark())
				.setBid(source.getBid())
				.setBidSize(source.getBidSize())
				.setAsk(source.getAsk())
				.setAskSize(source.getAskSize())
				.setVolume(source.getVolume())
				.setOpenInterest(source.getOpenInterest())
				.setImpliedVolatility(source.getImpliedVolatility())
				.setDelta(source.getDelta())
				.setGamma(source.getGamma())
				.setTheta(source.getTheta())
				.setVega(source.getVega())
				.setRho(source.getRho())
				.build();
		
		return option;
	}

}
