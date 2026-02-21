package com.comida.sia.fundamentals.port.repository;

import com.comida.sia.fundamentals.domain.model.sharesoutstanding.SharesOutstandingReport;

public interface SharesOutstandingRepository {
	void store(SharesOutstandingReport sharesOutstandingReport);
	void update(SharesOutstandingReport sharesOutstandingReport);
}
