package es.upm.miw.apaw.domain.services.roombooking;

import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.roombooking.Room;
import es.upm.miw.apaw.domain.ports.out.roombooking.RoomGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public Room read(UUID id) {
        return this.roomGateway.read(id)
                .orElseThrow(() -> new NotFoundException("Room id not found: " + id));
    }

    public Room update(UUID id, Room room) {
        Room storedRoom = this.read(id);
        if (!storedRoom.getName().equals(room.getName())
                && this.roomGateway.existsByName(room.getName())) {
            throw new ConflictException("Room name already exists: " + room.getName());
        }
        storedRoom.setName(room.getName());
        storedRoom.setCapacity(room.getCapacity());
        storedRoom.setFloor(room.getFloor());
        storedRoom.setVideoconferenceEquipped(room.getVideoconferenceEquipped());
        return this.roomGateway.update(storedRoom);
    }
}