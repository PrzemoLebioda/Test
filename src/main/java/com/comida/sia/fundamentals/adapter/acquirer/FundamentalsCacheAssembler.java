package com.comida.sia.fundamentals.adapter.acquirer;

import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.cashe.Cache;
import com.comida.sia.sharedkernel.cashe.CacheAssembler;
import com.comida.sia.sharedkernel.cashe.CacheSingleFile;
import com.comida.sia.sharedkernel.cashe.CacheIndexFile;
import com.comida.sia.sharedkernel.cashe.FileType;
import com.comida.sia.sharedkernel.period.InfinitePeriod;

public class FundamentalsCacheAssembler extends CacheAssembler{
		
	public FundamentalsCacheAssembler(String relativePath) {
		super(relativePath);
	}
	
	// Balance sheet
	public Cache assemblyQuarterlyBalanceSheetCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "balance_sheet_quarterly_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "balance_sheet")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyAnnualBalanceSheetCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "balance_sheet_annual_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "balance_sheet")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadQuarterlyBalanceSheetCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "balance_sheet_quarterly_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "balance_sheet")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadAnnualBalanceSheetCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "balance_sheet_annual_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "balance_sheet")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	// Cash flow
	public Cache assemblyQuarterlyCashFlowCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "cash_flow_quarterly_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "cash_flow")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyAnnualCashFlowCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "cash_flow_annual_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "cash_flow")
							.with(CacheIndexFile::setPath, getAbsolutFilePath()) 
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadQuaterlyCashFlowCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "cash_flow_quarterly_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "cash_flow")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadAnnualCashFlowCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "cash_flow_annual_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "cash_flow")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}

	//Dividend 
	public Cache assemblyDividendCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "dividend_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "dividend")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadDividendCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "dividend_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "dividend")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	//Split
	public Cache assemblySplitCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "split_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "split")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadSplitCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "split_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "split")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	//Earnings estimations
	public Cache assemblyEarningsEstimatesCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "earnings_estimates_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "earnings_estimates")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadEarningsEstimatesCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "earnings_estimates_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "earnings_estimates")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	//Earnings Calendar
	public Cache assemblyEarningsCalendarCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "earnings_calendar_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "earnings_calendar")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.CSV)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadEarningsCalendarCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "earnings_calendar_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "earnings_calendar")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.CSV)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	// Earnings 
	public Cache assemblyQuarterlyEarningsCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "earnings_quarterly_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "earnings")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyAnnualEarningsCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "earnings_annual_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "earnings")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadQuarterlyEarningsCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "earnings_quarterly_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "earnings")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadAnnualEarningsCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "earnings_annual_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "earnings")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	//Income
	public Cache assemblyQuarterlyIncomeCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "income_statement_quarterly_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "income_statement")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyAnnualIncomeCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "income_statement_annual_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "income_statement")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadQuarterlyIncomeCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "income_statement_quarterly_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "income_statement")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadAnnualIncomeCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "income_statement_annual_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "income_statement")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	//Shares outstanding
	public Cache assemblySharesOutstandingCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "shares_outstanding_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "shares_outstanding")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadSharesOutstandingCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "shares_outstanding_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "shares_outstanding")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	// Company data
	public Cache assemblyCompanyDataCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "key_metrics_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "key_metrics")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadCompanyDataCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "key_metrics_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "key_metrics")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.JSON)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	// Listed Stock
	public Cache assemblyListedStockCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "listed_stock_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "listed_stock")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.CSV)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadListedStockCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "listed_stock_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "listed_stock")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.CSV)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}
	
	//Delisted stock
	public Cache assemblyDelistedStockCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "delisted_stock_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "delisted_stock")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.CSV)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, false)
							.build())
				.build();
	}
	
	public Cache assemblyForceReadDelistedStockCache(){
		return GenericBuilder.of(CacheSingleFile::new)
				.with(CacheSingleFile::setRelativePath, getRelativePath())
				.with(CacheSingleFile::setCacheMetaFileName, "delisted_stock_index")
				.with(CacheSingleFile::setCasheMetaFile, 
						GenericBuilder.of(CacheIndexFile::new)
							.with(CacheIndexFile::setName, "delisted_stock")
							.with(CacheIndexFile::setPath, getAbsolutFilePath())
							.with(CacheIndexFile::setFileType, FileType.CSV)
							.with(CacheIndexFile::setFileLifeCyclePeriod, new InfinitePeriod())
							.with(CacheIndexFile::setForceRead, true)
							.build())
				.build();
	}

}
