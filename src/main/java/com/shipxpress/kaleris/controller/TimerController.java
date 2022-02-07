package com.shipxpress.kaleris.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.shipxpress.kaleris.dto.EventStatus;
import com.shipxpress.kaleris.service.EventStatusService;

@Controller
public class TimerController {
	
	@Autowired
	private Environment env;
	
	@Autowired
	private EventStatusService service;
	
	@RequestMapping({"", "/", "index"})
	private String index(HttpServletRequest request) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(env.getProperty("kaleris.hackathon.date.format"));
		EventStatus startedEvent = service.getStartedEvent();
		
		Date start = null;
		Date end = null;
		String status = null;
		
		if (startedEvent == null) {
			 start = format.parse(env.getProperty("kaleris.hackathon.start"));
			 end = format.parse(env.getProperty("kaleris.hackathon.end"));
			 status = "B";
			
		} else {
			start = startedEvent.getTimeStamp();
			end = startedEvent.getStopTime();
			status = "S";
		}
		
		Date currentDate = new Date();
		
		if (currentDate.after(end)) {
			status = "E";
		}
		
		System.out.println(start+" "+end+" "+status);
		
		request.getSession().setAttribute("event_status", status);
		request.getSession().setAttribute("button_status", service.getEventStartStatus());
		request.getSession().setAttribute("start_date", format.format(start));
		request.getSession().setAttribute("end_date", format.format(end));
		
		return "index.jsp";
		
	}
	
	
	@RequestMapping({"/reset", "/stop"})
	private RedirectView reset(HttpServletRequest request) throws ParseException {
		service.clearEvents();
		return new RedirectView("/");
	}
	
	@RequestMapping({"/event"})
	@ResponseBody
	private ResponseEntity<Map<String, String>> getEvent() throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat(env.getProperty("kaleris.hackathon.date.format"));
		EventStatus startedEvent = service.getStartedEvent();
		Map<String, String> map = new LinkedHashMap<>();
		if (startedEvent != null) {
			map.put("start", format.format(startedEvent.getTimeStamp()));
			map.put("end", format.format(startedEvent.getStopTime()));
		} else {
			map.put("start", null);
			map.put("end", null);
		}
		return ResponseEntity.ok(map);
	}
	
	
	@PostMapping({"/update-flag"})
	private ResponseEntity<Map<String, String>> update(EventStatus status, HttpServletRequest request) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat(env.getProperty("kaleris.hackathon.date.format"));
		EventStatus returnInstance = service.saveEventStatus(status);
		
		Map<String, String> map = new LinkedHashMap<>();
		
		if (returnInstance.getId() == null) {
			map.put("end_time", null);
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(map);
		} else {
			map.put("end_time", format.format(returnInstance.getStopTime()));
			return ResponseEntity.ok(map);
		}
		
	}
	

}
