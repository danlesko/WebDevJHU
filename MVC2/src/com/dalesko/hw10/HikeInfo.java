/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dalesko.hw10;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dalesko.hw10.Rates.HIKE;

/**
 *
 * @author spiegel
 */
public class HikeInfo {
    private String hike = null;
    private String startDate = null;
    private String duration = null;
    private String people = null;
    private String errMsg = null;
    private String cost = null;
    
    private boolean success = false;
    
    public void processRequest() {
    	
		// Initialize Date variables
		Date jStartDate = null;
		
		// Initialize BookingDay variables
		BookingDay startDay = null;
		BookingDay endDay = null;
    	
        try {
        	jStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        } catch (ParseException e) {
        	setErrMsg("Error parsing date! Please enter date in yyyy-MM-dd format if your browser does not support the input date type." + "\n");
		} 
        
        if(jStartDate != null) {
    		Calendar calStartDate = toCalendar(jStartDate);
    		calStartDate.add(Calendar.DAY_OF_MONTH, Integer.parseInt(duration));
    		Date endDate = calStartDate.getTime();
    		Date currentD = new Date();
    		Calendar currentDate = toCalendar(currentD);
    		int isFuture = calStartDate.compareTo(currentDate);
    		
    		// Parse the start and end date fields
    		try {
    			startDay = new BookingDay(jStartDate.getYear()+1900,jStartDate.getMonth()+1,jStartDate.getDate());
    		} catch (NullPointerException e1) {
    			setErrMsg("Error in start day" + "\n");
    		}
    		
    		try {
    			endDay = new BookingDay(endDate.getYear()+1900,endDate.getMonth()+1,endDate.getDate());
    		} catch (NullPointerException e2) {
    			setErrMsg("Error in End Day: "+ "\n");
    		}
    	    
    	    // Create a new Rates object and instantiate it with the type of hike chosen
    	    Rates rates = new Rates(HIKE.values()[Integer.parseInt(hike)-1]);
    		rates.setBeginDate(startDay);
    		rates.setEndDate(endDay);
            boolean isSuccess = rates.setDuration(Integer.parseInt(duration));
            
            // If not valid dates, show details 
            if(isFuture != 1) {
            	setErrMsg(
    				    "The start date is not in the future! \n");
            }
            else if(jStartDate.getYear()+1900 > 2025) {
            	setErrMsg(
    				    "You may not set a date that far in the future! (After 2025) \n");
            }
            else if(!rates.isValidDates()) {
            	setErrMsg(
    				    "The dates chosen are invalid! " + rates.getDetails() + "\n");
            } 
            // If not success, show the error dialog, otherwise append hike information to the errStr
            else if (!isSuccess) {
            	setErrMsg("Your duration is invalid!\n");
            }
            else {
            	setSuccess(success);
            	setCost(rates.getCost());
            }
            
        }
    	
    }
    
    public void setCost(double cost) {
    	Double pNum = Double.valueOf(people);
    	double totalCost = cost * pNum;
    	this.cost = String.valueOf(totalCost);
    }
    
    public String getCost() {
    	return cost;
    }
    
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
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
    	this.success = success;
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

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	/** Gets index of a string in an array of strings
    *
    * @param date to be converted
    */
	public static Calendar toCalendar(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	}
}
