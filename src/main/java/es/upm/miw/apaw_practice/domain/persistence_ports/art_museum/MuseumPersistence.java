package es.upm.miw.apaw_practice.domain.persistence_ports.art_museum;

import es.upm.miw.apaw_practice.domain.models.art_museum.Museum;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface MuseumPersistence {
    Stream<Museum> readAll();
    Museum readByName(String name);

}
