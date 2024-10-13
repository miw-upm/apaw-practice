package es.upm.miw.apaw_practice.domain.services.boardgame_cafe;

import es.upm.miw.apaw_practice.TestConfig;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.daos.MembershipRepository;
import es.upm.miw.apaw_practice.adapters.mongodb.boardgame_cafe.entities.MembershipEntity;
import es.upm.miw.apaw_practice.domain.models.boardgame_cafe.Membership;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
public class MembershipServiceIT {

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    void testReadByType() {
        MembershipEntity membership = this.membershipRepository.findAll().get(0);
        Membership newMembershipEntity = this.membershipService.readByType(membership.getType());
        assertEquals(membership.toMembership().getType(), newMembershipEntity.getType());
    }
}
