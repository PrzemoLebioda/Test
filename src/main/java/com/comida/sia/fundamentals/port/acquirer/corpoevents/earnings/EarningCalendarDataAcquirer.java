package com.comida.sia.fundamentals.port.acquirer.corpoevents.earnings;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import com.comida.sia.sharedkernel.cashe.LackOfNewDataException;
import com.comida.sia.sharedkernel.cashe.ObsoleteCashFileException;
import com.comida.sia.sync.supervision.domain.model.NotSupportedSynchronizationScope;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.opencsv.exceptions.CsvException;

public interface EarningCalendarDataAcquirer {
	public List<EarningEventData> gatherEarningCalendarData() throws IOException, CsvException, ParseException;
	public List<EarningEventData> gatherEarningCalendarData(SynchronizationStateDto syncState) throws ObsoleteCashFileException, LackOfNewDataException, IOException, ParseException, NotSupportedSynchronizationScope;
}
