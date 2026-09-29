package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import es.upm.miw.apaw.domain.model.roombooking.Room;
import es.upm.miw.apaw.domain.ports.out.roombooking.RoomGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RoomAdapter implements RoomGateway {

    private final RoomRepository roomRepository;

    @Override
    public boolean existsByName(String name) {
        return this.roomRepository.existsByName(name);
    }

    @Override
    public Room create(Room room) {
        return this.roomRepository.save(new RoomEntity(room)).toDomain();
    }

    @Override
    public Optional<Room> read(UUID id) {
        return this.roomRepository.findById(id).map(RoomEntity::toDomain);
    }
}