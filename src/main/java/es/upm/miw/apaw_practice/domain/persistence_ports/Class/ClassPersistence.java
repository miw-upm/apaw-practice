package es.upm.miw.apaw_practice.domain.persistence_ports.Class;

import es.upm.miw.apaw_practice.domain.models.Class.Class;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ClassPersistence {

    Stream<Class> readAll();

    Class create(Class myClass);

}