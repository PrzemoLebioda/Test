package com.comida.sia.fundamentals.port.acquirer.corpoevents.earnings;

import java.text.ParseException;

import com.comida.sia.sharedkernel.cvstool.CvsRecordParser;

public class EarningsEventsDataCvsRecordParser implements CvsRecordParser<EarningEventData>{

	@Override
	public EarningEventData parse(String[] dataStringListItem) throws ParseException {
		EarningEventData earningEventsData = new EarningEventData();
		
		earningEventsData.setSymbol(dataStringListItem[0]);
		earningEventsData.setReportDate(dataStringListItem[2]);
		earningEventsData.setFiscalDateEnding(dataStringListItem[3]);
		earningEventsData.setEstimate(dataStringListItem[4]);
		earningEventsData.setCurrency(dataStringListItem[5]);
		
		return earningEventsData;
	}

}
