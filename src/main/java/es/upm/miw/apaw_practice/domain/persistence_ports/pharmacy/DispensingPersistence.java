package es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Dispensing;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DispensingPersistence {

    Dispensing update(Dispensing dispensing);

    Dispensing readById(String id);

    Stream<Dispensing> readAll();

    void delete(String id);
}
