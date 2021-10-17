package es.upm.miw.apaw_practice.domain.persistence_ports.emarketer;

import es.upm.miw.apaw_practice.domain.models.emarketer.Customer;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CustomerPersistence {
    Stream<Customer> readAll();
}
