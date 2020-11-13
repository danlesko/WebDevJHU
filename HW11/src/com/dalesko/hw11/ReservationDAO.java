package com.dalesko.hw11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/** 
 * Data Access Object For ReservationController
 */
public class ReservationDAO {
	
	// Information about the database
	// This would typically be stored in a properties file outside of the class
	private final static String url="jdbc:mysql://web6.jhuep.com:3306/";
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String user = "johncolter";
	private final static String pass = "LetMeIn";
	private final static String db="class";
	private final static String options="?useSSL=false";
	
	private String startDate;
	
	// initialize driver - needed for class use although it is deprecated
	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// constructor that accepts startDate
    public ReservationDAO(String startDate) {
    	this.startDate = startDate;
    }

	/**
	 * Gets a list of reservations from the database based on startDay
	 * @return List<Reservation>
	 * @throws SQLException
	 */
    public List<Reservation> list() throws SQLException {
        List<Reservation>reservations = new ArrayList<Reservation>();

        try (
            Connection connection = DriverManager.getConnection(url + db + options, user, pass);        
            
        ) {
        	// SQL statement to get reservation information
        	PreparedStatement statement = connection.prepareStatement(
            		"SELECT reservation.first, reservation.last, reservation.StartDay as startDay, locations.location, guides.First as guideFirst, guides.Last as guideLast FROM reservation, locations, guides "
            		+ "WHERE locations.idlocations = reservation.location AND guides.idguides = reservation.guide AND reservation.StartDay >= ? ORDER BY reservation.StartDay ASC;");
        	statement.setString(1, startDate);
        	
        	// Get results from DB
        	ResultSet resultSet = statement.executeQuery();
        	
        	// For each result row, create a new reservation and add it to the reservations list
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setFirst(resultSet.getString("first"));
                reservation.setLast(resultSet.getString("last"));
                reservation.setLocation(resultSet.getString("location"));
                reservation.setStartDay(resultSet.getString("startDay"));
                reservation.setGuideFirst(resultSet.getString("guideFirst"));
                reservation.setGuideLast(resultSet.getString("guideLast"));
                reservations.add(reservation);
            }
        }

        return reservations;
    }

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

}
