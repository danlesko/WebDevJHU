package com.dalesko.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	private final static String url="jdbc:mysql://web6.jhuep.com:3306/";
	private final static String driver = "com.mysql.jdbc.Driver";
	private final static String user = "johncolter";
	private final static String pass = "LetMeIn";
	private final static String db="class";
	private final static String options="?useSSL=false";
	
	static {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try (Connection conn = DriverManager.getConnection(url + db + options, user, pass);
				Statement statement = conn.createStatement()) {
				String query = "SELECT * FROM guides";
				ResultSet rs = statement.executeQuery(query);
				
				while (rs.next()) {
					System.out.println("model = " + rs.getString("First"));
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
