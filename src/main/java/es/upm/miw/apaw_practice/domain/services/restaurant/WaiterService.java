package es.upm.miw.apaw_practice.domain.services.restaurant;

import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.ClientPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.WaiterPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class WaiterService {

    private final WaiterPersistence waiterPersistence;
    private final ClientPersistence clientPersistence;

    @Autowired
    public WaiterService(WaiterPersistence waiterPersistence, ClientPersistence clientPersistence) {
        this.waiterPersistence = waiterPersistence;
        this.clientPersistence = clientPersistence;
    }

    public Stream<Waiter> findBySectionAndCategory(String section, String category) {
        return this.waiterPersistence.findBySectionAndCategory(section, category);
    }

    public Waiter create(Waiter waiter) {
        return this.waiterPersistence.create(waiter);
    }

    private Stream<String> getSections(Integer number){
        return this.clientPersistence.readAll()
                .filter(client -> number.equals(client.getTable().getNumber()))
                .flatMap(client -> client.getWaiters().stream())
                .map(Waiter::getSection)
                .distinct();
    }

    public Stream<Waiter> findByNumberTable(Integer number) {
        return this.getSections(number)
                .map(section -> {
                    Waiter waiter = new Waiter();
                    waiter.setSection(section);
                    return waiter;
                });
    }
}
