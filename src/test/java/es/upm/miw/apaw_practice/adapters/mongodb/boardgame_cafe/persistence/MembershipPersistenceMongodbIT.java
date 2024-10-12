package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.persistence;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.domain.exceptions.NotFoundException;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class MembershipPersistenceMongodbIT {

    @Autowired
    private MembershipPersistenceMongodb membershipPersistence;

    @Test
    void testReadNotFound() {
        assertThrows(NotFoundException.class, () -> this.membershipPersistence.read("Pearl"));
    }

    @Test
    void testMembershipTypeNotExist() {
        assertFalse(this.membershipPersistence.existType("Pearl"));
    }

    @Test
    void testMembershipTypeExist() {
        assertTrue(this.membershipPersistence.existType("Gold"));
    }

    @Test
    void testCreateAndRead() {
        Membership membership = new Membership("Diamond",24,new BigDecimal("4.0") );
        this.membershipPersistence.create(membership);
        Membership membershipBD = this.membershipPersistence.read(membership.getType());
        assertEquals("Diamond", membershipBD.getType());
        assertEquals(new BigDecimal("4.0"), membershipBD.getDiscount());
    }

    @Test
    void testCreateAndUpdate() {
        Membership membership = new Membership("Copper", 36, new BigDecimal("4.5"));
        this.membershipPersistence.create(membership);
        Membership membershipBD = this.membershipPersistence.read(membership.getType());
        assertEquals(membership, membershipBD);
        assertEquals("Copper", membershipBD.getType());
        assertEquals(new BigDecimal("4.5"), membershipBD.getDiscount());
        membership.setType("Gold");
        membership.setDiscount(new BigDecimal("3.0"));
        this.membershipPersistence.update(membership.getType(), membership);
        membershipBD = this.membershipPersistence.read(membership.getType());
        assertEquals("Gold", membershipBD.getType());
        assertEquals(new BigDecimal("3.0"), membershipBD.getDiscount());
    }
}
