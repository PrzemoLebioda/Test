package com.comida.sia.sharedkernel.cvstool;

import java.text.ParseException;

import com.comida.sia.sync.supervision.domain.model.WaterMark;

public interface CvsRecordParser<T extends WaterMark> {
	T parse(String[] dataStringListItem) throws ParseException;
}
