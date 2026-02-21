package com.comida.sia.fundamentals.port.acquirer.earnings;

import com.comida.sia.fundamentals.port.acquirer.ReportData;
import com.comida.sia.sync.supervision.domain.model.Direction;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EarningsQuarterlyReportData extends ReportData{
	private String tickerSymbol;
	private String reportedDate;
	private String reportedEPS;
	private String estimatedEPS;
	private String surprise;
	private String surprisePercentage;
	private String reportTime;
	
	public EarningsQuarterlyReportData() {
		super(Direction.ASCENDING);
	}
	
	@Override
	public void enrich(String additionalData) {
		this.tickerSymbol = additionalData;
	}

	@Override
	public String toString() {
		return "EarningsQuarterlyReportData [tickerSymbol=" + tickerSymbol + ", reportedDate=" + reportedDate
				+ ", reportedEPS=" + reportedEPS + ", estimatedEPS=" + estimatedEPS + ", surprise=" + surprise
				+ ", surprisePercentage=" + surprisePercentage + ", reportTime=" + reportTime + ", fiscalDateEnding="
				+ fiscalDateEnding + "]";
	}
	
}
