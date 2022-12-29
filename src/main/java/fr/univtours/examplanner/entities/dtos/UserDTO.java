package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.controllers.DepartmentController;
import fr.univtours.examplanner.controllers.ManagerController;
import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.enums.UserRole;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class UserDTO extends WithIDEntity {

    /**
     * Information manager correspondantes à l'utilisateur<br/> Si null, l'utilisateur n'est pas un manager
     */
    @Nullable
    private final String managerID;

    /**
     * Adresse mail de l'utilisateur
     */
    @NotNull
    private String mail;

    /**
     * Département de l'utilisateur<br/> Si null, l'utilisateur possède soit le rôle de manager, soit celui de
     * scolarité
     */
    @Nullable
    private String departmentID;

    /**
     * Mot de passe de l'utilisateur (crypté)
     */
    @NotNull
    private String password;

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
        this.mail = mail;
        this.password = password;
        this.departmentID = departmentID;
        this.managerID = managerID;
    }

    public @NotNull String getMail() {
        return mail;
    }

    public void setMail( @NotNull String mail ) {
        this.mail = mail;
    }

    public @NotNull String getPassword() {
        return password;
    }

    public void setPassword( @NotNull String password ) {
        this.password = password;
    }

    public @Nullable String getDepartmentID() {
        return departmentID;
    }

    public @Nullable DepartmentDTO getDepartment() {
        DepartmentController dCtrl = new DepartmentController();
        return dCtrl.getByID(departmentID);
    }

    public void setDepartment( @Nullable DepartmentDTO department ) {
        this.departmentID = Objects.isNull(department) ? null : department.getId();
    }

    public @Nullable String getManagerID() {
        return managerID;
    }

    public @Nullable ManagerDTO getManager() {
        ManagerController mCtrl = new ManagerController();
        return mCtrl.getByID(managerID);
    }

    public void setManager( @Nullable ManagerDTO manager ) {
        this.departmentID = Objects.isNull(manager) ? null : manager.getId();
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( null == o || getClass() != o.getClass() ) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, departmentID);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
               "\n\tid: " +
               id +
               ", \n\tmail: " +
               mail +
               ", \n\tdepartment: " +
               departmentID +
               ", \n\tmanager profile: " +
               managerID +
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
        if ( Objects.isNull(departmentID) && Objects.isNull(managerID) ) {
            return UserRole.Schooling;
        }
        if ( Objects.isNull(departmentID) ) {
            return UserRole.Manager;
        }
        return UserRole.Department;
    }
}