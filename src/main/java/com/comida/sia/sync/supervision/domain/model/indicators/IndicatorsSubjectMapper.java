package com.comida.sia.sync.supervision.domain.model.indicators;

import com.comida.sia.sharedkernel.messaging.Subject;
import com.comida.sia.sync.supervision.domain.model.SubjectMapper;
import com.comida.sia.sync.supervision.domain.model.SynchronizationScope;

public class IndicatorsSubjectMapper implements SubjectMapper {

	@Override
	public SynchronizationScope getSyncStateTypeFrom(Subject subject) {
		switch (subject){
			case GDP_ANNUAL_SYNC_ORDERED:
				return SynchronizationScope.GDP_ANNUAL;
			case GDP_ANNUAL_SYNCED:
				return SynchronizationScope.GDP_ANNUAL;
			case GDP_QUARTER_SYNC_ORDERED:
				return SynchronizationScope.GDP_QUARTER;
			case GDP_QUARTER_SYNCED:
				return SynchronizationScope.GDP_QUARTER;
			case GDP_ANNUAL_PER_CAPITA_SYNC_ORDERED:
				return SynchronizationScope.GDP_PER_CAPITA_ANNUAL;
			case GDP_QUARTER_PER_CAPITA_SYNC_ORDERED:
				return SynchronizationScope.GDP_PER_CAPITA_QUARTER;
			case GDP_ANNUAL_PER_CAPITA_SYNCED:
				return SynchronizationScope.GDP_PER_CAPITA_ANNUAL;
			case GDP_QUARTER_PER_CAPITA_SYNCED:
				return SynchronizationScope.GDP_PER_CAPITA_QUARTER;
				
			case TREASURY_YEALD_DAILY_03_MONTH_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_03_MONTH;
			case TREASURY_YEALD_DAILY_02_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_02_YEAR;
			case TREASURY_YEALD_DAILY_05_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_05_YEAR;
			case TREASURY_YEALD_DAILY_07_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_07_YEAR;
			case TREASURY_YEALD_DAILY_10_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_10_YEAR;
			case TREASURY_YEALD_DAILY_30_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_30_YEAR;
				
			case TREASURY_YEALD_MONTHLY_03_MONTH_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_03_MONTH;
			case TREASURY_YEALD_MONTHLY_02_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_02_YEAR;
			case TREASURY_YEALD_MONTHLY_05_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_05_YEAR;
			case TREASURY_YEALD_MONTHLY_07_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_07_YEAR;
			case TREASURY_YEALD_MONTHLY_10_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_10_YEAR;
			case TREASURY_YEALD_MONTHLY_30_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_30_YEAR;
				
			case TREASURY_YEALD_WEEKLY_03_MONTH_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_03_MONTH;
			case TREASURY_YEALD_WEEKLY_02_YEAR_SYNC_ORDERED: 
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_02_YEAR;
			case TREASURY_YEALD_WEEKLY_05_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_05_YEAR;
			case TREASURY_YEALD_WEEKLY_07_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_07_YEAR;
			case TREASURY_YEALD_WEEKLY_10_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_10_YEAR;
			case TREASURY_YEALD_WEEKLY_30_YEAR_SYNC_ORDERED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_30_YEAR;
				
			case TREASURY_YEALD_DAILY_03_MONTH_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_03_MONTH;
			case TREASURY_YEALD_DAILY_02_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_02_YEAR;
			case TREASURY_YEALD_DAILY_05_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_05_YEAR;
			case TREASURY_YEALD_DAILY_07_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_07_YEAR;
			case TREASURY_YEALD_DAILY_10_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_10_YEAR;
			case TREASURY_YEALD_DAILY_30_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_DAILY_30_YEAR;
				
			case TREASURY_YEALD_MONTHLY_03_MONTH_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_03_MONTH;
			case TREASURY_YEALD_MONTHLY_02_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_02_YEAR;
			case TREASURY_YEALD_MONTHLY_05_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_05_YEAR;
			case TREASURY_YEALD_MONTHLY_07_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_07_YEAR;
			case TREASURY_YEALD_MONTHLY_10_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_10_YEAR;
			case TREASURY_YEALD_MONTHLY_30_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_MONTHLY_30_YEAR;
				
			case TREASURY_YEALD_WEEKLY_03_MONTH_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_03_MONTH;
			case TREASURY_YEALD_WEEKLY_02_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_02_YEAR;
			case TREASURY_YEALD_WEEKLY_05_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_05_YEAR;
			case TREASURY_YEALD_WEEKLY_07_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_07_YEAR;
			case TREASURY_YEALD_WEEKLY_10_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_10_YEAR;
			case TREASURY_YEALD_WEEKLY_30_YEAR_SYNCED:
				return SynchronizationScope.TREASURY_YEALD_WEEKLY_30_YEAR;
				
			case INTREST_RATE_MONTHLY_SYNC_ORDERED:
				return SynchronizationScope.INTREST_RATE_MONTHLY;
			case INTREST_RATE_WEEKLY_SYNC_ORDERED:
				return SynchronizationScope.INTREST_RATE_WEEKLY;
			case INTREST_RATE_DAILY_SYNC_ORDERED:
				return SynchronizationScope.INTREST_RATE_DAILY;
			case INTREST_RATE_MONTHLY_SYNCED:
				return SynchronizationScope.INTREST_RATE_MONTHLY;
			case INTREST_RATE_WEEKLY_SYNCED:
				return SynchronizationScope.INTREST_RATE_WEEKLY;
			case INTREST_RATE_DAILY_SYNCED:
				return SynchronizationScope.INTREST_RATE_DAILY;
				
			case CPI_MONTHLY_SYNC_ORDERED:
				return SynchronizationScope.CPI_MONTHLY;
			case CPI_SEMIANNUAL_SYNC_ORDERED:
				return SynchronizationScope.CPI_SEMIANNUAL;
			case CPI_MONTHLY_SYNCED:
				return SynchronizationScope.CPI_MONTHLY;
			case CPI_SEMIANNUAL_SYNCED:
				return SynchronizationScope.CPI_SEMIANNUAL;
			
			case INFLATION_ANNUAL_SYNC_ORDERED:
				return SynchronizationScope.INFLATION_ANNUAL;
			case INFLATION_ANNUAL_SYNCED:
				return SynchronizationScope.INFLATION_ANNUAL;
			
			case RETAIL_SALES_MONTHLY_SYNC_ORDERED:
				return SynchronizationScope.RETAIL_SALES_MONTHLY;
			case RETAIL_SALES_MONTHLY_SYNCED:
				return SynchronizationScope.RETAIL_SALES_MONTHLY;
				
			case DURABLE_GOODS_ORDER_MONTHLY_SYNC_ORDERED:
				return SynchronizationScope.DURABLE_GOODS_ORDERS_MONTHLY;
			case DURABLE_GOODS_ORDER_MONTHLY_SYNCED:
				return SynchronizationScope.DURABLE_GOODS_ORDERS_MONTHLY;
				
			case UNEMPLOYMENT_RATE_MONTHLY_SYNC_ORDERED:
				return SynchronizationScope.UNEMPLOYMENT_RATE_MONTHLY;
			case UNEMPLOYMENT_RATE_MONTHLY_SYNCED:
				return SynchronizationScope.UNEMPLOYMENT_RATE_MONTHLY;
				
			case NONFARM_PAYROLL_MONTHLY_SYNC_ORDERED:
				return SynchronizationScope.NONFARM_PAYROLL_MONTHLY;
			case NONFARM_PAYROLL_RATE_MONTHLY_SYNCED:
				return SynchronizationScope.NONFARM_PAYROLL_MONTHLY;
				
			default:
				throw new IllegalArgumentException("Not supported subject: " + subject);
		}
	}

}
