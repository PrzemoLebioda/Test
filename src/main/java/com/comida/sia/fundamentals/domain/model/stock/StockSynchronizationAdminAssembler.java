package com.comida.sia.fundamentals.domain.model.stock;

import com.comida.sia.fundamentals.port.acquirer.stock.StockData;
import com.comida.sia.sharedkernel.GenericBuilder;
import com.comida.sia.sharedkernel.messaging.Notifier;
import com.comida.sia.sync.supervision.domain.model.AscendDiscriminator;
import com.comida.sia.sync.supervision.domain.model.Register;
import com.comida.sia.sync.supervision.domain.model.SynchronizationAdmin;
import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;
import com.comida.sia.sync.supervision.domain.model.SynchronizationWorker;

public class StockSynchronizationAdminAssembler {
	public StockSynchronizationAdminAssembler() {
		super();
	}
	
	public SynchronizationAdmin<StockData, ListedStockTranslator, Stock> assemblyListedStockSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<StockData, ListedStockTranslator, Stock>::new)
				.with(SynchronizationAdmin<StockData, ListedStockTranslator, Stock>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<StockData, ListedStockTranslator, Stock>::setNotifier, notifier)
				.with(SynchronizationAdmin<StockData, ListedStockTranslator, Stock>::setDomainEventBuilder, new ListedStockSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<StockData, ListedStockTranslator, Stock>::setTranslator, new ListedStockTranslator(notifier))
				.with(SynchronizationAdmin<StockData, ListedStockTranslator, Stock>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<StockData, ListedStockTranslator, Stock>::new)
							.with(SynchronizationWorker<StockData, ListedStockTranslator, Stock>::setRegister, 
									GenericBuilder.of(Register<StockData, ListedStockTranslator, Stock>::new)
										.with(Register<StockData, ListedStockTranslator, Stock>::setPeriod, syncState.getPeriod())
										.with(Register<StockData, ListedStockTranslator, Stock>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<StockData>::new)
													.with(AscendDiscriminator<StockData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
	public SynchronizationAdmin<StockData, DelistedStockTranslator, Stock> assemblyDelistedStockSyncAdmin(SynchronizationStateDto syncState, Notifier notifier) {
		return GenericBuilder.of(SynchronizationAdmin<StockData, DelistedStockTranslator, Stock>::new)
				.with(SynchronizationAdmin<StockData, DelistedStockTranslator, Stock>::setTickerSymbol, "SIA_ROOT")
				.with(SynchronizationAdmin<StockData, DelistedStockTranslator, Stock>::setNotifier, notifier)
				.with(SynchronizationAdmin<StockData, DelistedStockTranslator, Stock>::setDomainEventBuilder, new DelistedStockSynchronizedDomainEventBuilder())
				.with(SynchronizationAdmin<StockData, DelistedStockTranslator, Stock>::setTranslator, new DelistedStockTranslator(notifier))
				.with(SynchronizationAdmin<StockData, DelistedStockTranslator, Stock>::setSyncWorker,
						GenericBuilder.of(SynchronizationWorker<StockData, DelistedStockTranslator, Stock>::new)
							.with(SynchronizationWorker<StockData, DelistedStockTranslator, Stock>::setRegister, 
									GenericBuilder.of(Register<StockData, DelistedStockTranslator, Stock>::new)
										.with(Register<StockData, DelistedStockTranslator, Stock>::setPeriod, syncState.getPeriod())
										.with(Register<StockData, DelistedStockTranslator, Stock>::setDiscriminator,
												GenericBuilder.of(AscendDiscriminator<StockData>::new)
													.with(AscendDiscriminator<StockData>::setCurrentWaterMarkLevel, syncState.getWaterMarkCurrentLevel())
													.build())
										.build())
							.build())
				.build();
	}
	
}
