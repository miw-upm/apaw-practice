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
        assertThrows(NotFoundException.class, () -> this.membershipPersistence.read(9999));
    }

    @Test
    void testMembershipIdNotExist() {
        assertFalse(this.membershipPersistence.existMembershipId(9999));
    }

    @Test
    void testMembershipIdExist() {
        assertTrue(this.membershipPersistence.existMembershipId(0));
    }

    @Test
    void testCreateAndRead() {
        Membership membership = new Membership(4, "Diamond",24,new BigDecimal("4.0") );
        this.membershipPersistence.create(membership);
        Membership membershipBD = this.membershipPersistence.read(membership.getMembershipId());
        assertEquals(4, membershipBD.getMembershipId());
        assertEquals("Diamond", membershipBD.getType());
        assertEquals(new BigDecimal("4.0"), membershipBD.getDiscount());
    }

    @Test
    void testCreateAndUpdate() {
        Membership membership = new Membership(5, "Copper", 36, new BigDecimal("4.5"));
        this.membershipPersistence.create(membership);
        Membership membershipBD = this.membershipPersistence.read(membership.getMembershipId());
        assertEquals(membership, membershipBD);
        assertEquals(5, membershipBD.getMembershipId());
        assertEquals("Copper", membershipBD.getType());
        assertEquals(new BigDecimal("4.5"), membershipBD.getDiscount());
        membership.setType("Glass");
        membership.setDiscount(new BigDecimal("5.0"));
        this.membershipPersistence.update(membership.getMembershipId(), membership);
        membershipBD = this.membershipPersistence.read(membership.getMembershipId());
        assertEquals("Glass", membershipBD.getType());
        assertEquals(new BigDecimal("5.0"), membershipBD.getDiscount());
    }

}
