package com.dalesko.hw12;


/** 
 * Bean for data returned from Hike query
 */
public class Reservation {
	private String first;
	private String last;
	private String startDay;
	private String location;
	private String guideFirst;
	private String guideLast;
	
	/** 
	 * Setters and getters for all member variables as defined to spec for JavaBeans
	 */
	public String getFirst() {
		return first;
	}
	public void setFirst(String first) {
		this.first = first;
	}
	public String getLast() {
		return last;
	}
	public void setLast(String last) {
		this.last = last;
	}
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGuideFirst() {
		return guideFirst;
	}
	public void setGuideFirst(String guideFirst) {
		this.guideFirst = guideFirst;
	}
	public String getGuideLast() {
		return guideLast;
	}
	public void setGuideLast(String guideLast) {
		this.guideLast = guideLast;
	}

}
