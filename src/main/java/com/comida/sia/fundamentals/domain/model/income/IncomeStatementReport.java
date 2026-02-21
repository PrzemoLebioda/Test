package com.comida.sia.fundamentals.domain.model.income;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import com.comida.sia.fundamentals.domain.model.ReportType;
import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.ComparationConcern;
import com.comida.sia.sharedkernel.CurrencySymbol;
import com.comida.sia.sharedkernel.domain.ValueObject;
import com.comida.sia.sync.supervision.domain.model.TimeSeries;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity(name = "IncomeStatementReport")
@Table(name = "fundamentals_income_statement_reports",
indexes = {
		@Index(name = "income_statement_reports_symbol_idx", columnList = "ticker_symbol"),
		@Index(name = "income_statement_reports_type_idx", columnList = "type"),
		@Index(name = "income_statement_reports_fiscal_date_ending_idx", columnList = "fiscal_date_ending")
})
@Getter
public class IncomeStatementReport implements ValueObject<IncomeStatementReport>, Comparable<IncomeStatementReport>, TimeSeries {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1781367036702410948L;
	
	@Id
	@Column(name = "id", nullable = false)
	private UUID id;
	
	@Column(name = "ticker_symbol", nullable = false)
	private String tickerSymbol;
	
	@Column(name = "type", nullable = false)
	@Enumerated(EnumType.STRING)
	private ReportType type;
	
	@Column(name = "fiscal_date_ending", columnDefinition = "date", nullable = false)
	private Date fiscalDateEnding;
	
	@Column(name = "reported_currency", nullable = false)
	@Enumerated(EnumType.STRING)
	private CurrencySymbol reportedCurrency;
	
	@Column(name = "gross_profit", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal grossProfit;
	
	@Column(name = "total_revenue", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal totalRevenue;
	
	@Column(name = "cost_of_revenue", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal costOfRevenue;
	
	@Column(name = "costof_goods_and_services_sold", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal costOfGoodsAndServicesSold;
	
	@Column(name = "operating_income", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal operatingIncome;
	
	@Column(name = "selling_general_and_administrative", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal sellingGeneralAndAdministrative;
	
	@Column(name = "research_and_development", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal researchAndDevelopment;
	
	@Column(name = "operating_expenses", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal operatingExpenses;
	
	@Column(name = "investment_incomeNet", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal investmentIncomeNet;
	
	@Column(name = "net_interest_income", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal netInterestIncome;
	
	@Column(name = "interest_income", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal interestIncome;
	
	@Column(name = "interest_expense", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal interestExpense;
	
	@Column(name = "non_interest_income", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal nonInterestIncome;
	
	@Column(name = "other_non_operating_income", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal otherNonOperatingIncome;
	
	@Column(name = "depreciation", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal depreciation;
	
	@Column(name = "depreciation_and_amortization", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal depreciationAndAmortization;
	
	@Column(name = "income_before_tax", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal incomeBeforeTax;
	
	@Column(name = "income_tax_expense", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal incomeTaxExpense;
	
	@Column(name = "interest_and_debt_expense", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal interestAndDebtExpense;
	
	@Column(name = "net_income_from_continuing_operations", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal netIncomeFromContinuingOperations;
	
	@Column(name = "comprehensive_income_net_of_tax", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal comprehensiveIncomeNetOfTax;
	
	@Column(name = "ebit", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal ebit;
	
	@Column(name = "ebitda", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal ebitda;
	
	@Column(name = "net_income", columnDefinition = "numeric(36,4)", nullable = true)
	private BigDecimal netIncome;
	
	public IncomeStatementReport() {
		super();
	}
	
	public IncomeStatementReport(Builder builder) throws ParseException {
		this.id = builder.id;
		this.type = builder.type;
		this.tickerSymbol = builder.tickerSymbol;
		
		this.fiscalDateEnding = builder.fiscalDateEnding;
		this.reportedCurrency = builder.reportedCurrency;
		this.grossProfit = builder.grossProfit;
		this.totalRevenue = builder.totalRevenue;
		this.costOfRevenue = builder.costOfRevenue;
		this.costOfGoodsAndServicesSold = builder.costOfGoodsAndServicesSold;
		this.operatingIncome = builder.operatingIncome;
		this.sellingGeneralAndAdministrative = builder.sellingGeneralAndAdministrative;
		this.researchAndDevelopment = builder.researchAndDevelopment;
		this.operatingExpenses = builder.operatingExpenses;
		this.investmentIncomeNet = builder.investmentIncomeNet;
		this.netInterestIncome = builder.netInterestIncome;
		this.interestIncome = builder.interestIncome;
		this.interestExpense = builder.interestExpense;
		this.nonInterestIncome = builder.nonInterestIncome;
		this.otherNonOperatingIncome = builder.otherNonOperatingIncome;
		this.depreciation = builder.depreciation;
		this.depreciationAndAmortization = builder.depreciationAndAmortization;
		this.incomeBeforeTax = builder.incomeBeforeTax;
		this.incomeTaxExpense = builder.incomeTaxExpense;
		this.interestAndDebtExpense = builder.interestAndDebtExpense;
		this.netIncomeFromContinuingOperations = builder.netIncomeFromContinuingOperations;
		this.comprehensiveIncomeNetOfTax = builder.comprehensiveIncomeNetOfTax;
		this.ebit = builder.ebit;
		this.ebitda = builder.ebitda;
		this.netIncome = builder.netIncome;
		
	}
	
	@Override
	public int compareTo(IncomeStatementReport o) {
		if (this.fiscalDateEnding == null || o.fiscalDateEnding == null) {
			return 0;
		} else {
			return this.fiscalDateEnding.compareTo(o.fiscalDateEnding);
		}
	}
	
	@Override
	public Date occuranceTime() {
		return fiscalDateEnding;
	}

	@Override
	public boolean sameValueAs(IncomeStatementReport other) {
		if(other != null) {
			return 	(this.tickerSymbol.equals(other.tickerSymbol)) &&
					(this.type.equals(other.type)) &&
					(ComparationConcern.sameDates(this.fiscalDateEnding, other.fiscalDateEnding)) &&
					((this.reportedCurrency == null && other.reportedCurrency == null) || (this.reportedCurrency != null && this.reportedCurrency.equals(other.reportedCurrency))) &&					
					((this.grossProfit == null && other.grossProfit == null) || (this.grossProfit != null && this.grossProfit.compareTo(other.grossProfit) == 0 )) &&
					((this.totalRevenue == null && other.totalRevenue == null) || (this.totalRevenue != null && this.totalRevenue.compareTo(other.totalRevenue) == 0 )) &&
					((this.costOfRevenue == null && other.costOfRevenue == null) || (this.costOfRevenue != null && this.costOfRevenue.compareTo(other.costOfRevenue) == 0 )) &&
					((this.costOfGoodsAndServicesSold == null && other.costOfGoodsAndServicesSold == null) || (this.costOfGoodsAndServicesSold != null && this.costOfGoodsAndServicesSold.compareTo(other.costOfGoodsAndServicesSold) == 0 )) &&
					((this.operatingIncome == null && other.operatingIncome == null) || (this.operatingIncome != null && this.operatingIncome.compareTo(other.operatingIncome) == 0 )) &&
					((this.sellingGeneralAndAdministrative == null && other.sellingGeneralAndAdministrative == null) || (this.sellingGeneralAndAdministrative != null && this.sellingGeneralAndAdministrative.compareTo(other.sellingGeneralAndAdministrative) == 0 )) &&
					((this.researchAndDevelopment == null && other.researchAndDevelopment == null) || (this.researchAndDevelopment != null && this.researchAndDevelopment.compareTo(other.researchAndDevelopment) == 0 )) &&
					((this.operatingExpenses == null && other.operatingExpenses == null) || (this.operatingExpenses != null && this.operatingExpenses.compareTo(other.operatingExpenses) == 0 )) &&
					((this.investmentIncomeNet == null && other.investmentIncomeNet == null) || (this.investmentIncomeNet != null && this.investmentIncomeNet.compareTo(other.investmentIncomeNet) == 0 )) &&
					((this.netInterestIncome == null && other.netInterestIncome == null) || (this.netInterestIncome != null && this.netInterestIncome.compareTo(other.netInterestIncome) == 0 )) &&
					((this.interestIncome == null && other.interestIncome == null) || (this.interestIncome != null && this.interestIncome.compareTo(other.interestIncome) == 0 )) &&
					((this.interestExpense == null && other.interestExpense == null) || (this.interestExpense != null && this.interestExpense.compareTo(other.interestExpense) == 0 )) &&
					((this.nonInterestIncome == null && other.nonInterestIncome == null) || (this.nonInterestIncome != null && this.nonInterestIncome.compareTo(other.nonInterestIncome) == 0 )) &&
					((this.otherNonOperatingIncome == null && other.otherNonOperatingIncome == null) || (this.otherNonOperatingIncome != null && this.otherNonOperatingIncome.compareTo(other.otherNonOperatingIncome) == 0 )) &&
					((this.depreciation == null && other.depreciation == null) || (this.depreciation != null && this.depreciation.compareTo(other.depreciation) == 0 )) &&
					((this.depreciationAndAmortization == null && other.depreciationAndAmortization == null) || (this.depreciationAndAmortization != null && this.depreciationAndAmortization.compareTo(other.depreciationAndAmortization) == 0 )) &&
					((this.incomeBeforeTax == null && other.incomeBeforeTax == null) || (this.incomeBeforeTax != null && this.incomeBeforeTax.compareTo(other.incomeBeforeTax) == 0 )) &&
					((this.incomeTaxExpense == null && other.incomeTaxExpense == null) || (this.incomeTaxExpense != null && this.incomeTaxExpense.compareTo(other.incomeTaxExpense) == 0 )) &&
					((this.interestAndDebtExpense == null && other.interestAndDebtExpense == null) || (this.interestAndDebtExpense != null && this.interestAndDebtExpense.compareTo(other.interestAndDebtExpense) == 0 )) &&
					((this.netIncomeFromContinuingOperations == null && other.netIncomeFromContinuingOperations == null) || (this.netIncomeFromContinuingOperations != null && this.netIncomeFromContinuingOperations.compareTo(other.netIncomeFromContinuingOperations) == 0 )) &&
					((this.comprehensiveIncomeNetOfTax == null && other.comprehensiveIncomeNetOfTax == null) || (this.comprehensiveIncomeNetOfTax != null && this.comprehensiveIncomeNetOfTax.compareTo(other.comprehensiveIncomeNetOfTax) == 0 )) &&
					((this.ebit == null && other.ebit == null) || (this.ebit != null && this.ebit.compareTo(other.ebit) == 0 )) &&
					((this.ebitda == null && other.ebitda == null) || (this.ebitda != null && this.ebitda.compareTo(other.ebitda) == 0 )) &&
					((this.netIncome == null && other.netIncome == null) || (this.netIncome != null && this.netIncome.compareTo(other.netIncome) == 0 ));
		}else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(comprehensiveIncomeNetOfTax, costOfRevenue, costOfGoodsAndServicesSold, depreciation,
				depreciationAndAmortization, ebit, ebitda, fiscalDateEnding, grossProfit, incomeBeforeTax,
				incomeTaxExpense, interestAndDebtExpense, interestExpense, interestIncome, investmentIncomeNet,
				netIncome, netIncomeFromContinuingOperations, netInterestIncome, nonInterestIncome, operatingExpenses,
				operatingIncome, otherNonOperatingIncome, reportedCurrency, researchAndDevelopment,
				sellingGeneralAndAdministrative, tickerSymbol, totalRevenue, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IncomeStatementReport other = (IncomeStatementReport) obj;
		return this.sameValueAs(other);
	}
	
	@Override
	public String toString() {
		return "IncomeStatementReport [id=" + id + ", tickerSymbol=" + tickerSymbol + ", type=" + type
				+ ", fiscalDateEnding=" + fiscalDateEnding + ", reportedCurrency=" + reportedCurrency + ", grossProfit="
				+ grossProfit + ", totalRevenue=" + totalRevenue + ", costOfRevenue=" + costOfRevenue
				+ ", costOfGoodsAndServicesSold=" + costOfGoodsAndServicesSold + ", operatingIncome=" + operatingIncome
				+ ", sellingGeneralAndAdministrative=" + sellingGeneralAndAdministrative + ", researchAndDevelopment="
				+ researchAndDevelopment + ", operatingExpenses=" + operatingExpenses + ", investmentIncomeNet="
				+ investmentIncomeNet + ", netInterestIncome=" + netInterestIncome + ", interestIncome="
				+ interestIncome + ", interestExpense=" + interestExpense + ", nonInterestIncome=" + nonInterestIncome
				+ ", otherNonOperatingIncome=" + otherNonOperatingIncome + ", depreciation=" + depreciation
				+ ", depreciationAndAmortization=" + depreciationAndAmortization + ", incomeBeforeTax="
				+ incomeBeforeTax + ", incomeTaxExpense=" + incomeTaxExpense + ", interestAndDebtExpense="
				+ interestAndDebtExpense + ", netIncomeFromContinuingOperations=" + netIncomeFromContinuingOperations
				+ ", comprehensiveIncomeNetOfTax=" + comprehensiveIncomeNetOfTax + ", ebit=" + ebit + ", ebitda="
				+ ebitda + ", netIncome=" + netIncome + "]";
	}

	public static class Builder{
		private UUID id;
		private String tickerSymbol;
		private ReportType type;
		private Date fiscalDateEnding;
		private CurrencySymbol reportedCurrency;
		private BigDecimal grossProfit;
		private BigDecimal totalRevenue;
		private BigDecimal costOfRevenue;
		private BigDecimal costOfGoodsAndServicesSold;
		private BigDecimal operatingIncome;
		private BigDecimal sellingGeneralAndAdministrative;
		private BigDecimal researchAndDevelopment;
		private BigDecimal operatingExpenses;
		private BigDecimal investmentIncomeNet;
		private BigDecimal netInterestIncome;
		private BigDecimal interestIncome;
		private BigDecimal interestExpense;
		private BigDecimal nonInterestIncome;
		private BigDecimal otherNonOperatingIncome;
		private BigDecimal depreciation;
		private BigDecimal depreciationAndAmortization;
		private BigDecimal incomeBeforeTax;
		private BigDecimal incomeTaxExpense;
		private BigDecimal interestAndDebtExpense;
		private BigDecimal netIncomeFromContinuingOperations;
		private BigDecimal comprehensiveIncomeNetOfTax;
		private BigDecimal ebit;
		private BigDecimal ebitda;
		private BigDecimal netIncome;
		
		public Builder(UUID id, String tickerSymbol, ReportType type) {
			setId(id);
			setTickerSymbol(tickerSymbol);
			setType(type);
		}
		
		private void setId(UUID id) {
			AssertionConcern.assertNotNull(id, "Id is necessary in order to build income statement report");
			this.id = id;
		}
		
		private void setTickerSymbol(String tickerSymbol) {
			AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is necessary in order to build income statement report");
			this.tickerSymbol = tickerSymbol;
		}
		
		private void setType(ReportType type) {
			AssertionConcern.assertNotNull(type, "Report type is necessary in order to build income statement report");
			this.type = type;
		}
		
		public Builder fiscalDateEnding(Date fiscalDateEnding) {
			AssertionConcern.assertNotNull(fiscalDateEnding, "Fiscal date ending is mandatory in order to create income statement report");
			this.fiscalDateEnding = fiscalDateEnding;
			return this;
		}
		
		public Builder reportedCurrency(CurrencySymbol reportedCurrency) {
			AssertionConcern.assertNotNull(reportedCurrency, "Currency is mandatory in order to create income statement report");
			this.reportedCurrency = reportedCurrency;
			return this;
		}
		
		public Builder grossProfit(BigDecimal grossProfit) {
			this.grossProfit = grossProfit;
			return this;
		}
		
		public Builder totalRevenue(BigDecimal totalRevenue) {
			this.totalRevenue = totalRevenue;
			return this;
		}
		
		public Builder costOfRevenue(BigDecimal costOfRevenue) {
			this.costOfRevenue = costOfRevenue;
			return this;
		}
		
		public Builder costOfGoodsAndServicesSold(BigDecimal costofGoodsAndServicesSold) {
			this.costOfGoodsAndServicesSold = costofGoodsAndServicesSold;
			return this;
		}
		
		public Builder operatingIncome(BigDecimal operatingIncome) {
			this.operatingIncome = operatingIncome;
			return this;
		}
		
		public Builder sellingGeneralAndAdministrative(BigDecimal sellingGeneralAndAdministrative) {
			this.sellingGeneralAndAdministrative = sellingGeneralAndAdministrative;
			return this;
		}
		
		public Builder researchAndDevelopment(BigDecimal researchAndDevelopment) {
			this.researchAndDevelopment = researchAndDevelopment;
			return this;
		}
		
		public Builder operatingExpenses(BigDecimal operatingExpenses) {
			this.operatingExpenses = operatingExpenses;
			return this;
		}
		
		public Builder investmentIncomeNet(BigDecimal investmentIncomeNet) {
			this.investmentIncomeNet = investmentIncomeNet;
			return this;
		}
		
		public Builder netInterestIncome(BigDecimal netInterestIncome) {
			this.netInterestIncome = netInterestIncome;
			return this;
		}
		
		public Builder interestIncome(BigDecimal interestIncome) {
			this.interestIncome = interestIncome;
			return this;
		}
		
		public Builder interestExpense(BigDecimal interestExpense) {
			this.interestExpense = interestExpense;
			return this;
		}
		
		public Builder nonInterestIncome(BigDecimal nonInterestIncome) {
			this.nonInterestIncome = nonInterestIncome;
			return this;
		}
		
		public Builder otherNonOperatingIncome(BigDecimal otherNonOperatingIncome) {
			this.otherNonOperatingIncome = otherNonOperatingIncome;
			return this;
		}
		
		public Builder depreciation(BigDecimal depreciation) {
			this.depreciation = depreciation;
			return this;
		}
		
		public Builder depreciationAndAmortization(BigDecimal depreciationAndAmortization) {
			this.depreciationAndAmortization = depreciationAndAmortization;
			return this;
		}
		
		public Builder incomeBeforeTax(BigDecimal incomeBeforeTax) {
			this.incomeBeforeTax = incomeBeforeTax;
			return this;
		}
		
		public Builder incomeTaxExpense(BigDecimal incomeTaxExpense) {
			this.incomeTaxExpense = incomeTaxExpense;
			return this;
		}
		
		public Builder interestAndDebtExpense(BigDecimal interestAndDebtExpense) {
			this.interestAndDebtExpense = interestAndDebtExpense;
			return this;
		}
		
		public Builder netIncomeFromContinuingOperations(BigDecimal netIncomeFromContinuingOperations) {
			this.netIncomeFromContinuingOperations = netIncomeFromContinuingOperations;
			return this;
		}
		
		public Builder comprehensiveIncomeNetOfTax(BigDecimal comprehensiveIncomeNetOfTax) {
			this.comprehensiveIncomeNetOfTax = comprehensiveIncomeNetOfTax;
			return this;
		}
		
		public Builder ebit(BigDecimal ebit) {
			this.ebit = ebit;
			return this;
		}
		
		public Builder ebitda(BigDecimal ebitda) {
			this.ebitda = ebitda;
			return this;
		}
		
		public Builder netIncome(BigDecimal netIncome) {
			this.netIncome = netIncome;
			return this;
		}
		
		public IncomeStatementReport build() throws ParseException {
			return new IncomeStatementReport(this);
		}
	}
}
