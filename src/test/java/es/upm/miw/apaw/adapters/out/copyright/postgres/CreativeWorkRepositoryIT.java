package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.CopyrightSeederForDev.WORK_0;
import static es.upm.miw.apaw.config.seeders.CopyrightSeederForDev.WORK_1;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class CreativeWorkRepositoryIT {

    @Autowired
    private CreativeWorkRepository creativeWorkRepository;

    @Test
    void testExistsByRegistrationCode() {
        assertThat(this.creativeWorkRepository.existsByRegistrationCode(WORK_0.getRegistrationCode())).isTrue();
        assertThat(this.creativeWorkRepository.existsByRegistrationCode("NON_EXISTING")).isFalse();
    }

    @Test
    void testGenerateClaimSummaries() {
        List<CreativeWorkClaimSummary> summaries = this.creativeWorkRepository.generateClaimSummaries();

        // In the seeder:
        // WORK_0 (RW-001) has 2 claims (5000.00 + 1000.00 = 6000.00)
        // WORK_1 (RW-002) has 1 claim (12500.00)
        // Ordered by SUM DESC: RW-002 first, then RW-001.

        assertThat(summaries).isNotEmpty();
        
        // Assert first element is RW-002
        CreativeWorkClaimSummary topSummary = summaries.get(0);
        assertThat(topSummary.getRegistrationCode()).isEqualTo(WORK_1.getRegistrationCode());
        assertThat(topSummary.getClaimCount()).isEqualTo(1L);
        assertThat(topSummary.getTotalRequestedCompensation()).isEqualByComparingTo(new BigDecimal("12500.00"));

        // Assert second element is RW-001
        CreativeWorkClaimSummary secondSummary = summaries.get(1);
        assertThat(secondSummary.getRegistrationCode()).isEqualTo(WORK_0.getRegistrationCode());
        assertThat(secondSummary.getClaimCount()).isEqualTo(2L);
        assertThat(secondSummary.getTotalRequestedCompensation()).isEqualByComparingTo(new BigDecimal("6000.00"));
    }
}
