package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.controllers.DepartmentController;
import fr.univtours.examplanner.controllers.ManagerController;
import fr.univtours.examplanner.controllers.UserController;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.enums.UserRole;
import fr.univtours.examplanner.exceptions.ControllerException;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class UserDTO extends WithIDEntity implements EditableEntity {

    /**
     * Information manager correspondantes à l'utilisateur<br/> Si null, l'utilisateur n'est pas un manager
     */
    @NotNull
    private final SimpleObjectProperty< @Nullable String > managerID = new SimpleObjectProperty<>();

    /**
     * Adresse mail de l'utilisateur
     */
    @NotNull
    private final SimpleObjectProperty< @NotNull String > mail = new SimpleObjectProperty<>();

    /**
     * Département de l'utilisateur<br/> Si null, l'utilisateur possède soit le rôle de manager, soit celui de
     * scolarité
     */
    @NotNull
    private final SimpleObjectProperty< @Nullable String > departmentID = new SimpleObjectProperty<>();

    /**
     * Mot de passe de l'utilisateur (crypté)
     */
    @NotNull
    private final SimpleObjectProperty< @NotNull String > password = new SimpleObjectProperty<>();

    /**
     * Utilisateur
     *
     * @param id           Identifiant de l'utilisateur dans la base de donnée, null si l'utilisateur n'est pas encore
     *                     enregistré
     * @param mail         Adresse mail de l'utilisateur
     * @param password     Mot de passe de l'utilisateur
     * @param departmentID Identifiant du département de l'utilisateur, s'il existe
     * @param managerID    Identifiant du manager correspondant à l'utilisateur, s'il existe
     */
    public UserDTO(
            @Nullable String id,
            @NotNull String mail,
            @NotNull String password,
            @Nullable String departmentID,
            @Nullable String managerID
    ) {
        super(id);
        this.mail.set(mail);
        this.password.set(password);
        this.departmentID.set(departmentID);
        this.managerID.set(managerID);
    }

    public @NotNull SimpleObjectProperty< @Nullable String > mailProperty() {
        return mail;
    }

    public @NotNull String getMail() {
        return mail.get();
    }

    public void setMail( @NotNull String mail ) {
        this.mail.set(mail);
    }

    public @NotNull SimpleObjectProperty< @NotNull String > passwordProperty() {
        return password;
    }

    public @NotNull String getPassword() {
        return password.get();
    }

    public void setPassword( @NotNull String password ) {
        this.password.set(password);
    }

    public @NotNull SimpleObjectProperty< @Nullable String > departmentIDProperty() {
        return departmentID;
    }

    public @Nullable String getDepartmentID() {
        return departmentID.get();
    }

    public @Nullable DepartmentDTO getDepartment() {
        String did = departmentID.get();
        if ( Objects.isNull(did) ) return null;
        return DepartmentController.getByID(did);
    }

    public void setDepartment( @Nullable DepartmentDTO department ) {
        String did = Objects.isNull(department) ? null : department.getName();
        this.departmentID.set(did);
    }

    public @NotNull SimpleObjectProperty< @Nullable String > managerIDProperty() {
        return managerID;
    }

    public @Nullable String getManagerID() {
        return managerID.get();
    }

    public @Nullable ManagerDTO getManager() throws ControllerException {
        String mid = managerID.get();
        if ( Objects.isNull(mid) ) return null;
        return ManagerController.getByID(mid);
    }

    public void setManager( @Nullable ManagerDTO manager ) {
        String mid = Objects.isNull(manager) ? null : manager.getId();
        this.managerID.set(mid);
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( null == o || getClass() != o.getClass() ) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id.get(), userDTO.id.get());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, departmentID);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
               "\n\tid: " +
               id.get() +
               ", \n\tmail: " +
               mail.get() +
               ", \n\tdepartment: " +
               departmentID.get() +
               ", \n\tmanager profile: " +
               managerID.get() +
               ", \n\trole: " +
               getRole() +
               "\n}";
    }

    /**
     * Calcule le rôle de l'utilisateur
     *
     * @return le rôle de l'utilisateur
     */
    public @NotNull UserRole getRole() {
        if ( Objects.isNull(departmentID.get()) && Objects.isNull(managerID.get()) ) {
            return UserRole.Schooling;
        }
        if ( Objects.isNull(departmentID.get()) ) {
            return UserRole.Manager;
        }
        return UserRole.Department;
    }

    @Override
    public void set( String property, Object value ) throws ControllerException {
        switch ( property ) {
            case "mail" -> setMail((String) value);
            case "password" -> setPassword((String) value);
            case "department" -> setDepartment((DepartmentDTO) value);
            case "manager" -> setManager((ManagerDTO) value);
            default -> throw new IllegalArgumentException("Unknown property: " + property);
        }
        if ( Objects.nonNull(id.get()) ) {
            UserController.save(this);
        }
    }
}