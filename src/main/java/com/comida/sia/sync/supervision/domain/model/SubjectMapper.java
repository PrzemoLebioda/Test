package com.comida.sia.sync.supervision.domain.model;

import com.comida.sia.sharedkernel.messaging.Subject;

public interface SubjectMapper {
	public SynchronizationScope getSyncStateTypeFrom(Subject subject);
}
