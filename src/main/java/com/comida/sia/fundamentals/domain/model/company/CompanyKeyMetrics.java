package com.comida.sia.fundamentals.domain.model.company;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.domain.ValueObject;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "CompanyKeyMetrics")
@Table(name = "companies_key_metrics",
	   indexes = {
		 @Index(name = "companies_key_metrics_registrtion_date_idx", columnList = "registrtion_date"),
		 @Index(name = "companies_key_metrics_latest_quarter_idx", columnList = "latest_quarter")
})
@Getter
public class CompanyKeyMetrics implements ValueObject<CompanyKeyMetrics>, Comparable<CompanyKeyMetrics>, TimeSeries{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2269098267653974189L;
	
	@Id
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name = "registrtion_date", nullable = true)
	private Date registrtionDate;
	
	@Column(name = "latest_quarter", columnDefinition = "date", nullable = true)
	private Date latestQuarter;
	
	@Column(name = "market_capitalization", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal marketCapitalization;
	
	@Column(name = "ebitda", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal ebitda;
	
	@Column(name = "pe_ratio", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal peRatio;
	
	@Column(name = "peg_ratio", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal pegRatio;
	
	@Column(name = "book_value", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal bookValue;
	
	@Column(name = "dividend_per_share", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal dividendPerShare;
	
	@Column(name = "dividend_yield", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal dividendYield;
	
	@Column(name = "eps", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal eps;
	
	@Column(name = "revenue_per_share_ttm", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal revenuePerShareTTM;
	
	@Column(name = "profit_margin", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal profitMargin;
	
	@Column(name = "operating_margin_ttm", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal operatingMarginTTM;
	
	@Column(name = "return_on_assets_ttm", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal returnOnAssetsTTM;
	
	@Column(name = "return_on_equity_ttm", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal returnOnEquityTTM;
	
	@Column(name = "revenue_ttm", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal revenueTTM;
	
	@Column(name = "gross_profit_ttm", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal grossProfitTTM;
	
	@Column(name = "diluted_epsttm", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal dilutedEPSTTM;
	
	@Column(name = "quarterly_earnings_growth_yoy", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal quarterlyEarningsGrowthYOY;
	
	@Column(name = "quarterly_revenue_growth_yoy", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal quarterlyRevenueGrowthYOY;
	
	@Column(name = "analyst_target_price", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal analystTargetPrice;
	
	@Column(name = "analyst_rating_strong_buy", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal analystRatingStrongBuy;
	
	@Column(name = "analyst_rating_buy", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal analystRatingBuy;
	
	@Column(name = "analyst_rating_hold", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal analystRatingHold;
	
	@Column(name = "analyst_rating_sell", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal analystRatingSell;
	
	@Column(name = "analyst_rating_strong_sell", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal analystRatingStrongSell;
	
	@Column(name = "trailing_pe", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal trailingPE;
	
	@Column(name = "forward_pe", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal forwardPE;
	
	@Column(name = "price_to_sales_ratio_ttm", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal priceToSalesRatioTTM;
	
	@Column(name = "price_to_book_ratio", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal priceToBookRatio;
	
	@Column(name = "ev_to_revenue", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal evToRevenue;
	
	@Column(name = "ev_to_ebitda", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal evToEBITDA;
	
	@Column(name = "beta", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal beta;
	
	@Column(name = "week_52_high", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal week52High;
	
	@Column(name = "week_52_low", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal week52Low;
	
	@Column(name = "day_50_moving_average", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal day50MovingAverage;
	
	@Column(name = "day_200_moving_average", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal day200MovingAverage;
	
	@Column(name = "shares_outstanding", columnDefinition = "numeric(36,4)", nullable = true)
    private BigDecimal sharesOutstanding;
	
	@Column(name = "dividend_date", columnDefinition = "date", nullable = true)
    private Date dividendDate;
	
	@Column(name = "ex_dividend_date", columnDefinition = "date", nullable = true)
    private Date exDividendDate;
    
    public CompanyKeyMetrics() {
    	super();
    }
    
    public CompanyKeyMetrics(Builder builder) {
    	super();
    	
    	this.id = builder.id;
    	this.registrtionDate = builder.registrationDate;
    	this.latestQuarter = builder.latestQuarter;
    	this.marketCapitalization = builder.marketCapitalization;
    	this.ebitda = builder.ebitda;
    	this.peRatio = builder.peRatio;
    	this.pegRatio = builder.pegRatio;
    	this.bookValue = builder.bookValue;
    	this.dividendPerShare = builder.dividendPerShare;
    	this.dividendYield = builder.dividendYield;
    	this.eps = builder.eps;
    	this.revenuePerShareTTM = builder.revenuePerShareTTM;
    	this.profitMargin = builder.profitMargin;
    	this.operatingMarginTTM = builder.operatingMarginTTM;
    	this.returnOnAssetsTTM = builder.returnOnAssetsTTM;
    	this.returnOnEquityTTM = builder.returnOnEquityTTM;
    	this.revenueTTM = builder.revenueTTM;
    	this.grossProfitTTM = builder.grossProfitTTM;
    	this.dilutedEPSTTM = builder.dilutedEPSTTM;
    	this.quarterlyEarningsGrowthYOY = builder.quarterlyEarningsGrowthYOY;
    	this.quarterlyRevenueGrowthYOY = builder.quarterlyRevenueGrowthYOY;
    	this.analystTargetPrice = builder.analystTargetPrice;
    	this.analystRatingStrongBuy = builder.analystRatingStrongBuy;
    	this.analystRatingBuy = builder.analystRatingBuy;
    	this.analystRatingHold = builder.analystRatingHold;
    	this.analystRatingSell = builder.analystRatingSell;
    	this.analystRatingStrongSell = builder.analystRatingStrongSell;
    	this.trailingPE = builder.trailingPE;
    	this.forwardPE = builder.forwardPE;
    	this.priceToSalesRatioTTM = builder.priceToSalesRatioTTM;
    	this.priceToBookRatio = builder.priceToBookRatio;
    	this.evToRevenue = builder.evToRevenue;
    	this.evToEBITDA = builder.evToEBITDA;
    	this.beta = builder.beta;
    	this.week52High = builder.week52High;
    	this.week52Low = builder.week52Low;
    	this.day50MovingAverage = builder.day50MovingAverage;
    	this.day200MovingAverage = builder.day200MovingAverage;
    	this.sharesOutstanding = builder.sharesOutstanding;
    	this.dividendDate = builder.dividendDate;
    	this.exDividendDate = builder.exDividendDate;
    }
    
    @Override
	public Date occuranceTime() {
		return latestQuarter;
	}
	
	@Override
	public int compareTo(CompanyKeyMetrics o) {
		if (latestQuarter == null || o.latestQuarter == null) {
			return 0;
		} else {
			return latestQuarter.compareTo(o.latestQuarter);
		}
	}

	@Override
	public boolean sameValueAs(CompanyKeyMetrics other) {
		if(other != null) {
			return  (ComparationConcern.sameDates(this.latestQuarter, other.latestQuarter)) &&
			    	(this.marketCapitalization != null && this.marketCapitalization.compareTo(other.marketCapitalization) == 0) &&
			    	((this.ebitda == null && this.ebitda == null) || (this.ebitda != null && this.ebitda.compareTo(other.ebitda) == 0)) &&
			    	((this.peRatio == null && this.peRatio == null) || (this.peRatio != null && this.peRatio.compareTo(other.peRatio) == 0)) &&
			    	((this.pegRatio == null && this.pegRatio == null) || (this.pegRatio != null && this.pegRatio.compareTo(other.pegRatio) == 0)) &&
			    	((this.bookValue == null && this.bookValue == null) || (this.bookValue != null && this.bookValue.compareTo(other.bookValue) == 0)) &&
			    	((this.dividendPerShare == null && this.dividendPerShare == null) || (this.dividendPerShare != null && this.dividendPerShare.compareTo(other.dividendPerShare) == 0)) &&
			    	((this.dividendYield == null && this.dividendYield == null) || (this.dividendYield != null && this.dividendYield.compareTo(other.dividendYield) == 0)) &&
			    	((this.eps == null && this.eps == null) || (this.eps != null && this.eps.compareTo(other.eps) == 0)) &&			    	
			    	((this.revenuePerShareTTM == null && this.revenuePerShareTTM == null) || (this.revenuePerShareTTM != null && this.revenuePerShareTTM.compareTo(other.revenuePerShareTTM) == 0)) &&
			    	((this.profitMargin == null && this.profitMargin == null) || (this.profitMargin != null && this.profitMargin.compareTo(other.profitMargin) == 0)) &&
			    	((this.operatingMarginTTM == null && this.operatingMarginTTM == null) || (this.operatingMarginTTM != null && this.operatingMarginTTM.compareTo(other.operatingMarginTTM) == 0)) &&
			    	((this.returnOnAssetsTTM == null && this.returnOnAssetsTTM == null) || (this.returnOnAssetsTTM != null && this.returnOnAssetsTTM.compareTo(other.returnOnAssetsTTM) == 0)) &&
			    	((this.returnOnEquityTTM == null && this.returnOnEquityTTM == null) || (this.returnOnEquityTTM != null && this.returnOnEquityTTM.compareTo(other.returnOnEquityTTM) == 0)) &&
			    	((this.revenueTTM == null && this.revenueTTM == null) || (this.revenueTTM != null && this.revenueTTM.compareTo(other.revenueTTM) == 0)) &&
			    	((this.grossProfitTTM == null && this.grossProfitTTM == null) || (this.grossProfitTTM != null && this.grossProfitTTM.compareTo(other.grossProfitTTM) == 0)) &&
			    	((this.dilutedEPSTTM == null && this.dilutedEPSTTM == null) || (this.dilutedEPSTTM != null && this.dilutedEPSTTM.compareTo(other.dilutedEPSTTM) == 0)) &&
			    	((this.quarterlyEarningsGrowthYOY == null && this.quarterlyEarningsGrowthYOY == null) || (this.quarterlyEarningsGrowthYOY != null && this.quarterlyEarningsGrowthYOY.compareTo(other.quarterlyEarningsGrowthYOY) == 0)) &&
			    	((this.quarterlyRevenueGrowthYOY == null && this.quarterlyRevenueGrowthYOY == null) || (this.quarterlyRevenueGrowthYOY != null && this.quarterlyRevenueGrowthYOY.compareTo(other.quarterlyRevenueGrowthYOY) == 0)) &&
			    	((this.analystTargetPrice == null && this.analystTargetPrice == null) || (this.analystTargetPrice != null && this.analystTargetPrice.compareTo(other.analystTargetPrice) == 0)) &&
			    	((this.analystRatingStrongBuy == null && this.analystRatingStrongBuy == null) || (this.analystRatingStrongBuy != null && this.analystRatingStrongBuy.compareTo(other.analystRatingStrongBuy) == 0)) &&
			    	((this.analystRatingBuy == null && this.analystRatingBuy == null) || (this.analystRatingBuy != null && this.analystRatingBuy.compareTo(other.analystRatingBuy) == 0)) &&
			    	((this.analystRatingHold == null && this.analystRatingHold == null) || (this.analystRatingHold != null && this.analystRatingHold.compareTo(other.analystRatingHold) == 0)) &&
			    	((this.analystRatingSell == null && this.analystRatingSell == null) || (this.analystRatingSell != null && this.analystRatingSell.compareTo(other.analystRatingSell) == 0)) &&
			    	((this.analystRatingStrongSell == null && this.analystRatingStrongSell == null) || (this.analystRatingStrongSell != null && this.analystRatingStrongSell.compareTo(other.analystRatingStrongSell) == 0)) &&
			    	((this.trailingPE == null && this.trailingPE == null) || (this.trailingPE != null && this.trailingPE.compareTo(other.trailingPE) == 0)) &&
			    	((this.forwardPE == null && this.forwardPE == null) || (this.forwardPE != null && this.forwardPE.compareTo(other.forwardPE) == 0)) &&
			    	((this.priceToSalesRatioTTM == null && this.priceToSalesRatioTTM == null) || (this.priceToSalesRatioTTM != null && this.priceToSalesRatioTTM.compareTo(other.priceToSalesRatioTTM) == 0)) &&
			    	((this.priceToBookRatio == null && this.priceToBookRatio == null) || (this.priceToBookRatio != null && this.priceToBookRatio.compareTo(other.priceToBookRatio) == 0)) &&
			    	((this.evToRevenue == null && this.evToRevenue == null) || (this.evToRevenue != null && this.evToRevenue.compareTo(other.evToRevenue) == 0)) &&
			    	((this.evToEBITDA == null && this.evToEBITDA == null) || (this.evToEBITDA != null && this.evToEBITDA.compareTo(other.evToEBITDA) == 0)) &&
			    	((this.beta == null && this.beta == null) || (this.beta != null && this.beta.compareTo(other.beta) == 0)) &&
			    	((this.week52High == null && this.week52High == null) || (this.week52High != null && this.week52High.compareTo(other.week52High) == 0)) &&
			    	((this.week52Low == null && this.week52Low == null) || (this.week52Low != null && this.week52Low.compareTo(other.week52Low) == 0)) &&
			    	((this.day50MovingAverage == null && this.day50MovingAverage == null) || (this.day50MovingAverage != null && this.day50MovingAverage.compareTo(other.day50MovingAverage) == 0)) &&
			    	((this.day200MovingAverage == null && this.day200MovingAverage == null) || (this.day200MovingAverage != null && this.day200MovingAverage.compareTo(other.day200MovingAverage) == 0)) &&
			    	((this.sharesOutstanding == null && this.sharesOutstanding == null) || (this.sharesOutstanding != null && this.sharesOutstanding.compareTo(other.sharesOutstanding) == 0)) &&
			    	(ComparationConcern.sameDates(this.dividendDate, other.dividendDate)) &&
			    	(ComparationConcern.sameDates(this.exDividendDate, other.exDividendDate));
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(analystRatingBuy, analystRatingHold, analystRatingSell, analystRatingStrongBuy,
				analystRatingStrongSell, analystTargetPrice, beta, bookValue, day200MovingAverage, day50MovingAverage,
				dilutedEPSTTM, dividendDate, dividendPerShare, dividendYield, ebitda, eps, evToEBITDA, evToRevenue,
				exDividendDate, forwardPE, grossProfitTTM, latestQuarter, marketCapitalization, operatingMarginTTM,
				pegRatio, peRatio, priceToBookRatio, priceToSalesRatioTTM, profitMargin, quarterlyEarningsGrowthYOY,
				quarterlyRevenueGrowthYOY, registrtionDate, returnOnAssetsTTM, returnOnEquityTTM, revenuePerShareTTM,
				revenueTTM, sharesOutstanding, trailingPE, week52High, week52Low);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompanyKeyMetrics other = (CompanyKeyMetrics) obj;
		return sameValueAs(other);
	}

	@Override
	public String toString() {
		return "KeyMetrics [registrtionDate=" + registrtionDate + ", latestQuarter=" + latestQuarter
				+ ", marketCapitalization=" + marketCapitalization + ", ebitda=" + ebitda + ", peRatio=" + peRatio
				+ ", pegRatio=" + pegRatio + ", bookValue=" + bookValue + ", dividendPerShare=" + dividendPerShare
				+ ", dividendYield=" + dividendYield + ", eps=" + eps + ", revenuePerShareTTM=" + revenuePerShareTTM
				+ ", profitMargin=" + profitMargin + ", operatingMarginTTM=" + operatingMarginTTM
				+ ", returnOnAssetsTTM=" + returnOnAssetsTTM + ", returnOnEquityTTM=" + returnOnEquityTTM
				+ ", revenueTTM=" + revenueTTM + ", grossProfitTTM=" + grossProfitTTM + ", dilutedEPSTTM="
				+ dilutedEPSTTM + ", quarterlyEarningsGrowthYOY=" + quarterlyEarningsGrowthYOY
				+ ", quarterlyRevenueGrowthYOY=" + quarterlyRevenueGrowthYOY + ", analystTargetPrice="
				+ analystTargetPrice + ", analystRatingStrongBuy=" + analystRatingStrongBuy + ", analystRatingBuy="
				+ analystRatingBuy + ", analystRatingHold=" + analystRatingHold + ", analystRatingSell="
				+ analystRatingSell + ", analystRatingStrongSell=" + analystRatingStrongSell + ", trailingPE="
				+ trailingPE + ", forwardPE=" + forwardPE + ", priceToSalesRatioTTM=" + priceToSalesRatioTTM
				+ ", priceToBookRatio=" + priceToBookRatio + ", evToRevenue=" + evToRevenue + ", evToEBITDA="
				+ evToEBITDA + ", beta=" + beta + ", week52High=" + week52High + ", week52Low=" + week52Low
				+ ", day50MovingAverage=" + day50MovingAverage + ", day200MovingAverage=" + day200MovingAverage
				+ ", sharesOutstanding=" + sharesOutstanding + ", dividendDate=" + dividendDate + ", exDividendDate="
				+ exDividendDate + "]";
	}	
	
	public static class Builder{
		private UUID id;
		private Date registrationDate;
		private Date latestQuarter;
		private BigDecimal marketCapitalization;
		private BigDecimal ebitda;
		private BigDecimal peRatio;
		private BigDecimal pegRatio;
		private BigDecimal bookValue;
		private BigDecimal dividendPerShare;
		private BigDecimal dividendYield;
		private BigDecimal eps;
		private BigDecimal revenuePerShareTTM;
		private BigDecimal profitMargin;
		private BigDecimal operatingMarginTTM;
		private BigDecimal returnOnAssetsTTM;
		private BigDecimal returnOnEquityTTM;
		private BigDecimal revenueTTM;
		private BigDecimal grossProfitTTM;
		private BigDecimal dilutedEPSTTM;
		private BigDecimal quarterlyEarningsGrowthYOY;
		private BigDecimal quarterlyRevenueGrowthYOY;
		private BigDecimal analystTargetPrice;
		private BigDecimal analystRatingStrongBuy;
		private BigDecimal analystRatingBuy;
		private BigDecimal analystRatingHold;
		private BigDecimal analystRatingSell;
		private BigDecimal analystRatingStrongSell;
		private BigDecimal trailingPE;
		private BigDecimal forwardPE;
		private BigDecimal priceToSalesRatioTTM;
		private BigDecimal priceToBookRatio;
		private BigDecimal evToRevenue;
		private BigDecimal evToEBITDA;
		private BigDecimal beta;
		private BigDecimal week52High;
		private BigDecimal week52Low;
		private BigDecimal day50MovingAverage;
		private BigDecimal day200MovingAverage;
		private BigDecimal sharesOutstanding;
		private Date dividendDate;
		private Date exDividendDate;
		
		public Builder(UUID id, Date registrationDate) {
			setId(id);
			setRegistrationDate(registrationDate);
		}
		
		private void setId(UUID id) {
			AssertionConcern.assertNotNull(id, "Id is mandatory in order to build company key metrics");
			this.id = id;
		}
		
		private void setRegistrationDate(Date registrtionDate) {
			AssertionConcern.assertNotNull(id, "Registration date is mandatory in order to build company key metrics");
			this.registrationDate = registrtionDate;
		}
		
		public Builder latestQuarter(Date latestQuarter) {
			this.latestQuarter = latestQuarter;
			return this;
		}
		
		public Builder marketCapitalization(BigDecimal marketCapitalization) {
			this.marketCapitalization = marketCapitalization;
			return this;
		}
		
		public Builder ebitda(BigDecimal ebitda) {
			this.ebitda = ebitda;
			return this;
		}
		
		public Builder peRatio(BigDecimal peRatio) {
			this.pegRatio = peRatio;
			return this;
		}
		
		public Builder pegRatio(BigDecimal pegRatio) {
			this.pegRatio = pegRatio;
			return this;
		}
		
		public Builder bookValue(BigDecimal bookValue) {
			this.bookValue = bookValue;
			return this;
		}
		
		public Builder dividendPerShare(BigDecimal dividendPerShare) {
			this.dividendPerShare = dividendPerShare;
			return this;
		}
		
		public Builder dividendYield(BigDecimal dividendYield) {
			this.dividendYield = dividendYield;
			return this;
		}
		
		public Builder eps(BigDecimal eps) {
			this.eps = eps;
			return this;
		}
		
		public Builder revenuePerShareTTM(BigDecimal revenuePerShareTTM) {
			this.revenuePerShareTTM = revenuePerShareTTM;
			return this;
		}
		
		public Builder profitMargin(BigDecimal profitMargin) {
			this.profitMargin = profitMargin;
			return this;
		}
		
		public Builder operatingMarginTTM(BigDecimal operatingMarginTTM) {
			this.operatingMarginTTM = operatingMarginTTM;
			return this;
		}
		
		public Builder returnOnAssetsTTM(BigDecimal returnOnAssetsTTM) {
			this.returnOnAssetsTTM = returnOnAssetsTTM;
			return this;
		}
		
		public Builder returnOnEquityTTM(BigDecimal returnOnEquityTTM) {
			this.returnOnEquityTTM = returnOnEquityTTM;
			return this;
		}
		
		public Builder revenueTTM(BigDecimal revenueTTM) {
			this.revenueTTM = revenueTTM;
			return this;
		}
		
		public Builder grossProfitTTM(BigDecimal grossProfitTTM) {
			this.grossProfitTTM = grossProfitTTM;
			return this;
		}
		
		public Builder dilutedEPSTTM(BigDecimal dilutedEPSTTM) {
			this.dilutedEPSTTM = dilutedEPSTTM;
			return this;
		}
		
		public Builder quarterlyEarningsGrowthYOY(BigDecimal quarterlyEarningsGrowthYOY) {
			this.quarterlyEarningsGrowthYOY = quarterlyEarningsGrowthYOY;
			return this;
		}
		
		public Builder quarterlyRevenueGrowthYOY(BigDecimal quarterlyRevenueGrowthYOY) {
			this.quarterlyRevenueGrowthYOY = quarterlyRevenueGrowthYOY;
			return this;
		}
		
		public Builder analystTargetPrice(BigDecimal analystTargetPrice) {
			this.analystTargetPrice = analystTargetPrice;
			return this;
		}
		
		public Builder analystRatingStrongBuy(BigDecimal analystRatingStrongBuy) {
			this.analystRatingStrongBuy = analystRatingStrongBuy;
			return this;
		}
		
		public Builder analystRatingBuy(BigDecimal analystRatingBuy) {
			this.analystRatingBuy = analystRatingBuy;
			return this;
		}
		
		public Builder analystRatingHold(BigDecimal analystRatingHold) {
			this.analystRatingHold = analystRatingHold;
			return this;
		}
		
		public Builder analystRatingSell(BigDecimal analystRatingSell) {
			this.analystRatingSell = analystRatingSell;
			return this;
		}
		
		public Builder analystRatingStrongSell(BigDecimal analystRatingStrongSell) {
			this.analystRatingStrongSell = analystRatingStrongSell;
			return this;
		}
		
		public Builder trailingPE(BigDecimal trailingPE) {
			this.trailingPE = trailingPE;
			return this;
		}
		
		public Builder forwardPE(BigDecimal forwardPE) {
			this.forwardPE = forwardPE;
			return this;
		}
		
		public Builder priceToSalesRatioTTM(BigDecimal priceToSalesRatioTTM) {
			this.priceToSalesRatioTTM = priceToSalesRatioTTM;
			return this;
		}
		
		public Builder priceToBookRatio(BigDecimal priceToBookRatio) {
			this.priceToBookRatio = priceToBookRatio;
			return this;
		}
		
		public Builder evToRevenue(BigDecimal evToRevenue) {
			this.evToRevenue = evToRevenue;
			return this;
		}
		
		public Builder evToEBITDA(BigDecimal evToEBITDA) {
			this.evToEBITDA = evToEBITDA;
			return this;
		}
		
		public Builder beta(BigDecimal beta) {
			this.beta = beta;
			return this;
		}
		
		public Builder week52High(BigDecimal week52High) {
			this.week52High = week52High;
			return this;
		}
		
		public Builder week52Low(BigDecimal week52Low) {
			this.week52Low = week52Low;
			return this;
		}
		
		public Builder day50MovingAverage(BigDecimal day50MovingAverage) {
			this.day50MovingAverage = day50MovingAverage;
			return this;
		}
		
		public Builder day200MovingAverage(BigDecimal day200MovingAverage) {
			this.day200MovingAverage = day200MovingAverage;
			return this;
		}
		
		public Builder sharesOutstanding(BigDecimal sharesOutstanding) {
			this.sharesOutstanding = sharesOutstanding;
			return this;
		}
		
		public Builder dividendDate(Date dividendDate) {
			this.dividendDate = dividendDate;
			return this;
		}
		
		public Builder exDividendDate(Date exDividendDate) {
			this.exDividendDate = exDividendDate;
			return this;
		}
		
		public CompanyKeyMetrics build() {
			return new CompanyKeyMetrics(this);
		}
	}
}
