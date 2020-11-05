/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dalesko.hw10;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dalesko.hw10.Rates.HIKE;


/**
 *
 * @author dalesko
 */
@WebServlet("/BHCController")	
public class BHCController extends HttpServlet {
    /** 
    * Processes requests for  HTTP <code>GET</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext servletContext = getServletContext();
        RequestDispatcher dispatcher = null;
        HikeInfo hikeInfo = new HikeInfo();
        
        String hike = null;
        String startDate = null;
        String duration = null;
        String people = null;
        
        // Initialize errStr
		String errStr = "";
		
		// Initialize Date variables
		Date jStartDate = null;
		
		// Initialize BookingDay variables
		BookingDay startDay = null;
		BookingDay endDay = null;
        
     	
        	
        hike = request.getParameter("hike");
        if (hike == null) {
        	hike = "1";
        }
        startDate = request.getParameter("startDate");
        if (startDate == null) {
        	startDate = "2020-04-20";
        }
        duration = request.getParameter("duration");
        if (duration == null) {
        	duration = "3";
        }
        
        people = request.getParameter("people");
        if (people == null) {
        	people = "1";
        }
        
        System.out.println(startDate);
        
        try {
        	jStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        } catch (ParseException e) {
        	errStr = errStr.concat("Error parsing date! Please enter date in yyyy-MM-dd format if your browser does not support the input date type." + "\n");
        	dispatcher = servletContext.getRequestDispatcher("/error.jsp");
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
    			errStr = errStr.concat("Error in start day" + "\n");
    			dispatcher = servletContext.getRequestDispatcher("/error.jsp");
    		}
    		
    		try {
    			endDay = new BookingDay(endDate.getYear()+1900,endDate.getMonth()+1,endDate.getDate());
    		} catch (NullPointerException e2) {
    			errStr = errStr.concat("Error in End Day: "+ "\n");
    			dispatcher = servletContext.getRequestDispatcher("/error.jsp");
    		}
    	    
    	    // Create a new Rates object and instantiate it with the type of hike chosen
    	    Rates rates = new Rates(HIKE.values()[Integer.parseInt(hike)-1]);
    		rates.setBeginDate(startDay);
    		rates.setEndDate(endDay);
            boolean success = rates.setDuration(Integer.parseInt(duration));
            
            // If not valid dates, show details 
            if(isFuture != 1) {
            	errStr = errStr.concat(
    				    "The start date is not in the future! \n");
            	dispatcher = servletContext.getRequestDispatcher("/error.jsp");
            }
            else if(jStartDate.getYear()+1900 > 2025) {
            	errStr = errStr.concat(
    				    "You may not set a date that far in the future! (After 2025) \n");
            	dispatcher = servletContext.getRequestDispatcher("/error.jsp");
            }
            else if(!rates.isValidDates()) {
            	errStr = errStr.concat(
    				    "The dates chosen are invalid! " + rates.getDetails() + "\n");
            	dispatcher = servletContext.getRequestDispatcher("/error.jsp");
            } 
            // If not success, show the error dialog, otherwise append hike information to the errStr
            else if (!success) {
            	errStr = errStr.concat(
    				    "Your duration is invalid!\n");
            	dispatcher = servletContext.getRequestDispatcher("/error.jsp");
            }
            else {
            	// Only show results if we have success
            	dispatcher = servletContext.getRequestDispatcher("/results.jsp");
    		}
            
            // If an error message has been set, set the bean
            if(!errStr.equals("")) {
            	hikeInfo.setErrStr(errStr);
            	System.out.println(hikeInfo.getErrStr());
            }
        }
        
        dispatcher.forward(request, response);
    } 

    /** 
    * Handles the HTTP <code>POST</code> method.
    * @param request servlet request
    * @param response servlet response
    */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    /** 
    * Returns a short description of the servlet.
    */
    public String getServletInfo() {
        return "Short description";
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
