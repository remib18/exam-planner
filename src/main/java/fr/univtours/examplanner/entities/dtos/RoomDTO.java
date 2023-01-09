package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.enums.ComputerEnvironment;
import fr.univtours.examplanner.enums.RoomEquipment;
import fr.univtours.examplanner.enums.RoomType;
import fr.univtours.examplanner.exceptions.ControllerException;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomDTO implements EditableEntity {

    /**
     * Description de l'environnement des posts pour les étudiants
     */
    private final SimpleObjectProperty< List< ComputerEnvironment > > computerEnvironments = new SimpleObjectProperty<>(
            new ArrayList<>());

    /**
     * Équipements de la salle
     */
    private final SimpleObjectProperty< List< RoomEquipment > > equipments =
            new SimpleObjectProperty<>(new ArrayList<>());

    /**
     * Type de la salle
     */
    @NotNull
    private final SimpleObjectProperty< @NotNull RoomType > type = new SimpleObjectProperty<>();

    /**
     * Créneaux de disponibilité de la salle
     */
    @NotNull
    private final SimpleObjectProperty< @NotNull List< @NotNull SlotDTO > > availableSlots = new SimpleObjectProperty<>(
            new ArrayList<>());

    /**
     * Nom de la salle
     */
    @NotNull
    private final SimpleObjectProperty< @NotNull String > name = new SimpleObjectProperty<>();

    /**
     * Capacité de la salle
     */
    private final SimpleObjectProperty< Integer > places = new SimpleObjectProperty<>();

    /**
     * Salle
     *
     * @param name                Nom de la salle
     * @param places              Capacité de la salle
     * @param type                Type de la salle
     * @param computerEnvironment Environnement des ordinateurs
     * @param roomEquipment       Equipements des salles
     * @param availableSlots      Créneaux de disponibilité de la salle
     * @throws ParseException Si l'objet n'existe pas dans l'énumération
     */
    public RoomDTO(
            @NotNull String name,
            @NotNull int places,
            @NotNull RoomType type,
            @Nullable List< ComputerEnvironment > computerEnvironment,
            @NotNull List< RoomEquipment > roomEquipment,
            @Nullable List< SlotDTO > availableSlots
    ) throws ParseException {
        this.name.set(name);
        this.places.set(places);
        this.type.set(type);
        this.computerEnvironments.get().addAll(computerEnvironment);
        this.equipments.get().addAll(roomEquipment);
        this.availableSlots.get().addAll(availableSlots);
    }

    public SimpleObjectProperty< String > nameProperty() {
        return name;
    }

    public SimpleObjectProperty< Integer > placesProperty() {
        return places;
    }

    public SimpleObjectProperty< RoomType > typeProperty() {
        return type;
    }

    public SimpleObjectProperty< List< ComputerEnvironment > > computerEnvironmentsProperty() {
        return computerEnvironments;
    }

    public SimpleObjectProperty< List< RoomEquipment > > equipmentsProperty() {
        return equipments;
    }

    public SimpleObjectProperty< List< SlotDTO > > availableSlotsProperty() {
        return availableSlots;
    }

    public @NotNull String getName() {
        return name.get();
    }

    public void setName( @NotNull String name ) {
        this.name.set(name);
    }

    public int getPlaces() {
        return places.get();
    }

    public void setPlaces( int places ) {
        this.places.set(places);
    }

    public @NotNull RoomType getType() {return type.get();}

    public void setType( RoomType type ) {
        this.type.set(type);
    }

    public List< ComputerEnvironment > getComputerEnvironments() {
        return computerEnvironments.get();
    }

    public void addComputerEnvironment( ComputerEnvironment computerEnvironment ) {
        this.computerEnvironments.get().add(computerEnvironment);
    }

    public void removeComputerEnvironment( ComputerEnvironment computerEnvironment ) {
        this.computerEnvironments.get().remove(computerEnvironment);
    }

    public List< RoomEquipment > getEquipments() {
        return equipments.get();
    }

    public void addEquipment( RoomEquipment equipment ) {
        this.equipments.get().add(equipment);
    }

    public void removeEquipment( RoomEquipment equipment ) {
        this.equipments.get().remove(equipment);
    }

    public @NotNull List< SlotDTO > getAvailableSlots() {
        return availableSlots.get();
    }

    public void addAvailableSlot( @NotNull SlotDTO slot ) {
        this.availableSlots.get().add(slot);
    }

    public void addAvailableSlot( @NotNull List< SlotDTO > slots ) {
        this.availableSlots.get().addAll(slots);
    }

    public void removeAvailableSlot( @NotNull SlotDTO slot ) {
        this.availableSlots.get().remove(slot);
    }

    public void removeAvailableSlot( @NotNull List< SlotDTO > slots ) {
        this.availableSlots.get().removeAll(slots);
    }

    public void removeAllAvailableSlots() {
        this.availableSlots.get().clear();
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( null == o || getClass() != o.getClass() ) return false;
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
               "\n\tname: " +
               name +
               ", \n\tplaces: " +
               places +
               ", \n\ttype: " +
               type +
               ", \n\tcomputerEnvironments: " +
               computerEnvironments +
               ", \n\tequipments: " +
               equipments +
               ", \n\tavailableSlots: " +
               availableSlots +
               "\n}";
    }

    @Override
    public void set( String property, Object value ) throws ControllerException {
        switch ( property ) {
            case "name" -> setName((String) value);
            case "places" -> setPlaces((int) value);
            case "type" -> setType((RoomType) value);
            default -> throw new RuntimeException();
		}
	}
}