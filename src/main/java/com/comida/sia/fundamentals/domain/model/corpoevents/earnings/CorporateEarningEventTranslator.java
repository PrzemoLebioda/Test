package com.comida.sia.fundamentals.domain.model.corpoevents.earnings;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.fundamentals.port.acquirer.corpoevents.earnings.EarningEventData;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.CurrencySymbol;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class CorporateEarningEventTranslator implements ModelTranslator<EarningEventData, CorporateEarningEvent> {
	
	private Notifier notifier;
	
	public CorporateEarningEventTranslator(Notifier notifier) {
		setNotifier(notifier);
	}
	
	private void setNotifier(Notifier notifier) {
		AssertionConcern.assertNotNull(notifier, "Notifier is mandatory in order to prepare corporate earning event object");
		this.notifier = notifier;
	}
	
	@Override
	public CorporateEarningEvent translate(EarningEventData source) throws ParseException {
		CorporateEarningEvent corporateEarningEvent = new CorporateEarningEvent.Builder(UUID.randomUUID(), source.getSymbol())
				.reportedDate(TranslationConcern.getDateFrom(source.getReportDate()))
				.fiscalDateEnding(TranslationConcern.getDateFrom(source.getFiscalDateEnding()))
				.estimate(TranslationConcern.getNumberFrom(source.getEstimate()))
				.currencySymbol(CurrencySymbol.get(source.getCurrency()))
				.build();
		
		corporateEarningEvent
			.withNotifier(notifier)
			.addToCalendar();
		
		return corporateEarningEvent;
	}

}
