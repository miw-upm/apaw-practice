package es.upm.miw.apaw_practice.domain.services.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.WaiterPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class WaiterService {

    private final WaiterPersistence waiterPersistence;

    @Autowired
    public WaiterService(WaiterPersistence waiterPersistence){
        this.waiterPersistence = waiterPersistence;
    }

    public Stream<Waiter> findBySectionAndCategory(String section, String category) {
        return this.waiterPersistence.findBySectionAndCategory(section, category);
    }

    public Waiter create(Waiter waiter) {
        return this.waiterPersistence.create(waiter);
    }
}
