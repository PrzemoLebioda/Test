package com.comida.sia.sync.supervision.domain.model;

import com.comida.sia.sharedkernel.messaging.SubjectedPayload;

public interface DomainEventBuilder<T extends WaterMark, R extends TimeSeries> {
	SubjectedPayload build(SynchronizationReport<T, R> report);
}
