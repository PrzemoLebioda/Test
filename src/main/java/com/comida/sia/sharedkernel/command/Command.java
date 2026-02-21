package com.comida.sia.sharedkernel.command;

import java.io.IOException;
import java.text.ParseException;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.opencsv.exceptions.CsvException;

public abstract class Command<T>{
	protected T requestClass;
	
	public Command(T requestClass) {
		AssertionConcern.assertNotNull(requestClass, "Request class must be provided in order to execute command");
		this.requestClass = requestClass;
	}
	
	public abstract void execute() throws ParseException, IOException, CsvException;
}
