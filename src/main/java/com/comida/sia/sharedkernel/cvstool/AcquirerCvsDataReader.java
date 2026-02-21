package com.comida.sia.sharedkernel.cvstool;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sync.supervision.domain.model.WaterMark;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class AcquirerCvsDataReader<T extends WaterMark, B extends CvsRecordParser<T>> {
	private Boolean hasHeader;
	private String csv;
	private B cvsRecordParser;
	
	public AcquirerCvsDataReader(String csv, Boolean withHeader, B cvsRecordParser) {
		this.setCsv(csv);
		this.setHasHeader(withHeader);
		this.setCvsRecordParser(cvsRecordParser);
	}
	
	private void setCsv(String csv) {
		AssertionConcern.assertNotEmpty(csv, "Content for data parsing must be provided");
		AssertionConcern.assertNotContains(csv, "\"message\": \"No data", "CVS doesn't contains any data", this);
		AssertionConcern.assertNotContains(csv, "This is a premium endpoint", "Data available only for premium access", this);
	
		this.csv = csv;
	}
	
	private void setHasHeader(Boolean hasHeader) {
		if(hasHeader == null) {
			this.hasHeader = false;
		}
		
		this.hasHeader = hasHeader;
	}
	
	private void setCvsRecordParser(B cvsRecordParser) {
		AssertionConcern.assertNotNull(cvsRecordParser, "CVS record Parser must be provided");
		this.cvsRecordParser = cvsRecordParser;
	}
	
	public List<T> getParsedDataList() throws IOException, CsvException, ParseException{
		try (CSVReader reader = new CSVReader(new StringReader(csv))) {
			List<String[]> allData = reader.readAll();
			
			List<T> parsedCvsDataList = getParsedDataListFrom(allData);
			Collections.sort(parsedCvsDataList);
			return parsedCvsDataList;
		}		
	}
	
	private List<T> getParsedDataListFrom(List<String[]> dataStringList) throws ParseException{
		List<T> optionsDataList = new ArrayList<T>();
		boolean isFirstRow = true;
		
		for(String[] row : dataStringList) {
			if(isFirstRow && hasHeader) {
				isFirstRow = false;
				continue;
			}
			optionsDataList.add(getParsedDataItemFrom(row));
			isFirstRow = false;
		}
		
		return optionsDataList;
	}
	
	private T getParsedDataItemFrom(String[] dataStringListItem) throws ParseException {
		return cvsRecordParser.parse(dataStringListItem);
	}
}
