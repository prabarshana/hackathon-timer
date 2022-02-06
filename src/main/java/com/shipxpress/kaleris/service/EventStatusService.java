package com.shipxpress.kaleris.service;

import com.shipxpress.kaleris.dto.EventStatus;

public interface EventStatusService {
	
	EventStatus saveEventStatus(EventStatus status);
	
	String getEventStartStatus();
	
	void clearEvents();

}
