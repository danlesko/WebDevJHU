package com.dalesko.bhc.restAPI;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.dalesko.bhc.HikeInfo;
import com.google.gson.*;

@Path("bhc")
public class BHCEndpoint {
	
	// Create HTML strings to return to client
	private String tableHtml = "<table>\n" + 
			"	<tr>\n" + 
			"		<td>Hike</td>\n" + 
			"		<td>%s</td>\n" + 
			"	</tr>\n" + 
			"	<tr>\n" + 
			"		<td>Start Date</td>\n" + 
			"		<td>%s</td>\n" + 
			"	</tr>\n" + 
			"	<tr>\n" + 
			"		<td>Duration</td>\n" + 
			"		<td>%s</td>\n" + 
			"	</tr>\n" + 
			"	<tr>\n" + 
			"		<td>People</td>\n" + 
			"		<td>%s</td>\n" + 
			"	</tr>\n" + 
			"	<tr>\n" + 
			"		<td>Cost</td>\n" + 
			"		<td>%s</td>\n" + 
			"	</tr>\n" + 
			"	<tr>\n" + 
			"		<td>Error Message</td>\n" + 
			"		<td>%s</td>\n" + 
			"	</tr>\n" + 
			"</table>";
	
	private String htmlStart = "<!DOCTYPE html>\n" + 
			"<html>\n" + 
			"<head>\n" + 
			"<style>\n" + 
			"h2 {\n" + 
			"  font-family: arial, sans-serif;\n" + 
			"}\n" + 
			"table {\n" + 
			"  font-family: arial, sans-serif;\n" + 
			"  border-collapse: collapse;\n" + 
			"  width: 100%;\n" + 
			"}\n" + 
			"\n" + 
			"td, th {\n" + 
			"  border: 1px solid black;\n" + 
			"  text-align: left;\n" + 
			"  padding: 8px;\n" + 
			"}\n" + 
			"\n" + 
			"tr:nth-child(even) {\n" + 
			"  background-color: #dddddd;\n" + 
			"}\n" + 
			"</style>\n" + 
			"</head>\n" + 
			"<body>\n" + 
			"\n" + 
			"<h2>Rate Calculation</h2>";
	
	private String htmlEnd = "</body>\n" + 
			"</html>";

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("what")
	public String getText() {
		return "SUCCESSFUL OUTPUT!";
	}
	
	@POST
	@Path("/getHikeInfoJSONAcceptJSON")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public HikeInfo setHike(HikeInfo hikeInfo) {
		
		// Because this function consumes a hikeInfo JSON, the bean is saturate upon call, so we check the values inside the bean instead of params
		if (hikeInfo.getHike() == null && hikeInfo.getStartDate() == null && hikeInfo.getDuration() == null && hikeInfo.getPeople() == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        } else {
        	hikeInfo.setErrMsg(null);
        }
        
        // Ask user to enter information for their hike if any of these parameters are empty
        // Need to do this in order to call the set calls below
        if (hikeInfo.getHike() == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        if (hikeInfo.getStartDate() == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        if (hikeInfo.getDuration() == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        if (hikeInfo.getPeople() == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        
        // Hydrate the bean
        if(hikeInfo.getErrMsg() == null) {
            hikeInfo.processRequest();
        }
		
        // Super cool library that converts a POGO/Bean into a JSON
		return hikeInfo;
	}
	
	// GET function that returns hike information as JSON
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getHikeInfoJSON")
	public HikeInfo getHikeInfoJSON(@QueryParam("hike") String hike, @QueryParam("startDate") String startDate, 
			@QueryParam("duration") String duration, @QueryParam("people") String people,
			@QueryParam("json") String json) {
		
		HikeInfo hikeInfo = new HikeInfo();
		
		if (hike == null && startDate == null && duration == null && people == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        } else {
        	hikeInfo.setErrMsg(null);
        }
        
        // Ask user to enter information for their hike if any of these parameters are empty
        // Need to do this in order to call the set calls below
        if (hike == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        if (startDate == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        if (duration == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        if (people == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        
        // Hydrate the bean
        if(hikeInfo.getErrMsg() == null) {
            hikeInfo.setHike(hike);
            hikeInfo.setStartDate(startDate);
            hikeInfo.setDuration(duration);
            hikeInfo.setPeople(people);
            
            hikeInfo.processRequest();
        }
		
        // Return hikeInfo, jackson will return it automatically as a JSON
		return hikeInfo;
		
	}
	
	// GET request that returns hike information as an HTML table
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/getHikeInfoHTML")
	public String getHikeInfo(@QueryParam("hike") String hike, @QueryParam("startDate") String startDate, 
			@QueryParam("duration") String duration, @QueryParam("people") String people) {
		
		HikeInfo hikeInfo = new HikeInfo();
		
		if (hike == null && startDate == null && duration == null && people == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        } else {
        	hikeInfo.setErrMsg(null);
        }
        
        // Ask user to enter information for their hike if any of these parameters are empty
        // Need to do this in order to call the set calls below
        if (hike == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        if (startDate == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        if (duration == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        if (people == null) {
        	hikeInfo.setErrMsg("Please enter information for your hike.");
        }
        
        // Hydrate the bean
        if(hikeInfo.getErrMsg() == null) {
            hikeInfo.setHike(hike);
            hikeInfo.setStartDate(startDate);
            hikeInfo.setDuration(duration);
            hikeInfo.setPeople(people);
            
            hikeInfo.processRequest();
        }
		
        // Return HTML table to display results of query
		return htmlStart + String.format(tableHtml, hikeInfo.getHike(), hikeInfo.getStartDate(), hikeInfo.getDuration(), hikeInfo.getPeople(), hikeInfo.getCost(), hikeInfo.getErrMsg()) + htmlEnd;
	}
	

}
