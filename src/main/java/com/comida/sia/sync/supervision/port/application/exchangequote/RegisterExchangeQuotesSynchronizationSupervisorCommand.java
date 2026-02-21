package com.comida.sia.sync.supervision.port.application.exchangequote;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;

public class RegisterExchangeQuotesSynchronizationSupervisorCommand extends Command<ExchangeQuotesSynchronizationSupervisorService> {

	private String tickerSymbol;
	private AssetType assetType;
	
	public RegisterExchangeQuotesSynchronizationSupervisorCommand(
			ExchangeQuotesSynchronizationSupervisorService requestClass,
			String tickerSymbol,
			AssetType assetType) {
		super(requestClass);
		setTickerSymbol(tickerSymbol);
		setAssetType(assetType);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create register supervisor command");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setAssetType(AssetType assetType) {
		AssertionConcern.assertNotNull(assetType, "Asset type  must be provided in order to create a synchronizator");
		this.assetType = assetType;
	}

	@Override
	public void execute() {
		requestClass.register(tickerSymbol, assetType);
	}

}
