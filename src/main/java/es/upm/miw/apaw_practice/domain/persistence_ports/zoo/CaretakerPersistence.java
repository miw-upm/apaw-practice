package es.upm.miw.apaw_practice.domain.persistence_ports.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Caretaker;
import org.springframework.stereotype.Repository;

@Repository
public interface CaretakerPersistence {

    Caretaker findByDni(String dni);

}
