package com.comida.sia.indicators.domain.model.treasuryyeald;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.indicators.port.acquirer.treasuryyeald.TreasuryYealdEntry;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class TreasuryYealdTranslator implements ModelTranslator<TreasuryYealdEntry, TreasuryYeald>{

	@Override
	public TreasuryYeald translate(TreasuryYealdEntry source) throws ParseException {
		TreasuryYeald treasuryYeald = new TreasuryYeald.Builder(UUID.randomUUID())
				.interval(source.getInterval())
				.date(TranslationConcern.getDateFrom(source.getDate()))
				.value(TranslationConcern.getNumberFrom(source.getValue()))
				.maturity(source.getMaturity())
				.build();
		return treasuryYeald;
	}

}
