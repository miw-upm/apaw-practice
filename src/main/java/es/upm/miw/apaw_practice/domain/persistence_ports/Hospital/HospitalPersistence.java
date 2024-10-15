package es.upm.miw.apaw_practice.domain.persistence_ports.Hospital;

import es.upm.miw.apaw_practice.domain.models.Hospital.Hospital;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalPersistence {


    // Method to create a new hospital
    Hospital create(Hospital hospital);
    boolean existsByName(String name);

}
