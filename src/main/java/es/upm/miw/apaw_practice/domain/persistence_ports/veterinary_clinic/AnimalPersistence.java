package es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Animal;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalPersistence {

    Animal readByName(String name);

    Animal update(Animal animal);
}