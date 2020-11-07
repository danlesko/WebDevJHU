package com.dalesko.hw10;

import java.util.Date;

public class HikeInfo {
	private String hike = null;
    private String startDate = null;
    private String duration = null;
    private String people = null;
    private String errStr = null;
	
	// Initialize Date variables
	Date jStartDate = null;
	
	// Initialize BookingDay variables
	BookingDay startDay = null;
	BookingDay endDay = null;
    
	public String getHike() {
		return hike;
	}
	public void setHike(String hike) {
		this.hike = hike;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public String getErrStr() {
		return errStr;
	}
	public void setErrStr(String errStr) {
		this.errStr = errStr;
	}
}
