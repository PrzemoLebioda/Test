package com.comida.sia.sync.supervision.port.application.exchangequote;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sharedkernel.command.Command;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;

public class DeactivateExchangeQuotesSynchronizationCommand extends Command<ExchangeQuotesSynchronizationSupervisorService>{

	private String tickerSymbol;
	private AssetType assetType;
	
	public DeactivateExchangeQuotesSynchronizationCommand(
			ExchangeQuotesSynchronizationSupervisorService requestClass,
			String tickerSymbol,
			AssetType assetType) {
		
		super(requestClass);
		setTickerSymbol(tickerSymbol);
		setAssetType(assetType);
	}
	
	private void setTickerSymbol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol is mandatory in order to create deactivate exchange quote synchronization command");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setAssetType(AssetType assetType) {
		AssertionConcern.assertNotNull(assetType, "Asset type  must be provided in order to create a company synchronizator");
		this.assetType = assetType;
	}

	@Override
	public void execute() {
		requestClass.deactivateSynchronization(tickerSymbol, assetType);
	}

}
