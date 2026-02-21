package com.comida.sia.fundamentals.domain.model.sharesoutstanding;

import com.comida.sia.fundamentals.port.acquirer.sharesoutstanding.SharesOutstandingReportData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class SharesOutstandingReportSynchronizedDomainEventBuilder implements DomainEventBuilder<SharesOutstandingReportData, SharesOutstandingReport> {

	@Override
	public SubjectedPayload build(SynchronizationReport<SharesOutstandingReportData, SharesOutstandingReport> report) {
		return new SharesOutstandingReportSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
