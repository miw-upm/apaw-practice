package es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.Owner;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerClinicPersistence {

    Owner create(Owner owner);

    boolean existName(String name);

    Owner read(String name);
}