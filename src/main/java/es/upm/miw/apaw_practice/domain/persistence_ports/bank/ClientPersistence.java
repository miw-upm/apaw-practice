package es.upm.miw.apaw_practice.domain.persistence_ports.bank;

import es.upm.miw.apaw_practice.domain.models.bank.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientPersistence {

    Client findByDni(String dni);
}
