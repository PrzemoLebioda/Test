package com.comida.sia.fundamentals.domain.model.sharesoutstanding;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.fundamentals.port.acquirer.sharesoutstanding.SharesOutstandingReportData;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class SharesOutstandingReportTranslator implements ModelTranslator<SharesOutstandingReportData, SharesOutstandingReport>{

	@Override
	public SharesOutstandingReport translate(SharesOutstandingReportData source) throws ParseException {
		return new SharesOutstandingReport.Builder(UUID.randomUUID(), source.getTickerSymbol())
				.date(TranslationConcern.getDateFrom(source.getDate()))
				.sharesOutstandingDiluted(TranslationConcern.getNumberFrom(source.getShares_outstanding_diluted()))
				.sharesOutstandingBasic(TranslationConcern.getNumberFrom(source.getShares_outstanding_basic()))
				.build();
	}

}
