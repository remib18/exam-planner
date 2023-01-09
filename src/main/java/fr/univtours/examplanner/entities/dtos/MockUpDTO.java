package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.controllers.SubjectController;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.enums.Degree;
import fr.univtours.examplanner.exceptions.ControllerException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Maquette ou cursus
public class MockUpDTO extends WithIDEntity implements EditableEntity {

    /**
     * Nom de la maquette
     */
    @NotNull
    private final String name;

    /**
     * Matières de la maquette
     */
    @NotNull
    private final List< @NotNull String > subjects = new ArrayList<>();

	/**
	 * Niveau de la maquette
	 */
	@NotNull
	private Degree degree;

	/**
	 * Semestre de la maquette
	 */
	private int semester;

	/**
	 * Maquette
	 *
	 * @param id       Identifiant de la maquette
	 * @param name     Nom de la maquette
	 * @param degree   Niveau de la maquette
	 * @param semester Semestre de la maquette
	 * @param subjects Liste des matières de la maquette
	 */

	public MockUpDTO(
			@Nullable String id,
			@NotNull String name,
			@NotNull Degree degree,
			int semester, @NotNull List< @NotNull String > subjects
	) {
		super(id);
		this.name = name;
		this.degree = degree;
		setSemester(semester);
		this.subjects.addAll(subjects);
	}

	/**
	 * Retourne le nom de la maquette
	 *
	 * @return le nom de la maquette
	 */

	public @NotNull String getName() {
		return name;
	}

	/**
	 * Retourne le niveau de la maquette
	 *
	 * @return le niveau de la maquette
	 */

	public @NotNull Degree getDegree() {
		return degree;
	}

	/**
	 * Modifie le niveau de la maquette
	 *
	 * @param degree le niveau de la maquette
	 */

	public void setDegree( @NotNull Degree degree ) {
		this.degree = degree;
	}

	/**
	 * Retourne le semestre de la maquette
	 *
	 * @return le semestre de la maquette
	 */

	public int getSemester() {
		return semester;
	}

	/**
	 * Modifie le semestre de la maquette Si le semestre est inférieur à 1, afficher une exception Sinon, la valeur du
	 * semestre actuel devient celle mise en paramètre
	 *
	 * @param semester le nouveau semestre
	 * @throws IllegalArgumentException si le semestre est inférieur à 1
     */

    public void setSemester( int semester ) throws IllegalArgumentException {
        if ( 1 > semester ) {
            throw new IllegalArgumentException("Semester must be greater than 0");
        }
        this.semester = semester;
    }

    public @NotNull List< SubjectDTO > getSubjects() {
        return subjects.stream().map(s -> {
            try {
                return SubjectController.getById(s);
            } catch ( ControllerException ignored ) {
                return null;
            }
        }).filter(Objects::nonNull).toList();
    }

    public @NotNull List< String > getSubjectsIDs() {
        return subjects;
    }

    /**
     * Ajoute une matière à la liste
     *
     * @param subject Matière à ajouter
     */

    public void addSubject( @NotNull SubjectDTO subject ) {
        if ( Objects.nonNull(subject.getId()) ) {
            this.subjects.add(subject.getId());
        }
	}

	/**
	 * Ajoute une liste de matières à la liste
	 *
	 * @param subjects Liste à ajouter
	 */

	public void addSubject( @NotNull List< SubjectDTO > subjects ) {
        this.subjects.addAll(subjects.stream().map(WithIDEntity::getId).filter(Objects::nonNull).toList());
	}

	/**
	 * Retire une matière de la liste
	 *
	 * @param subject Matière à retirer
	 */

	public void removeSubject( @NotNull SubjectDTO subject ) {
        this.subjects.remove(subject.getId());
	}

	/**
	 * Retire une liste de matières à la liste
	 *
	 * @param subjects Liste de matières
	 */

	public void removeSubject( @NotNull List< SubjectDTO > subjects ) {
        this.subjects.removeAll(subjects.stream().map(WithIDEntity::getId).toList());
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o ) return true;
		if ( null == o || getClass() != o.getClass() ) return false;
		MockUpDTO mockup = (MockUpDTO) o;
		return Objects.equals(id, mockup.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, degree, semester, subjects);
    }

    @Override
    public String toString() {
        return "Mockup{" +
               "\n\tid: " +
               id +
               ", \n\tname: " +
               name +
               ", \n\tdegree: " +
               degree +
               ", \n\tsemester: " +
               semester +
               ", \n\tsubjects: " +
               subjects +
               "\n}";
    }

    @Override
    public void set( String property, Object value ) throws ControllerException {
        // TODO: Implémenter, transformer les attributs en simple object property
    }
}
