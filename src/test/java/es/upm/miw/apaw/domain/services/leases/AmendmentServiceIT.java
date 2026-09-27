package es.upm.miw.apaw.domain.services.leases;

import es.upm.miw.apaw.adapters.out.leases.postgres.AmendmentEntity;
import es.upm.miw.apaw.adapters.out.leases.postgres.LeaseEntity;
import es.upm.miw.apaw.adapters.out.leases.postgres.LeaseRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.leases.Amendment;
import es.upm.miw.apaw.domain.model.leases.AmendmentType;
import es.upm.miw.apaw.domain.model.leases.AmendmentUpdate;
import es.upm.miw.apaw.domain.model.leases.LeaseType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.LeaseSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@ActiveProfiles("test")
class AmendmentServiceIT {
    @Autowired
    private AmendmentService amendmentService;
    @Autowired
    private LeaseRepository leaseRepository;

    @Test
    void testReadSeeder() {
        assertThat(this.amendmentService.read(AMENDMENT_ID_0)).usingRecursiveComparison().isEqualTo(AMENDMENT_0);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.amendmentService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalAmendments() {
        Amendment extra = this.createAmendment();
        List<Amendment> amendments = this.amendmentService.findAll();
        assertThat(amendments).extracting(Amendment::getId).contains(
                AMENDMENT_ID_0, AMENDMENT_ID_1, AMENDMENT_ID_2, AMENDMENT_ID_3, AMENDMENT_ID_4, extra.getId());
        assertThat(amendments).extracting(Amendment::getId).containsSubsequence(
                AMENDMENT_ID_0, AMENDMENT_ID_2, AMENDMENT_ID_1, AMENDMENT_ID_3, AMENDMENT_ID_4);
        assertThat(this.amendmentService.findAll()).extracting(Amendment::getId)
                .containsExactlyElementsOf(amendments.stream().map(Amendment::getId).toList());
    }

    @Test
    void testCreateDefaultsToNotApproved() {
        Amendment amendment = this.createAmendment();
        Amendment stored = this.amendmentService.read(amendment.getId());
        assertThat(stored).usingRecursiveComparison().isEqualTo(amendment);
        assertThat(stored.getApproved()).isFalse();
    }

    @Test
    void testUpdateReplacesMutableFields() {
        Amendment original = this.createAmendment();
        Amendment replacement = Amendment.builder().amendmentNumber(9).description("Replaced")
                .effectiveDate(LocalDate.of(2026, 2, 1)).approved(true)
                .amendmentType(AmendmentType.TERMINATION).build();
        this.amendmentService.update(original.getId(), replacement);
        Amendment updated = this.amendmentService.read(original.getId());
        assertThat(updated).usingRecursiveComparison().ignoringFields("id").isEqualTo(replacement);
        assertThat(updated.getAdditionalAmount()).isNull();
        assertThat(updated.getId()).isEqualTo(original.getId());
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.amendmentService.update(id, AMENDMENT_0))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testPatchOnlyPresentFields() {
        Amendment original = this.createAmendment();
        this.amendmentService.patch(original.getId(),
                new AmendmentUpdate(null, null, null, null, true, null));
        Amendment patched = this.amendmentService.read(original.getId());
        assertThat(patched.getApproved()).isTrue();
        assertThat(patched).usingRecursiveComparison().ignoringFields("approved").isEqualTo(original);
    }

    @Test
    void testPatchNotFound() {
        UUID id = UUID.randomUUID();
        AmendmentUpdate update = new AmendmentUpdate(null, "Missing", null, null, null, null);
        assertThatThrownBy(() -> this.amendmentService.patch(id, update))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testDelete() {
        UUID id = this.createAmendment().getId();
        this.amendmentService.delete(id);
        assertThatThrownBy(() -> this.amendmentService.read(id))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingAmendment() {
        UUID id = UUID.randomUUID();
        this.amendmentService.delete(id);
        assertThatThrownBy(() -> this.amendmentService.read(id)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedAmendment() {
        Amendment amendment = this.createAmendment();
        LeaseEntity lease = LeaseEntity.builder().id(UUID.randomUUID())
                .leaseNumber("IT-" + UUID.randomUUID()).propertyAddress("Test street 1")
                .startDate(LocalDate.of(2025, 1, 1)).monthlyRent(BigDecimal.TEN).active(true)
                .createdAt(LocalDateTime.now()).leaseType(LeaseType.OTHER).userId(UUID.randomUUID())
                .amendments(List.of(new AmendmentEntity(amendment))).build();
        this.leaseRepository.saveAndFlush(lease);
        UUID id = amendment.getId();
        assertThatThrownBy(() -> this.amendmentService.delete(id))
                .isInstanceOf(ConflictException.class).hasMessageContaining(id.toString());
        assertThat(this.amendmentService.read(id).getId()).isEqualTo(id);
    }

    private Amendment createAmendment() {
        return this.amendmentService.create(Amendment.builder().amendmentNumber(1)
                .description("IT amendment " + UUID.randomUUID()).effectiveDate(LocalDate.of(2026, 1, 1))
                .additionalAmount(new BigDecimal("15.00")).amendmentType(AmendmentType.OTHER).build());
    }
}
