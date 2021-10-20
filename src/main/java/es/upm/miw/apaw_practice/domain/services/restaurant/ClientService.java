package es.upm.miw.apaw_practice.domain.services.restaurant;

import es.upm.miw.apaw_practice.domain.exceptions.ConflictException;
import es.upm.miw.apaw_practice.domain.models.restaurant.Client;
import es.upm.miw.apaw_practice.domain.persistence_ports.restaurant.ClientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientPersistence clientPersistence;

    @Autowired
    public ClientService( ClientPersistence clientPersistence){
        this.clientPersistence = clientPersistence;
    }

    public Client read(String dni) {
        this.assertDniExist(dni);
        return this.clientPersistence.readByDni(dni);
    }

    public void assertDniExist(String dni){
        if(!this.clientPersistence.existDni(dni)){
            throw new ConflictException("DNI no exist: "+dni);
        }
    }

    public void delete(String dni) {
        this.clientPersistence.delete(dni);
    }
}
