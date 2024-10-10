package es.upm.miw.apaw_practice.domain.services.hotel_retired;

import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired.RoomPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomPersistence roomPersistence;

    @Autowired
    public RoomService(RoomPersistence roomPersistence) {
        this.roomPersistence = roomPersistence;
    }

    public Room create(Room room) {
        return this.roomPersistence.create(room);
    }

    public Room read(String num) {
        return this.roomPersistence.read(num);
    }

    public List<Room> search() {
        return this.roomPersistence.readAll().toList();
    }
}
