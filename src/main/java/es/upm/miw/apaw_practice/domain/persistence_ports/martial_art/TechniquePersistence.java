package es.upm.miw.apaw_practice.domain.persistence_ports.martial_art;

import es.upm.miw.apaw_practice.domain.models.martial_art.Technique;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("techniquePersistence")
public interface TechniquePersistence {

    Stream<Technique> readAll();

    Technique create(Technique technique);

    Technique read(String name);

    void delete(String name);
}
