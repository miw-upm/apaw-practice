package es.upm.miw.apaw_practice.domain.persistence_ports.movies;

import es.upm.miw.apaw_practice.domain.models.movies.Award;

public interface AwardPersistence {
    void create(Award award);
    void delete(String nameCategoryYear);
    boolean existsByName(String name);
}
