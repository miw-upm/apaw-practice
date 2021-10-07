package es.upm.miw.apaw_practice.domain.persistence_ports.football;

import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumPersistence {
    Stadium readByName(String name);

    void update(Stadium stadiumResult);
}
