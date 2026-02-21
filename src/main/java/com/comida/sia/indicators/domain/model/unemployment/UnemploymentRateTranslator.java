package com.comida.sia.indicators.domain.model.unemployment;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class UnemploymentRateTranslator implements ModelTranslator<IndicatorsDataEntry, UnemploymentRate>{

	@Override
	public UnemploymentRate translate(IndicatorsDataEntry source) throws ParseException {
		return new UnemploymentRate.UnemploymentRateBuilder(UUID.randomUUID())
				.date(TranslationConcern.getDateFrom(source.getDate()))
				.value(TranslationConcern.getNumberFrom(source.getValue()))
				.interval(source.getInterval())
				.build();
	}

}
