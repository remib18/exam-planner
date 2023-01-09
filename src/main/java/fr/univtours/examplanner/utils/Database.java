package fr.univtours.examplanner.utils;

import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Database {

    // Todo: Move this to a config file

    private static final String HOST = "jdbc:mysql://localhost/exam_planner";

    private static final String USER = "root";

    private static final String PASS = "";

    private static Connection connection;

    private Database() {super();}

    /**
     * Obtient une connexion à la base de données
     *
     * @return La connexion à la base de données
     *
     * @throws DatabaseConnectionException Si la connexion échoue
     */
    public static @NotNull Connection getConnection() throws DatabaseConnectionException {
        try {
            if ( Objects.isNull(connection) || connection.isClosed() ) {
                connection = DriverManager.getConnection(HOST, USER, PASS);
            }
            return connection;
        } catch ( SQLNonTransientConnectionException e ) {
            connection = null;
            return getConnection();
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
    public static @NotNull String getNewUUID() {
        return UUID.randomUUID().toString();
    }

    public static @NotNull String listToMysqlSet( @NotNull List< @NotNull String > list ) {
        StringBuilder stringBuilder = new StringBuilder();
        for ( String item : list ) {
            stringBuilder.append(item);
            stringBuilder.append(';');
        }
        String res = stringBuilder.toString();
        return 1 < res.length() ? res.substring(0, res.length() - 1) : res;
    }

    public static @NotNull List< @NotNull String > mysqlSetToList( @NotNull String str ) {
        return List.of(str.split(";"));
    }

}