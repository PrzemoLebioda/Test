package com.comida.sia.fundamentals.port.acquirer.corpoevents.splits;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SplitCalendarData {
	private String symbol;
	private List<SplitEventData> data;
	
	@Override
	public String toString() {
		return "SplitCalendarData [symbol=" + symbol + ", data=" + data + "]";
	}
}
