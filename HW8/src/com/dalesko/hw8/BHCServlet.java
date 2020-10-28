/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dalesko.hw8;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dalesko.hw8.Rates.HIKE;


@WebServlet(name = "BHCServlet", urlPatterns = {"/BHCServlet"})
public class BHCServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Create array of hike names for easy reference later
	private static String[] hikeNames = {"Gardiner Lake", "Hellroaring Plateau", "The Beaten Path"};
	
	// Create top of HTML doc str
	private static String htmlSetup = "<!doctype html>\n" + 
			"\n" + 
			"<html lang=\"en\">";
	
	// Create HTML head str
	private static String htmlHead = "<head>\n" + 
			"    <link rel=\"shortcut icon\" href=\"images/logo.png\" type=\"image/x-icon\">\n" + 
			"    <meta charset=\"utf-8\">\n" + 
			"    <!-- CSS only -->\n" + 
			"    <link rel=\"stylesheet\" href=\"css/styles.css\">\n" + 
			"    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css\" integrity=\"sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z\" crossorigin=\"anonymous\">\n" + 
			"\n" + 
			"    <!-- JS, Popper.js, and jQuery -->\n" + 
			"    <script src=\"https://code.jquery.com/jquery-3.5.1.slim.min.js\" integrity=\"sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj\" crossorigin=\"anonymous\"></script>\n" + 
			"    <script src=\"https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js\" integrity=\"sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN\" crossorigin=\"anonymous\"></script>\n" + 
			"    <script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js\" integrity=\"sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV\" crossorigin=\"anonymous\"></script>\n" + 
			"<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>\n" + 
			"    <script src=\"js/scripts.js\"></script>\n" +
			"    <title>The Beartooth Hiking Company (BHC)</title>\n" + 
			"    <meta name=\"description\" content=\"The Beartooth Hiking Company (BHC)\">\n" + 
			"\n" + 
			"</head>";
	
	// Create HTML navbar str
	private static String htmlNav = "<nav class=\"navbar navbar-expand-sm bg-dark navbar-dark fixed-top\">\n" + 
			"    <a class=\"navbar-brand\" href=\"#\"><img class=\"header-image\" src=\"images/logo.png\" alt=\"Beartooth\"/></a>\n" + 
			"    <ul class=\"navbar-nav\">\n" + 
			"        <li class=\"nav-item\">\n" + 
			"            <a class=\"nav-link\" href=\"./\">Home</a>\n" + 
			"        </li>\n" + 
			"        <li class=\"nav-item\">\n" + 
			"            <a class=\"nav-link\" href=\"https://www.nps.gov/findapark/index.htm\" target=\"_blank\">Find Other Parks</a>\n" + 
			"        </li>\n" + 
			"        <li class=\"nav-item\">\n" + 
			"            <a class=\"nav-link\" href=\"https://www.fs.usda.gov/recarea/shoshone/recarea/?recid=80899\" target=\"_blank\">More Info</a>\n" + 
			"        </li>\n" + 
			"    </ul>\n" + 
			"</nav>";
	
	// Create html table str
	private static String tableHtml = 
			"    <h3>Tour Options</h3>\n" + 
			"    <div class=\"table-size\">\n" + 
			"        <table class=\"table table-bordered table-striped table-responsive-md\" >\n" + 
			"            <thead>\n" + 
			"            <tr>\n" + 
			"                <th></th>\n" + 
			"                <th>Duration</th>\n" + 
			"                <th>Intensity</th>\n" + 
			"                <th>Pricing Per Day</th>\n" + 
			"            </tr>\n" + 
			"            </thead>\n" + 
			"            <tbody>\n" + 
			"            <tr class=\"table-warning\">\n" + 
			"                <th>Gardiner Lake</th>\n" + 
			"                <td>3 or 5</td>\n" + 
			"                <td>Intermediate</td>\n" + 
			"                <td>$40</td>\n" + 
			"            </tr>\n" + 
			"            <tr class=\"table-info\">\n" + 
			"                <th>Hellroaring Plateau</th>\n" + 
			"                <td>2, 3, or 5</td>\n" + 
			"                <td>Easy</td>\n" + 
			"                <td>$35</td>\n" + 
			"            </tr>\n" + 
			"            <tr class=\"table-danger\">\n" + 
			"                <th>The Beaten Path</th>\n" + 
			"                <td>5 or 7</td>\n" + 
			"                <td>Difficult</td>\n" + 
			"                <td>$45</td>\n" + 
			"            </tr>\n" + 
			"            </tbody>\n" + 
			"            <tfoot>\n" + 
			"            <tr class=\"table-primary\">\n" + 
			"                <td colspan=4 class=\"table-footer-note\">Note: All hikes have a 50% surcharge for Sat/Sun hikes.</td>\n" + 
			"            </tr>\n" + 
			"            </tfoot>\n" + 
			"        </table>\n" + 
			"    </div>" +
			"<h5>Rate Calculator</h5>";

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
        String people = null;
        
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
            
            try {
            	jStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            } catch (ParseException e) {
            	notificationStr = notificationStr.concat("Error parsing date! Please enter date in yyyy-MM-dd format if your browser does not support the input date type." + "\n<br />");
    		} 
            
            try {
            	getSelectedHike(hike);
            } catch (IndexOutOfBoundsException e) {
            	hike = "1";
            	notificationStr = notificationStr.concat("Failure to parse entered hike data. Setting to default."+ "\n");
            } catch (NumberFormatException e) {
            	hike = "1";
            	notificationStr = notificationStr.concat("Failure to parse entered hike data. Setting to default."+ "\n");
            }
            
            try {
            	getSelectedDuration(duration);
            } catch (IndexOutOfBoundsException e) {
            	duration = "1";
            	notificationStr = notificationStr.concat("Failure to parse entered duration data. Setting to default."+ "\n");
            } catch (NumberFormatException e) {
            	duration = "1";
            	notificationStr = notificationStr.concat("Failure to parse entered duration data. Setting to default."+ "\n");
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
	            if(isFuture != 1) {
	            	notificationStr = notificationStr.concat(
	    				    "The start date is not in the future! \n<br />");
	            }
	            else if(jStartDate.getYear()+1900 > 2025) {
	            	notificationStr = notificationStr.concat(
	    				    "You may not set a date that far in the future! (After 2025) \n<br />");
	            }
	            else if(!rates.isValidDates()) {
	            	notificationStr = notificationStr.concat(
	    				    "The dates chosen are invalid! " + rates.getDetails() + "\n<br />");
	            } 
	            // If not success, show the error dialog, otherwise append hike information to the notificationStr
	            else if (!success) {
	            	notificationStr = notificationStr.concat(
	    				    "Your duration is invalid!" + "\n<br />");
	            }
	            else {
	            	notificationStr = notificationStr.concat("Chosen Hike: " + hikeNames[Integer.parseInt(hike)-1] + "<br>");
	            	notificationStr = notificationStr.concat("Start Day: " + startDay + "\n<br />");
	            	notificationStr = notificationStr.concat("End Day: " + endDay + "\n<br />");
	            	notificationStr = notificationStr.concat("Weekdays: " + rates.getNormalDays() + " * $" + rates.getBaseRate() + " = $" + rates.getNormalDays()*rates.getBaseRate() +"<br>");
	            	notificationStr = notificationStr.concat("Weekends: " + rates.getPremiumDays() + " * $" + rates.getPremiumRate() + " = $" + rates.getPremiumDays()*rates.getPremiumRate() + "<br>");
	            	notificationStr = notificationStr.concat("Total Days: " + Integer.parseInt(duration) + "<br>");
	            	notificationStr = notificationStr.concat("Costs per person: $" + rates.getCost() + "<br>");
	            	notificationStr = notificationStr.concat("Number of hikers: <span id=\"numHikers\"></span><br>");
	            	notificationStr = notificationStr.concat("Total: $<span id=\"totalCost\">" + rates.getCost() + "</span><br>");
	    		}
            }
            
            // Format the HTML to be sent as a response. 
            out.println(htmlSetup);
            out.println(htmlHead);
            out.println("<body>");
            out.println(htmlNav);
            out.println("<div class=\"main container-fluid\" style=\"margin-top:120px\">");
            out.println(tableHtml);
            out.println(getHtmlForm(hike,startDate,duration,people));
            out.println("<br />");
            out.println("<div id=\"results\">");
            out.println(notificationStr);
            out.println("</div>");
            out.println("</div>");
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
    public String getHtmlForm(String hike, String date, String duration, String people) {
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
    			"		  <select name=\"duration\" id=\"duration\">\n" + 
    			getSelectedDuration(duration) +
    			"		  </select>\n" + 
    			"		<br /> \n" + 
    			"		\n" +"<label for=\"people\">Number of hikers:</label>\n" + 
    			"<select name=\"people\" id=\"people\">\n" +
    			getSelectedPeople(people) +
    					"		  </select>\n" + 
    					"		<br /> " +
    			"		<input type=\"SUBMIT\">\n" + "<input type=\"reset\">\n" + 
    			"</form>", date);
    	return formHtml;
    }
    
    /** Gets options for selected hike
    *
    * @param hike the selected hike
    */
    public String getSelectedPeople(String people) throws IndexOutOfBoundsException, NumberFormatException {
    	Integer peopleIndex = Integer.parseInt(people) - 1;
    	String selectedStr = "selected=\"selected\" ";
    	String[] values = {"<option value=\"1\">1</option>\n",
    			"<option value=\"2\">2</option>\n",
    			"<option value=\"3\">3</option>\n",
    			"<option value=\"4\">4</option>\n",
    			"<option value=\"5\">5</option>\n",
    			"<option value=\"6\">6</option>\n",
    			"<option value=\"7\">7</option>\n",
    			"<option value=\"8\">8</option>\n",
    			"<option value=\"9\">9</option>\n",
    			"<option value=\"10\">10</option>\n",};

    	values[peopleIndex] = values[peopleIndex].substring(0,8) + selectedStr + values[peopleIndex].substring(8,values[peopleIndex].length());

    	return String.join("", values);
    }
    
    
    /** Gets options for selected hike
    *
    * @param hike the selected hike
    */
    public String getSelectedHike(String hike) throws IndexOutOfBoundsException, NumberFormatException {
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
    public String getSelectedDuration(String duration) throws IndexOutOfBoundsException, NumberFormatException {
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
