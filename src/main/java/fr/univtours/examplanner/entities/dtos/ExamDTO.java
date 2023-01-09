package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.controllers.ExamController;
import fr.univtours.examplanner.controllers.GroupController;
import fr.univtours.examplanner.controllers.ManagerController;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.enums.ExamType;
import fr.univtours.examplanner.exceptions.ControllerException;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExamDTO extends WithIDEntity implements EditableEntity {

	private static ExamDTO instance;

	private final ExamController controller;

	public ExamDTO(){
		super();
		controller = new ExamController();
	}
	private static ExamDTO getInstance() {
		if ( Objects.isNull(instance) ) {
			instance = new ExamDTO();
		}
		return instance;
	}



	/**
	 * Groupes d'étudiants participant à l'examen
	 */
	@NotNull
	private final SimpleObjectProperty< @NotNull String > groupsIDs = new SimpleObjectProperty<>();

	/**
	 * Surveillants de l'examen
	 */
	@NotNull
	private final SimpleObjectProperty< @NotNull String > managersIDs = new SimpleObjectProperty<>();
	/**
	 * Précédence de l'examen
	 */
	@NotNull
	private final SimpleObjectProperty< @NotNull String > previousExamsIDs = new SimpleObjectProperty<>();
	/**
	 * Nom de l'examen
	 */
	private final SimpleObjectProperty< @NotNull String > name = new SimpleObjectProperty<>();
	/**
	 * Sujet de l'examen
	 */
	@NotNull
	private final SimpleObjectProperty< @NotNull String > subject = new SimpleObjectProperty<>();

	/**
	 * Durée de l'examen en heures
	 */
	private final SimpleObjectProperty< @NotNull Float > duration = new SimpleObjectProperty<>();

	@NotNull
	private final SimpleObjectProperty< @NotNull ExamType > type = new SimpleObjectProperty<>();

	/**
	 * Examen
	 *
	 * @param id               Identifiant de l'examen dans la base de donnée, si null l'examen n'est pas encore
	 *                          enregistré
	 * @param name             Nom de l'examen
	 * @param type			   Type de l'examen (Continuous ou final)
	 * @param groupsIDs        Groupes d'étudiants participant à l'examen
	 * @param managersIDs      Surveillants de l'examen
	 * @param subject          Sujet de l'examen
	 * @param duration         Durée de l'examen en heures
	 * @param previousExamsIDs Précédence de l'examen
	 */
	public ExamDTO(
			@Nullable String id,
			@NotNull String name,
			@NotNull ExamType type,
			@NotNull List<String> groupsIDs,
			@NotNull List<String> managersIDs,
			@NotNull String subject,
			float duration,
			@NotNull List<String> previousExamsIDs
	) {
		super(id);
		this.name.set(name);
		this.type.set(type);
		this.groupsIDs.set(groupsIDs.toString());
		this.managersIDs.addAll(managersIDs);
		this.subject.set(subject);
		setDuration(duration);
		this.previousExamsIDs.set(previousExamsIDs.toString());
		controller = new ExamController();
	}

	public @NotNull String getName() {
		return name.get();
	}

	public void setName(@NotNull String name) { this.name.set(name); }

	public @NotNull ExamType getType() { return type.get(); }

	public void setType(@NotNull ExamType type) { this.type.set(type); }

	public @NotNull List<GroupDTO> getGroups() throws ControllerException {
		return groupsIDs.stream().map(gid -> {
			try {
				return (new GroupController()).getByID(gid);
			} catch ( ControllerException e ) {
				throw new RuntimeException("Fail", e);
			}
		}).toList();
	}

	public void addGroup(@NotNull GroupDTO group) {
		if ( Objects.isNull(group.getId()) ) return;
		this.groupsIDs.set(group.getId());
	}

	public void addGroup(@NotNull List<GroupDTO> groups) {
		groups.stream()
			  .filter(g -> !Objects.isNull(g.getId()))
			  .map(g -> g.getId())
			  .forEach(this.groupsIDs::setValue);
	}


	public void removeGroup(@NotNull List<GroupDTO> groups) {
		List<GroupDTO> stringList = new ArrayList<>(groups);
		stringList.forEach(groups -> e );
	}

	public @NotNull List<ManagerDTO> getManagers() throws ControllerException {
		return managersIDs.stream().map(mid -> {
			try {
				return (new ManagerController()).getByID(mid);
			} catch ( ControllerException e ) {
				throw new RuntimeException("Fail", e);
			}
		}).toList();
	}

	public void addManager(@NotNull ManagerDTO manager) {
		if ( Objects.isNull(manager.getId()) ) return;
		this.groupsIDs.add(manager.getId());
	}

	public void addManager(@NotNull List<ManagerDTO> managers) {
		this.groupsIDs.addAll(managers.stream()
									.filter(m -> !Objects.isNull(m.getId()))
									.map(m -> m.getId())
									.toList());
	}

	public void removeManager(@NotNull ManagerDTO manager) {
		this.managersIDs.remove(manager.getId());
	}

	public void removeManager(@NotNull List<ManagerDTO> managers) {
		this.groupsIDs.removeAll(managers.stream()
									   .filter(m -> !Objects.isNull(m.getId()))
									   .map(m -> m.getId())
									   .toList());
	}

	public @NotNull String getSubject() {
		return subject.get();
	}

	public void setSubject(@NotNull SubjectDTO subject) { this.subject.set(subject.getName()); }

	public float getDuration() {
		return duration.get();
	}

	public void setDuration(float duration) {
		if ( 0 > duration ) {
			throw new IllegalArgumentException("La durée d'un examen ne peut pas être négative");
		}
		this.duration.set(duration);
	}

	public @NotNull List< ExamDTO > getPreviousExams() {
		return previousExamsIDs.stream().map(peid -> {
			try {
				return (new ExamController()).getById(peid);
			} catch ( ControllerException e ) {
				throw new RuntimeException("Fail", e);
			}
		}).toList();
	}

	public void addPreviousExam(@NotNull ExamDTO exam) {
		if ( Objects.isNull(exam.getId()) ) return;
		this.groupsIDs.set(exam.getId());
	}

	public void addPreviousExam(@NotNull List< ExamDTO > exams) {
		exams.stream()
			 .filter(e -> !Objects.isNull(e.getId()))
			 .map(e -> e.getId())
			 .forEach(this.groupsIDs::setValue);	}

	public void removePreviousExam(@NotNull ExamDTO exam) {
		this.previousExamsIDs.remove(exam.getId());
	}

	public void removePreviousExam(@NotNull List< ExamDTO > exams) {
		this.groupsIDs.removeAll(exams.stream()
										 .filter(e -> !Objects.isNull(e.getId()))
										 .map(e -> e.getId())
										 .toList());	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if ( null == o || getClass() != o.getClass()) return false;
		ExamDTO exam = (ExamDTO) o;
		return Objects.equals(id, exam.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, type, groupsIDs, managersIDs, subject, duration, previousExamsIDs);
	}

	@Override
	public String toString() {
		return "Exam{" +
				"\n\tid: " + id +
				", \n\tname: " + name +
			    ", \n\ttype: " + type.getTypeString() +
				", \n\tgroupsIds: " + groupsIDs +
				", \n\tmanagersIds: " + managersIDs +
				", \n\tsubject: " + subject +
				", \n\tduration: " + duration +
				", \n\tpreviousExamsIds: " + previousExamsIDs +
				"\n}";
	}

	@Override
	public void set( String property, Object value ) throws ControllerException
		{
		switch ( property ) {
			case "name" -> setName((String) value);
			case "type" ->setType((ExamType) value);
			case "groupsIds" ->(() value)
			case "subject" ->(() value)
			case "duration" ->(() value)
			case "previousExamsIds" ->(() value)

			default -> throw new IllegalArgumentException("Unknown property " + property);
		}
		if ( Objects.nonNull(id.get()) ) {
			getInstance().controller.save(this);
		}
	}
}