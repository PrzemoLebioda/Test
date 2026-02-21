package com.comida.sia.indicators.domain.model.gdp;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class GrossDomesticProductPerCapitaTranslator implements ModelTranslator<IndicatorsDataEntry, GrossDomesticProductPerCapita> {

	@Override
	public GrossDomesticProductPerCapita translate(IndicatorsDataEntry source) throws ParseException {
		GrossDomesticProductPerCapita gdp = new GrossDomesticProductPerCapita.GDPBuilder(UUID.randomUUID())
				.date(TranslationConcern.getDateFrom(source.getDate()))
				.value(TranslationConcern.getNumberFrom(source.getValue()))
				.interval(source.getInterval())
				.build();
				
		return gdp;
	}
}
