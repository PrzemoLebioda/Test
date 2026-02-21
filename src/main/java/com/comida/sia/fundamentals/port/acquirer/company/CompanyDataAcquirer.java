package com.comida.sia.fundamentals.port.acquirer.company;

import java.io.IOException;
import java.text.ParseException;

import com.comida.sia.sharedkernel.cashe.LackOfNewDataException;
import com.comida.sia.sharedkernel.cashe.ObsoleteCashFileException;
import com.comida.sia.sync.supervision.domain.model.NotSupportedSynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface CompanyDataAcquirer {
	/**
	 * Querying detailed information about given company
	 * 
	 * @param tickerSymbol
	 * @return
	 * @throws IOException 
	 */
	CompanyDetailsData gatherCompanyDetailsDataFor(String tickerSymbol) throws IOException;
	CompanyDetailsData gatherCompanyDetailsDataFor(String tickerSymbol, SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope;
}
