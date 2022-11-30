package fr.univtours.examplanner.utils;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	// Todo: Move this to a config file

	static final String HOST = "jdbc:mysql://localhost/examPlanner";
	static final String USER = "root";
	static final String PASS = "";
	private static Connection connection;

	private Database() {
	}

	/**
	 * Obtient une connexion à la base de données
	 *
	 * @return La connexion à la base de données
	 * @throws SQLException Si la connexion échoue
	 */
	public static @NotNull Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = DriverManager.getConnection(HOST, USER, PASS);
		}
		return connection;
	}

}