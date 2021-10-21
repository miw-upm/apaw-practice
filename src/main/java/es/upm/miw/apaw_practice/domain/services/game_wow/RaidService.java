package es.upm.miw.apaw_practice.domain.services.game_wow;

import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import es.upm.miw.apaw_practice.domain.models.game_wow.Raid;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.BossPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.DropPersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.FeaturePersistence;
import es.upm.miw.apaw_practice.domain.persistence_ports.game_wow.RaidPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class RaidService {

    private final RaidPersistence raidPersistence;
    private final FeaturePersistence featurePersistence;
    private final DropPersistence dropPersistence;
    private final BossPersistence bossPersistence;

    @Autowired
    public RaidService(RaidPersistence raidPersistence, FeaturePersistence featurePersistence, DropPersistence dropPersistence, BossPersistence bossPersistence) {
        this.raidPersistence = raidPersistence;
        this.featurePersistence = featurePersistence;
        this.dropPersistence = dropPersistence;
        this.bossPersistence = bossPersistence;
    }

    public Raid updateDificultyRaid(String id, String dificulty) {
        Raid raid = this.raidPersistence.readById(id);
        raid.setDificulty(dificulty);
        return this.raidPersistence.update(raid);
    }

    public Integer findPlayerNumberAdditionBySpellPower(Integer spellPower) {
        List<Feature> features = featurePersistence.readBySpellPower(spellPower);
        List<Drop> dropList = dropPersistence.readAll()
                .filter(drop -> features.contains(drop.getFeature()))
                .collect(Collectors.toList());
        List<Boss> bossList = bossPersistence.readAll()
                .filter(boss -> boss.getDropList().stream()
                        .filter(drop -> dropList. contains(drop))
                        .findAny()
                        .isPresent())
                .collect(Collectors.toList());
        return raidPersistence.readAll()
                .filter(raid -> raid.getBossList().stream()
                        .filter(boss -> bossList.contains(boss))
                        .findAny()
                        .isPresent())
                .map(raid -> raid.getPlayerNumber())
                .reduce((playerNumber1, playerNumber2) -> playerNumber1 + playerNumber2)
                .orElse(0);
    }
}
