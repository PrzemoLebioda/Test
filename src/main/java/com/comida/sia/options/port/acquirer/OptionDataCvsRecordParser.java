package com.comida.sia.options.port.acquirer;

import java.text.ParseException;

import com.comida.sia.sharedkernel.cvstool.CvsRecordParser;

public class OptionDataCvsRecordParser implements CvsRecordParser<OptionData> {

	public OptionDataCvsRecordParser(){
		
	}
	
	@Override
	public OptionData parse(String[] dataStringListItem) throws ParseException {
		OptionData optionData = new OptionData();
		
		optionData.setContractId(dataStringListItem[0]);
		optionData.setSymbol(dataStringListItem[1]);
		optionData.setExpiration(dataStringListItem[2]);
		optionData.setStrike(dataStringListItem[3]);
		optionData.setType(dataStringListItem[4]);
		optionData.setLast(dataStringListItem[5]);
		optionData.setMark(dataStringListItem[6]);
		optionData.setBid(dataStringListItem[7]);
		optionData.setBidSize(dataStringListItem[8]);
		optionData.setAsk(dataStringListItem[9]);
		optionData.setAskSize(dataStringListItem[10]);
		optionData.setVolume(dataStringListItem[11]);
		optionData.setOpenInterest(dataStringListItem[12]);
		optionData.setDate(dataStringListItem[13]);
		optionData.setImpliedVolatility(dataStringListItem[14]);
		optionData.setDelta(dataStringListItem[15]);
		optionData.setGamma(dataStringListItem[16]);
		optionData.setTheta(dataStringListItem[17]);
		optionData.setVega(dataStringListItem[18]);
		optionData.setRho(dataStringListItem[19]);
		
		return optionData;
	}

}
