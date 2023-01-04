package fr.univtours.examplanner;

import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.utils.Database;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            try ( ResultSet res = connection.createStatement().executeQuery("SELECT * FROM User") ) {}
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
        String uuid = Database.getNewUUID();
        Assertions.assertNotNull(uuid);
        Assertions.assertEquals(36, uuid.length());
    }

}
