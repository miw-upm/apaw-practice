package es.upm.miw.apaw.adapters.mongodb.martialartsgym.persistence;

import es.upm.miw.apaw.domain.models.martialartsgym.Membership;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MembershipPersistenceMongodbIT {

    @Autowired
    private MembershipPersistenceMongodb membershipPersistence;

    @Test
    void testReadAllMemberships() {
        List<Membership> memberships = this.membershipPersistence.readAll().toList();
        assertThat(memberships).isNotEmpty();
        assertThat(memberships.getFirst().getActivationDate()).isNotNull();
        assertThat(memberships.getFirst().getMonthlyFee()).isNotNull();
    }
}
