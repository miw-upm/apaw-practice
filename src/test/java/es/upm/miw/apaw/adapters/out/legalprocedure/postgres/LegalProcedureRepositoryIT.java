package es.upm.miw.apaw.adapters.out.legalprocedure.postgres;

import es.upm.miw.apaw.domain.model.legalprocedure.LegalTaskUsageReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.LegalProcedureSeederForDev.TASK_0;
import static es.upm.miw.apaw.config.seeders.LegalProcedureSeederForDev.TASK_2;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class LegalProcedureRepositoryIT {
    @Autowired
    private LegalProcedureRepository legalProcedureRepository;

    @Test
    void testFindLegalTaskUsageReport() {
        List<LegalTaskUsageReport> report = this.legalProcedureRepository.findLegalTaskUsageReport();

        assertThat(report).extracting(LegalTaskUsageReport::getTotalUsageCount)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(report).filteredOn(item -> item.getTaskTitle().equals(TASK_0.getTitle()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalUsageCount()).isGreaterThanOrEqualTo(4);
                    assertThat(item.getActiveUsageCount()).isGreaterThanOrEqualTo(3);
                    assertThat(item.getActiveUsageCount()).isLessThanOrEqualTo(item.getTotalUsageCount());
                });
        assertThat(report).filteredOn(item -> item.getTaskTitle().equals(TASK_2.getTitle()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getTotalUsageCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.getActiveUsageCount()).isGreaterThanOrEqualTo(1);
                    assertThat(item.getActiveUsageCount()).isLessThan(item.getTotalUsageCount());
                });
    }
}
