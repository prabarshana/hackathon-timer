package com.shipxpress.kaleris.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		
		String status = null;
		
		SimpleDateFormat format = new SimpleDateFormat(env.getProperty("kaleris.hackathon.date.format"));
		Date start = format.parse(env.getProperty("kaleris.hackathon.start"));
		Date end = format.parse(env.getProperty("kaleris.hackathon.end"));
		Date currentDate = new Date();
		
		if (currentDate.after(start) && currentDate.before(end)) {
			status = "S";
		}
		
		if (currentDate.before(start)) {
			status = "B";
		}
		
		if (currentDate.after(end)) {
			status = "E";
		}
		
		System.out.println(start+" "+end+" "+status);
		
		request.getSession().setAttribute("event_status", status);
		request.getSession().setAttribute("button_status", service.getEventStartStatus());
		request.getSession().setAttribute("start_date", env.getProperty("kaleris.hackathon.start"));
		request.getSession().setAttribute("end_date", env.getProperty("kaleris.hackathon.end"));
		
		return "index.jsp";
		
	}
	
	
	@RequestMapping({"/reset", "/stop"})
	private RedirectView reset(HttpServletRequest request) throws ParseException {
		service.clearEvents();
		return new RedirectView("/");
	}
	
	
	@PostMapping({"/update-flag"})
	private ResponseEntity<String> update(EventStatus status) throws ParseException {
		EventStatus returnInstance = service.saveEventStatus(status);
		if (returnInstance.getId() == null) {
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Event Already Started!");
		} else {
			return ResponseEntity.ok("Success!");
		}
		
	}
	

}
