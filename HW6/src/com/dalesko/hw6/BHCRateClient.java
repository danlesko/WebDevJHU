/*
 * GetHeader.java
 * 
 * Created on Oct 15, 2007
 * Updated to Eclipse on June 22, 2019
 */

package com.dalesko.hw6;

import java.net.*;
import java.io.*;

/**
 *
 * Sample program NetworkClient.java modified for HW6 to contact the "Beartooth Hiking Company's" Rate Calculator Server
 */
public class BHCRateClient extends NetworkClient {
	private int hike_id, begin_year, begin_month, begin_day, duration = 0;
	String response = null;

	// Constructor that accepts hike ID, date (year, month, day), and duration
	public BHCRateClient(int hike_id, int begin_year, int begin_month, int begin_day, int duration) {
		// Static for the BHC
		super("web6.jhuep.com", 20025);
		this.hike_id = hike_id;
		this.begin_year = begin_year;
		this.begin_month = begin_month;
		this.begin_day = begin_day;
		this.duration = duration;
	}

	@Override
	protected void handleConnection(Socket uriSocket) throws IOException {
		String line;
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(uriSocket.getInputStream()));
			out = new PrintWriter(uriSocket.getOutputStream(), true);
			out.println(Integer.toString(hike_id)+":" +Integer.toString( begin_year)+":"
					+Integer.toString(begin_month)+":"+Integer.toString(begin_day)+":"
					+Integer.toString(duration));
			while ((line = in.readLine()) != null && line.length() != 0) {
				setResponse(line);
			}
		} catch (IOException ioe) {
			System.err.println("Problem in communicating with socket: " + ioe.getMessage());
		} finally {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		}
	}
	
	public String getResponse() {
		return this.response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
}
