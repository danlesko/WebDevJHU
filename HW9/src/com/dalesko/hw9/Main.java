/*
 * Main.java
 * 
 * Created on Oct 13, 2007
 * Updated on June 22, 2019 for eclipse
 */

package com.dalesko.hw9;
import java.io.*;
import java.net.*;

/**
 *
 * Main class for creating a socket server and generating 
 * threads which are clients for socket
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        
        try {
            serverSocket = new ServerSocket(20013);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 20013.");
            System.exit(1);
        }

        Socket clientSocket = null;
        while (true) {
            clientSocket = serverSocket.accept();
            ClientThread thread = new ClientThread(clientSocket);
            thread.start();
        }
    }
}