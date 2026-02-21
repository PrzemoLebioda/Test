package com.comida.sia.fundamentals.port.acquirer.corpoevents.dividend;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DividendCalendarData {
	private String symbol;
	private List<DividendEventData> data;
	
	public DividendCalendarData() {
		super();
	}

	@Override
	public String toString() {
		return "DividendCalendarData [symbol=" + symbol + ", data=" + data + "]";
	}

}
