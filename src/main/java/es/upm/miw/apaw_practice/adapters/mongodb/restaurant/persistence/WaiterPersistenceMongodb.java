package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.WaiterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.WaiterEntity;
import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.WaiterPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("waiterPersistence")
public class WaiterPersistenceMongodb implements WaiterPersistence {

    private final WaiterRepository waiterRepository;

    @Autowired
    public WaiterPersistenceMongodb(WaiterRepository waiterRepository){
        this.waiterRepository = waiterRepository;
    }

    @Override
    public Stream<Waiter> findBySectionAndCategory(String section, String category) {
        return this.waiterRepository.findAll().stream()
                .filter(waiter -> section.equals(waiter.getSection()))
                .filter(waiter -> category.equals(waiter.getCategory()))
                .map(WaiterEntity::toWaiter);
    }
}
