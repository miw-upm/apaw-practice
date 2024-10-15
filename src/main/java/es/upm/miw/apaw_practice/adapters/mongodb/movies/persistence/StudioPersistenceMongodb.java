package es.upm.miw.apaw_practice.adapters.mongodb.movies.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.movies.daos.StudioRepository;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.movies.Studio;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.StudioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("studioPersistence")
public class StudioPersistenceMongodb implements StudioPersistence {

    private final StudioRepository studioRepository;

    @Autowired
    public StudioPersistenceMongodb(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    public Studio findByName(String name) {
        return this.studioRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Studio name: " + name))
                .toStudio();
    }
}
