package com.comida.sia.fundamentals.domain.model.earnings;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.ReportType;
import com.comida.sia.fundamentals.port.acquirer.earnings.EarningsAnnualReportData;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class EarningsAnnualReportTranslator implements ModelTranslator<EarningsAnnualReportData, EarningReport>{

	@Override
	public EarningReport translate(EarningsAnnualReportData source) throws ParseException {
		return new EarningReport.EarningsReportBuilder(UUID.randomUUID(), source.getTickerSymbol(), ReportType.ANNUAL_REPORT)
				.setFiscalDateEnding(TranslationConcern.getDateFrom(source.getFiscalDateEnding()))
				.setReportedEPS(TranslationConcern.getNumberFrom(source.getReportedEPS()))
				.build();
	}
	
	
}
