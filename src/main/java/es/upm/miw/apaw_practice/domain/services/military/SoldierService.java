package es.upm.miw.apaw_practice.domain.services.military;

import es.upm.miw.apaw_practice.domain.models.military.Soldier;
import es.upm.miw.apaw_practice.domain.models.military.SoldierRankUpdating;
import es.upm.miw.apaw_practice.domain.persistence_ports.military.SoldierPersistence;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class SoldierService {
    private final SoldierPersistence soldierPersistence;

    public SoldierService(SoldierPersistence soldierPersistence) {
        this.soldierPersistence = soldierPersistence;
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
}
