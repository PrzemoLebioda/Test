package com.comida.sia.fundamentals.port.acquirer.balancesheet;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BalanceSheetData {
	private String symbol;
	private List<BalanceSheetReportData> annualReports;
	private List<BalanceSheetReportData> quarterlyReports;
		
	public BalanceSheetData() {
		super();
	}

	@Override
	public String toString() {
		return "BalanceSheetData [symbol=" + symbol + ", annualReports=" + annualReports + ", quarterlyReports="
				+ quarterlyReports + "]";
	}
	
	
}
