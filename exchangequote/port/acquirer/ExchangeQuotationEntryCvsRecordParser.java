package com.comida.sia.exchangequote.port.acquirer;

import java.text.ParseException;

import com.comida.sia.sharedkernel.cvstool.CvsRecordParser;

public class ExchangeQuotationEntryCvsRecordParser implements CvsRecordParser<ExchangeQuotationEntry>{

	@Override
	public ExchangeQuotationEntry parse(String[] dataStringListItem) throws ParseException {
		ExchangeQuotationEntry quotation = new ExchangeQuotationEntry();
		
		quotation.setTimestamp(dataStringListItem[0]);
		quotation.setOpen(dataStringListItem[1]);
		quotation.setHigh(dataStringListItem[2]);
		quotation.setLow(dataStringListItem[3]);
		quotation.setClose(dataStringListItem[4]);
		quotation.setVloume(dataStringListItem[5]);
		
		return quotation;
	}

}
