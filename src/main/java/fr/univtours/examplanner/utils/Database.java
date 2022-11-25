package fr.univtours.examplanner.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	static final String HOST = "jdbc:mysql://localhost/examPlanner";
	static final String USER = "root";
	static final String PASS = "";
	private static Connection connection;

	private Database() {
	}

	public static Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(HOST, USER, PASS);
		}
		return connection;
	}

}