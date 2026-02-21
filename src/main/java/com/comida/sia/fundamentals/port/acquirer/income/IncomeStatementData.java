package com.comida.sia.fundamentals.port.acquirer.income;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncomeStatementData {
	private String symbol;
	private List<IncomeStatementReportData> annualReports;
	private List<IncomeStatementReportData> quarterlyReports;
	
	public IncomeStatementData() {
		super();
	}

	@Override
	public String toString() {
		return "IncomeStatementData [symbol=" + symbol + ", annualReports=" + annualReports + ", quarterlyReports="
				+ quarterlyReports + "]";
	}
	
	
}
