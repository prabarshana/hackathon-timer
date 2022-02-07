package com.shipxpress.kaleris.service;

import java.util.Date;

import com.shipxpress.kaleris.dto.EventStatus;

public interface EventStatusService {
	
	EventStatus saveEventStatus(EventStatus status);
	
	String getEventStartStatus();
	
	EventStatus getStartedEvent();
	
	void clearEvents();

}
