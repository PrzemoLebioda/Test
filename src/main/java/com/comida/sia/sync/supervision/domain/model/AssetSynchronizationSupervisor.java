package com.comida.sia.sync.supervision.domain.model;

import java.util.UUID;

import com.comida.sia.sharedkernel.AssertionConcern;
import com.comida.sia.sync.supervision.domain.model.company.AssetType;
import com.comida.sia.sync.supervision.domain.model.company.SynchronizationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Entity(name="AssetSynchronizationSupervisor")
public abstract class AssetSynchronizationSupervisor extends SynchronizationSupervisor {

	@Column(name = "ticker_symbol", nullable = true)
	@Getter private String tickerSymbol;
	
	@Column(name = "asset_type", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter private AssetType assetType;
	
	@Column(name = "synchronization_status", nullable = true)
	@Enumerated(EnumType.STRING)
	@Getter private SynchronizationStatus synchronizationStatus;
	
	public AssetSynchronizationSupervisor() {
		super();
	}
	
	public AssetSynchronizationSupervisor(UUID id, String tickerSymbol, AssetType assetType) {
		super(id);
		setTickerSybmol(tickerSymbol);
		setAssetType(assetType);
	}
	
	private void setTickerSybmol(String tickerSymbol) {
		AssertionConcern.assertNotEmpty(tickerSymbol, "Ticker symbol must be provided in order to create Company synchronization supervisor");
		this.tickerSymbol = tickerSymbol;
	}
	
	private void setAssetType(AssetType assetType) {
		AssertionConcern.assertNotNull(assetType, "Asset type must be provided in order to create Company synchrnization supervisor");
		this.assetType = assetType;
	}
	
	public void activateSynchronization() {
		this.synchronizationStatus = SynchronizationStatus.ACTIVE;
	}
	
	public void deactivateSynchronization() {
		this.synchronizationStatus = SynchronizationStatus.INACTIVE;
	}

	@Override
	public String toString() {
		return "AssetSynchronizationSupervisor [tickerSymbol=" + tickerSymbol + ", assetType=" + assetType
				+ ", synchronizationStatus=" + synchronizationStatus + ", notifier=" + notifier + ", subjectMapper="
				+ subjectMapper + "]";
	}
	
}
