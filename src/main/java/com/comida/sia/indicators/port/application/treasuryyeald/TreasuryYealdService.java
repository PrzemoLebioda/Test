package com.comida.sia.indicators.port.application.treasuryyeald;

import com.comida.sia.sync.supervision.domain.model.SynchronizationStateDto;

public interface TreasuryYealdService {
	public void synchronizeTreasuryYealdDaily02Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdDaily03Month(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdDaily05Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdDaily07Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdDaily10Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdDaily30Year(SynchronizationStateDto syncState);
	
	public void synchronizeTreasuryYealdWeekly02Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdWeekly03Month(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdWeekly05Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdWeekly07Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdWeekly10Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdWeekly30Year(SynchronizationStateDto syncState);
	
	public void synchronizeTreasuryYealdMonthly02Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdMonthly03Month(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdMonthly05Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdMonthly07Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdMonthly10Year(SynchronizationStateDto syncState);
	public void synchronizeTreasuryYealdMonthly30Year(SynchronizationStateDto syncState);
}
