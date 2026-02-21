package com.comida.sia.fundamentals.port.acquirer.earnings;

import com.comida.sia.fundamentals.port.acquirer.ReportData;
import com.comida.sia.sync.supervision.domain.model.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EarningsAnnualReportData extends ReportData{
	private String tickerSymbol;
	private String reportedEPS;
	
	public EarningsAnnualReportData() {
		super(Direction.ASCENDING);
	}
	
	@Override
	public void enrich(String additionalData) {
		this.tickerSymbol = additionalData;
	}

	@Override
	public String toString() {
		return "EarningsAnnualReportData [tickerSymbol=" + tickerSymbol + ", reportedEPS=" + reportedEPS
				+ ", fiscalDateEnding=" + fiscalDateEnding + "]";
	}
	
}
