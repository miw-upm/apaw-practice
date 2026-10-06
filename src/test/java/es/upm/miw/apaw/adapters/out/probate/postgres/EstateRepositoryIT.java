package es.upm.miw.apaw.adapters.out.probate.postgres;

import es.upm.miw.apaw.domain.model.probate.EstateUsageReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EstateRepositoryIT {
    @Autowired
    private EstateRepository estateRepository;

    @Test
    void testFindUsageReport() {
        List<EstateUsageReport> report = this.estateRepository.findUsageReport();

        assertThat(report).isNotEmpty();
        assertThat(report).extracting(EstateUsageReport::getHeirCount)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }
}
