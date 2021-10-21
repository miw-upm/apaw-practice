package es.upm.miw.apaw_practice.domain.persistence_ports.Class;

import org.springframework.stereotype.Repository;
import es.upm.miw.apaw_practice.domain.models.Class.Class;
import java.util.stream.Stream;

@Repository
public interface ClassPersistence {

    Stream<es.upm.miw.apaw_practice.domain.models.Class.Class> readAll();
    Class create(Class myClass);

}