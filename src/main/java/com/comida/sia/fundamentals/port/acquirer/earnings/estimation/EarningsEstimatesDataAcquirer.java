package com.comida.sia.fundamentals.port.acquirer.earnings.estimation;

import java.io.IOException;
import java.text.ParseException;

import com.comida.sia.sharedkernel.cashe.LackOfNewDataException;
import com.comida.sia.sharedkernel.cashe.ObsoleteCashFileException;
import com.comida.sia.sync.supervision.domain.model.NotSupportedSynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface EarningsEstimatesDataAcquirer {
	public EarningsEstimatesData gatherEarningsEstimatesDataFor(String tickerSymbol) throws IOException;
	public EarningsEstimatesData gatherEarningsEstimatesDataFor(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope;
}
