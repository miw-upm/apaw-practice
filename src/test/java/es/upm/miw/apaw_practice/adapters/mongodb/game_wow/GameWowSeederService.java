package es.upm.miw.apaw_practice.adapters.mongodb.game_wow;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.BossRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.Droprepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.FeatureRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.RaidRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.BossEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.DropEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.FeatureEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.RaidEntity;
import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class GameWowSeederService {

    @Autowired
    private BossRepository bossRepository;
    @Autowired
    private Droprepository dropRepository;
    @Autowired
    private FeatureRepository featureRepository;
    @Autowired
    private RaidRepository raidRepository;

    public void seedDatabase() {
        LogManager.getLogger(this.getClass()).warn("------- Game_wow Initial Load -----------");
        FeatureEntity[] features = {
                new FeatureEntity(new Feature("Trinket",158,null,null,"Use: Restores 1625 mana")),
                new FeatureEntity(new Feature("Neck",null,79,null,null)),
                new FeatureEntity(new Feature("Feet",106,null,null,"Use: Restores 1625 mana")),
                new FeatureEntity(new Feature("Chest",null,144,null,"Use: Restores 1625 mana")),
                new FeatureEntity(new Feature("Waist",106,null,null,null)),
                new FeatureEntity(new Feature("Legs",171,null,null,"Use: Restores 1625 mana"))
        };
        this.featureRepository.saveAll(Arrays.asList(features));

        DropEntity[] drops = {
                new DropEntity("Sliver of Pure Ice","mage,paladin,druid,priest,shaman,warlock",251,features[0]),
                new DropEntity("Marrowgar's Scratching Choker","paladin,warrior,dk",251,features[1]),
                new DropEntity("Boots of the Frozen Seed","druid,rogue",251,features[3]),
                new DropEntity("Ghoul Commander's Cuirass","paladin,warrior,dk",251,features[4]),
                new DropEntity("Cord of Dark Suffering","druid,rogue",251,features[4]),
                new DropEntity("Plaguebringer's Stained Pants","mage,priest,warlock",264,features[5])
        };
        this.dropRepository.saveAll(Arrays.asList(drops));

        BossEntity[] bosses = {
            new BossEntity("Lord Marrowgal", "10N", List.of(drops[0],drops[1])),
            new BossEntity("Lady DeathWhisper", "10N",List.of(drops[2],drops[3])),
            new BossEntity("GunShip Battle", "10N",List.of(drops[4])),
            new BossEntity("Festergut", "25N",List.of(drops[5]))
        };
        this.bossRepository.saveAll(Arrays.asList(bosses));

        RaidEntity[] raids = {
                new RaidEntity(new Date(), "ICC", "10N", 10, false, List.of(bosses[0], bosses[1], bosses[2])),
                new RaidEntity(new Date(), "ICC", "25N", 25, false, List.of(bosses[3]))
        };
        this.raidRepository.saveAll(Arrays.asList(raids));
    }

    public void deleteAll() {
        this.featureRepository.deleteAll();
        this.dropRepository.deleteAll();
        this.bossRepository.deleteAll();
    }
}
