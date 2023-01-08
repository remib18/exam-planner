package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.enums.ComputerEnvironment;
import fr.univtours.examplanner.enums.RoomEquipment;
import fr.univtours.examplanner.enums.RoomType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomDTO {

	/**
	 * Description de l'environnement des posts pour les étudiants
	 */
	private final List< ComputerEnvironment > computerEnvironments = new ArrayList<>();

	/**
	 * Équipements de la salle
	 */
	private final List< RoomEquipment > equipments = new ArrayList<>();

	/**
	 * Type de la salle
	 */
	@NotNull
	private RoomType type;

	/**
	 * Créneaux de disponibilité de la salle
	 */
	@NotNull
	private final List< SlotDTO > availableSlots = new ArrayList<>();

	/**
	 * Nom de la salle
	 */
	@NotNull
	private String name;

	/**
	 * Capacité de la salle
	 */
	private int places;

	/**
	 * Salle
	 *
	 * @param name                Nom de la salle
	 * @param places              Capacité de la salle
	 * @param type                Type de la salle
	 * @param computerEnvironment Environnement des ordinateurs
	 * @param roomEquipment       Equipements des salles
	 * @param availableSlots      Créneaux de disponibilité de la salle
	 * @throws ParseException L'objet n'existe pas dans l'énumeration
	 */
	public RoomDTO(
			@NotNull String name,
			@NotNull int places,
			@NotNull String type,
			@Nullable List< String > computerEnvironment,
			@NotNull List< String > roomEquipment,
			@Nullable List< SlotDTO > availableSlots
	) throws ParseException {
		this.name = name;
		this.places = places;
		this.type = RoomType.parse(type);
		for ( int i = 0 ; i < computerEnvironment.size() ; i++ ) {
			this.computerEnvironments.add(ComputerEnvironment.parse(computerEnvironment.get(i)));
		}
		for ( int i = 0 ; i < roomEquipment.size() ; i++ ) {
			this.equipments.add(RoomEquipment.parse(roomEquipment.get(i)));
		}
		this.availableSlots.addAll(availableSlots);
	}

	public @NotNull String getName() {
		return name;
	}

	public void setName( @NotNull String name ) {
		this.name = name;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces( int places ) {
		this.places = places;
	}

	public @NotNull RoomType getType() {

		return type;
	}

	public void setType( RoomType type ) {
		this.type = type;
	}

	public List< ComputerEnvironment > getComputerEnvironments() {
		return computerEnvironments;
	}

	public void addComputerEnvironment( ComputerEnvironment computerEnvironment) {
		this.computerEnvironments.add(computerEnvironment);
	}

	public void removeComputerEnvironment(ComputerEnvironment computerEnvironment) {
		this.computerEnvironments.remove(computerEnvironment);
	}

	public List<RoomEquipment> getEquipments() {
		return equipments;
	}

	public void addEquipment(RoomEquipment equipment) {
		this.equipments.add(equipment);
	}

	public void removeEquipment(RoomEquipment equipment) {
		this.equipments.remove(equipment);
	}

	public @NotNull List<SlotDTO> getAvailableSlots() {
		return availableSlots;
	}

	public void addAvailableSlot(@NotNull SlotDTO slot) {
		this.availableSlots.add(slot);
	}

	public void addAvailableSlot(@NotNull List<SlotDTO> slots) {
		this.availableSlots.addAll(slots);
	}

	public void removeAvailableSlot(@NotNull SlotDTO slot) {
		this.availableSlots.remove(slot);
	}

	public void removeAvailableSlot(@NotNull List<SlotDTO> slots) {
		this.availableSlots.removeAll(slots);
	}

	public void removeAllAvailableSlots() {
		this.availableSlots.clear();
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o) return true;
		if ( null == o || getClass() != o.getClass()) return false;
		RoomDTO roomDTO = (RoomDTO) o;
		return Objects.equals(name, roomDTO.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, places, type, computerEnvironments, equipments, availableSlots);
	}

	@Override
	public String toString() {
		return "RoomDTO{" +
				"\n\tname: " + name +
				", \n\tplaces: " + places +
				", \n\ttype: " + type +
				", \n\tcomputerEnvironments: " + computerEnvironments +
				", \n\tequipments: " + equipments +
				", \n\tavailableSlots: " + availableSlots +
				"\n}";
	}
}