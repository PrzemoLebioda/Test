package com.comida.sia.options.port.acquirer;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.opencsv.exceptions.CsvException;

public interface OptionAcquirer {
	List<OptionData> gatherOptionDataFor(String symbol, Date since) throws IOException, CsvException, ParseException;
}
