package es.upm.miw.apaw_practice.domain.persistence_ports.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ClientPersistence {
    Stream<Waiter> readCategoryBySectionWaiterAndDniClient(String dni, String section);

    boolean existDni(String dni);

    void delete(String dni);
}
