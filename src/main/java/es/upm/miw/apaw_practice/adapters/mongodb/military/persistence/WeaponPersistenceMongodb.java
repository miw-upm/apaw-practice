package es.upm.miw.apaw_practice.adapters.mongodb.military.persistence;

import es.upm.miw.apaw_practice.adapters.mongodb.military.daos.WeaponRepository;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.WeaponPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("weaponPersistence")
public class WeaponPersistenceMongodb implements WeaponPersistence {
    private final WeaponRepository weaponRepository;

    @Autowired
    public WeaponPersistenceMongodb(WeaponRepository weaponRepository) { this.weaponRepository = weaponRepository; }

    @Override
    public void delete(String serialCode) { this.weaponRepository.deleteBySerialCode(serialCode); }
}
