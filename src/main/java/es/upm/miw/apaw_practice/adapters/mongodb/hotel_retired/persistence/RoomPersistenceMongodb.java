package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.RoomRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.RoomEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Room;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired.RoomPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.stream.Stream;

@Repository("roomPersistence")
public class RoomPersistenceMongodb implements RoomPersistence {

    private final RoomRepository roomRepository;

    @Autowired
    public RoomPersistenceMongodb(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Stream<Room> readAll() {
        return this.roomRepository
                .findAll().stream()
                .map(RoomEntity::toRoom);
    }

    @Override
    public Room create(Room room) {
        return this.roomRepository
                .save(new RoomEntity(
                        room.getNum(),
                        room.getOccupied(),
                        room.getNumBeds(),
                        room.getPrice(),
                        new ArrayList<>())
                )
                .toRoom();
    }

    @Override
    public Room update(String num, Room room) {
        RoomEntity roomEntity = this.roomRepository
                .findByNum(num)
                .orElseThrow(() -> new NotFoundException("Room num: " + num));
        roomEntity.fromRoom(room);
        return this.roomRepository
                .save(roomEntity)
                .toRoom();
    }

    @Override
    public Room read(String num) {
        return this.roomRepository
                .findByNum(num)
                .orElseThrow(() -> new NotFoundException("Room num: " + num))
                .toRoom();
    }
}
