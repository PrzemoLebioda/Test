package com.comida.sia.fundamentals.domain.model.earnings.estimation;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.fundamentals.port.acquirer.earnings.estimation.EarningEstimatesReportData;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class EarningEstimatesReportTranslator implements ModelTranslator<EarningEstimatesReportData, EarningEstimatesReport> {

	@Override
	public EarningEstimatesReport translate(EarningEstimatesReportData source) throws ParseException {
		return new EarningEstimatesReport.Builder(UUID.randomUUID(), source.getTickerSymbol())
				.date(TranslationConcern.getDateFrom(source.getDate()))
				.horizon(Horizon.get(source.getHorizon()))
			    .epsEstimateAverage(TranslationConcern.getNumberFrom(source.getEps_estimate_average()))
			    .epsEstimateHigh(TranslationConcern.getNumberFrom(source.getEps_estimate_high()))
			    .epsEstimateLow(TranslationConcern.getNumberFrom(source.getEps_estimate_low()))
			    .epsEstimateAnalystCount(TranslationConcern.getNumberFrom(source.getEps_estimate_analyst_count()))
			    .epsEstimateAverage7DaysAgo(TranslationConcern.getNumberFrom(source.getEps_estimate_average_7_days_ago()))
			    .epsEstimateAverage30DaysAgo(TranslationConcern.getNumberFrom(source.getEps_estimate_average_30_days_ago()))
			    .epsEstimateAverage60DaysAgo(TranslationConcern.getNumberFrom(source.getEps_estimate_average_60_days_ago()))
			    .epsEstimateAverage90DaysAgo(TranslationConcern.getNumberFrom(source.getEps_estimate_average_90_days_ago()))
			    .epsEstimateRevisionUpTrailing7Days(TranslationConcern.getNumberFrom(source.getEps_estimate_revision_up_trailing_7_days()))
			    .epsEstimateRevisionDownTrailing7Days(TranslationConcern.getNumberFrom(source.getEps_estimate_revision_down_trailing_7_days()))
			    .epsEstimateRevisionUpTrailing30Days(TranslationConcern.getNumberFrom(source.getEps_estimate_revision_up_trailing_30_days()))
			    .epsEstimateRevisionDownTrailing30Days(TranslationConcern.getNumberFrom(source.getEps_estimate_revision_down_trailing_30_days()))
			    .revenueEstimateAverage(TranslationConcern.getNumberFrom(source.getRevenue_estimate_average()))
			    .revenueEstimateHigh(TranslationConcern.getNumberFrom(source.getRevenue_estimate_high()))
			    .revenueEstimateLow(TranslationConcern.getNumberFrom(source.getRevenue_estimate_low()))
			    .revenueEstimateAnalystCount(TranslationConcern.getNumberFrom(source.getRevenue_estimate_analyst_count()))
				.build();
	}

}
