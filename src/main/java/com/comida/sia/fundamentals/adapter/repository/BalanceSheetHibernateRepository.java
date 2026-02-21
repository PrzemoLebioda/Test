package com.comida.sia.fundamentals.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.balancesheet.BalanceSheetReport;
import com.comida.sia.fundamentals.port.repository.BalanceSheetRepository;

import jakarta.persistence.EntityManager;

@Component("BalanceSheetHibernateRepository")
public class BalanceSheetHibernateRepository extends HibernateRepository implements BalanceSheetRepository{

	public BalanceSheetHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	public Boolean exists(String tickerSymbol) {
		String query = "select exists(select ticker_symbol from sia_storage.balance_sheets_registers where ticker_symbol = :tickerSymbol)";
		
		Boolean exists = (Boolean)getEntityManager()
				.createNativeQuery(query)
				.setParameter("tickerSymbol", tickerSymbol)
				.getSingleResult();
		
		return exists;
	}

	@Override
	public void store(BalanceSheetReport report) {
		getEntityManager().persist(report);
		
		getEntityManager().flush();
		getEntityManager().clear();
	}

}
