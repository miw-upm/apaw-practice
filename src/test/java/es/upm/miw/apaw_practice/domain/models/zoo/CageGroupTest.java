package es.upm.miw.apaw_practice.domain.models.zoo;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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

    @Test
    void testAddition() {
        Cage cageA = new Cage(100.0, "A7", new Caretaker());
        Cage cageB = new Cage(35.0, "B2BB", new Caretaker());
        Cage cageC= new Cage(10.0, "R2D2", new Caretaker());
        CageGroup[] cagesA = {
                new SingleCage(cageA),
                new SingleCage(cageB)
        };
        CageGroup cageGroupA = new CompositeCage(new ArrayList<>(List.of(cagesA)));
        Assertions.assertEquals("A7B2BB", cageGroupA.getLocationCode());
        cageGroupA.add(new SingleCage(cageC));
        Assertions.assertEquals("A7B2BBR2D2", cageGroupA.getLocationCode());
        cageGroupA.add(new CompositeCage(List.of(cagesA)));
        Assertions.assertEquals("A7B2BBR2D2A7B2BB", cageGroupA.getLocationCode());
        SingleCage singleCage = new SingleCage(cageA);
        Assertions.assertEquals("A7", singleCage.getLocationCode());
        singleCage.add(new SingleCage(cageC));
        Assertions.assertEquals("A7", singleCage.getLocationCode());
    }

    @Test
    void testRemoval() {
        Cage cageA = new Cage(100.0, "A7", new Caretaker());
        Cage cageB = new Cage(35.0, "B2BB", new Caretaker());
        Cage cageC = new Cage(10.0, "R2D2", new Caretaker());
        CageGroup[] cagesA = {
                new SingleCage(cageA),
                new SingleCage(cageB)
        };
        CageGroup cageGroupA = new CompositeCage(new ArrayList<>(List.of(cagesA)));
        SingleCage singleCage = new SingleCage(cageC);
        cageGroupA.add(singleCage);
        Assertions.assertEquals("A7B2BBR2D2", cageGroupA.getLocationCode());
        cageGroupA.remove(singleCage);
        Assertions.assertEquals("A7B2BB", cageGroupA.getLocationCode());
        CageGroup cageGroupB = new CompositeCage(new ArrayList<>(List.of(cagesA)));
        cageGroupA.add(cageGroupB);
        Assertions.assertEquals("A7B2BBA7B2BB", cageGroupA.getLocationCode());
        cageGroupA.remove(cageGroupB);
        Assertions.assertEquals("A7B2BB", cageGroupA.getLocationCode());
        singleCage.add(cageGroupA);
        Assertions.assertEquals("R2D2", singleCage.getLocationCode());
    }
}
