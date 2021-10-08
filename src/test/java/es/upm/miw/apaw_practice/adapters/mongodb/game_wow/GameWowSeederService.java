package es.upm.miw.apaw_practice.adapters.mongodb.game_wow;

import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.BossRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.Droprepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.FeatureRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.daos.RaidRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.BossEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.DropEntity;
import es.upm.miw.apaw_practice.adapters.mongodb.game_wow.entities.FeatureEntity;
import es.upm.miw.apaw_practice.domain.models.game_wow.Boss;
import es.upm.miw.apaw_practice.domain.models.game_wow.Drop;
import es.upm.miw.apaw_practice.domain.models.game_wow.Feature;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

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
                //Sliver of Pure Ice
                new FeatureEntity(new Feature("Trinket",158,null,null,"Use: Restores 1625 mana")),
                //Marrowgar's Scratching Choker
                new FeatureEntity(new Feature("Neck",null,79,null,null)),
                //Boots of the Frozen Seed
                new FeatureEntity(new Feature("Feet",106,null,null,"Use: Restores 1625 mana")),
                //Ghoul Commander's Cuirass
                new FeatureEntity(new Feature("chest",null,144,null,"Use: Restores 1625 mana")),
                //
                new FeatureEntity(new Feature("Trinket",158,null,null,"Use: Restores 1625 mana")),
                //
                new FeatureEntity(new Feature("Trinket",158,null,null,"Use: Restores 1625 mana")),
        };
        this.featureRepository.saveAll(Arrays.asList(features));

        DropEntity[] drops = {
                new DropEntity(null,251,features[0]),
                new DropEntity(null,251,features[1]),
                new DropEntity(null,251,features[3]),
                new DropEntity("Human, Orc, Dwarf, Night Elf, Undead, Tauren, Gnome, Troll, Blood Elf, Draenei",
                        251,features[4]),
                //new DropEntity(null,251,features[]),
                //new DropEntity(null,251,features[]),
        };


        BossEntity[] bosses = {
            /*new BossEntity(new Boss("Lord Marrowgal", "10N", List.of(features[0], features[1])),
            new BossEntity(new Boss("Lady DeathWhisper", "10N",)),
            new BossEntity(new Boss("GunShip Battle", "10N",)),
            new BossEntity(new Boss("DeathBringer Saurfang", "10N",)),
            new BossEntity(new Boss("Festergut", "10N",)),
            new BossEntity(new Boss("Rotface", "10N",))*/
        };
    }
}
