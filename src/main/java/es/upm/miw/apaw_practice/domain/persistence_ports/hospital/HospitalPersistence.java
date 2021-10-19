package es.upm.miw.apaw_practice.domain.persistence_ports.hospital;

import es.upm.miw.apaw_practice.domain.models.hospital.Hospital;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalPersistence {

    Hospital create(Hospital hospital);
}