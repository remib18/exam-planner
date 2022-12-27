package fr.univtours.examplanner.utils;

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum Database {
	;

	// Todo: Move this to a config file

	private static final String HOST = "jdbc:mysql://localhost/examPlanner";

	private static final String USER = "root";

	private static final String PASS = "";

	private static Connection connection;

	/**
	 * Obtient une connexion à la base de données
	 *
	 * @return La connexion à la base de données
	 *
	 * @throws SQLException Si la connexion échoue
	 */
	public static @NotNull Connection getConnection() throws SQLException {
		if ( null == connection ) {
			connection = DriverManager.getConnection(HOST, USER, PASS);
		}
		return connection;
	}

	public static @NotNull String getNewUUID() {
		// TODO implement here
		return "";
	}

}