package es.upm.miw.apaw_practice.domain.services.bank;

import es.upm.miw.apaw_practice.domain.models.bank.Client;
import es.upm.miw.apaw_practice.domain.persistence_ports.bank.ClientPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientPersistence clientPersistence;

    @Autowired
    public ClientService(ClientPersistence clientPersistence) {
        this.clientPersistence = clientPersistence;
    }


    public Client findByDni(String dni) {
        return this.clientPersistence.findByDni(dni);
    }

    public Client updateName(String dni, String name){
        return this.clientPersistence.updateName(dni,name);
    }
}
