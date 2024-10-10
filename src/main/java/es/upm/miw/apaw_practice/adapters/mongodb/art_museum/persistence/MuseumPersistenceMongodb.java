package es.upm.miw.apaw_practice.adapters.mongodb.art_museum.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.daos.MuseumRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.ExhibitionEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.art_museum.entities.MuseumEntity;
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

    @Override
    public Museum updateExhibitionPrice(Museum museum, String exhibitionName) {
        MuseumEntity museumEntity = this.museumRepository.findByName(museum.getName())
                .orElseThrow(() -> new NotFoundException("Museum not found: " + museum.getName()));

        ExhibitionEntity exhibitionEntity = museumEntity.getExhibitionEntities().stream()
                .filter(exhibitionE -> exhibitionE.getName().equals(exhibitionName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Exhibition not found: " + exhibitionName));

        exhibitionEntity.setPrice(museum.getExhibitions().stream()
                .filter(exh -> exh.getName().equals(exhibitionName))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("Exhibition not found in museum: " + exhibitionName))
                .getPrice());

        return this.museumRepository.save(museumEntity).toMuseum();
    }
}
