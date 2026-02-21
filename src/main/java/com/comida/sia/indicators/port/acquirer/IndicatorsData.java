package com.comida.sia.indicators.port.acquirer;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndicatorsData {
	private String name;
	private String interval;
	private String unit;
	private List<IndicatorsDataEntry> data;
	
	@Override
	public String toString() {
		return "IndicatorsData [name=" + name + ", interval=" + interval + ", unit=" + unit + ", data=" + data + "]";
	}
}
