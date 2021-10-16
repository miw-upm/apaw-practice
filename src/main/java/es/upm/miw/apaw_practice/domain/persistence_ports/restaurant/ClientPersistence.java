package es.upm.miw.apaw_practice.domain.persistence_ports.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.Client;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface ClientPersistence {
    Stream<String> readCategoryBySectionWaiterAndDniClient(String dni, String section);

    boolean existDni(String dni);

    void delete(String dni);

    Stream<Client> readAll();
}