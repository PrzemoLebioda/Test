package com.comida.sia.fundamentals.port.acquirer.sharesoutstanding;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SharesOutstandingData {
	private String symbol;
	private String status;
	private List<SharesOutstandingReportData> data;
	
	public SharesOutstandingData() {
		super();
	}

	@Override
	public String toString() {
		return "SharesOutstandingData [symbol=" + symbol + ", status=" + status + ", data=" + data + "]";
	}
}
