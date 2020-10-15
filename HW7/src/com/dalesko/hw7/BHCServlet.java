/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dalesko.hw7;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dalesko.hw7.Rates.HIKE;


@WebServlet(name = "BHCServlet", urlPatterns = {"/BHCServlet"})
public class BHCServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String[] hikeNames = {"Gardiner Lake", "Hellroaring Plateau", "The Beaten Path"};
	
	private static String tableHtml = "<h3>Hiking Tours</h3>\n" + 
			"    <ul>\n" + 
			"        <li>Gardiner Lake</li>\n" + 
			"        <li>Hellroaring Plateau</li>\n" + 
			"        <li>The Beaten Path</li>\n" + 
			"    </ul>\n" + 
			"    <br/>\n" + 
			"    <h3>Tour Options</h3>\n" + 
			"    <table border=2 cellspacing=3 cellpadding=3 rules=groups>\n" + 
			"        <colgroup span=2></colgroup>\n" + 
			"        <colgroup span=2></colgroup>\n" + 
			"        <thead>\n" + 
			"        <tr>\n" + 
			"            <td colspan=2 rowspan=2></td>\n" + 
			"            <th colspan=2 align=center>Options</th>\n" + 
			"            <th colspan=2 ></th>\n" + 
			"        </tr>\n" + 
			"        <tr>\n" + 
			"            <th>Duration</th>\n" + 
			"            <th>Intensity</th>\n" + 
			"            <th colspan=2 align=center>Pricing Per Day</th>\n" + 
			"        </tr>\n" + 
			"        </thead>\n" + 
			"        <tbody>\n" + 
			"        <tr align=center>\n" + 
			"          <th rowspan=4></th>\n" + 
			"          <th>Gardiner Lake</th>\n" + 
			"          <td>3 or 5</td>\n" + 
			"          <td>Intermediate</td>\n" + 
			"          <td>$40</td>\n" + 
			"          <td></td>\n" + 
			"        </tr>\n" + 
			"        <tr align=center>\n" + 
			"          <th>Hellroaring Plateau</th>\n" + 
			"          <td>2, 3, or 5</td>\n" + 
			"          <td>Easy</td>\n" + 
			"          <td>$35</td>\n" + 
			"          <td></td>\n" + 
			"        </tr>\n" + 
			"        <tr align=center>\n" + 
			"          <th>The Beaten Path</th>\n" + 
			"          <td>5 or 7</td>\n" + 
			"          <td>Difficult</td>\n" + 
			"          <td>$45</td>\n" + 
			"          <td></td>\n" + 
			"        </tr>\n" + 
			"        </tbody>\n" + 
			"        <tfoot>\n" + 
			"        <tr>\n" + 
			"            <td colspan=6 align=center>Note: All hikes have a 50% surcharge for Sat/Sun hikes.</td>\n" + 
			"        </tr>\n" + 
			"        </tfoot>\n" + 
			"    </table>";

	/**
     * Processes requests for HTTP <code>GET</code> method
     * 
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String hike = null;
        String startDate = null;
        String duration = null;
        
        // Initialize notificationStr
		String notificationStr = "";
		
		// Initialize Date variables
		Date jStartDate = null;
		
		// Initialize BookingDay variables
		BookingDay startDay = null;
		BookingDay endDay = null;
        
        try {      	
        	
            hike = request.getParameter("hike");
            if (hike == null) {
            	hike = "<none entered>";
            }
            startDate = request.getParameter("startDate");
            if (startDate == null) {
            	startDate = "<none entered>";
            }
            duration = request.getParameter("duration");
            if (duration == null) {
            	duration = "<none entered>";
            }
            
            try {
            	jStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            } catch (ParseException e) {
            	notificationStr = notificationStr.concat("Error parsing date! Please enter date in yyyy-MM-dd format if your browser does not support the input date type." + "\n<br />");
    		} 
            
            if(jStartDate != null) {
	    		Calendar calStartDate = toCalendar(jStartDate);
	    		calStartDate.add(Calendar.DAY_OF_MONTH, Integer.parseInt(duration));
	    		Date endDate = calStartDate.getTime();
	    		
	    		// Parse the start and end date fields
	    		try {
	    			startDay = new BookingDay(jStartDate.getYear()+1900,jStartDate.getMonth()+1,jStartDate.getDate());
	    		} catch (NullPointerException e1) {
	    			notificationStr = notificationStr.concat("Error in start day" + "\n");
	    		}
	    		
	    		try {
	    			endDay = new BookingDay(endDate.getYear()+1900,endDate.getMonth()+1,endDate.getDate());
	    		} catch (NullPointerException e2) {
	    			notificationStr = notificationStr.concat("Error in End Day: "+ "\n");
	    		}
	    	    
	    	    // Create a new Rates object and instantiate it with the type of hike chosen
	    	    Rates rates = new Rates(HIKE.values()[Integer.parseInt(hike)-1]);
	    		rates.setBeginDate(startDay);
	    		rates.setEndDate(endDay);
	            boolean success = rates.setDuration(Integer.parseInt(duration));
	            
	            // If not valid dates, show details 
	            if(!rates.isValidDates()) {
	            	notificationStr = notificationStr.concat(
	    				    "The dates chosen are invalid! " + rates.getDetails() + "\n<br />");
	            } 
	            // If not success, show the error dialog, otherwise append hike information to the notificationStr
	            else if (!success) {
	            	notificationStr = notificationStr.concat(
	    				    "The duration chosen is invalid!" + "\n<br />");
	            }
	            else {
	            	notificationStr = notificationStr.concat("Chosen Hike: " + hikeNames[Integer.parseInt(hike)-1] + "<br>");
	            	notificationStr = notificationStr.concat("Start Day: " + startDay + "\n<br />");
	            	notificationStr = notificationStr.concat("End Day: " + endDay + "\n<br />");
	            	notificationStr = notificationStr.concat("Weekdays: " + rates.getNormalDays() + "<br>");
	            	notificationStr = notificationStr.concat("Weekends: " + rates.getPremiumDays() + "<br>");
	            	notificationStr = notificationStr.concat("Total Days: " + Integer.parseInt(duration) + "<br>");
	            	notificationStr = notificationStr.concat("Costs: $" + rates.getCost() + "<br>");
	    		}
            }
            
            // Format the HTML to be sent as a response. 
            out.println("<!doctype html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"utf-8\">");
            out.println("<title>The Beartooth Hiking Company (BHC)</title>");
            out.println("<meta name=\"description\" content=\"The Beartooth Hiking Company (BHC)\">");
            out.println("</head>");
            out.println("<body>");
            out.println(tableHtml);
            out.println("<br/>");
            out.println("<br/>");
            out.println(getHtmlForm(hike,startDate,duration));
            out.println("<form action=\"./\">");
            out.println("<input type=\"submit\" value=\"Reset\" />");
            out.println("</form>");
            out.println("<br/>");
            out.println(notificationStr);
            out.println("</body>");
            out.println("</html>");

        } finally { 
            out.close();
        }
   }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>POST</code> method.
     * Just defers it to GET
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    /** Gets HTML form string for easy parameterization
    *
    * @param hike the selected hike
    * @param date the selected date
    * @param duration the selected duration
    */
    public String getHtmlForm(String hike, String date, String duration) {
    	String formHtml = String.format("<form action=\"BHCServlet\" method=GET>\n" + 
    			"		<label for=\"hike\">Choose a hike:</label>\n" + 
    			"		  <select name=\"hike\" id=\"hike\">\n" + 
    			getSelectedHike(hike) + 
    			"		  </select>\n" + 
    			"		  <br/>\n" + 
    			"		<label for=\"start\">Start date:</label>\n" + 
    			"		<input type=\"date\" id=\"startDate\" name=\"startDate\"\n" + 
    			"		       value=\"%s\"\n" + 
    			"		       min=\"2020-01-01\" max=\"2025-12-31\">\n" + 
    			"		    <br/>   \n" + 
    			"		 <label for=\"duration\">Choose a duration:</label>\n" + 
    			"		  <select name=\"duration\" id=\"hikes\">\n" + 
    			getSelectedDuration(duration) +
    			"		  </select>\n" + 
    			"		<br /> \n" + 
    			"		\n" + 
    			"		<input type=\"SUBMIT\">\n" + 
    			"</form>", date);
    	return formHtml;
    }
    
    
    /** Gets options for selected hike
    *
    * @param hike the selected hike
    */
    public String getSelectedHike(String hike) {
    	Integer hikeIndex = Integer.parseInt(hike) - 1;
    	String selectedStr = "selected=\"selected\" ";
    	String[] values = {"<option value=\"1\">Gardiner Lake</option>",  
    			"<option value=\"2\">Hellroaring Plateau</option>",
    			"<option value=\"3\">The Beaten Path</option>"};

    	values[hikeIndex] = values[hikeIndex].substring(0,8) + selectedStr + values[hikeIndex].substring(8,values[hikeIndex].length());

    	return String.join("", values);
    }
    
    /** Gets options for select duration
    *
    * @param duration the duration of the hike
    */
    public String getSelectedDuration(String duration) {
    	Integer durationIndex = Integer.parseInt(duration) - 1;
    	String selectedStr = "selected=\"selected\"";
    	String[] values = {"<option value=\"1\">1</option>",  
    			"<option value=\"2\">2</option>",
    			"<option value=\"3\">3</option>",
    			"<option value=\"4\">4</option>",
    			"<option value=\"5\">5</option>",
    			"<option value=\"6\">6</option>",
    			"<option value=\"7\">7</option>",
    			"<option value=\"8\">8</option>",
    			"<option value=\"9\">9</option>"};
    	
    	values[durationIndex] = values[durationIndex].substring(0,8) + selectedStr + values[durationIndex].substring(8,values[durationIndex].length());

    	return String.join("", values);
    }
    
    /** Gets index of a string in an array of strings
    *
    * @param str the string to find
    * @param strs the array of strings
    */
	public int getIndex(String str, String[] strs) {
		int index = -1;
		for (int i=0;i<strs.length;i++) {
		    if (strs[i].equals(str)) {
		        index = i;
		        break;
		    }
		}
		return index;
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
