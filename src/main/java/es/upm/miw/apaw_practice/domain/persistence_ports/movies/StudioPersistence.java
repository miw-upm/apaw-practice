package es.upm.miw.apaw_practice.domain.persistence_ports.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Studio;
import org.springframework.stereotype.Repository;

@Repository
public interface StudioPersistence {

    Studio findByName(String name);
}
