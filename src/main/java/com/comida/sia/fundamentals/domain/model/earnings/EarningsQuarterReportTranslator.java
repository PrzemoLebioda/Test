package com.comida.sia.fundamentals.domain.model.earnings;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.ReportType;
import com.comida.sia.fundamentals.port.acquirer.earnings.EarningsQuarterlyReportData;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class EarningsQuarterReportTranslator implements ModelTranslator<EarningsQuarterlyReportData, EarningReport>{

	@Override
	public EarningReport translate(EarningsQuarterlyReportData source) throws ParseException {
		return new EarningReport.EarningsReportBuilder(UUID.randomUUID(), source.getTickerSymbol(), ReportType.QUARTER_REPORT)
				.setFiscalDateEnding(TranslationConcern.getDateFrom(source.getFiscalDateEnding()))
				.setReportedEPS(TranslationConcern.getNumberFrom(source.getReportedEPS()))
				.setReportedDate(TranslationConcern.getDateFrom(source.getReportedDate()))
				.setEstimatedEPS(TranslationConcern.getNumberFrom(source.getEstimatedEPS()))
				.setSurprise(TranslationConcern.getNumberFrom(source.getSurprise()))
				.setSurprisePercentage(TranslationConcern.getNumberFrom(source.getSurprisePercentage()))
				.setReportTime(source.getReportTime())
				.build();
	}

}
