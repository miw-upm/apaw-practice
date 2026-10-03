package es.upm.miw.apaw.adapters.out.contract.postgres;

import es.upm.miw.apaw.domain.model.contract.ContractExpirationReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static es.upm.miw.apaw.config.seeders.ContractSeederForDev.CONTRACT_0;
import static es.upm.miw.apaw.config.seeders.ContractSeederForDev.CONTRACT_2;
import static es.upm.miw.apaw.config.seeders.ContractSeederForDev.CONTRACT_4;
import static es.upm.miw.apaw.config.seeders.ContractSeederForDev.CONTRACT_5;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class ContractRepositoryIT {

    @Autowired
    private ContractRepository contractRepository;

    @Test
    void testFindExpirationReport() {
        List<ContractExpirationReport> report =
                this.contractRepository.findExpirationReport(
                        LocalDate.of(2026, 10, 3),
                        LocalDate.of(2026, 11, 2)
                );

        assertThat(report).hasSize(5);

        assertThat(report)
                .filteredOn(item -> item.userId().equals(
                        CONTRACT_0.getUserSnapshot().getId()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.expiringContractCount()).isEqualTo(2);
                    assertThat(item.activeClauseCount()).isEqualTo(3);
                });

        assertThat(report)
                .filteredOn(item -> item.userId().equals(
                        CONTRACT_2.getUserSnapshot().getId()))
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.expiringContractCount()).isEqualTo(1);
                    assertThat(item.activeClauseCount()).isEqualTo(0);
                });

        assertThat(report)
                .noneMatch(item -> item.userId().equals(
                        CONTRACT_4.getUserSnapshot().getId()));

        assertThat(report)
                .noneMatch(item -> item.userId().equals(
                        CONTRACT_5.getUserSnapshot().getId()));
    }
}