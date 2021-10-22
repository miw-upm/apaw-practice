package es.upm.miw.apaw_practice.domain.models.emarketer;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestConfig
public class CupsCompositeTest {


    private static final List<Cups> cups = new ArrayList<>();
    private static final List<CupsLeaf> cupsLeaf = new ArrayList<>();
    private static final List<CupsComposite> cupsComposite = new ArrayList<>();
    private static final CupsComposite treeFather = new CupsComposite("TreeFather");

    @BeforeAll
    static void createVehicles() {
        cups.add(new Cups("AAPPZZZ6KZ1R149943", new BigDecimal("844.56"), LocalDateTime.of(2021, 10, 12, 19, 00, 00), new Customer("Pedro", "Madrid", "particular")));
        cups.add(new Cups("AAPPZZZ6KZ1R149944", new BigDecimal("944.56"), LocalDateTime.of(2021, 10, 12, 19, 01, 00), new Customer("Paula", "Salamanca", "particular")));
        cups.add(new Cups("AAPPZZZ6KZ1R149945", new BigDecimal("1044.56"), LocalDateTime.of(2021, 10, 12, 19, 02, 00), new Customer("Juan", "Valladolid", "particular")));
        cupsLeaf.add(new CupsLeaf(cups.get(0)));
        cupsLeaf.add(new CupsLeaf(cups.get(1)));
        cupsLeaf.add(new CupsLeaf(cups.get(2)));
        cupsComposite.add(new CupsComposite("CupsAT"));
        cupsComposite.get(0).add(cupsLeaf.get(0));
        cupsComposite.get(0).add(cupsLeaf.get(1));
        cupsComposite.get(0).add(cupsLeaf.get(2));

        cups.add(new Cups("AAPPZZZ6KZ1R149946", new BigDecimal("544.56"), LocalDateTime.of(2021, 10, 12, 19, 00, 00), new Customer("Ramon", "Madrid", "particular")));
        cups.add(new Cups("AAPPZZZ6KZ1R149947", new BigDecimal("344.56"), LocalDateTime.of(2021, 10, 12, 19, 01, 00), new Customer("Lucas", "Salamanca", "particular")));
        cups.add(new Cups("AAPPZZZ6KZ1R149948", new BigDecimal("1144.56"), LocalDateTime.of(2021, 10, 12, 19, 02, 00), new Customer("Lola", "Valladolid", "particular")));
        cupsLeaf.add(new CupsLeaf(cups.get(0)));
        cupsLeaf.add(new CupsLeaf(cups.get(1)));
        cupsLeaf.add(new CupsLeaf(cups.get(2)));
        cupsComposite.add(new CupsComposite("CupsBT"));
        cupsComposite.get(1).add(cupsLeaf.get(0));
        cupsComposite.get(1).add(cupsLeaf.get(1));
        cupsComposite.get(1).add(cupsLeaf.get(2));

        treeFather.add(cupsComposite.get(0));
        treeFather.add(cupsComposite.get(1));
    }

    @AfterAll
    static void deleteAll() {
        cups.clear();
        cupsLeaf.clear();
        cupsComposite.clear();
    }

    @Test
    void testCompositePattern() {
        assertTrue(cupsComposite.get(0).isComposite());
        assertEquals("CupsAT", cupsComposite.get(0).getName());
        cupsComposite.get(0).remove(cupsLeaf.get(0));
        assertEquals(2, cupsComposite.get(0).getCupsComponents().size());
        assertEquals(3, cupsComposite.get(0).numberOfNodes());

        cupsComposite.get(0).add(cupsLeaf.get(0));
        assertEquals(4, cupsComposite.get(0).numberOfNodes());
        assertEquals(3, cupsComposite.get(0).numberOfLeafNodes());
        assertEquals(1, cupsComposite.get(0).numberOfCompositeNodes());

        assertEquals(4, cupsComposite.get(1).numberOfNodes());
        assertEquals(3, cupsComposite.get(1).numberOfLeafNodes());
        assertEquals(1, cupsComposite.get(1).numberOfCompositeNodes());

        assertEquals(9, treeFather.numberOfNodes());
        assertEquals(6, treeFather.numberOfLeafNodes());
        assertEquals(3, treeFather.numberOfCompositeNodes());
    }

}
