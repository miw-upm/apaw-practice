package es.upm.miw.apaw_practice.domain.persistence_ports.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.Reserve;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TablePersistence {

    boolean existNumber(Integer number);

    Stream<Reserve> readHoldersByNumber(Integer number);
}
