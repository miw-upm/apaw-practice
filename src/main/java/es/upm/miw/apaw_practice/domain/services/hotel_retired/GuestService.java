package es.upm.miw.apaw_practice.domain.services.hotel_retired;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.hotel_retired.Guest;
import es.upm.miw.apaw_practice.domain.persistence_ports.hotel_retired.GuestPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    private final GuestPersistence guestPersistence;

    @Autowired
    public GuestService(GuestPersistence guestPersistence) {
        this.guestPersistence = guestPersistence;
    }

    public Guest create(Guest guest) {
        this.assertNIFNotExists(guest.getNif());
        return this.guestPersistence.create(guest);
    }

    private void assertNIFNotExists(String nif) {
        if (this.guestPersistence.existsNIF(nif)) {
            throw new ConflictException("NIF exists: " + nif);
        }
    }

    public Guest read(String nif) {
        return this.guestPersistence.read(nif);
    }

    public void delete(String nif) {
        this.guestPersistence.delete(nif);
    }
}
