package com.comida.sia.sync.supervision.port.application.company;

import java.text.ParseException;
import java.util.Date;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;

public class RegisterCompanySynchronizationSupervisorCommand extends Command<CompanySynchronizationSupervisorService>{
	
	private String tickerSymbol;
	private Date ipoDate;
	private AssetType assetType;
	
	public RegisterCompanySynchronizationSupervisorCommand(CompanySynchronizationSupervisorService requestClass, String tickerSymbol, Date ipoDate, AssetType assetType) {
		super(requestClass);
		setTickerSymbol(tickerSymbol);
		setIpoDate(ipoDate);
		setAssetType(assetType);
	}

	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create register supervisor command");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setIpoDate(Date ipoDate) {
		AssertionConcern.assertNotNull(ipoDate, "Ipo date is mandatory in order to create register supervisor command");
		this.ipoDate = ipoDate;
	}
	
	private void setAssetType(AssetType assetType) {
		AssertionConcern.assertNotNull(assetType, "Asset type  must be provided in order to create a company synchronizator");
		this.assetType = assetType;
	}

	@Override
	public void execute() throws ParseException {
		requestClass.register(tickerSymbol, ipoDate, assetType);
		
	}
}
