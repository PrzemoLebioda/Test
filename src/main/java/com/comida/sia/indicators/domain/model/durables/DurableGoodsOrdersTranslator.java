package com.comida.sia.indicators.domain.model.durables;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.indicators.port.acquirer.IndicatorsDataEntry;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class DurableGoodsOrdersTranslator implements ModelTranslator<IndicatorsDataEntry, DurableGoodsOrders>{

	@Override
	public DurableGoodsOrders translate(IndicatorsDataEntry source) throws ParseException {
		return new DurableGoodsOrders.DurablesBuilder(UUID.randomUUID())
				.date(TranslationConcern.getDateFrom(source.getDate()))
				.value(TranslationConcern.getNumberFrom(source.getValue()))
				.interval(source.getInterval())
				.build();
	}

}
