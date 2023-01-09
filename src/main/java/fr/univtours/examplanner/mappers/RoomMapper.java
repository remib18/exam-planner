package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.RoomDTO;
import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.enums.ComputerEnvironment;
import fr.univtours.examplanner.enums.RoomEquipment;
import fr.univtours.examplanner.enums.RoomType;
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
                RoomType type = RoomType.parse(Database.mysqlSetToList(entities.getString("types")).get(0));

                Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rsStR = stmt.executeQuery("SELECT * FROM _slottoroom WHERE room = '" + name + "'");
                List< String > SlotsName = new ArrayList<>();
                while ( rsStR.next() ) {
                    SlotsName.add(rsStR.getString("slot"));
                }
                List< SlotDTO > availableSlot = new ArrayList<>();

                ResultSet rs = stmt.executeQuery("SELECT * FROM room WHERE name = '" + name + "'");
                if ( !rs.next() ) {
                    throw new ParseException("", 0);
                }
                List< ComputerEnvironment > computerEnvironment = Database.mysqlSetToList(rs.getString(
                        "computerEnvironment")).stream().map(comp -> ComputerEnvironment.parse(comp)).toList();
                List< RoomEquipment > roomEquipment = Database.mysqlSetToList(rs.getString("equipments"))
                                                              .stream()
                                                              .map(eq -> RoomEquipment.parse(eq))
                                                              .toList();

                room.add(new RoomDTO(name, places, type, computerEnvironment, roomEquipment, availableSlot));

            }
            return room;
        } catch ( SQLException | DatabaseConnectionException | ParseException e ) {
            throw new MappingException("Unable to map entity", e);
        }
    }
}