package es.upm.miw.apaw.adapters.out.leases.postgres;

import es.upm.miw.apaw.domain.model.leases.AmendmentType;
import es.upm.miw.apaw.domain.model.leases.LeaseAmendmentReport;
import es.upm.miw.apaw.domain.model.leases.LeaseType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class LeaseRepositoryIT {
    @Autowired
    private LeaseRepository leaseRepository;
    @Autowired
    private AmendmentRepository amendmentRepository;

    @Test
    void testFindLeaseAmendmentReport() {
        List<LeaseAmendmentReport> report = this.leaseRepository.findLeaseAmendmentReport();

        assertThat(report).extracting(LeaseAmendmentReport::getTotalAdditionalAmount)
                .isSortedAccordingTo(Comparator.reverseOrder());
        assertThat(report).extracting(LeaseAmendmentReport::getLeaseType).doesNotHaveDuplicates();
        assertThat(report).filteredOn(item -> item.getLeaseType() == LeaseType.RESIDENTIAL)
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getLeaseCount()).isGreaterThanOrEqualTo(1);
                    assertThat(item.getApprovedAmendmentCount()).isGreaterThanOrEqualTo(2);
                    assertThat(item.getTotalAdditionalAmount()).isGreaterThanOrEqualTo(new BigDecimal("25.00"));
                });
        assertThat(report).filteredOn(item -> item.getLeaseType() == LeaseType.COMMERCIAL)
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getApprovedAmendmentCount()).isGreaterThanOrEqualTo(1);
                    assertThat(item.getTotalAdditionalAmount()).isGreaterThanOrEqualTo(new BigDecimal("60.00"));
                });
    }

    @Test
    @Transactional
    void testFindLeaseAmendmentReportIgnoresNotApprovedAmendments() {
        AmendmentEntity approved = this.saveAmendment(true, new BigDecimal("500.00"));
        AmendmentEntity notApproved = this.saveAmendment(false, new BigDecimal("9000.00"));
        this.leaseRepository.saveAndFlush(this.lease(List.of(approved, notApproved)));

        List<LeaseAmendmentReport> report = this.leaseRepository.findLeaseAmendmentReport();

        assertThat(report).filteredOn(item -> item.getLeaseType() == LeaseType.INDUSTRIAL)
                .singleElement()
                .satisfies(item -> {
                    assertThat(item.getLeaseCount()).isEqualTo(1);
                    assertThat(item.getApprovedAmendmentCount()).isEqualTo(1);
                    assertThat(item.getTotalAdditionalAmount()).isEqualByComparingTo("500.00");
                });
        assertThat(report.getFirst().getLeaseType()).isEqualTo(LeaseType.INDUSTRIAL);
    }

    private AmendmentEntity saveAmendment(boolean approved, BigDecimal additionalAmount) {
        return this.amendmentRepository.save(AmendmentEntity.builder().id(UUID.randomUUID())
                .amendmentNumber(1).description("IT amendment").effectiveDate(LocalDate.of(2026, 1, 1))
                .additionalAmount(additionalAmount).approved(approved)
                .amendmentType(AmendmentType.PRICE_CHANGE).build());
    }

    private LeaseEntity lease(List<AmendmentEntity> amendments) {
        return LeaseEntity.builder().id(UUID.randomUUID()).leaseNumber("IT-" + UUID.randomUUID())
                .propertyAddress("Poligono Industrial 3, Getafe").startDate(LocalDate.of(2026, 1, 1))
                .monthlyRent(new BigDecimal("3000.00")).active(true).createdAt(LocalDateTime.now())
                .leaseType(LeaseType.INDUSTRIAL).userId(UUID.randomUUID()).amendments(amendments).build();
    }
}
