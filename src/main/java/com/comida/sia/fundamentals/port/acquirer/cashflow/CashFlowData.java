package com.comida.sia.fundamentals.port.acquirer.cashflow;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CashFlowData {
	private String symbol;
	private List<CashFlowReportData> annualReports;
	private List<CashFlowReportData> quarterlyReports;
	
	public CashFlowData() {
		super();
	}

	@Override
	public String toString() {
		return "CashFlowData [symbol=" + symbol + ", annualReports=" + annualReports + ", quarterlyReports="
				+ quarterlyReports + "]";
	}
	
	
}
