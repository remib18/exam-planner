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

    /**
     * Groupes d'étudiants participant à l'examen
     */
    @NotNull
    private final SimpleObjectProperty< List< String > > groupsIDs = new SimpleObjectProperty<>(new ArrayList<>());

    /**
     * Surveillants de l'examen
     */
    @NotNull
    private final SimpleObjectProperty< List< String > > managersIDs = new SimpleObjectProperty<>(new ArrayList<>());

    /**
     * Précédence de l'examen
     */
    @NotNull
    private final SimpleObjectProperty< List< String > > previousExamsIDs =
            new SimpleObjectProperty<>(new ArrayList<>());

    /**
     * Nom de l'examen
     */
    @NotNull
    private final SimpleObjectProperty< String > name = new SimpleObjectProperty<>();

    /**
     * Sujet de l'examen
     */
    @NotNull
    private final SimpleObjectProperty< String > subject = new SimpleObjectProperty<>();

    /**
     * Durée de l'examen en heures
     */
    private final SimpleObjectProperty< Float > duration = new SimpleObjectProperty<>();

    @NotNull
    private final SimpleObjectProperty< ExamType > type = new SimpleObjectProperty<>();

    /**
     * Examen
     *
     * @param id               Identifiant de l'examen dans la base de donnée, si null l'examen n'est pas encore
     *                          enregistré
     * @param name             Nom de l'examen
     * @param type               Type de l'examen (Continuous ou final)
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
            @NotNull List< String > groupsIDs,
            @NotNull List< String > managersIDs,
            @NotNull String subject,
            float duration,
            @NotNull List< String > previousExamsIDs
    ) {
        super(id);
        this.name.set(name);
        this.type.set(type);
        this.groupsIDs.get().addAll(groupsIDs);
        this.managersIDs.get().addAll(managersIDs);
        this.subject.set(subject);
        setDuration(duration);
        this.previousExamsIDs.get().addAll(previousExamsIDs);
    }

    public SimpleObjectProperty< String > nameProperty() {
        return name;
    }

    public SimpleObjectProperty< ExamType > typeProperty() {
        return type;
    }

    public SimpleObjectProperty< List< String > > groupsIDsProperty() {
        return groupsIDs;
    }

    public SimpleObjectProperty< List< String > > managersIDsProperty() {
        return managersIDs;
    }

    public SimpleObjectProperty< List< String > > previousExamsIDsProperty() {
        return previousExamsIDs;
    }

    public SimpleObjectProperty< String > subjectProperty() {
        return subject;
    }

    public SimpleObjectProperty< Float > durationProperty() {
        return duration;
    }

    public @NotNull String getName() {
        return name.get();
    }

    public void setName( @NotNull String name ) {
        this.name.set(name);
    }

    public @NotNull ExamType getType() {return type.get();}

    public void setType( @NotNull ExamType type ) {this.type.set(type);}

    public @NotNull List< GroupDTO > getGroups() throws ControllerException {
        return groupsIDs.get().stream().map(gid -> {
            try {
                return GroupController.getById(gid);
            } catch ( ControllerException e ) {
                throw new RuntimeException("Fail", e);
            }
        }).toList();
    }

    public void addGroup( @NotNull GroupDTO group ) {
        if ( Objects.isNull(group.getId()) ) return;
        this.groupsIDs.get().add(group.getId());
    }

    public void addGroup( @NotNull List< GroupDTO > groups ) {
        this.groupsIDs.get()
                      .addAll(groups.stream().filter(g -> !Objects.isNull(g.getId())).map(g -> g.getId()).toList());
    }

    public void removeGroup( @NotNull GroupDTO group ) {
        this.groupsIDs.get().remove(group.getId());
    }

    public void removeGroup( @NotNull List< GroupDTO > groups ) {
        this.groupsIDs.get()
                      .removeAll(groups.stream().filter(g -> !Objects.isNull(g.getId())).map(g -> g.getId()).toList());
    }

    public @NotNull List< ManagerDTO > getManagers() throws ControllerException {
        return managersIDs.get().stream().map(mid -> {
            try {
                return ManagerController.getByID(mid);
            } catch ( ControllerException e ) {
                throw new RuntimeException("Fail", e);
            }
        }).toList();
    }

    public void addManager( @NotNull ManagerDTO manager ) {
        if ( Objects.isNull(manager.getId()) ) return;
        this.groupsIDs.get().add(manager.getId());
    }

    public void addManager( @NotNull List< ManagerDTO > managers ) {
        this.groupsIDs.get()
                      .addAll(managers.stream().filter(m -> !Objects.isNull(m.getId())).map(m -> m.getId()).toList());
    }

    public void removeManager( @NotNull ManagerDTO manager ) {
        this.managersIDs.get().remove(manager.getId());
    }

    public void removeManager( @NotNull List< ManagerDTO > managers ) {
        this.groupsIDs.get()
                      .removeAll(managers.stream()
                                         .filter(m -> !Objects.isNull(m.getId()))
                                         .map(m -> m.getId())
                                         .toList());
    }

    public @NotNull String getSubject() {
        return subject.get();
    }

    public void setSubject( @NotNull SubjectDTO subject ) {
        this.subject.set(subject.getName());
    }

    public float getDuration() {
        return duration.get();
    }

    public void setDuration( float duration ) {
        if ( 0 > duration ) {
            throw new IllegalArgumentException("La durée d'un examen ne peut pas être négative");
        }
        this.duration.set(duration);
    }

	public @NotNull List< ExamDTO > getPreviousExams() {
        return previousExamsIDs.get().stream().map(peid -> {
            try {
                return ExamController.getById(peid);
            } catch ( ControllerException e ) {
                throw new RuntimeException("Fail", e);
            }
        }).toList();
    }

    public void addPreviousExam( @NotNull ExamDTO exam ) {
        if ( Objects.isNull(exam.getId()) ) return;
        this.groupsIDs.get().add(exam.getId());
    }

    public void addPreviousExam( @NotNull List< ExamDTO > exams ) {
        this.groupsIDs.get()
                      .addAll(exams.stream().filter(e -> !Objects.isNull(e.getId())).map(e -> e.getId()).toList());
    }

    public void removePreviousExam( @NotNull ExamDTO exam ) {
        this.previousExamsIDs.get().remove(exam.getId());
    }

    public void removePreviousExam( @NotNull List< ExamDTO > exams ) {
        this.groupsIDs.get()
                      .removeAll(exams.stream().filter(e -> !Objects.isNull(e.getId())).map(e -> e.getId()).toList());
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( null == o || getClass() != o.getClass() ) return false;
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
               type.get().getTypeString() +
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
            case "name" -> setName((String) value);
            case "type" -> setType((ExamType) value);
            case "subject" -> setSubject((SubjectDTO) value);
            case "duration" -> setDuration((Float) value);
        }
    }
}