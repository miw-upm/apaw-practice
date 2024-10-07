package es.upm.miw.apaw_practice.domain.services.art_museum;

import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import es.upm.miw.apaw_practice.domain.persistence_ports.art_museum.MuseumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MuseumService {
    private final MuseumPersistence museumPersistence;

    @Autowired
    public MuseumService(MuseumPersistence museumPersistence) {
        this.museumPersistence = museumPersistence;
    }

    public Museum read(String name) {
        return this.museumPersistence.readByName(name);
    }

    public void delete(String name) {
        this.museumPersistence.deleteByName(name);
    }
}
