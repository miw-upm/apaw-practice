package es.upm.miw.apaw_practice.adapters.mongodb.bank.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.bank.daos.ClientRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.bank.entities.ClientEntity;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.bank.Client;
import es.upm.miw.apaw_practice.domain.persistence_ports.bank.ClientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("clientPersistence")
public class ClientPersistenceMongodb implements ClientPersistence {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientPersistenceMongodb(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client findByDni(String dni) {
        return this.clientRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException(" Client dni: " + dni))
                .toClient();
    }

    @Override
    public Client updateName(String dni, String name) {
        ClientEntity clientEntity = this.clientRepository.findByDni(dni)
                .orElseThrow(() -> new NotFoundException(" Client dni: " + dni));
        clientEntity.setName(name);
        return this.clientRepository.save(clientEntity).toClient();
    }

}
