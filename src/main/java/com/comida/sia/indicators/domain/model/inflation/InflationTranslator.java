package com.comida.sia.indicators.domain.model.inflation;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class InflationTranslator implements ModelTranslator<IndicatorsDataEntry, Inflation>{

	@Override
	public Inflation translate(IndicatorsDataEntry source) throws ParseException {
		return new Inflation.InflationBuilder(UUID.randomUUID())
				.date(TranslationConcern.getDateFrom(source.getDate()))
				.value(TranslationConcern.getNumberFrom(source.getValue()))
				.interval(source.getInterval())
				.build();
	}
}
