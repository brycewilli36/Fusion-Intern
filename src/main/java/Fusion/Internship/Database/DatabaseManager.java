package Fusion.Internship.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	public static final String URL = "jdbc:mysql://127.0.0.1:3306/fusiontechnologydatabase";
	public static final String USER = "root";
	public static final String PASS = "password"; //very safe and secure
	public static Connection conn = null;
	
		public static Connection openConnection() throws Exception
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(URL, USER, PASS);
				if(conn != null) {
					System.out.println("Connected");
				}
			} catch (Exception ex) {
				throw new RuntimeException("Error connecting to the database", ex);
			}
			return conn;
		}
}