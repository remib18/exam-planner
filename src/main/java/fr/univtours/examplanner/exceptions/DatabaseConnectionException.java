package fr.univtours.examplanner.exceptions;

public class DatabaseConnectionException extends Exception {

    public DatabaseConnectionException( String message, Throwable cause ) {
        super(message, cause);
    }

}