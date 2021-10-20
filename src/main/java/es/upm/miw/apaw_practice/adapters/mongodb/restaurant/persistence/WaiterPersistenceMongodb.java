package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.ClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.WaiterRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.ClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.WaiterEntity;
import es.upm.miw.apaw_practice.domain.models.restaurant.Waiter;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.WaiterPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("waiterPersistence")
public class WaiterPersistenceMongodb implements WaiterPersistence {

    private final WaiterRepository waiterRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public WaiterPersistenceMongodb(WaiterRepository waiterRepository, ClientRepository clientRepository){
        this.waiterRepository = waiterRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public Stream<Waiter> findSection() {
        return this.waiterRepository.findAll().stream()
                .map(WaiterEntity::toWaiter)
                .map(Waiter::ofSection);
    }

    @Override
    public Waiter create(Waiter waiter) {
        return this.waiterRepository
                .save(new WaiterEntity(waiter))
                .toWaiter();
    }

    private Stream<String> getSections(Integer number){
        return this.clientRepository.findAll().stream()
                .map(ClientEntity::toClient)
                .filter(client -> number.equals(client.getTable().getNumber()))
                .flatMap(client -> client.getWaiters().stream())
                .map(Waiter::getSection)
                .distinct();
    }

    @Override
    public Stream<Waiter> findByNumberTable(Integer number) {
        return this.getSections(number)
                .map(section -> {
                    Waiter waiter = new Waiter();
                    waiter.setSection(section);
                    return waiter;
                });
    }


}
