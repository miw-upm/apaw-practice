package es.upm.miw.apaw.domain.ports.out.roombooking;

import es.upm.miw.apaw.domain.model.roombooking.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RoomGateway {
    boolean existsByName(String name);
    Room create(Room room);
    Optional<Room> read(UUID id);
    List<Room> findAll();
    Room update(Room room);
    boolean isReferenced(UUID id);
    void delete(UUID id);
}