package com.comida.sia.sync.supervision.domain.model;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import lombok.Getter;

public enum SynchronizationScope {
	LISTING_SYNC("listing_sync", true),
	DELISTING_SYNC("delisting_sync", true),
	
	COMPANY_KEY_METRICS("company_key_metrics", true),
	BALANCE_SHEET_ANNUAL_REPORT("balance_sheet_annual_report", true),
	BALANCE_SHEET_QUARTER_REPORT("balance_sheet_quarter_report", true),
	CASH_FLOW_ANNUAL_REPORT("casch_flow_annual_report", true),
	CASH_FLOW_QUARTER_REPORT("cash_flow_quarter_report", true),
	EARNINGS_ANNUAL_REPORT("earnings_annual_report", true),
	EARNINGS_QUARTER_REPORT("earnings_quarter_report", true),
	INCOME_STATEMENT_ANNUAL_REPORT("income_statement_annual_report", true),
	INCOME_STATEMENT_QUARTER_REPORT("income_statement_quarter_report", true),
	SHARES_OUTSTANDING_REPORT("shares_outstanding_report", true),
	EARNING_ESTIMATES_REPORT("earning_estimates_report", true),
	DIVIDEND_EVENT("dividend_event", true),
	SPLIT_EVENT("split_event", true),
	EARNINGS_CALENDAR("earnings_calendar", true),
	INSIDERS_TRANSACTIONS("insiders_transactions", true),
	OPTIONS("options", true),
	//----
	
	LISTING_STATUS("listing_status", true),
	DELISTING_STATUS("delisting_status", true),
	
	NEWS_FEED("news_feed", true),
	
	GDP_ANNUAL("gdp_annual", true),
	GDP_QUARTER("gdp_quarter", true),
	GDP_PER_CAPITA_ANNUAL("gdp_per_capita_annual", true),
	GDP_PER_CAPITA_QUARTER("gdp_per_capita_quarter", true),
	
	TREASURY_YEALD_DAILY_03_MONTH("treasury_yeald_daily_03_month", true),
	TREASURY_YEALD_DAILY_02_YEAR("treasury_yeald_daily_02_year", true),
	TREASURY_YEALD_DAILY_05_YEAR("treasury_yeald_daily_05_year", true),
	TREASURY_YEALD_DAILY_07_YEAR("treasury_yeald_daily_06_year", true),
	TREASURY_YEALD_DAILY_10_YEAR("treasury_yeald_daily_10_year", true),
	TREASURY_YEALD_DAILY_30_YEAR("treasury_yeald_daily_30_year", true),
	
	TREASURY_YEALD_MONTHLY_03_MONTH("treasury_yeald_monthly_03_month", true),
	TREASURY_YEALD_MONTHLY_02_YEAR("treasury_yeald_monthly_02_year", true),
	TREASURY_YEALD_MONTHLY_05_YEAR("treasury_yeald_monthly_05_year", true),
	TREASURY_YEALD_MONTHLY_07_YEAR("treasury_yeald_monthly_07_year", true),
	TREASURY_YEALD_MONTHLY_10_YEAR("treasury_yeald_monthly_10_year", true),
	TREASURY_YEALD_MONTHLY_30_YEAR("treasury_yeald_monthly_30_year", true),
	
	TREASURY_YEALD_WEEKLY_03_MONTH("treasury_yeald_weekly_03_month", true),
	TREASURY_YEALD_WEEKLY_02_YEAR("treasury_yeald_weekly_02_year", true),
	TREASURY_YEALD_WEEKLY_05_YEAR("treasury_yeald_weekly_05_year", true),
	TREASURY_YEALD_WEEKLY_07_YEAR("treasury_yeald_weekly_07_year", true),
	TREASURY_YEALD_WEEKLY_10_YEAR("treasury_yeald_weekly_10_year", true),
	TREASURY_YEALD_WEEKLY_30_YEAR("treasury_yeald_weekly_30_year", true),
	
	INTREST_RATE_MONTHLY("intrest_rate_monthly", true),
	INTREST_RATE_WEEKLY("intrest_rate_weekly", true),
	INTREST_RATE_DAILY("intrest_rate_daily", true),
	
	CPI_MONTHLY("cpi monthly", true),
	CPI_SEMIANNUAL("cpi semiannual", true),
	
	INFLATION_ANNUAL("inflation annual", true),
	
	RETAIL_SALES_MONTHLY("retail sales monthly", true),
	
	DURABLE_GOODS_ORDERS_MONTHLY("durable goods orders monthly", true),
	
	UNEMPLOYMENT_RATE_MONTHLY("unemployment rate", true),
	
	NONFARM_PAYROLL_MONTHLY("nonfarm payroll monthly", true),
	
	EXCHANGE_QUOTE_MONTHLY("monthly exchange quote", true),
	EXCHANGE_QUOTE_WEEKLY("weekly exchange quote", true),
	EXCHANGE_QUOTE_DAILY("daily exchange quote", true),
	EXCHANGE_QUOTE_INTERDAY_60_MIN("interday 60 min exchange quote", true),
	EXCHANGE_QUOTE_INTERDAY_30_MIN("interday 30 min exchange quote", true),
	EXCHANGE_QUOTE_INTERDAY_15_MIN("interday 15 min exchange quote", true),
	EXCHANGE_QUOTE_INTERDAY_05_MIN("interday 05 min exchange quote", true),
	EXCHANGE_QUOTE_INTERDAY_01_MIN("interday 01 min exchange quote", true),
	FIRST_QUITATION("first quotation", true);
	
	@Getter private String name;
	private Boolean synchronizable;
	private static final Map<String, SynchronizationScope> ENUM_MAP;
	
	private SynchronizationScope(String name, Boolean synchronizable) {
		this.name = name;
		this.synchronizable = synchronizable;
	}
	
	static {
        Map<String, SynchronizationScope> map = new ConcurrentHashMap<String, SynchronizationScope>();
        for (SynchronizationScope instance : SynchronizationScope.values()) {
            map.put(instance.getName().toLowerCase(),instance);
        }
        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static SynchronizationScope get (String name) {
        return ENUM_MAP.get(name.toLowerCase());
    }
    
    public Boolean isSynchronizable() {
    	return synchronizable;
    }
}
