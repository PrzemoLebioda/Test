package com.comida.sia.fundamentals.port.acquirer.stock;

import java.text.ParseException;

import com.comida.sia.sharedkernel.cvstool.CvsRecordParser;

public class StockDataCvsRecordParser implements CvsRecordParser<StockData>{

	@Override
	public StockData parse(String[] dataStringListItem) throws ParseException {
		StockData companyData = new StockData();
		
		companyData.setSymbol(dataStringListItem[0]);
		companyData.setName(dataStringListItem[1]);
		companyData.setExchange(dataStringListItem[2]);
		companyData.setAssetType(dataStringListItem[3]);
		companyData.setIpoDate(dataStringListItem[4]);
		companyData.setDelistingDate(dataStringListItem[5]);
		companyData.setStatus(dataStringListItem[6]);
		
		return companyData;
	}

}
