package fr.univtours.examplanner;

import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.utils.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

class DatabaseTest {

    @Test
    void getConnection() {
        try {
            Connection connection = Database.getConnection();
            Assertions.assertNotNull(connection);
            connection.close();
        } catch ( DatabaseConnectionException e ) {
            System.out.println(e.getMessage());
            Assertions.fail("Database connection failed");
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            Assertions.fail("Database disconnection failed");
        }
    }

    @Test
    void dumbQuery() {
        try {
            Connection connection = Database.getConnection();
            connection.createStatement().executeQuery("SELECT * FROM User");
            connection.close();
        } catch ( DatabaseConnectionException e ) {
            System.out.println(e.getMessage());
            Assertions.fail("Database connection failed");
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            Assertions.fail("Operation failed");
        }
    }

    @Test
    void getNewUUID() {
        try {
            Connection connection = Database.getConnection();
            String uuid = Database.getNewUUID();
            String sql = "INSERT INTO User (id, mail, password) VALUES (" + uuid + ", ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, "nameTest");
            statement.setString(2, "passwordTest");
            int affectedRows = statement.executeUpdate();
            Assertions.assertEquals(1, affectedRows);
            Statement statement1 = connection.createStatement();
            statement1.executeUpdate("DELETE FROM User WHERE id = " + uuid);
            connection.close();
        } catch ( DatabaseConnectionException e ) {
            System.out.println(e.getMessage());
            Assertions.fail("Database connection failed");
        } catch ( SQLException e ) {
            System.out.println(e.getMessage());
            Assertions.fail("Operation failed");
        }
    }

}
