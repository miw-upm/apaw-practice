package es.upm.miw.apaw.domain.services.leases;

import es.upm.miw.apaw.adapters.out.leases.postgres.AmendmentEntity;
import es.upm.miw.apaw.adapters.out.leases.postgres.LeaseEntity;
import es.upm.miw.apaw.adapters.out.leases.postgres.LeaseRepository;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.leases.Amendment;
import es.upm.miw.apaw.domain.model.leases.AmendmentType;
import es.upm.miw.apaw.domain.model.leases.CreationLease;
import es.upm.miw.apaw.domain.model.leases.Lease;
import es.upm.miw.apaw.domain.model.leases.LeaseFindCriteria;
import es.upm.miw.apaw.domain.model.leases.LeaseType;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.LeaseSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class LeaseServiceIT {
    private static final UserSnapshot USER = UserSnapshot.builder()
            .id(UUID.fromString("aaaaaaaa-bbbb-cccc-dddd-eeeeffff0005"))
            .mobile("600000105")
            .firstName("cliente5")
            .build();

    @Autowired
    private LeaseService leaseService;
    @Autowired
    private AmendmentService amendmentService;
    @Autowired
    private LeaseRepository leaseRepository;
    @MockitoBean
    private UserFinder userFinder;

    @BeforeEach
    void setUp() {
        when(this.userFinder.read(USER.getId())).thenReturn(USER);
        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Collection<UUID> ids = invocation.getArgument(0);
            return ids.stream().map(this::userOf).toList();
        });
    }

    @Test
    void testCreate() {
        Amendment first = this.createAmendment();
        Amendment second = this.createAmendment();
        CreationLease creation = this.creation(List.of(first.getId(), second.getId()));

        Lease lease = this.leaseService.create(creation);

        assertThat(lease.getId()).isNotNull();
        assertThat(lease.getCreatedAt()).isNotNull();
        assertThat(lease.getActive()).isTrue();
        assertThat(lease.getAmendments()).extracting(Amendment::getId).containsExactly(first.getId(), second.getId());
        assertThat(lease.getUserSnapshot()).isEqualTo(USER);
        LeaseEntity entity = this.leaseRepository.findById(lease.getId()).orElseThrow();
        assertThat(entity.getLeaseNumber()).isEqualTo(creation.getLeaseNumber());
        assertThat(entity.getAmendments()).extracting(AmendmentEntity::getId)
                .containsExactlyInAnyOrder(first.getId(), second.getId());
        assertThat(entity.getUserId()).isEqualTo(USER.getId());
        verify(this.userFinder, times(1)).read(any());
    }

    @Test
    void testCreateWithoutAmendments() {
        CreationLease creation = this.creation(null);
        creation.setActive(false);

        Lease lease = this.leaseService.create(creation);

        assertThat(lease.getAmendments()).isEmpty();
        assertThat(lease.getActive()).isFalse();
        assertThat(this.leaseRepository.findById(lease.getId())).isPresent();
    }

    @Test
    void testCreateDuplicateLeaseNumber() {
        CreationLease creation = this.creation(List.of());
        creation.setLeaseNumber(LEASE_0.getLeaseNumber());
        assertThatThrownBy(() -> this.leaseService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(LEASE_0.getLeaseNumber());
        verifyNoInteractions(this.userFinder);
    }

    @Test
    void testCreateDuplicateCadastralReference() {
        CreationLease creation = this.creation(List.of());
        creation.setCadastralReference(LEASE_0.getCadastralReference());
        assertThatThrownBy(() -> this.leaseService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(LEASE_0.getCadastralReference());
    }

    @Test
    void testCreateAmendmentNotFound() {
        UUID missingId = UUID.randomUUID();
        CreationLease creation = this.creation(List.of(missingId));
        assertThatThrownBy(() -> this.leaseService.create(creation))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingId.toString());
    }

    @Test
    void testCreateAmendmentAlreadyInOtherLease() {
        CreationLease creation = this.creation(List.of(AMENDMENT_ID_0));
        assertThatThrownBy(() -> this.leaseService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(AMENDMENT_ID_0.toString());
        assertThat(this.leaseRepository.findById(LEASE_ID_0).orElseThrow().getAmendments())
                .extracting(AmendmentEntity::getId).contains(AMENDMENT_ID_0);
    }

    @Test
    void testCreateRepeatedAmendmentId() {
        Amendment amendment = this.createAmendment();
        CreationLease creation = this.creation(List.of(amendment.getId(), amendment.getId()));
        assertThatThrownBy(() -> this.leaseService.create(creation))
                .isInstanceOf(BadRequestException.class).hasMessageContaining(amendment.getId().toString());
    }

    @Test
    void testCreateUserNotFound() {
        UUID missingUserId = UUID.randomUUID();
        when(this.userFinder.read(missingUserId))
                .thenThrow(new NotFoundException("User id not found: " + missingUserId));
        CreationLease creation = this.creation(List.of());
        creation.setUserId(missingUserId);
        assertThatThrownBy(() -> this.leaseService.create(creation))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingUserId.toString());
        assertThat(this.leaseRepository.existsByLeaseNumber(creation.getLeaseNumber())).isFalse();
    }

    @Test
    void testFindAllWithoutCriteria() {
        List<Lease> leases = this.leaseService.find(new LeaseFindCriteria());
        assertThat(leases).extracting(Lease::getId).contains(LEASE_ID_0, LEASE_ID_1, LEASE_ID_2);
        assertThat(leases).extracting(Lease::getLeaseNumber).isSorted();
        verify(this.userFinder, times(1)).findByIds(any());
    }

    @Test
    void testFindReturnsSummariesWithUser() {
        List<Lease> leases = this.leaseService.find(new LeaseFindCriteria());
        Lease lease = leases.stream().filter(found -> found.getId().equals(LEASE_ID_0)).findFirst().orElseThrow();
        assertThat(lease.getAmendments()).isNull();
        assertThat(lease.getLeaseNumber()).isEqualTo(LEASE_0.getLeaseNumber());
        assertThat(lease.getUserSnapshot()).isEqualTo(USER);
    }

    @Test
    void testFindByLeaseType() {
        List<Lease> leases = this.leaseService.find(
                LeaseFindCriteria.builder().leaseType(LeaseType.COMMERCIAL).build());
        assertThat(leases).extracting(Lease::getId).contains(LEASE_ID_1).doesNotContain(LEASE_ID_0, LEASE_ID_2);
        assertThat(leases).extracting(Lease::getLeaseType).containsOnly(LeaseType.COMMERCIAL);
    }

    @Test
    void testFindInForce() {
        Lease future = this.leaseService.create(this.creationStartingAt(LocalDate.now().plusMonths(1)));
        List<Lease> leases = this.leaseService.find(LeaseFindCriteria.builder().inForce(true).build());
        assertThat(leases).extracting(Lease::getId).contains(LEASE_ID_1).doesNotContain(LEASE_ID_2, future.getId());
    }

    @Test
    void testFindNotInForce() {
        Lease future = this.leaseService.create(this.creationStartingAt(LocalDate.now().plusMonths(1)));
        List<Lease> leases = this.leaseService.find(LeaseFindCriteria.builder().inForce(false).build());
        assertThat(leases).extracting(Lease::getId).contains(LEASE_ID_2, future.getId()).doesNotContain(LEASE_ID_1);
    }

    @Test
    void testFindByAmendmentType() {
        List<Lease> leases = this.leaseService.find(
                LeaseFindCriteria.builder().amendmentType(AmendmentType.SCOPE_CHANGE).build());
        assertThat(leases).extracting(Lease::getId).contains(LEASE_ID_1).doesNotContain(LEASE_ID_0, LEASE_ID_2);
    }

    @Test
    void testFindByAmendmentTypeWithoutDuplicates() {
        Amendment first = this.createAmendment();
        Amendment second = this.createAmendment();
        Lease lease = this.leaseService.create(this.creation(List.of(first.getId(), second.getId())));
        List<Lease> leases = this.leaseService.find(
                LeaseFindCriteria.builder().amendmentType(AmendmentType.OTHER).build());
        assertThat(leases).extracting(Lease::getId).containsOnlyOnce(lease.getId());
    }

    @Test
    void testFindByUserMobile() {
        List<Lease> leases = this.leaseService.find(
                LeaseFindCriteria.builder().userMobile(USER.getMobile()).build());
        assertThat(leases).extracting(Lease::getId).contains(LEASE_ID_0).doesNotContain(LEASE_ID_1, LEASE_ID_2);
        assertThat(leases).extracting(lease -> lease.getUserSnapshot().getMobile()).containsOnly(USER.getMobile());
        verify(this.userFinder, times(1)).findByIds(any());
    }

    @Test
    void testFindCombinedCriteria() {
        List<Lease> leases = this.leaseService.find(LeaseFindCriteria.builder()
                .leaseType(LeaseType.RESIDENTIAL).amendmentType(AmendmentType.PRICE_CHANGE)
                .userMobile(USER.getMobile()).build());
        assertThat(leases).extracting(Lease::getId).contains(LEASE_ID_0).doesNotContain(LEASE_ID_1, LEASE_ID_2);
    }

    @Test
    void testFindWithoutResultsDoesNotCallUsers() {
        List<Lease> leases = this.leaseService.find(LeaseFindCriteria.builder()
                .leaseType(LeaseType.INDUSTRIAL).amendmentType(AmendmentType.TERMINATION).build());
        assertThat(leases).isEmpty();
        verify(this.userFinder, never()).findByIds(any());
    }

    private CreationLease creationStartingAt(LocalDate startDate) {
        CreationLease creation = this.creation(List.of());
        creation.setStartDate(startDate);
        return creation;
    }

    private UserSnapshot userOf(UUID id) {
        if (USER.getId().equals(id)) {
            return USER;
        }
        return UserSnapshot.builder().id(id).mobile("699" + id.toString().substring(30)).firstName("other").build();
    }

    private CreationLease creation(List<UUID> amendmentIds) {
        return CreationLease.builder()
                .leaseNumber("IT-" + UUID.randomUUID())
                .propertyAddress("Calle de Prueba 1, Madrid")
                .startDate(LocalDate.of(2026, 1, 1))
                .monthlyRent(new BigDecimal("800.00"))
                .leaseType(LeaseType.RESIDENTIAL)
                .amendmentIds(amendmentIds)
                .userId(USER.getId())
                .build();
    }

    private Amendment createAmendment() {
        return this.amendmentService.create(Amendment.builder().amendmentNumber(1)
                .description("IT amendment " + UUID.randomUUID()).effectiveDate(LocalDate.of(2026, 1, 1))
                .amendmentType(AmendmentType.OTHER).build());
    }
}
