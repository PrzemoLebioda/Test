package com.comida.sia.options.port.acquirer;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OprionsResult  {
	String symbol;
	List<OptionData> options;
	
	public OprionsResult() {
		
	}
}
