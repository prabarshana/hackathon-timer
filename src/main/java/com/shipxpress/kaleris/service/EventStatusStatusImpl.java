package com.shipxpress.kaleris.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipxpress.kaleris.dao.EventDao;
import com.shipxpress.kaleris.dto.EventStatus;


@Service
public class EventStatusStatusImpl implements EventStatusService{
	
	@Autowired
	private EventDao dao;

	@Override
	public EventStatus saveEventStatus(EventStatus status) {
		
		if (dao.findAll() == null || dao.findAll().size() == 0) {
			return dao.save(status);		
		} else {
			return status;
		}
		
	}

	@Override
	public String getEventStartStatus() {
		return (dao.findAll() != null && dao.findAll().size() > 0) ? "CLICKED" : "NOT_CLICKED";
	}

	@Override
	public void clearEvents() {
		dao.deleteAll();
		
	}

}
