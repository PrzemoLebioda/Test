package com.comida.sia.fundamentals.port.acquirer.company;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.TranslationConcern;
import com.comida.sia.sync.supervision.domain.model.Direction;
import com.comida.sia.sync.supervision.domain.model.WaterMark;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyDetailsData extends WaterMark{
	private String Symbol;
    private String AssetType;
    private String Name;
    private String Description;
    private String CIK;
    private String Exchange;
    private String Currency;
    private String Country;
    private String Sector;
    private String Industry;
    private String Address;
    private String OfficialSite;
    private String FiscalYearEnd;
    private String LatestQuarter;
    private String MarketCapitalization;
    private String EBITDA;
    private String PERatio;
    private String PEGRatio;
    private String BookValue;
    private String DividendPerShare;
    private String DividendYield;
    private String EPS;
    private String RevenuePerShareTTM;
    private String ProfitMargin;
    private String OperatingMarginTTM;
    private String ReturnOnAssetsTTM;
    private String ReturnOnEquityTTM;
    private String RevenueTTM;
    private String GrossProfitTTM;
    private String DilutedEPSTTM;
    private String QuarterlyEarningsGrowthYOY;
    private String QuarterlyRevenueGrowthYOY;
    private String AnalystTargetPrice;
    private String AnalystRatingStrongBuy;
    private String AnalystRatingBuy;
    private String AnalystRatingHold;
    private String AnalystRatingSell;
    private String AnalystRatingStrongSell;
    private String TrailingPE;
    private String ForwardPE;
    private String PriceToSalesRatioTTM;
    private String PriceToBookRatio;
    private String EVToRevenue;
    private String EVToEBITDA;
    private String Beta;
    private String Week52High;
    private String Week52Low;
    private String Day50MovingAverage;
    private String Day200MovingAverage;
    private String SharesOutstanding;
    private String DividendDate;
    private String ExDividendDate;
	
	public CompanyDetailsData() {
		super(Direction.ASCENDING);
	}

	@Override
	public String calculateLevel() {
		try {
			return TranslationConcern.fillLeadingZeros(TranslationConcern.getConventer().convertToAlphaNumeric(TranslationConcern.getDateFrom(LatestQuarter)), 10);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Date occuranceTime() throws ParseException {
		return TranslationConcern.getDateFrom(LatestQuarter);
	}	
	
	@Override
	public String toString() {
		return "CompanyData [Symbol=" + Symbol + ", AssetType=" + AssetType + ", Name=" + Name + ", Description="
				+ Description + ", CIK=" + CIK + ", Exchange=" + Exchange + ", Currency=" + Currency + ", Country="
				+ Country + ", Sector=" + Sector + ", Industry=" + Industry + ", Address=" + Address + ", OfficialSite="
				+ OfficialSite + ", FiscalYearEnd=" + FiscalYearEnd + ", LatestQuarter=" + LatestQuarter
				+ ", MarketCapitalization=" + MarketCapitalization + ", EBITDA=" + EBITDA + ", PERatio=" + PERatio
				+ ", PEGRatio=" + PEGRatio + ", BookValue=" + BookValue + ", DividendPerShare=" + DividendPerShare
				+ ", DividendYield=" + DividendYield + ", EPS=" + EPS + ", RevenuePerShareTTM=" + RevenuePerShareTTM
				+ ", ProfitMargin=" + ProfitMargin + ", OperatingMarginTTM=" + OperatingMarginTTM
				+ ", ReturnOnAssetsTTM=" + ReturnOnAssetsTTM + ", ReturnOnEquityTTM=" + ReturnOnEquityTTM
				+ ", RevenueTTM=" + RevenueTTM + ", GrossProfitTTM=" + GrossProfitTTM + ", DilutedEPSTTM="
				+ DilutedEPSTTM + ", QuarterlyEarningsGrowthYOY=" + QuarterlyEarningsGrowthYOY
				+ ", QuarterlyRevenueGrowthYOY=" + QuarterlyRevenueGrowthYOY + ", AnalystTargetPrice="
				+ AnalystTargetPrice + ", AnalystRatingStrongBuy=" + AnalystRatingStrongBuy + ", AnalystRatingBuy="
				+ AnalystRatingBuy + ", AnalystRatingHold=" + AnalystRatingHold + ", AnalystRatingSell="
				+ AnalystRatingSell + ", AnalystRatingStrongSell=" + AnalystRatingStrongSell + ", TrailingPE="
				+ TrailingPE + ", ForwardPE=" + ForwardPE + ", PriceToSalesRatioTTM=" + PriceToSalesRatioTTM
				+ ", PriceToBookRatio=" + PriceToBookRatio + ", EVToRevenue=" + EVToRevenue + ", EVToEBITDA="
				+ EVToEBITDA + ", Beta=" + Beta + ", Week52High=" + Week52High + ", Week52Low=" + Week52Low
				+ ", Day50MovingAverage=" + Day50MovingAverage + ", Day200MovingAverage=" + Day200MovingAverage
				+ ", SharesOutstanding=" + SharesOutstanding + ", DividendDate=" + DividendDate + ", ExDividendDate="
				+ ExDividendDate + "]";
	}
}
