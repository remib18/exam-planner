package fr.univtours.examplanner.entities;

import fr.univtours.examplanner.entities.dtos.GroupDTO;
import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.entities.dtos.SubjectDTO;
import fr.univtours.examplanner.utils.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Exam extends WithIDEntity {

	/**
	 * Groupes d'étudiants participant à l'examen
	 */
	@NotNull
	private final List<GroupDTO> groups = new ArrayList<>();

	/**
	 * Surveillants de l'examen
	 */
	@NotNull
	private final List<ManagerDTO> managers = new ArrayList<>();

	/**
	 * Précédence de l'examen
	 */
	@NotNull
	private final List<Exam> previousExams = new ArrayList<>();

	/**
	 * Nom de l'examen
	 */
	@NotNull
	private String name;
	/**
	 * Sujet de l'examen
	 */
	@NotNull
	private SubjectDTO subject;

	/**
	 * Durée de l'examen en heures
	 */
	private float duration;

	/**
	 * Examen
	 *
	 * @param id            Identifiant de l'examen dans la base de donnée, si null l'examen n'est pas encore enregistré
	 * @param name          Nom de l'examen
	 * @param groups        Groupes d'étudiants participant à l'examen
	 * @param managers      Surveillants de l'examen
	 * @param subject       Sujet de l'examen
	 * @param duration      Durée de l'examen en heures
	 * @param previousExams Précédence de l'examen
	 */
	public Exam(
			@Nullable String id,
			@NotNull String name,
			@NotNull List<GroupDTO> groups,
			@NotNull List<ManagerDTO> managers,
			@NotNull SubjectDTO subject,
			float duration,
			@NotNull List<Exam> previousExams
	) {
		super(id);
		this.name = name;
		this.groups.addAll(groups);
		this.managers.addAll(managers);
		this.subject = subject;
		setDuration(duration);
		this.previousExams.addAll(previousExams);
	}

	public @NotNull String getName() {
		return name;
	}

	public void setName(@NotNull String name) {
		this.name = name;
	}

	public @NotNull List<GroupDTO> getGroups() {
		return groups;
	}

	public void addGroup(@NotNull GroupDTO group) {
		this.groups.add(group);
	}

	public void addGroup(@NotNull List<GroupDTO> groups) {
		this.groups.addAll(groups);
	}

	public void removeGroup(@NotNull GroupDTO group) {
		this.groups.remove(group);
	}

	public void removeGroup(@NotNull List<GroupDTO> groups) {
		this.groups.removeAll(groups);
	}

	public @NotNull List<ManagerDTO> getManagers() {
		return managers;
	}

	public void addManager(@NotNull ManagerDTO manager) {
		this.managers.add(manager);
	}

	public void addManager(@NotNull List<ManagerDTO> managers) {
		this.managers.addAll(managers);
	}

	public void removeManager(@NotNull ManagerDTO manager) {
		this.managers.remove(manager);
	}

	public void removeManager(@NotNull List<ManagerDTO> managers) {
		this.managers.removeAll(managers);
	}

	public @NotNull SubjectDTO getSubject() {
		return subject;
	}

	public void setSubject(@NotNull SubjectDTO subject) {
		this.subject = subject;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		if (duration < 0) {
			throw new IllegalArgumentException("La durée d'un examen ne peut pas être négative");
		}
		this.duration = duration;
	}

	public @NotNull List<Exam> getPreviousExams() {
		return previousExams;
	}

	public void addPreviousExam(@NotNull Exam exam) {
		this.previousExams.add(exam);
	}

	public void addPreviousExam(@NotNull List<Exam> exams) {
		this.previousExams.addAll(exams);
	}

	public void removePreviousExam(@NotNull Exam exam) {
		this.previousExams.remove(exam);
	}

	public void removePreviousExam(@NotNull List<Exam> exams) {
		this.previousExams.removeAll(exams);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Exam exam = (Exam) o;
		return Objects.equals(id, exam.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, groups, managers, subject, duration, previousExams);
	}

	@Override
	public String toString() {
		return "Exam{" +
				"\n\tid: " + id +
				", \n\tname: " + name +
				", \n\tgroupsIds: " + EntityUtils.listToIdString(groups) +
				", \n\tmanagersIds: " + EntityUtils.listToIdString(managers) +
				", \n\tsubject: " + subject +
				", \n\tduration: " + duration +
				", \n\tpreviousExamsIds: " + EntityUtils.listToIdString(previousExams) +
				"\n}";
	}
}