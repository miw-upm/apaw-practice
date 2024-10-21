package es.upm.miw.apaw_practice.domain.models.military;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@TestConfig
class SoldierTest {
    private SoldierComponent squad;
    private SoldierComponent platoon;

    @BeforeEach
    void init() {
        SoldierComponent soldier1 = new SoldierLeaf(new Soldier("99887766X", "Asela Martín Calvo", "Private", LocalDate.of(1986, 7, 22)));
        SoldierComponent soldier2 = new SoldierLeaf(new Soldier("55443322Y", "Manuel Rodríguez Belda", "Private", LocalDate.of(1999, 11, 4)));
        SoldierComponent soldier3 = new SoldierLeaf(new Soldier("22110099B", "Jorge Patino López", "Captain", LocalDate.of(1972, 1, 15)));

        this.squad = new SoldierComposite();
        this.squad.add(soldier1);
        this.squad.add(soldier2);

        this.platoon = new SoldierComposite();
        this.platoon.add(squad);
        this.platoon.add(soldier3);
    }

    @Test
    void testCompositeIdentityDocument() {
        assertEquals("99887766X, 55443322Y", this.squad.getIdentityDocument());
        assertEquals("99887766X, 55443322Y, 22110099B", this.platoon.getIdentityDocument());
    }

    @Test
    void testCompositeFullName() {
        assertEquals("Asela Martín Calvo, Manuel Rodríguez Belda, Jorge Patino López", this.platoon.getFullName());
    }

    @Test
    void testCompositeRank() {
        assertEquals("Private", this.squad.getRank());
        assertEquals("Private, Captain", this.platoon.getRank());
    }

    @Test
    void testCompositeBirthDate() {
        assertEquals(LocalDate.of(1986, 7, 22), this.squad.getBirthDate());
        assertEquals(LocalDate.of(1972, 1, 15), this.platoon.getBirthDate());
    }

    @Test
    void testBuilderFull() {
        Soldier soldier = Soldier.builder()
                .identityDocument("X8776655E")
                .fullName("Enya Cox Dempsey")
                .rank("Lieutenant")
                .birthDate(LocalDate.of(1988, 10, 21))
                .build();
        assertEquals("X8776655E", soldier.getIdentityDocument());
        assertEquals("Enya Cox Dempsey", soldier.getFullName());
        assertEquals("Lieutenant", soldier.getRank());
        assertEquals(LocalDate.of(1988, 10, 21), soldier.getBirthDate());
    }

    @Test
    void testBuilderPartial() {
        Soldier soldier = Soldier.builder()
                .identityDocument("Z4332211Y")
                .fullName("Cornelia Jakobs")
                .rank("Captain")
                .build();
        assertEquals("Z4332211Y", soldier.getIdentityDocument());
        assertEquals("Cornelia Jakobs", soldier.getFullName());
        assertEquals("Captain", soldier.getRank());
        assertNull(soldier.getBirthDate());
    }
}
