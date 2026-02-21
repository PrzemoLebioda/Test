package com.comida.sia.indicators.port.acquirer.treasuryyeald;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreasuryYealdData {
	private String name;
	private String interval;
	private String unit;
	private List<TreasuryYealdEntry> data;
	
	@Override
	public String toString() {
		return "TreasuryYealdData [name=" + name + ", interval=" + interval + ", unit=" + unit + ", data=" + data + "]";
	}
}
