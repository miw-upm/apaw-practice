package es.upm.miw.apaw_practice.domain.models.zoo;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

@TestConfig
class CageGroupTest {

    @Test
    void testSingleCage() {
        Cage cage = new Cage(100.0, "A7", new Caretaker());
        CageGroup cageGroup = new SingleCage(cage);
        Assertions.assertEquals("A7", cageGroup.getLocationCode());
    }

    @Test
    void testCompositeCage() {
        Cage cageA = new Cage(100.0, "A7", new Caretaker());
        Cage cageB = new Cage(35.0, "B2BB", new Caretaker());
        Cage cageC= new Cage(10.0, "R2D2", new Caretaker());
        CageGroup[] cagesA = {
            new SingleCage(cageA),
            new SingleCage(cageB)
        };
        CageGroup cageGroupA = new CompositeCage(List.of(cagesA));
        CageGroup[] cagesB = {
                cageGroupA,
                new SingleCage(cageC)
        };
        CageGroup cageGroupB = new CompositeCage(List.of(cagesB));
        Assertions.assertEquals("A7B2BB", cageGroupA.getLocationCode());
        Assertions.assertEquals("A7B2BBR2D2", cageGroupB.getLocationCode());
    }
}
