package es.upm.miw.apaw_practice.domain.persistence_ports.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface WaiterPersistence {
    Stream<Waiter> findBySectionAndCategory(String section, String category);
}
