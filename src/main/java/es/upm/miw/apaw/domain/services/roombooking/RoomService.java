package es.upm.miw.apaw.domain.services.roombooking;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.model.roombooking.Room;
import es.upm.miw.apaw.domain.ports.out.roombooking.RoomGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomGateway roomGateway;

    public Room create(Room room) {
        if (this.roomGateway.existsByName(room.getName())) {
            throw new ConflictException("Room name already exists: " + room.getName());
        }
        room.doDefault();
        return this.roomGateway.create(room);
    }
}