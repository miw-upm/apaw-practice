package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.MuseumRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import es.upm.miw.apaw_practice.domain.persistence_ports.art_museum.MuseumPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("museumPersistence")
public class MuseumPersistenceMongodb implements MuseumPersistence {
    private final MuseumRepository museumRepository;

    @Autowired
    public MuseumPersistenceMongodb(MuseumRepository museumRepository) {
        this.museumRepository = museumRepository;
    }


    @Override
    public Museum readByName(String name) {
        return this.museumRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException(" Museum name: " + name))
                .toMuseum();
    }

    @Override
    public void deleteByName(String name) {
        this.museumRepository.deleteByName(name);
    }
}