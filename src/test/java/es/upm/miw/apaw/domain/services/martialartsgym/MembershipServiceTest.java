package es.upm.miw.apaw.domain.services.martialartsgym;

import es.upm.miw.apaw.domain.models.martialartsgym.Membership;
import es.upm.miw.apaw.domain.persistenceports.martialartsgym.MembershipPersistence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootTest
@ActiveProfiles("test")
class MembershipServiceTest {

    @MockitoBean
    private MembershipPersistence membershipPersistence;

    @Autowired
    private MembershipService membershipService;

    @Test
    void testGetAllMemberships() {
        Membership membership = Membership.builder()
                .id(UUID.randomUUID())
                .monthlyFee(new BigDecimal("40.00"))
                .activationDate(LocalDate.now().minusMonths(3))
                .isCurrentlyActive(true)
                .build();


        BDDMockito.given(membershipPersistence.readAll())
                .willReturn(Stream.of(membership));

        Stream<Membership> memberships = membershipService.getAllMemberships();


        var list = memberships.toList();

        Assertions.assertFalse(list.isEmpty());
        Assertions.assertEquals(new BigDecimal("40.00"), list.getFirst().getMonthlyFee());
        Assertions.assertTrue(list.getFirst().getIsCurrentlyActive());
    }
}
