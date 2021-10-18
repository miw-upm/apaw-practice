package es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Drug;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface DrugPersistence {
    Stream<Drug> readAll();
}
