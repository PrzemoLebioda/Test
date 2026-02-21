package com.comida.sia.fundamentals.domain.model.corpoevents.dividend;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.fundamentals.port.acquirer.corpoevents.dividend.DividendEventData;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class CorporateDividendEventTranslator implements ModelTranslator<DividendEventData ,CorporateDividendEvent> {

	@Override
	public CorporateDividendEvent translate(DividendEventData source) throws ParseException {
		
		return new CorporateDividendEvent.Builder(UUID.randomUUID(), source.getTickerSymbol())
				.exDividendDate(TranslationConcern.getDateFrom(source.getEx_dividend_date()))
				.declarationDate(TranslationConcern.getDateFrom(source.getDeclaration_date()))
				.recordDate(TranslationConcern.getDateFrom(source.getRecord_date()))
				.paymentDate(TranslationConcern.getDateFrom(source.getPayment_date()))
				.amount(TranslationConcern.getNumberFrom(source.getAmount()))
				
				.build();
	}

}
