package es.upm.miw.apaw_practice.domain.persistence_ports.restaurant;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.TableEntity;
import es.upm.miw.apaw_practice.domain.models.restaurant.Reserve;
import es.upm.miw.apaw_practice.domain.models.restaurant.Table;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TablePersistence {

    boolean existNumber(Integer number);

    Stream<Reserve> readHoldersByNumber(Integer number);

    Table updateNumPeople(Table table);

    TableEntity readByNumber(Integer id);

    Stream<Table> readAll();

    Table update(Table table);

    Table findByCategoryWaiter(String category);
}
