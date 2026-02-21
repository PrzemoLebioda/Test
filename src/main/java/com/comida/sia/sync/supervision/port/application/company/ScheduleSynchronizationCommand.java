package com.comida.sia.sync.supervision.port.application.company;

import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;

public class ScheduleSynchronizationCommand extends Command<CompanySynchronizationSupervisorService> {

	private String tickerSymbol;
	private Date plannedEarningsReportReleaseDate;
	
	public ScheduleSynchronizationCommand(
			CompanySynchronizationSupervisorService requestClass,
			String tickerSymbol,
			Date plannedEarningsReportReleaseDate) {
		super(requestClass);
		setTickerSymbol(tickerSymbol);
		setPlannedEarningsReportReleaseDate(plannedEarningsReportReleaseDate);
	}

	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create register supervisor command");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setPlannedEarningsReportReleaseDate(Date plannedEarningsReportReleaseDate) {
		AssertionConcern.assertNotNull(plannedEarningsReportReleaseDate, "Planned earnings report release date is mandatory in order to create register supervisor command");
		this.plannedEarningsReportReleaseDate = plannedEarningsReportReleaseDate;
	}
	
	@Override
	public void execute() {
		requestClass.sheduleSynchronization(tickerSymbol, plannedEarningsReportReleaseDate);
	}

}
