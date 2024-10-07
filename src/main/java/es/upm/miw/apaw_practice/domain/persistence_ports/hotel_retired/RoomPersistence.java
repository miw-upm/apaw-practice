package es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface RoomPersistence {

    Stream<Room> readAll();

    Room create(Room room);

    Room update(String num, Room room);

    Room read(String num);
}
