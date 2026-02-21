 package com.comida.sia.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;

@Configuration
@ComponentScan({ "com.comida.sia" })
public class RabbitmqConfig {
	//STOCK SYNC SUPERVISOR
	public static final String STOCK_SYNC_EXCHANGE = "stock-synchronization-exchange";
	public static final String STOCK_SYNC_QUEUE = "stock-synchronization-queue";
	//END OF STOCK SYNC SUPERVISOR
	
	//STOCK LISTING STATUS
	public static final String STOCK_LISTING_STATUS_EXCHANGE = "stock-listing-status-exchange";
	public static final String STOCK_LISTING_STATUS_QUEUE = "stock-listing-status-queue";
	//END OF STOCK LISTING STATUS
	
	//CALENDAR SYNC SUPERVISOR
	public static final String CALENDAR_SYNC_EXCHANGE = "calendar-synchronization-exchange";
	public static final String CALENDAR_SYNC_QUEUE = "calendar-synchronization-queue";
	//END OF CALENDAR SYNC SUPERVISOR
	
	//NEWS FEED SYNC SUPERVISOR
	public static final String NEWS_SYNC_EXCHANGE = "news-synchronization-exchange";
	public static final String NEWS_SYNC_QUEUE = "news-synchronization-queue";
	//EOF NEWS FEED SYNC SUPERVISOR
	
	//EARNING ESTIMATES
	public static final String EARNING_CALENDAR_EXCHANGE = "earning-calendar-exchange";
	public static final String EARNING_CALENDAR_QUEUE = "earning-calendar-queue";
	//EOF EARNING ESTIMATES
	
	//COMPANY SYNCH SPERVISOR
	public static final String COMPANY_SYNC_EXCHANGE = "company-synchronization-exchange";
	public static final String COMPANY_SYNC_QUEUE = "company-synchronization-queue";
	//END OF COMPANY SYNCH SPERVISOR
	
	//KEY METRICS
	public static final String KEY_METRICS_EXCHANGE = "key-metrics-exchange";
	public static final String KEY_METRICS_QUEUE = "key-metrics-queue";
	//END OF KEY METRICS
	
	//BALANCE SHEET
	public static final String BALANCE_SHEET_REPORTS_EXCHANGE = "balance-sheet-reports-exchange";
	public static final String BALANCE_SHEET_REPORTS_QUEUE = "balance-sheet-report-queue";
	// END OF BALANCE SHEET
	
	//CASH FLOW
	public static final String CASH_FLOW_REPORTS_EXCHANGE = "cash-flow-reports-exchange";
	public static final String CASH_FLOW_REPORTS_QUEUE = "cash-flow-report-queue";
	//END OF CASH FLOW
	
	//EARNINGS
	public static final String EARNINGS_REPORTS_EXCHANGE = "earnings-reports-exchange";
	public static final String EARNINGS_REPORTS_QUEUE = "earnings-report-queue";
	//END OF EARNINGS
	
	//INCOME STATEMENT
	public static final String INCOME_STATEMENT_REPORTS_EXCHANGE = "income-reports-exchange";
	public static final String INCOME_STATEMENT_REPORTS_QUEUE = "income-report-queue";
	//END OF INCOME STATEMENT
	
	//CORPORATE DIVIDEND EVENT
	public static final String CORPORATE_DIVIDEND_EVENT_EXCHANGE = "corporate-dividend-event-exchange";
	public static final String CORPORATE_DIVIDEND_EVENT_QUEUE = "corporate-dividend-event-queue";
	//END OF CORPORATE DIVIDEND EVENT
	
	//CORPORATE SPLIT EVENT
	public static final String CORPORATE_SPLIT_EVENT_EXCHANGE = "corporate-split-event-exchange";
	public static final String CORPORATE_SPLIT_EVENT_QUEUE = "corporate-split-event-queue";
	//END OF CORPORATE SPLIT EVENT
	
	//SHARES OUTSTANDING
	public static final String SHARES_OUTSTANDING_REPORT_EXCHANGE = "shares-outstanding-report-exchange";
	public static final String SHARES_OUTSTANDING_REPORT_QUEUE = "shares-outstanding-report-queue";
	//END OF SHARES OUTSTANDING
	
	//EARNING ESTIMATES
	public static final String EARNING_ESTIMATES_REPORT_EXCHANGE = "earning-estimates-report-exchange";
	public static final String EARNING_ESTIMATES_REPORT_QUEUE = "earning-estimates-report-queue";
	//EOF EARNING ESTIMATES
	
	//INTELLIGENCE - INSIDERS TRANSACTIONS
	public static final String INSIDERS_TRANSACTIONS_EXCHANGE = "insider-transactions-exchange";
	public static final String INSIDERS_TRANSACTIONS_QUEUE = "insider-transactions-queue";
	//EOF INTELLIGENCE - INSIDERS TRANSACTIONS
	
	//INTELLIGENCE - OPTIONS
	public static final String OPTIONS_EXCHANGE = "options-exchange";
	public static final String OPTIONS_QUEUE = "options-queue";
	//EOF OPTIONS
	
	//INTELLIGENCE - NEWS
	public static final String NEWS_EXCHANGE = "news-exchange";
	public static final String NEWS_QUEUE = "news-queue";
	//EOF INTELLIGENCE - NEWS
	
	//INDICATORS SYNC SUPERVISOR
	public static final String INDICATORS_SYNC_EXCHANGE = "indicators-synchronization-exchange";
	public static final String INDICATORS_SYNC_QUEUE = "indicators-synchronization-queue";
	//EOF INDICATORS SYNC SUPERVISOR
	
	//INDICATORS - GDP
	public static final String GDP_EXCHANGE = "gdp-exchange";
	public static final String GDP_QUEUE = "gdp-queue";
	//EOF INDICATORS - GDP
	
	//INDICATORS - TREASURY YEALD
	public static final String TREASURY_YEALD_EXCHANGE = "treasury-yeald-exchange";
	public static final String TREASURY_YEALD_QUEUE = "treasury-yeald-queue";
	//EOF INDICATORS - TREASURY YEALD
	
	//INDICATORS - INTREST RATE
	public static final String INTREST_RATE_EXCHANGE = "intrest-rate-exchange";
	public static final String INTREST_RATE_QUEUE = "intrest-rate-queue";
	//EOF INDICATORS - INTREST RATE
	
	//INDICATORS - CPI
	public static final String CPI_EXCHANGE = "cpi-exchange";
	public static final String CPI_QUEUE = "cpi-queue";
	//EOF INDICATORS - CPI
	
	//INDICATORS - INFLATION
	public static final String INFLATION_EXCHANGE = "inflation-exchange";
	public static final String INFLATION_QUEUE = "inflation-queue";
	//EOF INDICATORS - INFLATION
	
	//INDICATORS - RETAIL_SALES
	public static final String RETAIL_SALES_EXCHANGE = "retail-sales-exchange";
	public static final String RETAIL_SALES_QUEUE = "retail-sales-queue";
	//EOF INDICATORS - RETAIL_SALES
	
	//INDICATORS - DURABLE_GOODS_ORDERS
	public static final String DURABLE_GOODS_ORDERS_EXCHANGE = "durable-goods-orders-exchange";
	public static final String DURABLE_GOODS_ORDERS_QUEUE = "durable-goods-orders-queue";
	//EOF INDICATORS - DURABLE_GOODS_ORDERS
	
	//INDICATORS - UNENPLOYMENT_RATE
	public static final String UNEMPLOYMENT_RATE_EXCHANGE = "unemployment-rate-exchange";
	public static final String UNEMPLOYMENT_RATE_QUEUE = "unemployment-rate-queue";
	//EOF INDICATORS - UNENPLOYMENT_RATE
	
	//INDICATORS - UNENPLOYMENT_RATE
	public static final String NONFARM_PAYROLL_EXCHANGE = "nonfarm-payroll-exchange";
	public static final String NONFARM_PAYROLL_QUEUE = "nonfarm-payroll-queue";
	//EOF INDICATORS - UNENPLOYMENT_RATE
	
	//QUOTATIONS SYNCH SPERVISOR
	public static final String QUOTATIONS_SYNC_EXCHANGE = "quotations-synchronization-exchange";
	public static final String QUOTATIONS_SYNC_QUEUE = "quotations-synchronization-queue";
	//END OF QUOTATIONS SYNCH SPERVISOR
		
	//QUOTATIONS - ADJUSTED
	public static final String QUOTATION_ADJUSTED_EXCHANGE = "quotation-adjusted-exchange";
	public static final String QUOTATION_ADJUSTED_QUEUE = "quotation-adjusted-queue";
	//EOF QUOTATIONS - ADJUSTED
	
	//QUOTATIONS - INTERDAY
	public static final String QUOTATION_INTERDAY_EXCHANGE = "quotation-interday-exchange";
	public static final String QUOTATION_INTERDAY_QUEUE = "quotation-interday-queue";
	//EOF QUOTATIONS - INTERDAY
	
	@Bean
	TopicExchange stockSyncExchange() {
		return new TopicExchange(STOCK_SYNC_EXCHANGE);
	}
	
	@Bean
	Queue stockListingStatusQueue() {
		return new Queue(STOCK_LISTING_STATUS_QUEUE);
	}
	
	@Bean
	TopicExchange stockListingStatusExchange() {
		return new TopicExchange(STOCK_LISTING_STATUS_EXCHANGE);
	}
	
	@Bean
	Queue stockSyncQueue() {
		return new Queue(STOCK_SYNC_QUEUE);
	}
	
	@Bean
	TopicExchange calendarSyncExchange() {
		return new TopicExchange(CALENDAR_SYNC_EXCHANGE);
	}
	
	@Bean
	Queue newsSyncQueue() {
		return new Queue(NEWS_SYNC_QUEUE);
	}
	
	@Bean
	TopicExchange newsSyncExchange() {
		return new TopicExchange(NEWS_SYNC_EXCHANGE);
	}
	
	@Bean
	Queue earningCalendarQueue() {
		return new Queue(EARNING_CALENDAR_QUEUE);
	}
	
	@Bean
	TopicExchange earningCalendarExchange() {
		return new TopicExchange(EARNING_CALENDAR_EXCHANGE);
	}
	
	@Bean
	Queue calendarSyncQueue() {
		return new Queue(CALENDAR_SYNC_QUEUE);
	}
	
	@Bean
	TopicExchange companySyncExchange() {
		return new TopicExchange(COMPANY_SYNC_EXCHANGE);
	}
	
	@Bean
	Queue keyMetricsQueue() {
		return new Queue(KEY_METRICS_QUEUE, true);
	}
	
	@Bean
	Queue balanceSheetReportsQueue() {
		return new Queue(BALANCE_SHEET_REPORTS_QUEUE, true);
	}
	
	@Bean
	Queue cashFlowReportsQueue() {
		return new Queue(CASH_FLOW_REPORTS_QUEUE, true);
	}
	
	@Bean
	Queue earningsReportsQueue() {
		return new Queue(EARNINGS_REPORTS_QUEUE, true);
	}
	
	@Bean
	Queue incomeStatementReportsQueue() {
		return new Queue(INCOME_STATEMENT_REPORTS_QUEUE, true);
	}
	
	@Bean
	Queue corporateDividendEventQueue() {
		return new Queue(CORPORATE_DIVIDEND_EVENT_QUEUE, true);
	}
	
	@Bean
	Queue sharesOutstandingQueue() {
		return new Queue(SHARES_OUTSTANDING_REPORT_QUEUE, true);
	}
	
	@Bean
	Queue earningEstimatesQueue() {
		return new Queue(EARNING_ESTIMATES_REPORT_QUEUE, true);
	}
	
	@Bean
	Queue corporateSplitEventQueue() {
		return new Queue(CORPORATE_SPLIT_EVENT_QUEUE, true);
	}
	
	@Bean
	Queue insidersTransactionsQueue() {
		return new Queue(INSIDERS_TRANSACTIONS_QUEUE, true);
	}
	
	@Bean
	Queue optionsQueue() {
		return new Queue(OPTIONS_QUEUE, true);
	}
	
	@Bean
	Queue companySyncQueue() {
		return new Queue(COMPANY_SYNC_QUEUE, true);
	}
	
	@Bean
	Queue newsQueue() {
		return new Queue(NEWS_QUEUE, true);
	}
	
	@Bean
	Queue indicatorsSyncQueue() {
		return new Queue(INDICATORS_SYNC_QUEUE, true);
	}
	
	@Bean
	Queue gdpQueue() {
		return new Queue(GDP_QUEUE, true);
	}
	
	@Bean
	Queue treasuryYealdQueue() {
		return new Queue(TREASURY_YEALD_QUEUE, true);
	}
	
	@Bean
	Queue intrestRateQueue() {
		return new Queue(INTREST_RATE_QUEUE, true);
	}
	
	@Bean
	Queue cpiQueue() {
		return new Queue(CPI_QUEUE, true);
	}
	
	@Bean
	Queue inflationQueue() {
		return new Queue(INFLATION_QUEUE, true);
	}
	
	@Bean
	Queue retailSalesQueue() {
		return new Queue(RETAIL_SALES_QUEUE, true);
	}
	
	@Bean
	Queue durableGoodsOrdersQueue() {
		return new Queue(DURABLE_GOODS_ORDERS_QUEUE, true);
	}
	
	@Bean
	Queue unemploymentRateQueue() {
		return new Queue(UNEMPLOYMENT_RATE_QUEUE, true);
	}
	
	@Bean
	Queue nonfarmPayrollQueue() {
		return new Queue(NONFARM_PAYROLL_QUEUE, true);
	}
	
	@Bean
	TopicExchange keyMetricsExchange() {
		return new TopicExchange(KEY_METRICS_EXCHANGE);
	}
	
	@Bean
	TopicExchange balanceSheetReportExchange() {
		return new TopicExchange(BALANCE_SHEET_REPORTS_EXCHANGE);
	}
	
	@Bean
	TopicExchange cashFlowReportExchange() {
		return new TopicExchange(CASH_FLOW_REPORTS_EXCHANGE);
	}
	
	@Bean
	TopicExchange earningsReportExchange() {
		return new TopicExchange(EARNINGS_REPORTS_EXCHANGE);
	}
	
	@Bean
	TopicExchange incomeStatementReportExchange() {
		return new TopicExchange(INCOME_STATEMENT_REPORTS_EXCHANGE);
	}
	
	@Bean
	TopicExchange corporateDividendEventExchange() {
		return new TopicExchange(CORPORATE_DIVIDEND_EVENT_EXCHANGE);
	}	
	
	@Bean
	TopicExchange corporateSplitEventExchange() {
		return new TopicExchange(CORPORATE_SPLIT_EVENT_EXCHANGE);
	}
	
	@Bean
	TopicExchange sharesOutstandingExchange() {
		return new TopicExchange(SHARES_OUTSTANDING_REPORT_EXCHANGE);
	}
	
	@Bean
	TopicExchange earningEstimatesExchange() {
		return new TopicExchange(EARNING_ESTIMATES_REPORT_EXCHANGE);
	}
	
	@Bean
	TopicExchange insidersTransactionsExchange() {
		return new TopicExchange(INSIDERS_TRANSACTIONS_EXCHANGE);
	}
	
	@Bean
	TopicExchange optionsExchange() {
		return new TopicExchange(OPTIONS_EXCHANGE);
	}
	
	@Bean
	TopicExchange newsExchange() {
		return new TopicExchange(NEWS_EXCHANGE);
	}
	
	@Bean
	TopicExchange indicatorsSyncExchange() {
		return new TopicExchange(INDICATORS_SYNC_EXCHANGE);
	}
	
	@Bean
	TopicExchange gdpExchange() {
		return new TopicExchange(GDP_EXCHANGE);
	}
	
	@Bean
	TopicExchange treasuryYealdExchange() {
		return new TopicExchange(TREASURY_YEALD_EXCHANGE);
	}
	
	@Bean
	TopicExchange intrestRateExchange() {
		return new TopicExchange(INTREST_RATE_EXCHANGE);
	}
	
	@Bean
	TopicExchange cpiExchange() {
		return new TopicExchange(CPI_EXCHANGE);
	}
	
	@Bean
	TopicExchange inflationExchange() {
		return new TopicExchange(INFLATION_EXCHANGE);
	}
	
	@Bean
	TopicExchange retailSalesExchange() {
		return new TopicExchange(RETAIL_SALES_EXCHANGE);
	}
	
	@Bean
	TopicExchange durableGoodsOrdersExchange() {
		return new TopicExchange(DURABLE_GOODS_ORDERS_EXCHANGE);
	}
	
	@Bean
	TopicExchange unemploymentRateExchange() {
		return new TopicExchange(UNEMPLOYMENT_RATE_EXCHANGE);
	}
	
	@Bean
	TopicExchange nonfarmPayrollExchange() {
		return new TopicExchange(NONFARM_PAYROLL_EXCHANGE);
	}
	
	@Bean
	TopicExchange quotationSyncExchange() {
		return new TopicExchange(QUOTATIONS_SYNC_EXCHANGE);
	}
	
	@Bean
	Queue quotationSyncQueue() {
		return new Queue(QUOTATIONS_SYNC_QUEUE);
	}
	
	@Bean
	TopicExchange quotationAdjustedExchange() {
		return new TopicExchange(QUOTATION_ADJUSTED_EXCHANGE);
	}
	
	@Bean
	Queue quotationAdjustedQueue() {
		return new Queue(QUOTATION_ADJUSTED_QUEUE);
	}
	
	@Bean
	TopicExchange quotationInterdayExchange() {
		return new TopicExchange(QUOTATION_INTERDAY_EXCHANGE);
	}
	
	@Bean
	Queue quotationInterdayQueue() {
		return new Queue(QUOTATION_INTERDAY_QUEUE);
	}
	
	//STOCK_SYNC_EXCHANGE -> STOCK_LISTING_STATUS_QUEUE			("stock.*.sync.ordered")
	@Bean
	Binding stockSyncToStockListingStatusBinding(@Qualifier("stockListingStatusQueue") Queue queue, @Qualifier("stockSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("stock.*.sync.ordered");
	}

	//STOCK_LISTING_STATUS_EXCHANGE -> STOCK_SYNC_QUEUE			("stock.*.sync.completed")
	@Bean
	Binding stockListingStatusToStockSyncBinding(@Qualifier("stockSyncQueue") Queue queue, @Qualifier("stockListingStatusExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("stock.*.sync.completed");
	}

	//STOCK_LISTING_STATUS_EXCHANGE -> COMPANY_SYNC_QUEUE		("stock.*")
	@Bean
	Binding stockListingStatusToCompanySyncBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("stockListingStatusExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("stock.*");
	}
	
	//STOCK_LISTING_STATUS_EXCHANGE -> QUOTATIONS_SYNC_QUEUE	("stock.*")
	@Bean
	Binding stockListingStatusToQuotationsSyncBinding(@Qualifier("quotationSyncQueue") Queue queue, @Qualifier("stockListingStatusExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("stock.*");
	}

	//COMPANY_SYNC_EXCHANGE -> KEY_METRICS_QUEUE				("company.fundamentals.keymetrics.sync.ordered")
	@Bean
	Binding companySyncToKeyMetricsBinding(@Qualifier("keyMetricsQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.keymetrics.sync.ordered");
	}
	//COMPANY_SYNC_EXCHANGE -> BALANCE_SHEET_REPORTS_QUEUE		("company.fundamentals.balancesheet.*.sync.ordered")
	@Bean
	Binding companySyncToBalanceSheetBinding(@Qualifier("balanceSheetReportsQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.balancesheet.*.sync.ordered");
	}
	//COMPANY_SYNC_EXCHANGE -> CASH_FLOW_REPORTS_QUEUE			("company.fundamentals.cashflow.*.sync.ordered")
	@Bean
	Binding companySyncToCashflowBinding(@Qualifier("cashFlowReportsQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.cashflow.*.sync.ordered");
	}
	//COMPANY_SYNC_EXCHANGE -> EARNINGS_REPORTS_QUEUE			("company.fundamentals.earnings.report.*.sync.ordered")
	@Bean
	Binding companySyncToEarningsBinding(@Qualifier("earningsReportsQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.earnings.report.*.sync.ordered");
	}
	//COMPANY_SYNC_EXCHANGE -> INCOME_STATEMENT_REPORTS_QUEUE	("company.fundamentals.income.*.sync.ordered")
	@Bean
	Binding companySyncToIncomeBinding(@Qualifier("incomeStatementReportsQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.income.*.sync.ordered");
	}
	//COMPANY_SYNC_EXCHANGE -> SHARES_OUTSTANDING_REPORT_QUEUE	("company.fundamentals.sharesoutstanding.sync.ordered")
	@Bean
	Binding companySyncToSharesOutstandingBinding(@Qualifier("sharesOutstandingQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.sharesoutstanding.sync.ordered");
	}
	//COMPANY_SYNC_EXCHANGE -> EARNING_ESTIMATES_REPORT_QUEUE	("company.fundamentals.earnings.estimates.sync.ordered")
	@Bean
	Binding companySyncToEaeningsEstimatesBinding(@Qualifier("earningEstimatesQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.earnings.estimates.sync.ordered");
	}
	//COMPANY_SYNC_EXCHANGE -> CORPORATE_DIVIDEND_EVENT_QUEUE	("company.fundamentals.calendar.dividend.sync.ordered")
	@Bean
	Binding companySyncToCorporateDividentEventBinding(@Qualifier("corporateDividendEventQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.calendar.dividend.sync.ordered");
	}
	//COMPANY_SYNC_EXCHANGE -> CORPORATE_SPLIT_EVENT_QUEUE 		("company.fundamentals.calendar.splits.sync.ordered")
	@Bean
	Binding companySyncToCorporateSplitEventBinding(@Qualifier("corporateSplitEventQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.calendar.splits.sync.ordered");
	}
	
	//COMPANY_SYNC_EXCHANGE -> INSIDERS_TRANSACTIONS_QUEUE		("company.intelligence.insiderstransactions.sync.ordered")
	@Bean
	Binding companySyncToInsidersTransactionsBinding(@Qualifier("insidersTransactionsQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.intelligence.insiderstransactions.sync.ordered");
	}
	
	//COMPANY_SYNC_EXCHANGE -> OPTIONS_QUEUE					("company.options.sync.ordered")
	@Bean
	Binding companySyncToOptionsBinding(@Qualifier("optionsQueue") Queue queue, @Qualifier("companySyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.options.sync.ordered");
	}
	
	//KEY_METRICS_EXCHANGE -> COMPANY_SYNC_QUEUE				("company.fundamentals.keymetrics.sync.completed")
	@Bean
	Binding KeyMetricsToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("keyMetricsExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.keymetrics.sync.completed");
	}
	//BALANCE_SHEET_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE		("company.fundamentals.balancesheet.*.sync.completed")
	@Bean
	Binding BalanceSheetToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("balanceSheetReportExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.balancesheet.*.sync.completed");
	}
	//CASH_FLOW_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE			("company.fundamentals.cashflow.*.sync.completed")
	@Bean
	Binding CashflowToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("cashFlowReportExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.cashflow.*.sync.completed");
	}
	//EARNINGS_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE			("company.fundamentals.earnings.report.*.sync.completed")
	@Bean
	Binding EarningsToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("earningsReportExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.earnings.report.*.sync.completed");
	}
	//INCOME_STATEMENT_REPORTS_EXCHANGE -> COMPANY_SYNC_QUEUE	("company.fundamentals.income.*.sync.completed")
	@Bean
	Binding IncomeToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("incomeStatementReportExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.income.*.sync.completed");
	}
	//SHARES_OUTSTANDING_REPORT_EXCHANGE -> COMPANY_SYNC_QUEUE	("company.fundamentals.sharesoutstanding.sync.completed")
	@Bean
	Binding SharesOutstandingToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("sharesOutstandingExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.sharesoutstanding.sync.completed");
	}
	//EARNING_ESTIMATES_REPORT_EXCHANGE -> COMPANY_SYNC_QUEUE	("company.fundamentals.earnings.estimates.sync.completed")
	@Bean
	Binding EarningEstimatesToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("earningEstimatesExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.earnings.estimates.sync.completed");
	}
	//CORPORATE_DIVIDEND_EVENT_EXCHANGE -> COMPANY_SYNC_QUEUE	("company.fundamentals.calendar.dividend.sync.completed")
	@Bean
	Binding CorporateDividendToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("corporateDividendEventExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.calendar.dividend.sync.completed");
	}
	//CORPORATE_SPLIT_EVENT_EXCHANGE -> COMPANY_SYNC_QUEUE		("company.fundamentals.calendar.splits.sync.completed")
	@Bean
	Binding CorporateSplitToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("corporateSplitEventExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.fundamentals.calendar.splits.sync.completed");
	}
	
	//INSIDERS_TRANSACTIONS_EXCHANGE -> COMPANY_SYNC_QUEUE		("company.intelligence.insiderstransactions.sync.completed")
	@Bean
	Binding InsidersTransactionsToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("insidersTransactionsExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.intelligence.insiderstransactions.sync.completed");
	}
	
	//OPTIONS_EXCHANGE -> COMPANY_SYNC_QUEUE					("company.options.sync.completed")
	@Bean
	Binding OptionsToCompanySynctBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("optionsExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("company.options.sync.completed");
	}

	//CALENDAR_SYNC_EXCHANGE -> EARNING_CALENDAR_QUEUE			("calendar.earnings.sync.ordered")
	@Bean
	Binding calendarSyncToEarningCalendarBinding(@Qualifier("earningCalendarQueue") Queue queue, @Qualifier("calendarSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("calendar.earnings.sync.ordered");
	}
	//EARNING_CALENDAR_EXCHANGE -> CALENDAR_SYNC_QUEUE			("calendar.earnings.sync.completed")
	@Bean
	Binding EarningCalendarToCalendarSyncBinding(@Qualifier("calendarSyncQueue") Queue queue, @Qualifier("earningCalendarExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("calendar.earnings.sync.completed");
	}
	//EARNING_CALENDAR_EXCHANGE -> COMPANY_SYNC_QUEUE			("calendar.earnings.sync.updated")
	@Bean
	Binding EarningCalendarToCompanySyncBinding(@Qualifier("companySyncQueue") Queue queue, @Qualifier("earningCalendarExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("calendar.earnings.sync.updated");
	}
	
	//NEWS_SYNC_EXCHANGE -> NEWS_QUEUE							("intelligence.news.sync.ordered")
	@Bean
	Binding newsSyncToNewsBinding(@Qualifier("newsQueue") Queue queue, @Qualifier("newsSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("intelligence.news.sync.ordered");
	}
	//NEWS_EXCHANGE -> NEWS_SYNC_QUEUE							("intelligence.news.sync.completed")
	@Bean
	Binding newsToNewsSyncBinding(@Qualifier("newsSyncQueue") Queue queue, @Qualifier("newsExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("intelligence.news.sync.completed");
	}
	
	//INDICATORS_SYNC_EXCHANGE -> GDP_QUEUE						("indicators.*.*.sync.ordered")
	@Bean
	Binding indicatorsSyncToGdpBinding(@Qualifier("gdpQueue") Queue queue, @Qualifier("indicatorsSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.ordered");
	}

	//GDP_EXCHANGE -> INDICATORS_SYNC_QUEUE						("indicators.*.*.sync.completed")
	@Bean
	Binding gdpToIndicatorsSyncBinding(@Qualifier("indicatorsSyncQueue") Queue queue, @Qualifier("gdpExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.completed");
	}
	
	//INDICATORS_SYNC_EXCHANGE -> TREASURY_YEALD_QUEUE			("indicators.*.*.*.sync.ordered")
	@Bean
	Binding indicatorsSyncToTreasuryYealdBinding(@Qualifier("treasuryYealdQueue") Queue queue, @Qualifier("indicatorsSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.*.sync.ordered");
	}

	//TREASURY_YEALD_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.*.sync.completed")
	@Bean
	Binding tresuryYealdToIndicatorsSyncBinding(@Qualifier("indicatorsSyncQueue") Queue queue, @Qualifier("treasuryYealdExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.*.sync.completed");
	}
	
	//INDICATORS_SYNC_EXCHANGE -> INTREST_RATE_QUEUE			("indicators.*.*.sync.ordered")
	@Bean
	Binding indicatorsSyncToIntrestRateBinding(@Qualifier("intrestRateQueue") Queue queue, @Qualifier("indicatorsSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.ordered");
	}

	//INTREST_RATE_EXCHANGE -> INDICATORS_SYNC_QUEUE			("indicators.*.*.sync.completed")
	@Bean
	Binding intrestRateToIndicatorsSyncBinding(@Qualifier("indicatorsSyncQueue") Queue queue, @Qualifier("intrestRateExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.completed");
	}
	
	//INDICATORS_SYNC_EXCHANGE -> CPI_QUEUE						("indicators.*.*.sync.ordered")
	@Bean
	Binding indicatorsSyncToCpiBinding(@Qualifier("cpiQueue") Queue queue, @Qualifier("indicatorsSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.ordered");
	}

	//CPI_EXCHANGE -> INDICATORS_SYNC_QUEUE						("indicators.*.*.sync.completed")
	@Bean
	Binding cpiToIndicatorsSyncBinding(@Qualifier("indicatorsSyncQueue") Queue queue, @Qualifier("cpiExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.completed");
	}
	
	//INDICATORS_SYNC_EXCHANGE -> INFLATION_QUEUE				("indicators.*.*.sync.ordered")
	@Bean
	Binding indicatorsSyncToInflationBinding(@Qualifier("inflationQueue") Queue queue, @Qualifier("indicatorsSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.ordered");
	}

	//INFLATION_EXCHANGE -> INDICATORS_SYNC_QUEUE				("indicators.*.*.sync.completed")
	@Bean
	Binding inflationToIndicatorsSyncBinding(@Qualifier("indicatorsSyncQueue") Queue queue, @Qualifier("inflationExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.completed");
	}
	
	//INDICATORS_SYNC_EXCHANGE -> RETAIL_SALSE_QUEUE				("indicators.*.*.sync.ordered")
	@Bean
	Binding indicatorsSyncToRetailSalesBinding(@Qualifier("retailSalesQueue") Queue queue, @Qualifier("indicatorsSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.ordered");
	}

	//RETAIL_SALES_EXCHANGE -> INDICATORS_SYNC_QUEUE				("indicators.*.*.sync.completed")
	@Bean
	Binding retailSalesToIndicatorsSyncBinding(@Qualifier("indicatorsSyncQueue") Queue queue, @Qualifier("retailSalesExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.completed");
	}
	
	//INDICATORS_SYNC_EXCHANGE -> DURABLE_GOODS_ORDERS_QUEUE				("indicators.*.*.sync.ordered")
	@Bean
	Binding indicatorsSyncToDurableGoodsOrdersBinding(@Qualifier("durableGoodsOrdersQueue") Queue queue, @Qualifier("indicatorsSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.ordered");
	}

	//DURABLE_GOODS_ORDERS_EXCHANGE -> INDICATORS_SYNC_QUEUE				("indicators.*.*.sync.completed")
	@Bean
	Binding durableGoodsOrdersToIndicatorsSyncBinding(@Qualifier("indicatorsSyncQueue") Queue queue, @Qualifier("durableGoodsOrdersExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.completed");
	}
	
	//INDICATORS_SYNC_EXCHANGE -> UNEMPLOYMENT_RATE_QUEUE					("indicators.*.*.sync.ordered")
	@Bean
	Binding indicatorsSyncToUnemploymentRateBinding(@Qualifier("unemploymentRateQueue") Queue queue, @Qualifier("indicatorsSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.ordered");
	}

	//UNEMPLOYMENT_RATE_EXCHANGE -> INDICATORS_SYNC_QUEUE					("indicators.*.*.sync.completed")
	@Bean
	Binding unemploymentRateToIndicatorsSyncBinding(@Qualifier("indicatorsSyncQueue") Queue queue, @Qualifier("unemploymentRateExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.completed");
	}
	
	//INDICATORS_SYNC_EXCHANGE -> NONFARM_PAYROLL_QUEUE						("indicators.*.*.sync.ordered")
	@Bean
	Binding indicatorsSyncToNonfarmPayrollBinding(@Qualifier("nonfarmPayrollQueue") Queue queue, @Qualifier("indicatorsSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.ordered");
	}

	//NONFARM_PAYROLL_EXCHANGE -> INDICATORS_SYNC_QUEUE						("indicators.*.*.sync.completed")
	@Bean
	Binding nonfarmPayrollToIndicatorsSyncBinding(@Qualifier("indicatorsSyncQueue") Queue queue, @Qualifier("nonfarmPayrollExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("indicators.*.*.sync.completed");
	}
	
	//QUOTATIONS_SYNC_EXCHANGE -> QUOTATION_ADJUSTED_QUEUE						("quotations.adjusted.*.sync.ordered")
	@Bean
	Binding quotationSyncToQuotationAdjustedBinding(@Qualifier("quotationAdjustedQueue") Queue queue, @Qualifier("quotationSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("quotations.adjusted.*.sync.ordered");
	}
	
	//QUOTATION_ADJUSTED_EXCHANGE -> QUOTATIONS_SYNC_QUEUE			("quotations.adjusted.*.sync.completed")
	@Bean
	Binding quotationAdjustedToQuotationSyncBinding(@Qualifier("quotationSyncQueue") Queue queue, @Qualifier("quotationAdjustedExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("quotations.adjusted.*.sync.completed");
	}
	
	//QUOTATION_ADJUSTED_EXCHANGE -> QUOTATIONS_SYNC_QUEUE			("quotations.adjusted.first.all_intervals.sync.completed")
	@Bean
	Binding quotationAdjustedToQuotationSyncFirstQuotationBinding(@Qualifier("quotationSyncQueue") Queue queue, @Qualifier("quotationAdjustedExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("quotations.adjusted.first.all_intervals.sync.completed");
	}
	
	//QUOTATIONS_SYNC_EXCHANGE -> QUOTATION_INTERDAY_QUEUE		("quotations.interday.*.sync.ordered")
	@Bean
	Binding quotationSyncToQuotationInterdayBinding(@Qualifier("quotationInterdayQueue") Queue queue, @Qualifier("quotationSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("quotations.interday.*.sync.ordered");
	}
	
	//QUOTATION_INTERDAY_EXCHANGE -> QUOTATIONS_SYNC_QUEUE		("quotations.interday.*.sync.completed")
	@Bean
	Binding quotationInterdayToQuotationSyncBinding(@Qualifier("quotationInterdayQueue") Queue queue, @Qualifier("quotationSyncExchange") TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("quotations.interday.*.sync.completed");
	}
}
