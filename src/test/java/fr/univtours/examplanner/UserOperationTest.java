package fr.univtours.examplanner;

import fr.univtours.examplanner.controllers.AuthenticationController;
import fr.univtours.examplanner.controllers.UserController;
import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.enums.UserRole;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.utils.Database;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UserOperationTest {

    static Stream< UserDTO > expectedUsersProvider() {
        return Stream.of(
                new UserDTO("user1", "user1@example.com", "password1", "Department 1", "manager1"),
                new UserDTO("user2", "user2@example.com", "password2", "Department 1", "manager1"),
                new UserDTO("user3", "user3@example.com", "password3", "Department 2", "manager2")
        );
    }

    @Test
    void getAll_shouldReturnNonNullList() throws ControllerException {
        List< UserDTO > allUsers = UserController.getAll();
        assertNotNull(allUsers);
        assertEquals(3, UserController.getAll().size());
    }

    @ParameterizedTest
    @MethodSource("expectedUsersProvider")
    void getAll_shouldContainExpectedUsers( UserDTO expectedUser ) throws ControllerException {
        List< UserDTO > allUsers = UserController.getAll();
        assertTrue(allUsers.contains(expectedUser));
        assertEquals(3, UserController.getAll().size());
    }

    @ParameterizedTest
    @MethodSource("expectedUsersProvider")
    void getByID_shouldReturnUser( UserDTO expectedUser ) throws ControllerException {
        assert null != expectedUser.getId();
        UserDTO user = UserController.getByID(expectedUser.getId());
        assertEquals(expectedUser, user);
        assertEquals(3, UserController.getAll().size());
    }

    @Test
    void getByID_shouldReturnNull() throws ControllerException {
        UserDTO user = UserController.getByID("unknown");
        assertNull(user);
        assertEquals(3, UserController.getAll().size());
    }

    @Test
    void create_shouldCreateAndReturnASchoolingUser() throws ControllerException {
        UserDTO user = UserController.create(
                "user4@example.com",
                AuthenticationController.encryptPassword("password4"),
                UserRole.Schooling,
                null
        );

        // Vérification de la création
        assertNotNull(user);
        assertNotNull(user.getId());

        // Vérification des types
        assertInstanceOf(UserDTO.class, user);
        assertInstanceOf(String.class, user.getId());

        // Vérification des valeurs
        assertEquals("user4@example.com", user.getMail());
        assertEquals(AuthenticationController.encryptPassword("password4"), user.getPassword());
        assertEquals(UserRole.Schooling, user.getRole());
        assertNull(user.getManagerID());
        assertNull(user.getDepartmentID());
        assertEquals(4, UserController.getAll().size());
    }

    @Test
    void create_shouldCreateAndReturnAManagerUser() throws ControllerException {
        UserDTO user = UserController.create(
                "user4@example.com",
                AuthenticationController.encryptPassword("password4"),
                UserRole.Manager,
                "manager1"
        );

        // Vérification de la création
        assertNotNull(user);
        assertNotNull(user.getId());

        // Vérification des types
        assertInstanceOf(UserDTO.class, user);
        assertInstanceOf(String.class, user.getId());

        // Vérification des valeurs
        assertEquals("user4@example.com", user.getMail());
        assertEquals(AuthenticationController.encryptPassword("password4"), user.getPassword());
        assertEquals(UserRole.Manager, user.getRole());
        assertEquals("manager1", user.getManagerID());
        assertNull(user.getDepartmentID());
        assertEquals(4, UserController.getAll().size());
    }

    @Test
    void create_shouldCreateAndReturnADepartmentUser() throws ControllerException {
        UserDTO user = UserController.create(
                "user4@example.com",
                AuthenticationController.encryptPassword("password4"),
                UserRole.Department,
                "Department 1"
        );

        // Vérification de la création
        assertNotNull(user);
        assertNotNull(user.getId());

        // Vérification des types
        assertInstanceOf(UserDTO.class, user);
        assertInstanceOf(String.class, user.getId());

        // Vérification des valeurs
        assertEquals("user4@example.com", user.getMail());
        assertEquals(AuthenticationController.encryptPassword("password4"), user.getPassword());
        assertEquals(UserRole.Department, user.getRole());
        assertNull(user.getManagerID());
        assertEquals("Department 1", user.getDepartmentID());
        assertEquals(4, UserController.getAll().size());
    }

    @Test
    void create_shouldNotCreateUser() throws ControllerException {
        assertThrowsExactly(
                ControllerException.class,
                () -> UserController.create("user3@example.com", "password3New", UserRole.Schooling, null)
        );
        assertEquals(3, UserController.getAll().size());
    }

    @Test
    void save_shouldCreateUser() throws ControllerException {
        UserDTO user = new UserDTO(null, "user4@example.com", "password4", null, null);
        UserController.save(user);
        assertNotNull(user.getId());
        assertEquals(4, UserController.getAll().size());
    }

    @Test
    void save_shouldNotCreateUser() throws ControllerException {
        UserDTO user = new UserDTO(null, "user3@example.com", "password3New", null, null);
        assertThrowsExactly(ControllerException.class, () -> UserController.save(user));
        assertEquals(3, UserController.getAll().size());
    }

    @Test
    void save_shouldNotCreateUser2() throws ControllerException {
        UserDTO user = new UserDTO(Database.getNewUUID(), "user3@example.com", "password3New", null, null);
        assertThrowsExactly(ControllerException.class, () -> UserController.save(user));
        assertEquals(3, UserController.getAll().size());
    }

    @Test
    void save_shouldUpdateUser() throws ControllerException {
        UserDTO user = UserController.getByID("user3");
        if ( Objects.isNull(user) ) {
            fail("User not found");
        }
        user.setMail("user4@example.com");
        UserController.save(user);
        assertEquals(3, UserController.getAll().size());
    }

    @Test
    void save_shouldUpdateUser2() throws ControllerException {
        UserDTO user = UserController.getByID("user3");
        if ( Objects.isNull(user) ) {
            fail("User not found");
        }
        user.setPassword("newPassword");
        UserController.save(user);
        assertEquals(3, UserController.getAll().size());
    }

    @Test
    void save_shouldNotUpdateUser() throws ControllerException {
        UserDTO user = UserController.getByID("user3");
        if ( Objects.isNull(user) ) {
            fail("User not found");
        }
        user.setMail("user2@example.com");
        assertThrowsExactly(ControllerException.class, () -> UserController.save(user));
        assertEquals(3, UserController.getAll().size());
    }

    @Test
    void delete_shouldDeleteUser() throws ControllerException {
        UserDTO user = UserController.getByID("user2");
        if ( Objects.isNull(user) ) {
            fail("User not found");
        }
        UserController.delete(user);
        UserController.getAll()
                      .stream()
                      .filter(u -> !Objects.isNull(u.getId()) && u.getId().equals("user2"))
                      .findFirst()
                      .ifPresent(u -> fail("User not deleted"));
        assertEquals(2, UserController.getAll().size());
    }

    @BeforeEach
    void setUp() throws DatabaseConnectionException, SQLException {
        try ( Statement stmt = Database.getConnection().createStatement() ) {
            // Insert test data into the Department table
            stmt.execute("INSERT INTO Department (name) VALUES ('Department 1')");
            stmt.execute("INSERT INTO Department (name) VALUES ('Department 2')");

            // Insert test data into the Manager table
            stmt.execute(
                    "INSERT INTO Manager (id, civility, lastname, firstname) VALUES ('manager1', 'Man', 'Manager', " +
                    "'One')");
            stmt.execute(
                    "INSERT INTO Manager (id, civility, lastname, firstname) VALUES ('manager2', 'Woman', 'Manager'," +
                    " " +
                    "'Two')");

            // Insert test data into the User table
            stmt.execute("INSERT INTO User (id, mail, password, department, manager) VALUES ('user1', 'user1@example" +
                         ".com', " +
                         "'password1', 'Department 1', 'manager1')");
            stmt.execute("INSERT INTO User (id, mail, password, department, manager) VALUES ('user2', 'user2@example" +
                         ".com', " +
                         "'password2', 'Department 1', 'manager1')");
            stmt.execute("INSERT INTO User (id, mail, password, department, manager) VALUES ('user3', 'user3@example" +
                         ".com', " +
                         "'password3', 'Department 2', 'manager2')");
        }
    }

    @AfterEach
    void tearDown() throws DatabaseConnectionException, SQLException {
        Connection conn = Database.getConnection();
        try ( Statement stmt = conn.createStatement() ) {
            // Delete test data from the User table
            stmt.execute("DELETE FROM User WHERE mail = 'user4@example.com'");
            stmt.execute("DELETE FROM User WHERE id IN ('user1', 'user2', 'user3')");

            // Delete test data from the Department table
            stmt.execute("DELETE FROM Department WHERE name IN ('Department 1', 'Department 2')");

            // Delete test data from the Manager table
            stmt.execute("DELETE FROM Manager WHERE id IN ('manager1', 'manager2')");
        }
    }

}
