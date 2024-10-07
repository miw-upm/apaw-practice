package es.upm.miw.apaw_practice.domain.persistence_ports.boardgame_cafe;

import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Customer;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface CustomerPersistence {
    Stream<Customer> readAll();

    Customer create(Customer customer);

    Customer update(String email, Customer customer);

    Customer read(String email);

    boolean existEmail(String email);
}
