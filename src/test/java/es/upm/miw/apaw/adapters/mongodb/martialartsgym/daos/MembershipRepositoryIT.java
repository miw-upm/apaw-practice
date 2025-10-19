package es.upm.miw.apaw.adapters.mongodb.martialartsgym.daos;

import es.upm.miw.apaw.adapters.mongodb.martialartsgym.entities.MembershipEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MembershipRepositoryIT {

    @Autowired
    private MembershipRepository membershipRepository;

    @Test
    void testCreateAndFind() {
        MembershipEntity membership = MembershipEntity.builder()
                .id(UUID.randomUUID())
                .monthlyFee(new BigDecimal("45.00"))
                .activationDate(LocalDate.now())
                .isCurrentlyActive(true)
                .build();

        membershipRepository.save(membership);

        MembershipEntity found = membershipRepository.findById(membership.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getMonthlyFee()).isEqualByComparingTo(new BigDecimal("45.00"));
        assertThat(found.getIsCurrentlyActive()).isTrue();
    }

    @Test
    void testFindAllContainsSeededData() {
        List<MembershipEntity> all = membershipRepository.findAll();
        assertThat(all).isNotEmpty();
        assertThat(all.getFirst().getActivationDate()).isNotNull();
    }
}
