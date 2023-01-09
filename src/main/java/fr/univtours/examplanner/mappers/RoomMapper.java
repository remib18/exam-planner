package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.RoomDTO;
import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class RoomMapper implements BaseMapper {

    /**
     * Créée une nouvelle classe RoomDTO(name, place, type, computerEnvironments, equipments, availableSlots) grâce aux
     * éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link RoomDTO}
     */
    public @NotNull List< RoomDTO > entityToDTO( @NotNull ResultSet entities ) throws MappingException {
        List< RoomDTO > room = new ArrayList<>();
        try {
            while ( entities.next() ) {
                String name = entities.getString("name");
                int places = entities.getInt("places");
                String type = entities.getString("type");

                Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rsStR = stmt.executeQuery("SELECT * FROM _slottoroom WHERE name = " + name);
                List< String > SlotsName = new ArrayList<>();
                while ( rsStR.next() ) {
                    SlotsName.add(rsStR.getString("slot"));
                }
                List< SlotDTO > availableSlot = new ArrayList<>();

                ResultSet rs = stmt.executeQuery("SELECT * FROM room WHERE name = " + name);
                List< String > computerEnvironment = new ArrayList<>();
                List< String > roomEquipment = new ArrayList<>();
                while ( rs.next() ) {
                    computerEnvironment.add(rs.getString("computerEnvironment"));
                    roomEquipment.add(rs.getString("roomEquipment"));
                }

                room.add(new RoomDTO(name, places, type, computerEnvironment, roomEquipment, availableSlot));

            }
            return room;
        } catch ( SQLException | DatabaseConnectionException | ParseException e ) {
            throw new MappingException("Unable to map entity", e);
        }
    }
}