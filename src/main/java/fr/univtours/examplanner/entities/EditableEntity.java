package fr.univtours.examplanner.entities;

import fr.univtours.examplanner.exceptions.ControllerException;

public interface EditableEntity {

    void set( String property, Object value ) throws ControllerException;

}
