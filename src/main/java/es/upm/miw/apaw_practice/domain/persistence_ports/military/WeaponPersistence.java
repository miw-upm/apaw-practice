package es.upm.miw.apaw_practice.domain.persistence_ports.military;

import es.upm.miw.apaw_practice.domain.models.military.Weapon;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface WeaponPersistence {
    void delete(String serialCode);
    Stream<Weapon> findByManufacturer(String manufacturer);
}
