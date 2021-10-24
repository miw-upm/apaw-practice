package es.upm.miw.apaw_practice.domain.persistence_ports.pharmacy;

import es.upm.miw.apaw_practice.domain.models.pharmacy.Pharmacy;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface PharmacyPersistence {

    Pharmacy create(Pharmacy pharmacy);

    Pharmacy readByRegistrationNumber(String registrationNumber);

    Stream<Pharmacy> readAll();

    Pharmacy update(String registrationNumber, Pharmacy pharmacy);
}
