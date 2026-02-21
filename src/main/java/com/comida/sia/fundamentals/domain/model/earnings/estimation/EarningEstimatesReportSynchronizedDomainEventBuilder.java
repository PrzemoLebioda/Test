package com.comida.sia.fundamentals.domain.model.earnings.estimation;
import com.comida.sia.fundamentals.port.acquirer.earnings.estimation.EarningEstimatesReportData;
import com.comida.sia.sharedkernel.messaging.SubjectedPayload;
import com.comida.sia.sync.supervision.domain.model.DomainEventBuilder;
import com.comida.sia.sync.supervision.domain.model.SynchronizationReport;

public class EarningEstimatesReportSynchronizedDomainEventBuilder implements DomainEventBuilder<EarningEstimatesReportData, EarningEstimatesReport> {

	public EarningEstimatesReportSynchronizedDomainEventBuilder() {
		super();
	}
	
	@Override
	public SubjectedPayload build(SynchronizationReport<EarningEstimatesReportData, EarningEstimatesReport> report) {
		return new EarningEstimatesReportSynchronizedDomainEvent(
				report.getTickerSymbol(),
				report.getSummary());
	}

}
