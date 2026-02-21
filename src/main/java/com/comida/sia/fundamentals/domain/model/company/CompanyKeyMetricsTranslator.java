package com.comida.sia.fundamentals.domain.model.company;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import com.comida.sia.fundamentals.port.acquirer.company.CompanyDetailsData;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class CompanyKeyMetricsTranslator implements ModelTranslator<CompanyDetailsData, CompanyKeyMetrics> {

	@Override
	public CompanyKeyMetrics translate(CompanyDetailsData source) throws ParseException {
		return new CompanyKeyMetrics.Builder(UUID.randomUUID(), new Date())	
				.latestQuarter(TranslationConcern.getDateFrom(source.getLatestQuarter()))
				.marketCapitalization(TranslationConcern.getNumberFrom(source.getMarketCapitalization()))
				.ebitda(TranslationConcern.getNumberFrom(source.getEBITDA()))
				.peRatio(TranslationConcern.getNumberFrom(source.getPEGRatio()))
				.pegRatio(TranslationConcern.getNumberFrom(source.getPEGRatio()))
				.bookValue(TranslationConcern.getNumberFrom(source.getBookValue()))
				.dividendPerShare(TranslationConcern.getNumberFrom(source.getDividendPerShare()))
				.dividendYield(TranslationConcern.getNumberFrom(source.getDividendYield()))
				.eps(TranslationConcern.getNumberFrom(source.getEPS()))
				.revenuePerShareTTM(TranslationConcern.getNumberFrom(source.getRevenuePerShareTTM()))
				.profitMargin(TranslationConcern.getNumberFrom(source.getProfitMargin()))
				.operatingMarginTTM(TranslationConcern.getNumberFrom(source.getOperatingMarginTTM()))
				.returnOnAssetsTTM(TranslationConcern.getNumberFrom(source.getReturnOnAssetsTTM()))
				.returnOnEquityTTM(TranslationConcern.getNumberFrom(source.getReturnOnEquityTTM()))
				.revenueTTM(TranslationConcern.getNumberFrom(source.getRevenueTTM()))
				.grossProfitTTM(TranslationConcern.getNumberFrom(source.getGrossProfitTTM()))
				.dilutedEPSTTM(TranslationConcern.getNumberFrom(source.getDilutedEPSTTM()))
				.quarterlyEarningsGrowthYOY(TranslationConcern.getNumberFrom(source.getQuarterlyEarningsGrowthYOY()))
				.quarterlyRevenueGrowthYOY(TranslationConcern.getNumberFrom(source.getQuarterlyRevenueGrowthYOY()))
				.analystTargetPrice(TranslationConcern.getNumberFrom(source.getAnalystTargetPrice()))
				.analystRatingStrongBuy(TranslationConcern.getNumberFrom(source.getAnalystRatingStrongBuy()))
				.analystRatingBuy(TranslationConcern.getNumberFrom(source.getAnalystRatingBuy()))
				.analystRatingHold(TranslationConcern.getNumberFrom(source.getAnalystRatingHold()))
				.analystRatingSell(TranslationConcern.getNumberFrom(source.getAnalystRatingSell()))
				.analystRatingStrongSell(TranslationConcern.getNumberFrom(source.getAnalystRatingStrongSell()))
				.trailingPE(TranslationConcern.getNumberFrom(source.getTrailingPE()))
				.forwardPE(TranslationConcern.getNumberFrom(source.getForwardPE()))
				.priceToSalesRatioTTM(TranslationConcern.getNumberFrom(source.getPriceToSalesRatioTTM()))
				.priceToBookRatio(TranslationConcern.getNumberFrom(source.getPriceToBookRatio()))
				.evToRevenue(TranslationConcern.getNumberFrom(source.getEVToRevenue()))
				.evToEBITDA(TranslationConcern.getNumberFrom(source.getEBITDA()))
				.beta(TranslationConcern.getNumberFrom(source.getBeta()))
				.week52High(TranslationConcern.getNumberFrom(source.getWeek52High()))
				.week52Low(TranslationConcern.getNumberFrom(source.getWeek52Low()))
				.day50MovingAverage(TranslationConcern.getNumberFrom(source.getDay50MovingAverage()))
				.day200MovingAverage(TranslationConcern.getNumberFrom(source.getDay200MovingAverage()))
				.sharesOutstanding(TranslationConcern.getNumberFrom(source.getSharesOutstanding()))
				.dividendDate(TranslationConcern.getDateFrom(source.getDividendDate()))
				.exDividendDate(TranslationConcern.getDateFrom(source.getExDividendDate()))
				
				.build();
	}

}
