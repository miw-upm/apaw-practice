package es.upm.miw.apaw_practice.domain.persistence_ports.car_workshop;

import org.springframework.stereotype.Repository;

@Repository
public interface TyrePersistence {
    void deleteManufacturer(String manufacturer);
}
