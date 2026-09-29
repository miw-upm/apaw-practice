package es.upm.miw.apaw.adapters.out.roombooking.postgres;

import es.upm.miw.apaw.domain.model.roombooking.Room;
import es.upm.miw.apaw.domain.ports.out.roombooking.RoomGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class RoomAdapter implements RoomGateway {

    private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;

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

    @Override
    public List<Room> findAll() {
        return this.roomRepository.findAllByOrderByNameAsc().stream()
                .map(RoomEntity::toDomain)
                .toList();
    }

    @Override
    public Room update(Room room) {
        return this.roomRepository.save(new RoomEntity(room)).toDomain();
    }

    @Override
    public boolean isReferenced(UUID id) {
        return this.bookingRepository.existsByRoomId(id);
    }

    @Override
    public void delete(UUID id) {
        this.roomRepository.deleteById(id);
    }
}