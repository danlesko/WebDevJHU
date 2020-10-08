/*
 * NetworkClient.java
 * 
 * Created on Oct 14, 2007
 * Updated for eclipse on June 22, 2019
 */

package com.dalesko.hw6;

import java.net.*;
import java.io.*;

/**
 * Sample code to demonstrate handling basic http requests
 */
public class NetworkClient {

	private final String host;
	private final int port;

	public NetworkClient(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public void connect() {
		try {
			Socket client = new Socket(host, port);
			handleConnection(client);
			close(client);
		} catch (UnknownHostException uhe) {
			System.out.println("Unknown host: " + host);
			uhe.printStackTrace();
		} catch (IOException ioe) {
			System.out.println("IOException on: " + host);
			ioe.printStackTrace();
		}
	}

	public void close(Socket client) throws IOException {
		if (client != null)
			client.close();
	}

	protected void handleConnection(Socket client) throws IOException {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new PrintWriter(client.getOutputStream(), true);
			System.out.println("NetworkClient.handleConnection():\n" + "Made connection to host " + host + " and got "
					+ in.readLine() + " in response");
		} finally {
			if (out != null)
				out.close();
			if (in != null)
				in.close();
		}
	}

}