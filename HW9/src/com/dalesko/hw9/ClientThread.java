/*
 * ClientThread.java
 * 
 * Created on Nov 4, 2007
 * Updated on Oct 28, 2020 for HW9 
 */


package com.dalesko.hw9;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.dalesko.hw9.Rates.HIKE;

/**
 * Generate a threaded client to a local server
 */
public class ClientThread extends Thread {

	// Initialize member variables
    private final Socket socket;
    // Assign client socket to socket in constructor
    public ClientThread(Socket clientSocket) {
        this.socket = clientSocket;
    }
    
    // Start the thread execution
    public void run () {
        BufferedReader in = null;
        PrintWriter out = null;
        String hike = null;
        String duration = null;
        
		
		// Initialize Date variables
		Date jStartDate = null;
		
		// Initialize BookingDay variables
		BookingDay startDay = null;
		BookingDay endDay = null;
        
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputLine = null;
            while (!socket.isClosed()) {
            	
            	// The string to be 
                inputLine = in.readLine();
                if (inputLine == null) {
                	out.println("-0.01:Input is null");
                    break;
                }
                
                // Create tokens from the input string
                String tokens[] = inputLine.split(":");
                if (tokens.length != 5) {
                	out.println("-0.01:Invalid parameters passed");
                    break;
                }
                
                // Initialize hike, duration, and startDate
                hike = tokens[0];
                duration = tokens[4];
                
                try {
                	jStartDate = new SimpleDateFormat("yyyy-MM-dd").parse(tokens[1]+"-"+tokens[2]+"-"+tokens[3]);
                } catch (ParseException e) {
                	out.println("-0.01:Invalid date format");
                    break;
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
    	    			out.println("-0.01:Error in start date");
                        break;
    	    		}
    	    		
    	    		try {
    	    			endDay = new BookingDay(endDate.getYear()+1900,endDate.getMonth()+1,endDate.getDate());
    	    		} catch (NullPointerException e2) {
    	    			out.println("-0.01:Error in end date");
                        break;
    	    		}
    	    	    
    	    	    // Create a new Rates object and instantiate it with the type of hike chosen
    	    	    Rates rates = new Rates(HIKE.values()[Integer.parseInt(hike)]);
    	    		rates.setBeginDate(startDay);
    	    		rates.setEndDate(endDay);
    	            boolean success = rates.setDuration(Integer.parseInt(duration));
    	            
    	            // If not valid dates, show details 
    	            if(isFuture != 1) {
    	            	out.println("-0.01:Start date not in the future");
                        break;
    	            }
    	            else if(jStartDate.getYear()+1900 > 2025) {
    	            	out.println("-0.01:Start date too far in the future (2025)");
                        break;
    	            }
    	            else if(!rates.isValidDates()) {
    	            	out.println("-0.01:Invalid dates");
                        break;
    	            } 
    	            // If not success, show the error dialog, otherwise append hike information to the notificationStr
    	            else if (!success) {
    	            	out.println("-0.01:Invalid duration");
                        break;
    	            }
    	            else {
    	            	out.println(rates.getCost()+":Quoted Rate");
                        break;
    	    		}
                }
                
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
