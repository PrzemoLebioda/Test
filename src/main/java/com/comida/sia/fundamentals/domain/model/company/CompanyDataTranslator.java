package com.comida.sia.fundamentals.domain.model.company;

import java.text.ParseException;

import com.comida.sia.fundamentals.port.acquirer.company.CompanyDetailsData;
import com.comida.sia.sharedkernel.CountrySymbol;
import com.comida.sia.sharedkernel.CurrencySymbol;
import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class CompanyDataTranslator implements ModelTranslator<CompanyDetailsData, Company> {

	@Override
	public Company translate(CompanyDetailsData source) throws ParseException {
		return new Company.Builder(source.getSymbol())
				.latestQuarter(TranslationConcern.getDateFrom(source.getLatestQuarter()))
				.cik(source.getCIK())
				.description(source.getDescription())
				.exchange(Exchange.get(source.getExchange()))
				.currency(CurrencySymbol.get(source.getCurrency()))
				.country(CountrySymbol.get(source.getCountry()))
				.sector(Sector.get(source.getSector()))
				.industry(Industry.get(source.getIndustry()))
				.address(source.getAddress())
				.officialSite(source.getOfficialSite())
				.fiscalYearEnd(source.getFiscalYearEnd())
				.assetType(AssetType.get(source.getAssetType()))
				.build();
	}

}
