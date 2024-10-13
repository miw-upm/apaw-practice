package es.upm.miw.apaw_practice.domain.models.car;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class OwnerCarTest {

    private OwnerCarComponent ownerCarComponent;
    private OwnerCarComponent ownerCarComposite;

    private OwnerCarComponent ownerCarLeaf1;

    private OwnerCarComponent ownerCarLeaf2;

    @BeforeEach
    void ini() {
        this.ownerCarComponent = new OwnerCarComposite();
        this.ownerCarLeaf1 = new OwnerCarLeaf(new OwnerCar("Pedro", "SDFSDW", LocalDate.of(1982,3,4)));
        this.ownerCarLeaf2 = new OwnerCarLeaf(new OwnerCar("Jose", "PWEONW", LocalDate.of(1986,4,4)));

        this.ownerCarComponent.add(ownerCarLeaf1);
        this.ownerCarComposite = new OwnerCarComposite();
        this.ownerCarComponent.add(ownerCarComposite);
        this.ownerCarComposite.add(ownerCarLeaf2);
    }

    @Test
    void testDetailsIfLeaf() {
        assertEquals("Pedro", this.ownerCarLeaf1.getNames().findFirst().get());
        assertEquals("SDFSDW", this.ownerCarLeaf1.getDriverLicenses().findFirst().get());
        assertEquals(LocalDate.of(1982,3,4), this.ownerCarLeaf1.getBirthOfDates().findFirst().get());
    }

    @Test
    void testIsComposite() {
        assertFalse(this.ownerCarLeaf1.isComposite());
        assertTrue(this.ownerCarComponent.isComposite());
    }

    @Test
    void testAddLeafToComposite() {
        OwnerCarComponent newLeaf = new OwnerCarLeaf(new OwnerCar("Raul", "CMVBXJ", LocalDate.of(1989,4,4)));
        this.ownerCarComposite.add(newLeaf);
        assertEquals(2, ((OwnerCarComposite) this.ownerCarComposite).getNames().toList().size());
    }

    @Test
    void testRemoveLeafFromComposite() {
        this.ownerCarComposite.remove(ownerCarLeaf2);
        assertEquals(0, ((OwnerCarComposite) this.ownerCarComposite).getNames().toList().size());
    }


}
