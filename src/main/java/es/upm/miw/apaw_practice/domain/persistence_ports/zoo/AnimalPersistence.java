package es.upm.miw.apaw_practice.domain.persistence_ports.zoo;

import es.upm.miw.apaw_practice.domain.models.zoo.Animal;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalPersistence {

    void delete(Animal animal);

}
