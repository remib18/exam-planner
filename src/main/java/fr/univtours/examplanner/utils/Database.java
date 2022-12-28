package fr.univtours.examplanner.utils;

import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

public enum Database {
    ;

    // Todo: Move this to a config file

    private static final String HOST = "jdbc:mysql://localhost/exam_planner";

    private static final String USER = "root";

    private static final String PASS = "";

    private static Connection connection;

    /**
     * Obtient une connexion à la base de données
     *
     * @return La connexion à la base de données
     *
     * @throws DatabaseConnectionException Si la connexion échoue
     */
    public static @NotNull Connection getConnection() throws DatabaseConnectionException {
        try {
            if ( Objects.isNull(connection) ) {
                connection = DriverManager.getConnection(HOST, USER, PASS);
            }
            return connection;
        } catch ( SQLException e ) {
            throw new DatabaseConnectionException("Impossible de se connecter à la base de données : " + e.getMessage(),
                    e
            );
        }
    }

    /**
     * Génère un UUID pour la base de données selon la norme RFC 4122 (version 4)
     *
     * @return L'UUID généré
     */
    public static String getNewUUID() {
        UUID uuid = UUID.randomUUID();
        return "'" + uuid + "'";
    }
}
