package es.upm.miw.apaw_practice.domain.persistence_ports.theme_park;

import es.upm.miw.apaw_practice.domain.models.theme_park.Operator;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface OperatorPersistence {

    Operator readByIdEmployee(String idEmployee);

    void delete(String idEmployee);

    Stream<Operator> readAll();
}
