package es.upm.miw.apaw.domain.persistenceports.football;

import es.upm.miw.apaw.domain.models.football.Stadium;

import java.util.List;
import java.util.Optional;

public interface StadiumPersistence {

    Optional<Stadium> findByOfficialName(String name);

    List<Stadium> readAll();

    Stadium save(Stadium stadium);

    boolean existsByOfficialName(String name);

    void delete(Stadium stadium);


}
