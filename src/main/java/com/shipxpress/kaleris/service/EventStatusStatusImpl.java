package com.shipxpress.kaleris.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.shipxpress.kaleris.dao.EventDao;
import com.shipxpress.kaleris.dto.EventStatus;


@Service
public class EventStatusStatusImpl implements EventStatusService{
	
	@Autowired
	private EventDao dao;
	
	@Autowired
	private Environment env;

	@Override
	public EventStatus saveEventStatus(EventStatus status) {
		
		int days = Integer.parseInt(env.getProperty("kaleris.hackathon.days.add"));
		int hours = Integer.parseInt(env.getProperty("kaleris.hackathon.hours.add"));
		int minutes = Integer.parseInt(env.getProperty("kaleris.hackathon.hours.minutes"));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(status.getTimeStamp());
		cal.add(Calendar.DAY_OF_MONTH, days);
		cal.add(Calendar.HOUR, hours);
		cal.add(Calendar.MINUTE, minutes);
		status.setStopTime(cal.getTime());
		
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

	@Override
	public EventStatus getStartedEvent() {
		List<EventStatus> findAll = dao.findAll();
		return findAll != null && findAll.size() > 0 ? findAll.get(0) : null;
	}

}
