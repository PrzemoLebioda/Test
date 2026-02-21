package com.comida.sia.sharedkernel.messaging;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;


public enum Subject {
	LISTED_STOCKS_SYNC_ORDERED("stock.listed.sync.ordered"),												//STOCK_SYNC_EXCHANGE -> STOCK_LISTING_STATUS_QUEUE			("stock.*.sync.ordered"),
	DELISTED_STOCKS_SYNC_ORDERED("stock.delisted.sync.ordered"),											//STOCK_SYNC_EXCHANGE -> STOCK_LISTING_STATUS_QUEUE			("stock.*.sync.ordered")
	
	LISTED_STOCK_SYNCED("stock.listed.sync.completed"),														//STOCK_LISTING_STATUS_EXCHANGE -> STOCK_SYNC_QUEUE			("stock.*.sync.completed")
	DELISTED_STOCK_SYNCED("stock.delisted.sync.completed"),													//STOCK_LISTING_STATUS_EXCHANGE -> STOCK_SYNC_QUEUE			("stock.*.sync.completed")	
	
	STOCK_LISTED("stock.listed"),																			//STOCK_LISTING_STATUS_EXCHANGE -> COMPANY_SYNC_QUEUE, QUOTATIONS_SYNC_QUEUE		("stock.*")
	STOCK_DELISTED("stock.delisted"),																		//STOCK_LISTING_STATUS_EXCHANGE -> COMPANY_SYNC_QUEUE, QUOTATIONS_SYNC_QUEUE		("stock.*")
	
	COMPANY_KEY_METRICS_SYNC_ORDERED("company.fundamentals.keymetrics.sync.ordered"),						//COMPANY_SYNC_EXCHANGE -> KEY_METRICS_QUEUE				("company.fundamentals.keymetrics.sync.ordered")
	BALANCE_SHEET_ANNUAL_REPORT_SYNC_ORDERED("company.fundamentals.balancesheet.annual.sync.ordered"),		//COMPANY_SYNC_EXCHANGE -> BALANCE_SHEET_REPORTS_QUEUE		("company.fundamentals.balancesheet.*.sync.ordered")
	BALANCE_SHEET_QUARTER_REPORT_SYNC_ORDERED("company.fundamentals.balancesheet.quarter.sync.ordered"),	//COMPANY_SYNC_EXCHANGE -> BALANCE_SHEET_REPORTS_QUEUE		("company.fundamentals.balancesheet.*.sync.ordered")
	CASH_FLOW_ANNUAL_REPORT_SYNC_ORDERED("company.fundamentals.cashflow.annual.sync.ordered"),				//COMPANY_SYNC_EXCHANGE -> CASH_FLOW_REPORTS_QUEUE			("company.fundamentals.cashflow.*.sync.ordered")
	CASH_FLOW_QUARTER_REPORT_SYNC_ORDERED("company.fundamentals.cashflow.quarter.sync.ordered"),			//COMPANY_SYNC_EXCHANGE -> CASH_FLOW_REPORTS_QUEUE			("company.fundamentals.cashflow.*.sync.ordered")
	EARNINGS_ANNUAL_REPORT_SYNC_ORDERED("company.fundamentals.earnings.report.annual.sync.ordered"),		//COMPANY_SYNC_EXCHANGE -> EARNINGS_REPORTS_QUEUE			("company.fundamentals.earnings.report.*.sync.ordered")
	EARNINGS_QUARTER_REPORT_SYNC_ORDERED("company.fundamentals.earnings.report.quarter.sync.ordered"),		//COMPANY_SYNC_EXCHANGE -> EARNINGS_REPORTS_QUEUE			("company.fundamentals.earnings.report.*.sync.ordered")
	INCOME_ANNUAL_REPORT_SYNC_ORDERED("company.fundamentals.income.annual.sync.ordered"),					//COMPANY_SYNC_EXCHANGE -> INCOME_STATEMENT_REPORTS_QUEUE	("company.fundamentals.income.*.sync.ordered")
	INCOME_QUARTER_REPORT_SYNC_ORDERED("company.fundamentals.income.quarter.sync.ordered"),					//COMPANY_SYNC_EXCHANGE -> INCOME_STATEMENT_REPORTS_QUEUE	("company.fundamentals.income.*.sync.ordered")
	SHARES_OUTSTANDING_REPORT_SYNC_ORDERED("company.fundamentals.sharesoutstanding.sync.ordered"),			//COMPANY_SYNC_EXCHANGE -> SHARES_OUTSTANDING_REPORT_QUEUE	("company.fundamentals.sharesoutstanding.sync.ordered")
	EARNINGS_ESTIMATES_SYNC_ORDERED("company.fundamentals.earnings.estimates.sync.ordered"),				//COMPANY_SYNC_EXCHANGE -> EARNING_ESTIMATES_REPORT_QUEUE	("company.fundamentals.earnings.estimates.sync.ordered")
	CALENDAR_DIVIDEND_SYNC_ORDERED("company.fundamentals.calendar.dividend.sync.ordered"),					//COMPANY_SYNC_EXCHANGE -> CORPORATE_DIVIDEND_EVENT_QUEUE	("company.fundamentals.calendar.dividend.sync.ordered")
	CALENDAR_SPLITS_SYNC_ORDERED("company.fundamentals.calendar.splits.sync.ordered"),						//COMPANY_SYNC_EXCHANGE -> CORPORATE_SPLIT_EVENT_QUEUE 		("company.fundamentals.calendar.splits.sync.ordered")
	INSIDER_TRANSACTIONS_SYNC_ORDERED("company.intelligence.insiderstransactions.sync.ordered"),			//COMPANY_SYNC_EXCHANGE -> INSIDERS_TRANSACTIONS_QUEUE		("company.intelligence.insiderstransactions.sync.ordered")
	OPTIONS_SYNC_ORDERED("company.options.sync.ordered"),													//COMPANY_SYNC_EXCHANGE -> OPTIONS_QUEUE					("company.options.sync.ordered")
	
	COMPANY_KEY_METRICS_SYNCED("company.fundamentals.keymetrics.sync.completed"),							//KEY_METRICS_EXCHANGE -> COMPANY_SYNC_QUEUE				("company.fundamentals.keymetrics.sync.completed")
	BALANCE_SHEET_ANNUAL_REPORT_SYNCED("company.fundamentals.balancesheet.annual.sync.completed"),			//BALANCE_SHEET_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE		("company.fundamentals.balancesheet.*.sync.completed")
	BALANCE_SHEET_QUARTER_REPORT_SYNCED("company.fundamentals.balancesheet.quarter.sync.completed"),		//BALANCE_SHEET_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE		("company.fundamentals.balancesheet.*.sync.completed")
	CASH_FLOW_ANNUAL_REPORT_SYNCED("company.fundamentals.cashflow.annual.sync.completed"),					//CASH_FLOW_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE			("company.fundamentals.cashflow.*.sync.completed")
	CASH_FLOW_QUARTER_REPORT_SYNCED("company.fundamentals.cashflow.quarter.sync.completed"),				//CASH_FLOW_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE			("company.fundamentals.cashflow.*.sync.completed")
	EARNINGS_ANNUAL_REPORT_SYNCED("company.fundamentals.earnings.report.annual.sync.completed"),			//EARNINGS_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE			("company.fundamentals.earnings.report.*.sync.completed")
	EARNINGS_QUARTER_REPORT_SYNCED("company.fundamentals.earnings.report.quarter.sync.completed"),			//EARNINGS_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE			("company.fundamentals.earnings.report.*.sync.completed")
	INCOME_ANNUAL_REPORT_SYNCED("company.fundamentals.income.annual.sync.completed"),						//INCOME_STATEMENT_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE	("company.fundamentals.income.*.sync.completed")
	INCOME_QUARTER_REPORT_SYNCED("company.fundamentals.income.quarter.sync.completed"),						//INCOME_STATEMENT_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE	("company.fundamentals.income.*.sync.completed")
	SHARES_OUTSTANDING_REPORT_SYNCED("company.fundamentals.sharesoutstanding.sync.completed"),				//SHARES_OUTSTANDING_REPORT_EXCHANGE -> COMPANY_SYNC_QUEUE	("company.fundamentals.sharesoutstanding.sync.completed")
	EARNINGS_ESTIMATES_SYNCED("company.fundamentals.earnings.estimates.sync.completed"),					//EARNING_ESTIMATES_REPORT_EXCHANGE -> COMPANY_SYNC_QUEUE	("company.fundamentals.earnings.estimates.sync.completed")
	CALENDAR_DIVIDEND_SYNCED("company.fundamentals.calendar.dividend.sync.completed"),						//CORPORATE_DIVIDEND_EVENT_EXCHANGE -> COMPANY_SYNC_QUEUE	("company.fundamentals.calendar.dividend.sync.completed")
	CALENDAR_SPLITS_SYNCED("company.fundamentals.calendar.splits.sync.completed"),							//CORPORATE_SPLIT_EVENT_EXCHANGE -> COMPANY_SYNC_QUEUE		("company.fundamentals.calendar.splits.sync.completed")
	INSIDER_TRANSACTIONS_SYNCED("company.intelligence.insiderstransactions.sync.completed"),				//INSIDERS_TRANSACTIONS_EXCHANGE -> COMPANY_SYNC_QUEUE		("company.intelligence.insiderstransactions.sync.completed")
	OPTIONS_SYNCED("company.options.sync.completed"),														//OPTIONS_EXCHANGE -> COMPANY_SYNC_QUEUE					("company.options.sync.completed")
	
	CALENDAR_EARNINGS_SYNC_ORDERED("calendar.earnings.sync.ordered"),										//CALENDAR_SYNC_EXCHANGE -> EARNING_CALENDAR_QUEUE			("calendar.earnings.sync.ordered")
	CALENDAR_EARNINGS_SYNCED("calendar.earnings.sync.completed"),											//EARNING_CALENDAR_EXCHANGE -> CALENDAR_SYNC_QUEUE			("calendar.earnings.sync.completed")
	CALENDAR_EARNINGS_UPDATED("calendar.earnings.sync.updated"),											//EARNING_CALENDAR_EXCHANGE -> COMPANY_SYNC_QUEUE			("calendar.earnings.sync.updated")
	
	NEWS_FEED_SYNC_ORDERED("intelligence.news.sync.ordered"),												//NEWS_SYNC_EXCHANGE -> NEWS_QUEUE							("intelligence.news.sync.ordered")
	NEWS_FEED_SYNCED("intelligence.news.sync.completed"),													//NEWS_EXCHANGE -> NEWS_SYNC_QUEUE							("intelligence.news.sync.completed")
	
	GDP_ANNUAL_SYNC_ORDERED("indicators.gdp.annual.sync.ordered"),											//INDICATORS_SYNC_EXCHANGE -> GDP_QUEUE						("indicators.*.*.sync.ordered")
	GDP_QUARTER_SYNC_ORDERED("indicators.gdp.quarter.sync.ordered"),										//INDICATORS_SYNC_EXCHANGE -> GDP_QUEUE						("indicators.*.*.sync.ordered")
	GDP_ANNUAL_PER_CAPITA_SYNC_ORDERED("indicators.gdppercapita.annual.sync.ordered"),						//INDICATORS_SYNC_EXCHANGE -> GDP_QUEUE						("indicators.*.*.sync.ordered")
	GDP_QUARTER_PER_CAPITA_SYNC_ORDERED("indicators.gdppercapita.quarter.sync.ordered"),					//INDICATORS_SYNC_EXCHANGE -> GDP_QUEUE						("indicators.*.*.sync.ordered")	
	GDP_ANNUAL_SYNCED("indicators.gdp.annual.sync.completed"),												//GDP_EXCHANGE -> INDICATORS_SYNC_QUEUE						("indicators.*.*.sync.completed")
	GDP_QUARTER_SYNCED("indicators.gdp.quarter.sync.completed"),											//GDP_EXCHANGE -> INDICATORS_SYNC_QUEUE						("indicators.*.*.sync.completed")
	GDP_ANNUAL_PER_CAPITA_SYNCED("indicators.gdppercapita.annual.sync.completed"),							//GDP_EXCHANGE -> INDICATORS_SYNC_QUEUE						("indicators.*.*.sync.completed")
	GDP_QUARTER_PER_CAPITA_SYNCED("indicators.gdppercapita.quarter.sync.completed"),						//GDP_EXCHANGE -> INDICATORS_SYNC_QUEUE						("indicators.*.*.sync.completed")
	
	INTREST_RATE_MONTHLY_SYNC_ORDERED("indicators.intrest_rate.monthly.sync.ordered"),						//INDICATORS_SYNC_EXCHANGE -> INTREST_RATE_QUEUE			("indicators.*.*.sync.ordered")
	INTREST_RATE_WEEKLY_SYNC_ORDERED("indicators.intrest_rate.weekly.sync.ordered"),						//INDICATORS_SYNC_EXCHANGE -> INTREST_RATE_QUEUE			("indicators.*.*.sync.ordered")
	INTREST_RATE_DAILY_SYNC_ORDERED("indicators.intrest_rate.daily.sync.ordered"),							//INDICATORS_SYNC_EXCHANGE -> INTREST_RATE_QUEUE			("indicators.*.*.sync.ordered")
	INTREST_RATE_MONTHLY_SYNCED("indicators.intrest_rate.monthly.sync.completed"),							//INTREST_RATE_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.sync.completed")
	INTREST_RATE_WEEKLY_SYNCED("indicators.intrest_rate.weekly.sync.completed"),							//INTREST_RATE_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.sync.completed")
	INTREST_RATE_DAILY_SYNCED("indicators.intrest_rate.daily.sync.completed"),								//INTREST_RATE_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.sync.completed")
	
	TREASURY_YEALD_DAILY_03_MONTH_SYNC_ORDERED("indicators.treasury_yeald.daily.03month.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_DAILY_02_YEAR_SYNC_ORDERED("indicators.treasury_yeald.daily.02year.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_DAILY_05_YEAR_SYNC_ORDERED("indicators.treasury_yeald.daily.05year.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_DAILY_07_YEAR_SYNC_ORDERED("indicators.treasury_yeald.daily.07year.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_DAILY_10_YEAR_SYNC_ORDERED("indicators.treasury_yeald.daily.10year.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_DAILY_30_YEAR_SYNC_ORDERED("indicators.treasury_yeald.daily.30year.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	
	TREASURY_YEALD_MONTHLY_03_MONTH_SYNC_ORDERED("indicators.treasury_yeald.monthly.03month.sync.ordered"),	//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_MONTHLY_02_YEAR_SYNC_ORDERED("indicators.treasury_yeald.monthly.02year.sync.ordered"),	//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_MONTHLY_05_YEAR_SYNC_ORDERED("indicators.treasury_yeald.monthly.05year.sync.ordered"),	//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_MONTHLY_07_YEAR_SYNC_ORDERED("indicators.treasury_yeald.monthly.07year.sync.ordered"),	//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_MONTHLY_10_YEAR_SYNC_ORDERED("indicators.treasury_yeald.monthly.10year.sync.ordered"),	//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_MONTHLY_30_YEAR_SYNC_ORDERED("indicators.treasury_yeald.monthly.30year.sync.ordered"),	//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	
	TREASURY_YEALD_WEEKLY_03_MONTH_SYNC_ORDERED("indicators.treasury_yeald.weekly.03month.sync.ordered"),	//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_WEEKLY_02_YEAR_SYNC_ORDERED("indicators.treasury_yeald.weekly.02year.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_WEEKLY_05_YEAR_SYNC_ORDERED("indicators.treasury_yeald.weekly.05year.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_WEEKLY_07_YEAR_SYNC_ORDERED("indicators.treasury_yeald.weekly.07year.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_WEEKLY_10_YEAR_SYNC_ORDERED("indicators.treasury_yeald.weekly.10year.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	TREASURY_YEALD_WEEKLY_30_YEAR_SYNC_ORDERED("indicators.treasury_yeald.weekly.30year.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	
	TREASURY_YEALD_DAILY_03_MONTH_SYNCED("indicators.treasury_yeald.daily.03month.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_DAILY_02_YEAR_SYNCED("indicators.treasury_yeald.daily.02year.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_DAILY_05_YEAR_SYNCED("indicators.treasury_yeald.daily.05year.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_DAILY_07_YEAR_SYNCED("indicators.treasury_yeald.daily.06year.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_DAILY_10_YEAR_SYNCED("indicators.treasury_yeald.daily.10year.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_DAILY_30_YEAR_SYNCED("indicators.treasury_yeald.daily.30year.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	
	TREASURY_YEALD_MONTHLY_03_MONTH_SYNCED("indicators.treasury_yeald.monthly.03month.sync.completed"),		//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_MONTHLY_02_YEAR_SYNCED("indicators.treasury_yeald.monthly.02year.sync.completed"),		//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_MONTHLY_05_YEAR_SYNCED("indicators.treasury_yeald.monthly.05year.sync.completed"),		//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_MONTHLY_07_YEAR_SYNCED("indicators.treasury_yeald.monthly.07year.sync.completed"),		//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_MONTHLY_10_YEAR_SYNCED("indicators.treasury_yeald.monthly.10year.sync.completed"),		//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_MONTHLY_30_YEAR_SYNCED("indicators.treasury_yeald.monthly.30year.sync.completed"),		//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	
	TREASURY_YEALD_WEEKLY_03_MONTH_SYNCED("indicators.treasury_yeald.weekly.03month.sync.completed"),		//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_WEEKLY_02_YEAR_SYNCED("indicators.treasury_yeald.weekly.02year.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_WEEKLY_05_YEAR_SYNCED("indicators.treasury_yeald.weekly.05year.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_WEEKLY_07_YEAR_SYNCED("indicators.treasury_yeald.weekly.07year.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_WEEKLY_10_YEAR_SYNCED("indicators.treasury_yeald.weekly.10year.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	TREASURY_YEALD_WEEKLY_30_YEAR_SYNCED("indicators.treasury_yeald.weekly.30year.sync.completed"),			//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")

	CPI_MONTHLY_SYNC_ORDERED("indicators.cpi.monthly.sync.ordered"),										//INDICATORS_SYNC_EXCHANGE -> CPI_QUEUE						("indicators.*.*.sync.ordered")
	CPI_SEMIANNUAL_SYNC_ORDERED("indicators.cpi.semiannual.sync.ordered"),									//INDICATORS_SYNC_EXCHANGE -> CPI_QUEUE						("indicators.*.*.sync.ordered")
	
	CPI_MONTHLY_SYNCED("indicators.cpi.monthly.sync.completed"),											//CPI_EXCHANGE -> INDICATORS_SYNC_QUEUE						("indicators.*.*.sync.completed")
	CPI_SEMIANNUAL_SYNCED("indicators.cpi.semiannual.sync.completed"),										//CPI_EXCHANGE -> INDICATORS_SYNC_QUEUE						("indicators.*.*.sync.completed")

	INFLATION_ANNUAL_SYNC_ORDERED("indicators.inflation.annual.sync.ordered"),								//INDICATORS_SYNC_EXCHANGE -> INFLATION_QUEUE				("indicators.*.*.sync.ordered")
	INFLATION_ANNUAL_SYNCED("indicators.inflation.annual.sync.completed"),									//INFLATION_EXCHANGE -> INDICATORS_SYNC_QUEUE				("indicators.*.*.sync.completed")
	
	RETAIL_SALES_MONTHLY_SYNC_ORDERED("indicators.retail_sales.monthly.sync.ordered"),						//INDICATORS_SYNC_EXCHANGE -> RETAIL_SALES_QUEUE			("indicators.*.*.sync.ordered")
	RETAIL_SALES_MONTHLY_SYNCED("indicators.retail_sales.monthly.sync.completed"),							//RETAIL_SALES_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.sync.completed")
	
	DURABLE_GOODS_ORDER_MONTHLY_SYNC_ORDERED("indicators.durable_goods_order.monthly.sync.ordered"),		//INDICATORS_SYNC_EXCHANGE -> DURABLE_GOODS_ORDERS_QUEUE	("indicators.*.*.sync.ordered")
	DURABLE_GOODS_ORDER_MONTHLY_SYNCED("indicators.durable_goods_order.monthly.sync.completed"),			//DURABLE_GOODS_ORDERS_EXCHANGE -> INDICATORS_SYNC_QUEUE	("indicators.*.*.sync.completed") 
	
	UNEMPLOYMENT_RATE_MONTHLY_SYNC_ORDERED("indicators.unemployment_rate.monthly.sync.ordered"),			//INDICATORS_SYNC_EXCHANGE -> UNEMPLOYMENT_RATE_QUEUE		("indicators.*.*.sync.ordered")
	UNEMPLOYMENT_RATE_MONTHLY_SYNCED("indicators.unemployment_rate.monthly.sync.completed"),				//UNEMPLOYMENT_RATE_EXCHANGE -> INDICATORS_SYNC_QUEUE		("indicators.*.*.sync.completed") 
	
	NONFARM_PAYROLL_MONTHLY_SYNC_ORDERED("indicators.nonfarm_payroll.monthly.sync.ordered"),				//INDICATORS_SYNC_EXCHANGE -> NONFARM_PAYROLL_QUEUE			("indicators.*.*.sync.ordered")
	NONFARM_PAYROLL_RATE_MONTHLY_SYNCED("indicators.nonfarm_payroll.monthly.sync.completed"),				//NONFARM_PAYROLL_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.sync.completed") 
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------		
	EXCHANGE_QUOTE_MONTHLY_ADJUSTED_SYNC_ORDERED("quotations.adjusted.monthly.sync.ordered"),				//QUOTATIONS_SYNC_EXCHANGE	 -> QUOTATION_ADJUSTED_QUEUE	("quotations.adjusted.*.sync.ordered")
	EXCHANGE_QUOTE_MONTHLY_ADJUSTED_SYNCED("quotations.adjusted.monthly.sync.completed"),					//QUOTATION_ADJUSTED_EXCHANGE -> QUOTATIONS_SYNC_QUEUE		("quotations.adjusted.*.sync.completed") 
	
	EXCHANGE_QUOTE_WEEKLY_ADJUSTED_SYNC_ORDERED("quotations.adjusted.weekly.sync.ordered"),					//QUOTATIONS_SYNC_EXCHANGE -> QUOTATION_ADJUSTED_QUEUE		("quotations.adjusted.*.sync.ordered")
	EXCHANGE_QUOTE_WEEKLY_ADJUSTED_SYNCED("quotations.adjusted.weekly.sync.completed"),						//QUOTATION_ADJUSTED_EXCHANGE -> QUOTATIONS_SYNC_QUEUE		("quotations.adjusted.*.sync.completed")
	
	EXCHANGE_QUOTE_DAILY_ADJUSTED_SYNC_ORDERED("quotations.adjusted.daily.sync.ordered"),					//QUOTATIONS_SYNC_EXCHANGE -> QUOTATION_ADJUSTED_QUEUE		("quotations.adjusted.*.sync.ordered")
	EXCHANGE_QUOTE_DAILY_ADJUSTED_SYNCED("quotations.adjusted.daily.sync.completed"),						//QUOTATION_ADJUSTED_EXCHANGE -> QUOTATIONS_SYNC_QUEUE		("quotations.adjusted.*.sync.completed")
	
	EXCHANGE_QUOTE_INTERDAY_60_SYNC_ORDERED("quotations.interday.60.sync.ordered"),							//QUOTATIONS_SYNC_EXCHANGE -> QUOTATION_INTERDAY_QUEUE		("quotations.interday.*.sync.ordered")
	EXCHANGE_QUOTE_INTERDAY_60_SYNCED("quotations.interday60.sync.completed"),								//QUOTATION_INTERDAY_EXCHANGE -> QUOTATIONS_SYNC_QUEUE		("quotations.interday.*.sync.completed")
	
	EXCHANGE_QUOTE_INTERDAY_30_SYNC_ORDERED("quotations.interday.30.sync.ordered"),							//QUOTATIONS_SYNC_EXCHANGE -> QUOTATION_INTERDAY_QUEUE		("quotations.interday.*.sync.ordered")
	EXCHANGE_QUOTE_INTERDAY_30_SYNCED("quotations.interday30.sync.completed"),								//QUOTATION_INTERDAY_EXCHANGE -> QUOTATIONS_SYNC_QUEUE		("quotations.interday.*.sync.completed")
	
	EXCHANGE_QUOTE_INTERDAY_15_SYNC_ORDERED("quotations.interday.15.sync.ordered"),							//QUOTATIONS_SYNC_EXCHANGE -> QUOTATION_INTERDAY_QUEUE		("quotations.interday.*.sync.ordered")
	EXCHANGE_QUOTE_INTERDAY_15_SYNCED("quotations.interday15.sync.completed"),								//QUOTATION_INTERDAY_EXCHANGE -> QUOTATIONS_SYNC_QUEUE		("quotations.interday.*.sync.completed")
	
	EXCHANGE_QUOTE_INTERDAY_05_SYNC_ORDERED("quotations.interday.05.sync.ordered"),							//QUOTATIONS_SYNC_EXCHANGE -> QUOTATION_INTERDAY_QUEUE		("quotations.interday.*.sync.ordered")
	EXCHANGE_QUOTE_INTERDAY_05_SYNCED("quotations.interday05.sync.completed"),								//QUOTATION_INTERDAY_EXCHANGE -> QUOTATIONS_SYNC_QUEUE		("quotations.interday.*.sync.completed")
	
	EXCHANGE_QUOTE_INTERDAY_01_SYNC_ORDERED("quotations.interday.01.sync.ordered"),							//QUOTATIONS_SYNC_EXCHANGE -> QUOTATION_INTERDAY_QUEUE		("quotations.interday.*.sync.ordered")
	EXCHANGE_QUOTE_INTERDAY_01_SYNCED("quotations.interday01.sync.completed"),								//QUOTATION_INTERDAY_EXCHANGE -> QUOTATIONS_SYNC_QUEUE		("quotations.interday.*.sync.completed")
	
	FIRST_QUITATION_SYNCED("quotations.adjusted.first.all_intervals.sync.completed");						//QUOTATION_ADJUSTED_EXCHANGE -> QUOTATION_INTERDAY_QUEUE	("quotations.adjusted.first.all_intervals.sync.completed")
																											//QUOTATION_ADJUSTED_EXCHANGE -> QUOTATION_INTERDAY_QUEUE	("quotations.adjusted.first.all_intervals.sync.completed")
																											//QUOTATION_ADJUSTED_EXCHANGE -> QUOTATION_INTERDAY_QUEUE	("quotations.adjusted.first.all_intervals.sync.completed")
																											//QUOTATION_ADJUSTED_EXCHANGE -> QUOTATION_INTERDAY_QUEUE	("quotations.adjusted.first.all_intervals.sync.completed")
																											//QUOTATION_ADJUSTED_EXCHANGE -> QUOTATION_INTERDAY_QUEUE	("quotations.adjusted.first.all_intervals.sync.completed")
	@Getter private String name;
	private static final Map<String, Subject> ENUM_MAP;
	
	private Subject(String name) {
		this.name = name;
	}
	
	static {
        Map<String, Subject> map = new ConcurrentHashMap<String, Subject>();
        for (Subject instance : Subject.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Subject get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
}
