package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.enums.UserRole;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class UserDTO extends WithIDEntity {

	/**
	 * Adresse mail de l'utilisateur
	 */
	@NotNull
	private String mail;

	/**
	 * Département de l'utilisateur<br/>
	 * Si null, l'utilisateur possède le rôle de scolarité
	 */
	@Nullable
	private DepartmentDTO department;

	/**
	 * Rôle de l'utilisateur
	 */
	@NotNull
	private UserRole role;

	/**
	 * Utilisateur
	 *
	 * @param id         Identifiant de l'utilisateur dans la base de donnée, null si l'utilisateur n'est pas encore enregistré
	 * @param mail       Adresse mail de l'utilisateur
	 * @param department Département de l'utilisateur
	 * @param role       Rôle de l'utilisateur
	 */
	public UserDTO(@Nullable String id, @NotNull String mail, @Nullable DepartmentDTO department, @NotNull UserRole role) {
		super(id);
		this.mail = mail;
		this.department = department;
		this.role = role;
	}

	public @NotNull String getMail() {
		return mail;
	}

	public void setMail(@NotNull String mail) {
		this.mail = mail;
	}

	public @Nullable DepartmentDTO getDepartment() {
		return department;
	}

	public void setDepartment(@Nullable DepartmentDTO department) {
		if (Objects.isNull(department) && role != UserRole.Schooling) {
			throw new IllegalArgumentException("A department is required for a user with a role other than schooling");
		}
		this.department = department;
	}

	public @NotNull UserRole getRole() {
		return role;
	}

	public void setRole(@NotNull UserRole role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDTO userDTO = (UserDTO) o;
		return Objects.equals(id, userDTO.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, mail, department, role);
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"\n\tid: " + id +
				", \n\tmail: " + mail +
				", \n\tdepartment: " + department +
				", \n\trole: " + role +
				"\n}";
	}
}