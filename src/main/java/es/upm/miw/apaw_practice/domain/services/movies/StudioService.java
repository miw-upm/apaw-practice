package es.upm.miw.apaw_practice.domain.services.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Studio;
import es.upm.miw.apaw_practice.domain.persistence_ports.movies.StudioPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudioService {

    private final StudioPersistence studioPersistence;

    @Autowired
    public StudioService(StudioPersistence studioPersistence) {
        this.studioPersistence = studioPersistence;
    }

    public Studio findByName(String name) {
        return this.studioPersistence.findByName(name);
    }
}
