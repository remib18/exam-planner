package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.controllers.ExamController;
import fr.univtours.examplanner.controllers.GroupController;
import fr.univtours.examplanner.controllers.ManagerController;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.enums.ExamType;
import fr.univtours.examplanner.exceptions.ControllerException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExamDTO extends WithIDEntity implements EditableEntity {

    /**
     * Groupes d'étudiants participant à l'examen
     */
    @NotNull
    private final List< String > groupsIDs = new ArrayList<>();

    /**
     * Surveillants de l'examen
     */
	@NotNull
	private final List<String> managersIDs = new ArrayList<>();

	/**
	 * Précédence de l'examen
	 */
	@NotNull
	private final List<String> previousExamsIDs = new ArrayList<>();

	/**
	 * Nom de l'examen
	 */
	@NotNull
	private String name;
	/**
	 * Sujet de l'examen
	 */
	@NotNull
	private String subject;

	/**
	 * Durée de l'examen en heures
	 */
	private float duration;

	@NotNull
	private ExamType type ;

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
		this.name = name;
		this.type = type;
		this.groupsIDs.addAll(groupsIDs);
		this.managersIDs.addAll(managersIDs);
		this.subject = subject;
		setDuration(duration);
		this.previousExamsIDs.addAll(previousExamsIDs);
	}

	public @NotNull String getName() {
		return name;
	}

	public void setName(@NotNull String name) {
		this.name = name;
	}

	public @NotNull ExamType getType() { return type; }

	public void setType(@NotNull ExamType type) { this.type = type; }

	public @NotNull List<GroupDTO> getGroups() throws ControllerException {
		return groupsIDs.stream().map(gid -> {
            try {
                return GroupController.getById(gid);
			} catch ( ControllerException e ) {
				throw new RuntimeException("Fail", e);
			}
		}).toList();
	}

	public void addGroup(@NotNull GroupDTO group) {
		if ( Objects.isNull(group.getId()) ) return;
		this.groupsIDs.add(group.getId());
	}

	public void addGroup(@NotNull List<GroupDTO> groups) {
		this.groupsIDs.addAll(groups.stream()
									.filter(g -> !Objects.isNull(g.getId()))
									.map(g -> g.getId())
									.toList());
	}

	public void removeGroup(@NotNull GroupDTO group) {
		this.groupsIDs.remove(group.getId());
	}

	public void removeGroup(@NotNull List<GroupDTO> groups) {
		this.groupsIDs.removeAll(groups.stream()
									   .filter(g -> !Objects.isNull(g.getId()))
									   .map(g -> g.getId())
									   .toList());
	}

	public @NotNull List<ManagerDTO> getManagers() throws ControllerException {
		return managersIDs.stream().map(mid -> {
            try {
                return ManagerController.getByID(mid);
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
		return subject;
	}

	public void setSubject(@NotNull SubjectDTO subject) {
		this.subject = subject.getName();
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		if ( 0 > duration ) {
			throw new IllegalArgumentException("La durée d'un examen ne peut pas être négative");
		}
		this.duration = duration;
	}

	public @NotNull List< ExamDTO > getPreviousExams() {
		return previousExamsIDs.stream().map(peid -> {
            try {
                return ExamController.getById(peid);
			} catch ( ControllerException e ) {
				throw new RuntimeException("Fail", e);
			}
		}).toList();
	}

	public void addPreviousExam(@NotNull ExamDTO exam) {
		if ( Objects.isNull(exam.getId()) ) return;
		this.groupsIDs.add(exam.getId());
	}

	public void addPreviousExam(@NotNull List< ExamDTO > exams) {
		this.groupsIDs.addAll(exams.stream()
									  .filter(e -> !Objects.isNull(e.getId()))
									  .map(e -> e.getId())
									  .toList());	}

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
               "\n\tid: " +
               id +
               ", \n\tname: " +
               name +
               ", \n\ttype: " +
               type.getTypeString() +
               ", \n\tgroupsIds: " +
               groupsIDs +
               ", \n\tmanagersIds: " +
               managersIDs +
               ", \n\tsubject: " +
               subject +
               ", \n\tduration: " +
               duration +
               ", \n\tpreviousExamsIds: " +
               previousExamsIDs +
               "\n}";
    }

    @Override
    public void set( String property, Object value ) throws ControllerException {
        switch ( property ) {
            case "name" -> name = (String) value;
            case "type" -> type = (ExamType) value;
            case "subject" -> setSubject((SubjectDTO) value);
            case "duration" -> setDuration((Float) value);
        }
    }
}