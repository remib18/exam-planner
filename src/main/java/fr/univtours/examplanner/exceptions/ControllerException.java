package fr.univtours.examplanner.exceptions;

public class ControllerException extends Exception {

    public ControllerException( String message, Exception e ) {
        super(message, e);
    }
}