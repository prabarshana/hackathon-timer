package com.shipxpress.kaleris.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "EventStatus")
public class EventStatus {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String flag;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "EventStatus [id=" + id + ", flag=" + flag + ", timeStamp=" + timeStamp + "]";
	}

	
}
