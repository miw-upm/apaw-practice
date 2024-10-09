package es.upm.miw.apaw_practice.domain.services.military;

import es.upm.miw.apaw_practice.domain.persistence_ports.military.WeaponPersistence;
import org.springframework.stereotype.Service;

@Service
public class WeaponService {
    private final WeaponPersistence weaponPersistence;

    public WeaponService(WeaponPersistence weaponPersistence) { this.weaponPersistence = weaponPersistence; }

    public void delete(String serialCode) { this.weaponPersistence.delete(serialCode); }
}
