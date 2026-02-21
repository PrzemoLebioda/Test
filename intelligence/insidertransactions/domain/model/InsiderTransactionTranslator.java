package com.comida.sia.intelligence.insidertransactions.domain.model;

import java.text.ParseException;
import java.util.UUID;

import com.comida.sia.intelligence.insidertransactions.port.acquirer.InsiderTransactionData;
import com.comida.sia.sync.supervision.domain.model.ModelTranslator;

public class InsiderTransactionTranslator implements ModelTranslator<InsiderTransactionData, InsiderTransaction> {

	@Override
	public InsiderTransaction translate(InsiderTransactionData source) throws ParseException {
		InsiderTransaction insiderTransaction = new InsiderTransaction.Builder(UUID.randomUUID())
				.setExecutive(source.getExecutive())
				.setExecutiveTitle(source.getExecutive_title())
				.setSecutityType(source.getSecurity_type())
				.setSharePrice(source.getShare_price())
				.setShares(source.getShares())
				.setTicekrSymbok(source.getTicker())
				.setTransactionDate(source.getTransaction_date())
				.setTransactionType(source.getAcquisition_or_disposal())
				.build();
		
		if(insiderTransaction.getTransactionDate() == null) {
			return null;
		} else {
			return insiderTransaction;
		}
	}

}
