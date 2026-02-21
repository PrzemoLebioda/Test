package com.comida.sia.sharedkernel.messaging;

import java.io.IOException;
import java.text.ParseException;

import com.opencsv.exceptions.CsvException;

public interface NotificationHandler {
	public boolean canHandle(String message);
	public void hanle() throws ParseException, IOException, CsvException;
}
