package com.comida.sia.fundamentals.port.acquirer.earnings;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EarningsData {
	private String symbol;
	private List<EarningsAnnualReportData> annualEarnings;
	private List<EarningsQuarterlyReportData> quarterlyEarnings;
	
	public EarningsData() {
		super();
	}

	@Override
	public String toString() {
		return "EarningsData [symbol=" + symbol + ", annualReports=" + annualEarnings + ", quarterlyEarnings="
				+ quarterlyEarnings + "]";
	}
}
