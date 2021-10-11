package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.TableRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.ReserveEntity;
import es.upm.miw.apaw_practice.domain.models.restaurant.Reserve;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.TablePersistence;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("tablePersistence")
public class TablePersistenceMongodb implements TablePersistence {

    private final TableRepository tableRepository;

    public TablePersistenceMongodb(TableRepository tableRepository){
        this.tableRepository = tableRepository;
    }

    @Override
    public boolean existNumber(Integer number) {
        return this.tableRepository.findByNumber(number)
                .isPresent();
    }

    @Override
    public Stream<Reserve> readHoldersByNumber(Integer number) {
        return this.tableRepository.findAll().stream()
                .filter(table -> number.equals(table.getNumber()))
                .flatMap(table -> table.getReserves().stream())
                .map(ReserveEntity::toReserve);
    }
}
