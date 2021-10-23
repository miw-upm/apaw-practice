package es.upm.miw.apaw_practice.domain.persistence_ports.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Emarketer;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;


@Repository
public interface EmarketerPersistence {

    void delete(String name);

    Stream<Emarketer> readAll();

    Stream<Emarketer> readByCups(String cups);

    Stream<Emarketer> readByPlanDescription(String description);

}
