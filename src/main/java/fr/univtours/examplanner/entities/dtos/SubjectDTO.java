package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.controllers.SubjectController;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.exceptions.ControllerException;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class SubjectDTO extends WithIDEntity implements EditableEntity {

    /**
     * Nom de la matière
     */
    @NotNull
    private final SimpleObjectProperty< String > name = new SimpleObjectProperty<>();

    /**
     * Matière d'un cursus
     *
     * @param id   Identifiant de la matière dans la base de donnée, null si la matière n'est pas encore enregistrée
     * @param name Nom de la matière
     */
    public SubjectDTO( @Nullable String id, @NotNull String name ) {
        super(id);
        this.name.set(name);
	}

	/**
	 * Retourne le nom de la matière
	 *
	 * @return name Nom de la matière
	 */

	public @NotNull String getName() {
        return name.get();
	}

	/**
	 * Modifie le nom de la matière
	 *
	 * @param name Nom de la matière
	 */

	public void setName( @NotNull String name ) {
        this.name.set(name);
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o ) return true;
		if ( null == o || getClass() != o.getClass() ) return false;
		SubjectDTO that = (SubjectDTO) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "SubjectDTO{" + "\n\tid: " + id + ", \n\tname: " + name + "\n}";
    }

    @Override
    public void set( String property, Object value ) throws ControllerException {
        if ( property.equals("name") ) {
            setName((String) value);
        } else {
            throw new IllegalArgumentException("Unknown property " + property);
        }
        if ( Objects.nonNull(id.get()) ) {
            SubjectController.save(this);
		}
	}
}
