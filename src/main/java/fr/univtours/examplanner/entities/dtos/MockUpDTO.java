package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.enums.Degree;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Maquette ou cursus
public class MockUpDTO extends WithIDEntity {

	/**
	 * Nom de la maquette
	 */
	@NotNull
	private final String name;

	/**
	 * Matières de la maquette
	 */
	@NotNull
	private final List<SubjectDTO> subjects = new ArrayList<>();

	/**
	 * Niveau de la maquette
	 */
	@NotNull
	private Degree degree;

	/**
	 * Semestre de la maquette
	 */
	private int semester;

	public MockUpDTO(
			@Nullable String id,
			@NotNull String name,
			@NotNull Degree degree,
			int semester,
			@NotNull List<SubjectDTO> subjects
	) {
		super(id);
		this.name = name;
		this.degree = degree;
		setSemester(semester);
		this.subjects.addAll(subjects);
	}

	public @NotNull String getName() {
		return name;
	}

	public @NotNull Degree getDegree() {
		return degree;
	}

	public void setDegree(@NotNull Degree degree) {
		this.degree = degree;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) throws IllegalArgumentException {
		if (semester < 1) {
			throw new IllegalArgumentException("Semester must be greater than 0");
		}
		this.semester = semester;
	}

	public @NotNull List<SubjectDTO> getSubjects() {
		return subjects;
	}

	public void addSubject(@NotNull SubjectDTO subject) {
		this.subjects.add(subject);
	}

	public void addSubject(@NotNull List<SubjectDTO> subjects) {
		this.subjects.addAll(subjects);
	}

	public void removeSubject(@NotNull SubjectDTO subject) {
		this.subjects.remove(subject);
	}

	public void removeSubject(@NotNull List<SubjectDTO> subjects) {
		this.subjects.removeAll(subjects);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
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
				"\n\tid: " + id +
				", \n\tname: " + name +
				", \n\tdegree: " + degree +
				", \n\tsemester: " + semester +
				", \n\tsubjects: " + subjects +
				"\n}";
	}
}
