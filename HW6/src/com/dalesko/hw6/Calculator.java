/*
 * Calculator.java
 *
 * Created on September 30, 2020
 *
 */

package com.dalesko.hw6;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import com.dalesko.hw6.Rates.HIKE;

/**
 *
 * @author dlesko1
 */
public class Calculator extends JFrame implements ActionListener{
	
	// Create String of HTML and add it to a JLabel
	private static String tableHtml = "<html>" + 
			"    <table cellspacing=3 cellpadding=3 rules=groups>\r\n" + 
			"        <colgroup span=2></colgroup>\r\n" + 
			"        <colgroup span=2></colgroup>\r\n" + 
			"        <thead>\r\n" + 
			"        <tr>\r\n" + 
			"            <td colspan=2 rowspan=2></td>\r\n" + 
			"            <th colspan=2 align=center>Options</th>\r\n" + 
			"            <th colspan=2 ></th>\r\n" + 
			"        </tr>\r\n" + 
			"        <tr>\r\n" + 
			"            <th>Duration</th>\r\n" + 
			"            <th>Intensity</th>\r\n" + 
			"            <th colspan=2 align=center>Pricing Per Day</th>\r\n" + 
			"        </tr>\r\n" + 
			"        </thead>\r\n" + 
			"        <tbody>\r\n" + 
			"        <tr align=center>\r\n" + 
			"          <th rowspan=4></th>\r\n" + 
			"          <th>Gardiner Lake</th>\r\n" + 
			"          <td>3 or 5</td>\r\n" + 
			"          <td>Intermediate</td>\r\n" + 
			"          <td>$40</td>\r\n" + 
			"          <td></td>\r\n" + 
			"        </tr>\r\n" + 
			"        <tr align=center>\r\n" + 
			"          <th>Hellroaring Plateau</th>\r\n" + 
			"          <td>2, 3, or 5</td>\r\n" + 
			"          <td>Easy</td>\r\n" + 
			"          <td>$35</td>\r\n" + 
			"          <td></td>\r\n" + 
			"        </tr>\r\n" + 
			"        <tr align=center>\r\n" + 
			"          <th>The Beaten Path</th>\r\n" + 
			"          <td>5 or 7</td>\r\n" + 
			"          <td>Difficult</td>\r\n" + 
			"          <td>$45</td>\r\n" + 
			"          <td></td>\r\n" + 
			"        </tr>\r\n" + 
			"        </tbody>\r\n" + 
			"        <tfoot>\r\n" + 
			"        <tr>\r\n" + 
			"            <td colspan=6 align=center>Note: All hikes have a 50% surcharge for Sat/Sun hikes.</td>\r\n" + 
			"        </tr>\r\n" + 
			"        </tfoot>\r\n" + 
			"    </table><html>";
	JLabel jLabelHtml = new JLabel(tableHtml);
	
	// Create label for the text fields
	private final JLabel startDateLabel = new JLabel("Start Date (YYYY-MM-DD): ");
	
    // Create sub panels for the border layout
    private final JPanel northPanel = new JPanel();
	private final JPanel westPanel = new JPanel();
	private final JPanel eastPanel = new JPanel();
	private final JPanel centerPanel = new JPanel();
	
	// Create array of string containing hikes
    private final String hikes[] = { "Gardiner Lake", "Hellroaring Plateau", "The Beaten Path" }; 

    // Create drop down of hikes
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private final JComboBox hikesComboBox = new JComboBox(hikes); 
    
    // Create array of string containing hikes
    private final String hikeLengths[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" }; 

    // Create drop down of hikes
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private final JComboBox hikeLengthsComboBox = new JComboBox(hikeLengths); 
    
    // Create button to calculate total
    private final JButton calculateButton = new JButton("Calculate");
    
    // Create two text fields with a simple date format
    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private final JFormattedTextField dateTextFieldCenter = new JFormattedTextField(df);
    
    /** Creates a new instance of Main */
    public Calculator() {
    	
    	// Set constructor with a valuable application title
        super("Bear Tooth Hiking Company Rate Calculator");
        
        setFocusableWindowState(true);
        
        // Add the HTML table to the top of the window
        northPanel.add(jLabelHtml);
        add(northPanel, BorderLayout.NORTH);
        
        // Add west panel layout
        westPanel.add(hikesComboBox);
        add(westPanel, BorderLayout.WEST);
        
        // Add east panel to layout
        eastPanel.add(hikeLengthsComboBox);
        add(eastPanel, BorderLayout.EAST);
        
        // Add components to west sub-panel and add it to west side of application
        centerPanel.add(startDateLabel);
        dateTextFieldCenter.setColumns(8);
        centerPanel.add(dateTextFieldCenter);
        add(centerPanel, BorderLayout.CENTER);
        
        // Use a MaskFormatter to show the placeholder chars
        // https://stackoverflow.com/questions/4252257/jformattedtextfield-with-maskformatter
        df.setLenient(false);
        try {
            MaskFormatter dateMask = new MaskFormatter("####-##-##");
            dateMask.install(dateTextFieldCenter);
        } catch (ParseException ex) {
        	System.out.println("Parse exception: " + ex);
        }
        
        // Add calculate button to bottom of layout
        add(calculateButton, BorderLayout.SOUTH);
        
        // Add listener to the calculate button
        calculateButton.addActionListener(this);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Calculator();
    }

	@Override
	/**
     * @param e the event
     */
	public void actionPerformed(ActionEvent e) {
		
	    // Get the hike duration
	    int hikeDuration = getIndex((String)hikeLengthsComboBox.getSelectedItem(), hikeLengths);
	    
	    // Initialize start and end dates
		Date startDate = (Date)dateTextFieldCenter.getValue();
		Calendar calStartDate = toCalendar(startDate);
		calStartDate.add(Calendar.DAY_OF_MONTH, hikeDuration);
		Date endDate = calStartDate.getTime();
		
		// Get selected hike
		String selectedHike = (String)hikesComboBox.getSelectedItem();
		
		// Initialize BookingDay startDay and endDay to use with Rates class
		BookingDay startDay = null;
		BookingDay endDay = null;
		
		// Initialize notificationStr
		String notificationStr = "";
		
		// Parse the start and end date fields
		try {
			startDay = new BookingDay(startDate.getYear()+1900,startDate.getMonth()+1,startDate.getDay()+1);
		} catch (NullPointerException e1) {
			JOptionPane.showMessageDialog(null,
				    "You made an error entering the start date!");
			return;
		}
		
		try {
			endDay = new BookingDay(endDate.getYear()+1900,endDate.getMonth()+1,endDate.getDay()+1);
		} catch (NullPointerException e2) {
			JOptionPane.showMessageDialog(null,
				    "You made an error entering the end date!");
			return;
		}
		
		// If not valid days, show error message, otherwise append info to notificationStr
//		if (!startDay.isValidDate()) {
//			JOptionPane.showMessageDialog(null,
//				    startDay + " is not a valid start day.");
//			return;
//		}
//		else {
//			notificationStr = notificationStr.concat("Start Day: " + startDay + "\n");
//		}
//		if (!endDay.isValidDate()) {
//			JOptionPane.showMessageDialog(null,
//				    endDay + " is not a valid end day.");
//			return;
//		}
//		else {
//			notificationStr = notificationStr.concat("End Day: " + endDay + "\n");
//		}
		
		
		// Calculate the duration in days
		long diffInMillies = Math.abs(endDate.getTime() - startDate.getTime());
	    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
	    
	    // Get the index of the selected hike from the hikes array
	    int hikeIndex = getIndex(selectedHike, hikes);
	    
	    // Create a new Rates object and instantiate it with the type of hike chosen
//	    Rates rates = new Rates(HIKE.values()[hikeIndex]);
//		rates.setBeginDate(startDay);
//		rates.setEndDate(endDay);
//        boolean success = rates.setDuration((int)diff);
        
     // If not valid dates, show details 
//        if(!rates.isValidDates()) {
//        	JOptionPane.showMessageDialog(null,
//				    "The dates chosen are invalid! " + rates.getDetails());
//			return;
//        }
        
        BHCRateClient rateClient = new BHCRateClient(hikeIndex, startDate.getYear()+1900, startDate.getMonth()+1, startDate.getDay()+1, (int)diff);
        rateClient.connect();
        
        String response = rateClient.getResponse();
        
        String[] responseTokens = response.split(":");
        
        if (responseTokens[0].equals("-0.01")) {
        	JOptionPane.showMessageDialog(null,
        			responseTokens[1]);
        } else {
        	notificationStr = notificationStr.concat("Start Date: " + startDate.toString() + "\n");
        	notificationStr = notificationStr.concat("Total Days: " + (int)diff + "\n");
        	notificationStr = notificationStr.concat("Costs: " + responseTokens[0] + "\n");
        }
        
        // If not success, show the error dialog, otherwise append hike information to the notificationStr
//        if(!success) {
//        	JOptionPane.showMessageDialog(null,
//				    "The duration chosen is invalid!");
//			return;
//        }
//        else {
//        	notificationStr = notificationStr.concat("Weekdays: " + rates.getNormalDays() + "\n");
//        	notificationStr = notificationStr.concat("Weekends: " + rates.getPremiumDays() + "\n");
//        	notificationStr = notificationStr.concat("Costs: " + response + "\n");
//		}
        
        // Launch dialog window displaying details
		JOptionPane.showMessageDialog(null,
				notificationStr);
	}
	
	/** Gets index of a string in an array of strings
    *
    * @param str, the string to find
    * @param strs, the array of strings
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
