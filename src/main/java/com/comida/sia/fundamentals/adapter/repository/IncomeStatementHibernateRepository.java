package com.comida.sia.fundamentals.adapter.repository;

import org.springframework.stereotype.Component;

import com.comida.sia.config.HibernateRepository;
import com.comida.sia.fundamentals.domain.model.income.IncomeStatementReport;
import com.comida.sia.fundamentals.port.repository.IncomeStatementRepository;

import jakarta.persistence.EntityManager;

@Component("IncomeStatementHibernateRepository")
public class IncomeStatementHibernateRepository extends HibernateRepository implements IncomeStatementRepository{

	public IncomeStatementHibernateRepository(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void store(IncomeStatementReport incomeStatementReport) {
		getEntityManager().persist(incomeStatementReport);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	@Override
	public void update(IncomeStatementReport incomeStatementReport) {
		getEntityManager().merge(incomeStatementReport);
		
		getEntityManager().flush();
		getEntityManager().clear();
		
	}

	/*
	 * Trzymane jako przykład pobierania danych
	@Override
	public IncomeStatementsRegister getFor(String tickerSymbol) {
		IncomeStatementsRegister incomeStatementsRegister;
		
		String query = "FROM IncomeStatementsRegister "
				 	 + "WHERE tickerSymbol = :tickerSymbol";
	
		incomeStatementsRegister = (IncomeStatementsRegister)getEntityManager()
				.createQuery(query)
				.setParameter("tickerSymbol", tickerSymbol)
				.getSingleResult();
		
		getEntityManager().detach(incomeStatementsRegister);
	
		return incomeStatementsRegister;
	}

	@Override
	public Boolean exists(String tickerSymbol) {
		String query = "select exists(select ticker_symbol from sia_storage.income_statements_register where ticker_symbol = :tickerSymbol)";
		
		Boolean exists = (Boolean)getEntityManager()
				.createNativeQuery(query)
				.setParameter("tickerSymbol", tickerSymbol)
				.getSingleResult();
		
		return exists;
	}
	*/
}
