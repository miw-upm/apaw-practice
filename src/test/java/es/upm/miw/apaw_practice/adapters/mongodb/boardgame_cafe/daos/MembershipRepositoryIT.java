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
    void testFindByType() {
        Optional<MembershipEntity> membershipEntity = this.membershipRepository.findByType("Bronze");
        assertTrue(membershipEntity.isPresent());
        assertNotNull(membershipEntity.get());
        MembershipEntity membership = membershipEntity.get();
        assertEquals("Bronze", membership.getType());
        assertEquals(1, membership.getDuration());
        assertEquals(new BigDecimal("2.0"), membership.getDiscount());
    }
}
