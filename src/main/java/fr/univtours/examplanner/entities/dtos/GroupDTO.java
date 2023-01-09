package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.controllers.GroupController;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.utils.EntityUtils;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupDTO extends WithIDEntity implements EditableEntity {

	private static GroupDTO instance;

	private GroupController controller;

	public GroupDTO(){
		super();
		controller = new GroupController();
	}
	private static GroupDTO getInstance() {
		if ( Objects.isNull(instance) ) {
			instance = new GroupDTO();
		}
		return instance;
	}

	/**
	 * Liste des groupes enfants
	 */
	@NotNull
	private final SimpleObjectProperty< @NotNull List< @NotNull String > > childrenIDs =
			new SimpleObjectProperty<>(new ArrayList<>());
	/**
	 * Nom du groupe<br/>
	 * Ne peut pas être null
	 */
	@NotNull
	private final SimpleObjectProperty< @NotNull String > name = new SimpleObjectProperty<>();
	/**
	 * Le groupe contient-il des personnes en situation de handicap<br/>
	 * Donnée utile pour la sélection d'une salle adaptée (rez-de-chaussée, ascenseur, ...)
	 */
	private final SimpleObjectProperty< @NotNull Boolean > containsStudentsWithReducedMobility = new SimpleObjectProperty<>();

	/**
	 * Nombre d'étudiants avec un besoin de secrétaire<br/>
	 * Pour chaque étudiant, il faut un secrétaire et une salle
	 */
	private final SimpleObjectProperty< @NotNull Integer > numberOfStudentsWithWriterNeeds = new SimpleObjectProperty<>();

	/**
	 * Nombre d'étudiants avec un besoin d'isolation = une salle seul<br/>
	 * Pour chaque étudiant, il faut une salle et un surveillant
	 */
	private final SimpleObjectProperty< @NotNull Integer > numberOfStudentsWithIsolationNeeds =
			new SimpleObjectProperty<>();

	/**
	 * Nombre d'étudiants avec un besoin de temps supplémentaires 1/3 ou 1/4 temps<br/>
	 * Il faut une salle et un surveillant pour l'ensemble de ces étudiants
	 */
	private final SimpleObjectProperty< @NotNull Integer > numberOfStudentsWithPartTimeNeeds =
			new SimpleObjectProperty<>();

	/**
	 * Nombre d'étudiants total du groupe, sans distinction de besoin
	 */
	private final SimpleObjectProperty< @NotNull Integer > numberOfStudentsWithoutAdjustment = new SimpleObjectProperty<>();

	/**
	 * Groupe d'étudiants, peut représenter des groupes de TD comme des groupes d'options
	 *
	 * @param id                                  Identifiant du groupe dans la base de donnée, <code>null</code> si
	 *                                               le groupe n'est pas encore enregistré
	 * @param name                                Nom du groupe
	 * @param containsStudentsWithReducedMobility Le groupe contient-il des personnes en situation de handicap
	 * @param numberOfStudentsWithWriterNeeds     Nombre d'étudiants avec un besoin de secrétaire
	 * @param numberOfStudentsWithIsolationNeeds  Nombre d'étudiants avec un besoin d'isolation
	 * @param numberOfStudentsWithPartTimeNeeds   Nombre d'étudiants avec un besoin de temps supplémentaires
	 * @param numberOfStudentsWithoutAdjustment                    Nombre d'étudiants total du groupe, sans
	 *                                                                distinction de besoin
	 * @param children                            Liste des groupes enfants
	 */
	public GroupDTO(
			@Nullable String id,
			@NotNull String name,
			boolean containsStudentsWithReducedMobility,
			int numberOfStudentsWithWriterNeeds,
			int numberOfStudentsWithIsolationNeeds,
			int numberOfStudentsWithPartTimeNeeds,
			int numberOfStudentsWithoutAdjustment,
			@NotNull List< String > children
	) {
		super(id);
		this.name.set(name);
		this.containsStudentsWithReducedMobility.set(containsStudentsWithReducedMobility);
		this.numberOfStudentsWithWriterNeeds.set(numberOfStudentsWithWriterNeeds);
		this.numberOfStudentsWithIsolationNeeds.set(numberOfStudentsWithIsolationNeeds);
		this.numberOfStudentsWithPartTimeNeeds.set(numberOfStudentsWithPartTimeNeeds);
		this.numberOfStudentsWithoutAdjustment.set(numberOfStudentsWithoutAdjustment);
		this.childrenIDs.get().addAll(children);
	}


	public @NotNull String getName() {
		return name.get();
	}

	public void setName(@NotNull String name) {
		this.name.set(name);
	}

	public boolean doesContainsStudentsWithReducedMobility() {
		return containsStudentsWithReducedMobility.get();
	}

	public void setContainsStudentsWithReducedMobility(boolean containsStudentsWithReducedMobility ) {
		this.containsStudentsWithReducedMobility.set(containsStudentsWithReducedMobility);
	}

	public int getNumberOfStudentsWithWriterNeeds() {
		return numberOfStudentsWithWriterNeeds.get();
	}

	public void setNumberOfStudentsWithWriterNeeds(int numberOfStudentsWithWriterNeeds) {
		this.numberOfStudentsWithWriterNeeds.set(numberOfStudentsWithWriterNeeds);
	}

	public int getNumberOfStudentsWithIsolationNeeds() {
		return numberOfStudentsWithIsolationNeeds.get();
	}

	public void setNumberOfStudentsWithIsolationNeeds(int numberOfStudentsWithIsolationNeeds) {
		this.numberOfStudentsWithIsolationNeeds.set(numberOfStudentsWithIsolationNeeds);
	}

	public int getNumberOfStudentsWithPartTimeNeeds() {
		return numberOfStudentsWithPartTimeNeeds.get();
	}

	public void setNumberOfStudentsWithPartTimeNeeds( int numberOfStudentsWithPartTimeNeeds ) {
		this.numberOfStudentsWithPartTimeNeeds.set(numberOfStudentsWithPartTimeNeeds);
	}

	public int getNumberOfStudentsWithoutAdjustment() {
		return numberOfStudentsWithoutAdjustment.get();
	}

	public void setnumberOfStudentsWithoutAdjustment( int numberOfStudentsWithoutAdjustment ) {
		this.numberOfStudentsWithoutAdjustment.set(numberOfStudentsWithoutAdjustment);
	}

	public @NotNull List< String > getChildrenIDs() {
		return childrenIDs.get();
	}

	public void addChild(@NotNull GroupDTO child) throws IllegalArgumentException {
		if (childrenIDs.get().contains(child)) {
			throw new IllegalArgumentException("Cannot add a child twice");
		}
		childrenIDs.get().add(child.getId());
	}

	public void removeChild( @NotNull GroupDTO child ) {
		childrenIDs.get().remove(child.getId());
	}

	public void removeChild( @NotNull List< GroupDTO > children ) {
		this.childrenIDs.get().removeAll(children);
	}

	@Override
	public boolean equals(Object o ) {
		if ( this == o ) return true;
		if ( null == o || getClass() != o.getClass() ) return false;
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
				numberOfStudentsWithoutAdjustment,
				childrenIDs
		);
	}

	@Override
	public @NotNull String toString() {
		return "GroupDTO{" +
			   "\n\tid: " +
			   id +
			   ", \n\tname: " +
			   name +
			   ", \n\tcontainsStudentsWithReducedMobility: " +
			   containsStudentsWithReducedMobility +
			   ", \n\tnumberOfStudentsWithWriterNeeds: " +
			   numberOfStudentsWithWriterNeeds +
			   ", \n\tnumberOfStudentsWithIsolationNeeds: " +
			   numberOfStudentsWithIsolationNeeds +
			   ", \n\tnumberOfStudentsWithPartTimeNeeds: " +
			   numberOfStudentsWithPartTimeNeeds +
			   ", \n\tnumberOfStudentsWithoutAdjustment: " +
			   numberOfStudentsWithoutAdjustment +
			   ", \n\tchildrenIds: " +
			   EntityUtils.listToString(childrenIDs.get(), e -> e) +
			   "\n}";
	}

	public List< GroupDTO > getChildren() {
		return childrenIDs.get().stream().map(i -> {
			try {
				return GroupController.getById(i);
			} catch ( ControllerException e ) {
				return null;
			}
		}).filter(Objects::nonNull).toList();
	}

	@Override
	public void set( String property, Object value ) throws ControllerException {
		{
			switch ( property ) {
				case "name" -> setName((String) value);
				case "containsStudentsWithReducedMobility" -> setContainsStudentsWithReducedMobility((boolean) value);
				case "numberOfStudentsWithWriterNeeds" -> setNumberOfStudentsWithWriterNeeds((int) value);
				case "numberOfStudentsWithIsolationNeeds" -> setNumberOfStudentsWithIsolationNeeds((int) value);
				case "numberOfStudentsWithPartTimeNeeds" -> setNumberOfStudentsWithPartTimeNeeds((int) value);
				case "numberOfStudentsWithoutAdjustment" -> setnumberOfStudentsWithoutAdjustment((int) value);
				default -> throw new IllegalArgumentException("Unknown property " + property);
			}
			if ( Objects.nonNull(id.get()) ) {
				getInstance().controller.save(this);
			}
		}
	}

}
