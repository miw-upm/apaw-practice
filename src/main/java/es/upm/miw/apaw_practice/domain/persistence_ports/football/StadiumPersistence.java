package es.upm.miw.apaw_practice.domain.persistence_ports.football;

import es.upm.miw.apaw_practice.domain.models.football.Stadium;
import org.springframework.stereotype.Repository;

@Repository
public interface StadiumPersistence {
    Stadium readByCity(String city);

    void update(Stadium stadiumResult);
}
