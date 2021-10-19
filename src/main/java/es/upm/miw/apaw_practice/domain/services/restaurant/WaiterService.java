package es.upm.miw.apaw_practice.domain.services.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.TablePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.WaiterPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class WaiterService {

    private final WaiterPersistence waiterPersistence;
    private final TablePersistence tablePersistence;

    @Autowired
    public WaiterService(WaiterPersistence waiterPersistence, TablePersistence tablePersistence) {
        this.waiterPersistence = waiterPersistence;
        this.tablePersistence = tablePersistence;
    }

    public Stream<Waiter> findSection() {
        return this.waiterPersistence.findSection();
    }

    public Waiter create(Waiter waiter) {
        return this.waiterPersistence.create(waiter);
    }

    public Stream<Waiter> findByNumberTable(Integer number) {
        this.tablePersistence.existNumber(number);
        return this.waiterPersistence.findByNumberTable(number);
    }
}
