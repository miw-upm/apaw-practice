package es.upm.miw.apaw_practice.domain.services.military;

import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.Weapon;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.MissionPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.WeaponPersistence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class WeaponService {
    private final WeaponPersistence weaponPersistence;
    private final MissionPersistence missionPersistence;

    public WeaponService(WeaponPersistence weaponPersistence, MissionPersistence missionPersistence) {
        this.weaponPersistence = weaponPersistence;
        this.missionPersistence = missionPersistence;
    }

    public void delete(String serialCode) {
        this.weaponPersistence.delete(serialCode);
    }

    public Stream<String> findDistinctSoldierRanksByManufacturer(String manufacturer) {
        List<Weapon> weapons = this.weaponPersistence.findByManufacturer(manufacturer).toList();

        return this.missionPersistence.readAll()
                .filter(mission -> mission.getWeapons().stream()
                        .anyMatch(weapons::contains))
                .flatMap(mission -> mission.getUnit().getSoldiers().stream())
                .map(Soldier::getRank)
                .distinct();
    }
}
