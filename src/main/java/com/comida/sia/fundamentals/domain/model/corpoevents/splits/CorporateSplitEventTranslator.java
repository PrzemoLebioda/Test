package com.comida.sia.fundamentals.domain.model.corpoevents.splits;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.fundamentals.port.acquirer.corpoevents.splits.SplitEventData;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;


public class CorporateSplitEventTranslator implements ModelTranslator<SplitEventData ,CorporateSplitEvent>{

	@Override
	public CorporateSplitEvent translate(SplitEventData source) throws ParseException {
		// TODO Auto-generated method stub
		return new CorporateSplitEvent.Builder(UUID.randomUUID(), source.getTickerSymbol())
				.splitFactor(TranslationConcern.getNumberFrom(source.getSplit_factor()))
				.effectiveDate(TranslationConcern.getDateFrom(source.getEffective_date()))
				.build();
	}

}
