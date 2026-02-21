package com.comida.sia.indicators.domain.model.cpi;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class ConsumerPriceIndexTranslator implements ModelTranslator<IndicatorsDataEntry, ConsumerPriceIndex>{

	@Override
	public ConsumerPriceIndex translate(IndicatorsDataEntry source) throws ParseException {
		return new ConsumerPriceIndex.CPIBuilder(UUID.randomUUID())
				.date(TranslationConcern.getDateFrom(source.getDate()))
				.value(TranslationConcern.getNumberFrom(source.getValue()))
				.interval(source.getInterval())
				.build();
	}

}
