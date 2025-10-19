package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Membership;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.MembershipPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MembershipServiceIT {

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private MembershipPersistence membershipPersistence;

    @Test
    void testGetAllMembershipsIntegration() {
        Stream<Membership> memberships = membershipService.getAllMemberships();

        var list = memberships.toList();

        assertThat(list).isNotEmpty();
        assertThat(list.getFirst().getMonthlyFee()).isNotNull();
        assertThat(list.getFirst().getActivationDate()).isNotNull();
    }
}
