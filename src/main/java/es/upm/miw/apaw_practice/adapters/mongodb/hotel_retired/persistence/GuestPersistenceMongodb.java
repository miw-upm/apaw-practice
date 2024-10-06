package es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.daos.GuestRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.hotel_retired.entities.GuestEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired.GuestPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("guestPersistence")
public class GuestPersistenceMongodb implements GuestPersistence {

    private final GuestRepository guestRepository;

    @Autowired
    public GuestPersistenceMongodb(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    @Override
    public Stream<Guest> readAll() {
        return this.guestRepository
                .findAll().stream()
                .map(GuestEntity::toGuest);
    }

    @Override
    public Guest create(Guest guest) {
        return this.guestRepository
                .save(new GuestEntity(guest.getNif(), guest.getFullName(), guest.getBirthDay()))
                .toGuest();
    }

    @Override
    public Guest update(String nif, Guest guest) {
        GuestEntity guestEntity = this.guestRepository
                .findByNif(nif)
                .orElseThrow(() -> new NotFoundException("Guest nif: " + nif));
        guestEntity.fromGuest(guest);
        return this.guestRepository
                .save(guestEntity)
                .toGuest();
    }

    @Override
    public Guest read(String nif) {
        return this.guestRepository
                .findByNif(nif)
                .orElseThrow(() -> new NotFoundException("Guest nif: " + nif))
                .toGuest();
    }

    @Override
    public boolean existsNIF(String nif) {
        return this.guestRepository
                .findByNif(nif)
                .isPresent();
    }
}
