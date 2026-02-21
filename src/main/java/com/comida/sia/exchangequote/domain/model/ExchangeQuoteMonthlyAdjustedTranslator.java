package com.comida.sia.exchangequote.domain.model;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.exchangequote.port.acquirer.ExchangeQuotationEntry;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class ExchangeQuoteMonthlyAdjustedTranslator implements ModelTranslator<ExchangeQuotationEntry, ExchangeQuoteMonthlyAdjusted>{

	@Override
	public ExchangeQuoteMonthlyAdjusted translate(ExchangeQuotationEntry source) throws ParseException {
		return new ExchangeQuoteMonthlyAdjusted.ExchangeQuoteMonthlyAdjustedBuilder(UUID.randomUUID())
				//.exchange(null)
				//.sector("")
				//.industry("")
				//.symbol("")
				.period(TranslationConcern.getDateFrom(source.getTimestamp()))
				.quotationTime(TranslationConcern.getDateFrom(source.getTimestamp()))
				.open(TranslationConcern.getNumberFrom(source.getOpen()))
				.high(TranslationConcern.getNumberFrom(source.getHigh()))
				.low(TranslationConcern.getNumberFrom(source.getLow()))
				.close(TranslationConcern.getNumberFrom(source.getClose()))
				.volume(TranslationConcern.getLongFrom(source.getVloume()))
				.adjustedClose(TranslationConcern.getNumberFrom(source.getAdjustedClose()))
				.dividendAmount(TranslationConcern.getNumberFrom(source.getDividendAmount()))
				.splitCoefficient(TranslationConcern.getNumberFrom(source.getSplitCoefficient()))
				.build();
	}

}
