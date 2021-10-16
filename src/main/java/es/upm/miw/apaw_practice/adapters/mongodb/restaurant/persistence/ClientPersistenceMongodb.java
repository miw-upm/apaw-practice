package es.upm.miw.apaw_practice.adapters.mongodb.restaurant.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.daos.ClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.ClientEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.restaurant.entities.WaiterEntity;
import es.upm.miw.apaw_practice.domain.models.restaurant.Client;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.ClientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository("clientPersistence")
public class ClientPersistenceMongodb implements ClientPersistence {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientPersistenceMongodb(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public Stream<String> readCategoryBySectionWaiterAndDniClient(String dni, String section) {
        return this.clientRepository.findAll().stream()
                .filter(client -> dni.equals(client.getDni()))
                .flatMap(client -> client.getWaiters().stream())
                .filter(waiter -> section.equals(waiter.getSection()))
                .map(WaiterEntity::getCategory);
    }

    @Override
    public boolean existDni(String dni) {
        return this.clientRepository.findByDni(dni).isPresent();
    }

    @Override
    public void delete(String dni) {
        this.clientRepository.deleteByDni(dni);
    }

    @Override
    public Stream<Client> readAll() {
        return this.clientRepository.findAll()
                .stream().map(ClientEntity::toClient);
    }

}