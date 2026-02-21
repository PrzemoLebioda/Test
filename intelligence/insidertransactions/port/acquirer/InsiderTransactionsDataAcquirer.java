package com.comida.sia.intelligence.insidertransactions.port.acquirer;

import java.io.IOException;
import java.text.ParseException;

import com.comida.sia.sharedkernel.cashe.LackOfNewDataException;
import com.comida.sia.sharedkernel.cashe.ObsoleteCashFileException;
import com.comida.sia.sync.supervision.domain.model.NotSupportedSynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface InsiderTransactionsDataAcquirer {
	public InsiderTransactionResult gatherInsiderTransactionsFor(String symbol) throws IOException;
	public InsiderTransactionResult gatherInsiderTransactionsFor(String symbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope;
}
