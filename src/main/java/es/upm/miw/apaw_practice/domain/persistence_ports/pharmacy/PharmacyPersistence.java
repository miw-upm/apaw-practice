package es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyPersistence {

    Pharmacy create(Pharmacy pharmacy);
}


