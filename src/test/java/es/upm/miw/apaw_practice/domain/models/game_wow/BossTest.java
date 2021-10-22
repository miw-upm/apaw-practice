package es.upm.miw.apaw_practice.domain.models.game_wow;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BossTest {

    private static final List<Boss> bossesList = new ArrayList<>();
    private static final List<TreeBossLeaf> treeBossLeafList = new ArrayList<>();
    private static final List<TreeBossComposite> treeBossCompositeList = new ArrayList<>();
    private static final TreeBossComposite treeFatherComposite= new TreeBossComposite("TreeFatherComposite");

    @BeforeEach
    void before() {
        Feature[] features = {
                Feature.builder()
                        .part("Trinket")
                        .spellPower(158)
                        .meleeAtack(null)
                        .temple(0)
                        .extraSpell("Use: Restores 1625 mana")
                        .build(),
                Feature.builder()
                        .part("Neck")
                        .spellPower(null)
                        .meleeAtack(79)
                        .temple(0)
                        .extraSpell(null)
                        .build()
        };
        Drop[] drops = {
                new Drop("Sliver of Pure Ice", "mage,paladin,druid,priest,shaman,warlock", 251, features[0]),
                new Drop("Marrowgar's Scratching Choker", "paladin,warrior,dk", 251, features[1]),
                new Drop("Boots of the Frozen Seed", "druid,rogue", 251, features[1])
        };

        this.bossesList.add(new Boss("Lord Marrowgal H", "25H", List.of(drops[0], drops[1])));
        this.bossesList.add(new Boss("Lady DeathWhisper H", "25H", List.of(drops[2])));
        this.bossesList.add( new Boss("GunShip Battle H", "25H", List.of(drops[1])));
        this.bossesList.add(new Boss("Festergut H", "25H", List.of(drops[0])));

        treeBossLeafList.add(new TreeBossLeaf(bossesList.get(0)));
        treeBossLeafList.add(new TreeBossLeaf(bossesList.get(1)));
        treeBossLeafList.add(new TreeBossLeaf(bossesList.get(2)));
        treeBossLeafList.add(new TreeBossLeaf(bossesList.get(3)));

        treeBossCompositeList.add(new TreeBossComposite("Boss"));
        treeBossCompositeList.get(0).add(new TreeBossLeaf(bossesList.get(0)));
        treeBossCompositeList.get(0).add(new TreeBossLeaf(bossesList.get(1)));

        treeBossCompositeList.add(new TreeBossComposite("Mini Boss"));
        treeBossCompositeList.get(1).add(new TreeBossLeaf(bossesList.get(2)));
        treeBossCompositeList.get(1).add(new TreeBossLeaf(bossesList.get(3)));

        treeFatherComposite.add(treeBossCompositeList.get(0));
        treeFatherComposite.add(treeBossCompositeList.get(1));
    }

    @Test
    void testDirectorLeaf() {
        assertFalse(treeBossLeafList.get(0).isComposite());
        assertEquals("Lord Marrowgal H", treeBossLeafList.get(0).description());
    }

    @Test
    void testCompositePattern() {
        assertTrue(treeBossCompositeList.get(0).isComposite());
        assertEquals("Boss", treeBossCompositeList.get(0).getName());
        assertEquals("Mini Boss", treeBossCompositeList.get(1).getName());
        assertEquals("Festergut H", (treeBossCompositeList.get(1).getTreeBossList().get(1).description()));
        assertEquals(4, treeFatherComposite.getTreeBossList().size());
        assertEquals("25H", treeBossCompositeList.get(0).getTreeBossList().get(0).effort());
        assertEquals("TreeFatherComposite", treeFatherComposite.getName());
    }

    @AfterAll
    static void deleteAll() {
        bossesList.clear();
        treeBossLeafList.clear();
        treeBossCompositeList.clear();
    }
}
