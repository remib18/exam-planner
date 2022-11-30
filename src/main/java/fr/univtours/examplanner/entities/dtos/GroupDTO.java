package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.utils.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupDTO extends WithIDEntity {

	/**
	 * Liste des groupes enfants
	 */
	@NotNull
	private final List<GroupDTO> children = new ArrayList<>();
	/**
	 * Nom du groupe<br/>
	 * Ne peut pas être null
	 */
	@NotNull
	private String name;
	/**
	 * Le groupe contient-il des personnes en situation de handicap<br/>
	 * Donnée utile pour la sélection d'une salle adaptée (rez-de-chaussée, ascenseur, ...)
	 */
	private boolean containsStudentsWithReducedMobility;
	/**
	 * Nombre d'étudiants avec un besoin de secrétaire<br/>
	 * Pour chaque étudiant, il faut un secrétaire et une salle
	 */
	private int numberOfStudentsWithWriterNeeds;
	/**
	 * Nombre d'étudiants avec un besoin d'isolation = une salle seul<br/>
	 * Pour chaque étudiant, il faut une salle et un surveillant
	 */
	private int numberOfStudentsWithIsolationNeeds;
	/**
	 * Nombre d'étudiants avec un besoin de temps supplémentaire 1/3 ou 1/4 temps<br/>
	 * Il faut une salle et un surveillant pour l'ensemble de ces étudiants
	 */
	private int numberOfStudentsWithPartTimeNeeds;
	/**
	 * Nombre d'étudiants total du groupe, sans distinction de besoin
	 */
	private int numberOfStudents;

	/**
	 * Groupe d'étudiants, peut représenter des groupes de TD comme des groupes d'options
	 *
	 * @param id                                  Identifiant du groupe dans la base de donnée, <code>null</code> si le groupe n'est pas encore enregistré
	 * @param name                                Nom du groupe
	 * @param containsStudentsWithReducedMobility Le groupe contient-il des personnes en situation de handicap
	 * @param numberOfStudentsWithWriterNeeds     Nombre d'étudiants avec un besoin de secrétaire
	 * @param numberOfStudentsWithIsolationNeeds  Nombre d'étudiants avec un besoin d'isolation
	 * @param numberOfStudentsWithPartTimeNeeds   Nombre d'étudiants avec un besoin de temps supplémentaire
	 * @param numberOfStudents                    Nombre d'étudiants total du groupe, sans distinction de besoin
	 * @param children                            Liste des groupes enfants
	 */
	public GroupDTO(
			@Nullable String id,
			@NotNull String name,
			boolean containsStudentsWithReducedMobility,
			int numberOfStudentsWithWriterNeeds,
			int numberOfStudentsWithIsolationNeeds,
			int numberOfStudentsWithPartTimeNeeds,
			int numberOfStudents,
			@NotNull List<GroupDTO> children
	) {
		super(id);
		this.name = name;
		this.containsStudentsWithReducedMobility = containsStudentsWithReducedMobility;
		this.numberOfStudentsWithWriterNeeds = numberOfStudentsWithWriterNeeds;
		this.numberOfStudentsWithIsolationNeeds = numberOfStudentsWithIsolationNeeds;
		this.numberOfStudentsWithPartTimeNeeds = numberOfStudentsWithPartTimeNeeds;
		this.numberOfStudents = numberOfStudents;
		this.children.addAll(children);
	}

	public @NotNull String getName() {
		return name;
	}

	public void setName(@NotNull String name) {
		this.name = name;
	}

	public boolean doesContainsStudentsWithReducedMobility() {
		return containsStudentsWithReducedMobility;
	}

	public void setContainsStudentsWithReducedMobility(boolean containsStudentsWithReducedMobility) {
		this.containsStudentsWithReducedMobility = containsStudentsWithReducedMobility;
	}

	public int getNumberOfStudentsWithWriterNeeds() {
		return numberOfStudentsWithWriterNeeds;
	}

	public void setNumberOfStudentsWithWriterNeeds(int numberOfStudentsWithWriterNeeds) {
		this.numberOfStudentsWithWriterNeeds = numberOfStudentsWithWriterNeeds;
	}

	public int getNumberOfStudentsWithIsolationNeeds() {
		return numberOfStudentsWithIsolationNeeds;
	}

	public void setNumberOfStudentsWithIsolationNeeds(int numberOfStudentsWithIsolationNeeds) {
		this.numberOfStudentsWithIsolationNeeds = numberOfStudentsWithIsolationNeeds;
	}

	public int getNumberOfStudentsWithPartTimeNeeds() {
		return numberOfStudentsWithPartTimeNeeds;
	}

	public void setNumberOfStudentsWithPartTimeNeeds(int numberOfStudentsWithPartTimeNeeds) {
		this.numberOfStudentsWithPartTimeNeeds = numberOfStudentsWithPartTimeNeeds;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public @NotNull List<GroupDTO> getChildren() {
		return children;
	}

	public void addChild(@NotNull GroupDTO child) throws IllegalArgumentException {
		if (children.contains(child)) {
			throw new IllegalArgumentException("Cannot add a child twice");
		}
		children.add(child);
	}

	public void addChild(@NotNull List<GroupDTO> children) {
		children.forEach(this::addChild);
	}

	public void removeChild(@NotNull GroupDTO child) {
		children.remove(child);
	}

	public void removeChild(@NotNull List<GroupDTO> children) {
		this.children.removeAll(children);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GroupDTO groupDTO = (GroupDTO) o;
		return Objects.equals(id, groupDTO.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(
				id,
				name,
				containsStudentsWithReducedMobility,
				numberOfStudentsWithWriterNeeds,
				numberOfStudentsWithIsolationNeeds,
				numberOfStudentsWithPartTimeNeeds,
				numberOfStudents,
				children
		);
	}

	@Override
	public @NotNull String toString() {
		return "GroupDTO{" +
				"\n\tid: " + id +
				", \n\tname: " + name +
				", \n\tcontainsStudentsWithReducedMobility: " + containsStudentsWithReducedMobility +
				", \n\tnumberOfStudentsWithWriterNeeds: " + numberOfStudentsWithWriterNeeds +
				", \n\tnumberOfStudentsWithIsolationNeeds: " + numberOfStudentsWithIsolationNeeds +
				", \n\tnumberOfStudentsWithPartTimeNeeds: " + numberOfStudentsWithPartTimeNeeds +
				", \n\tnumberOfStudents: " + numberOfStudents +
				", \n\tchildrenIds: " + EntityUtils.listToIdString(children) +
				"\n}";
	}
}
