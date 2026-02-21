package com.comida.sia.intelligence.insidertransactions.port.acquirer;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InsiderTransactionResult {
	private List<InsiderTransactionData> data;
	
	public InsiderTransactionResult(){
		super();
	}

	@Override
	public String toString() {
		return "InsiderTransactionResult [data=" + data + "]";
	}
	
}
