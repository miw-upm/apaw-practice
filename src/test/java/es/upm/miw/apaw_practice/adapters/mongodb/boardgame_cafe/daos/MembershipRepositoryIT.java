package es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.MembershipEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class MembershipRepositoryIT {

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    void testFindByMembershipId() {
        Optional<MembershipEntity> membershipEntity = this.membershipRepository.findByMembershipId(0);
        assertTrue(membershipEntity.isPresent());
        assertNotNull(membershipEntity.get());
        MembershipEntity membership = membershipEntity.get();
        assertEquals(0, membership.getMembershipId());
        assertEquals("Bronze", membership.getType());
        assertEquals(1, membership.getDuration());
        assertEquals(new BigDecimal("2.0"), membership.getDiscount());
    }
}
