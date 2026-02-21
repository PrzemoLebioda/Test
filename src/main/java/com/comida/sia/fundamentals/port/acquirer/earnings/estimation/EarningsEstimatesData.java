package com.comida.sia.fundamentals.port.acquirer.earnings.estimation;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EarningsEstimatesData {
	private String symbol;
	private List<EarningEstimatesReportData> estimates;
	
	public EarningsEstimatesData() {
		super();
	}

	@Override
	public String toString() {
		return "EarningsEstimatesData [symbol=" + symbol + ", estimates=" + estimates + "]";
	}
}
