/*
 * GetURL.java
 * 
 * Originally created on Oct 15, 2007, 8:27:32 PM
 * Updated for eclipse on Oct 6, 2020
 * 
 */

package geturl;
import java.io.*;
import java.net.*;

/**
 * @author RF Spiegel
 * Program takes a URL as input and outputs contents of that
 * URL using Java API
 */
public class GetURL {
    
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: GetURL url");
        } else {
            try {
                URL url = new URL(args[0]);
                BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    System.out.println(" " + line);
                }
                in.close();
            } catch (MalformedURLException mue) {
                // URL constructor
                System.out.println(args[0] + "is an invalid URL: " + mue);
            } catch (IOException ioe) {
                // Stream constructors
                System.out.println("IOException: " + ioe);
            }
        }
    }
}