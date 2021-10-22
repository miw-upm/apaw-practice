package es.upm.miw.apaw_practice.domain.models.hospital;

import es.upm.miw.apaw_practice.TestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class HospitalCompositeTest {

    private Hospital hospital1, hospital2;
    private HospitalComposite hospitalComposite;
    private HospitalLeaf hospitalLeaf1, hospitalLeaf2;

    @BeforeEach
    void before(){
        hospital1 = new Hospital("Hospital number 1", "Street number 1", 150, null);
        hospital2 = new Hospital("Hospital number 2", "Street number 2", 490, null);
        hospitalComposite = new HospitalComposite();
        hospitalLeaf1 = new HospitalLeaf(hospital1);
        hospitalLeaf2 = new HospitalLeaf(hospital2);
        hospitalComposite.add(hospitalLeaf1);
    }

    @Test
    void testNumberOfNodes(){
        assertEquals(this.hospitalComposite.numberOfNodes(),1);
        assertEquals(this.hospitalLeaf1.numberOfNodes(),0);
    }

    @Test
    void testIsComposite(){
        assertTrue(this.hospitalComposite.isComposite());
        assertFalse(this.hospitalLeaf1.isComposite());
    }

    @Test
    void testAdd(){
        this.hospitalComposite.add(this.hospitalLeaf2);
        assertEquals(this.hospitalComposite.numberOfNodes(),2);
    }

    @Test
    void testRemove(){
        this.hospitalComposite.remove(this.hospitalLeaf2);
        assertEquals(this.hospitalComposite.numberOfNodes(),1);
    }
}
