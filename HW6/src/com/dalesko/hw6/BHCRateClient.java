package com.dalesko.hw6;

import java.net.*;
import java.io.*;

/**
 *
 * NetworkClient.java extended for HW6 to contact the "Beartooth Hiking Company's" Rate Calculator Server
 */
public class BHCRateClient extends NetworkClient {
	private int hike_id, begin_year, begin_month, begin_day, duration = 0;
	String response = null;

	// Constructor that accepts hike ID, date (year, month, day), and duration
	public BHCRateClient(int hike_id, int begin_year, int begin_month, int begin_day, int duration) {
		// Static for the BHC
		super("web6.jhuep.com", 20013);
		this.hike_id = hike_id;
		this.begin_year = begin_year;
		this.begin_month = begin_month;
		this.begin_day = begin_day;
		this.duration = duration;
	}

	// Override handleConnection to pass hike information to socket 
	@Override
	protected void handleConnection(Socket uriSocket) throws IOException {
		// Initialize variables
		String line;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			// Create IO for socket
			in = new BufferedReader(new InputStreamReader(uriSocket.getInputStream()));
			out = new PrintWriter(uriSocket.getOutputStream(), true);
			
			// Send hike query 
			out.println(Integer.toString(hike_id)+":" +Integer.toString( begin_year)+":"
					+Integer.toString(begin_month)+":"+Integer.toString(begin_day)+":"
					+Integer.toString(duration));
			
			// Read in hike query
			while ((line = in.readLine()) != null && line.length() != 0) {
				setResponse(line);
			}
		} catch (IOException ioe) {
			// Handle error
			System.err.println("Problem in communicating with socket: " + ioe.getMessage());
		} finally {
			// Close streams
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		}
	}
	
	// Get response
	public String getResponse() {
		return this.response;
	}
	
	// Set response
	public void setResponse(String response) {
		this.response = response;
	}
}
