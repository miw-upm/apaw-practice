package es.upm.miw.apaw_practice.domain.services.military;

import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.SoldierRankUpdating;
import es.upm.miw.apaw_practice.domain.models.military.Weapon;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.MissionPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.SoldierPersistence;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class SoldierService {
    private final SoldierPersistence soldierPersistence;
    private final MissionPersistence missionPersistence;

    public SoldierService(SoldierPersistence soldierPersistence, MissionPersistence missionPersistence) {
        this.soldierPersistence = soldierPersistence;
        this.missionPersistence = missionPersistence;
    }

    public Stream<Soldier> readAll() {
        return this.soldierPersistence.readAll();
    }

    public void updateRanks(Stream<SoldierRankUpdating> soldierRankUpdatingStream) {
        soldierRankUpdatingStream.map(soldierNewRank -> {
                    Soldier soldier = this.soldierPersistence.read(soldierNewRank.getIdentityDocument());
                    soldier.setRank(soldierNewRank.getRank());
                    return soldier;
                })
                .forEach(soldier -> this.soldierPersistence.update(soldier.getIdentityDocument(), soldier));
    }

    public long countDistinctWeaponsByFullName(String fullName) {
        List<Soldier> soldiers = this.soldierPersistence.findByFullName(fullName).toList();

        return this.missionPersistence.readAll()
                .filter(mission -> mission.getUnit().getSoldiers().stream()
                        .anyMatch(soldiers::contains))
                .flatMap(mission -> mission.getWeapons().stream())
                .map(Weapon::getSerialCode)
                .distinct()
                .count();
    }
}
