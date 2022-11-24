package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.enums.ComputerEnvironment;
import fr.univtours.examplanner.enums.RoomEquipment;
import fr.univtours.examplanner.enums.RoomType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class RoomDTO {

	/**
	 * Type de la salle
	 */
	@NotNull
	private final List<RoomType> type = new ArrayList<>();

	/**
	 * Description de l'environnement des posts pour les étudiants
	 */
	private final List<ComputerEnvironment> computerEnvironments = new ArrayList<>();

	/**
	 * Équipements de la salle
	 */
	private final List<RoomEquipment> equipments = new ArrayList<>();
	/**
	 * Créneaux de disponibilité de la salle
	 */
	@NotNull
	private final List<SlotDTO> availableSlots = new ArrayList<>();
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
	 * @param name           Nom de la salle
	 * @param places         Capacité de la salle
	 * @param type           Type de la salle
	 * @param availableSlots Créneaux de disponibilité de la salle
	 */
	public RoomDTO(@NotNull String name, int places, @NotNull List<RoomType> type, @NotNull List<SlotDTO> availableSlots) {
		this.name = name;
		this.places = places;
		this.type.addAll(type);
		this.availableSlots.addAll(availableSlots);
	}

	public @NotNull String getName() {
		return name;
	}

	public void setName(@NotNull String name) {
		this.name = name;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public @NotNull List<RoomType> getType() {
		return type;
	}

	public void addType(@NotNull RoomType type) {
		this.type.add(type);
	}

	public void removeType(@NotNull RoomType type) {
		this.type.remove(type);
	}

	public List<ComputerEnvironment> getComputerEnvironments() {
		return computerEnvironments;
	}

	public void addComputerEnvironment(ComputerEnvironment computerEnvironment) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
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