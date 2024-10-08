package es.upm.miw.apaw_practice.domain.persistence_ports.military;

import org.springframework.stereotype.Repository;

@Repository
public interface WeaponPersistence {
    void delete(String serialCode);
}
