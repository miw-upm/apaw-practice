package es.upm.miw.apaw_practice.domain.persistence_ports.veterinary_clinic;

import es.upm.miw.apaw_practice.domain.models.veterinay_clinic.OwnerClinic;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerClinicPersistence {

    OwnerClinic create(OwnerClinic ownerClinic);

    boolean existName(String name);

    OwnerClinic read(String name);

    OwnerClinic update(String address, String phone, OwnerClinic ownerClinic);
}