package es.upm.miw.apaw.domain.services.immigrationissues;

import es.upm.miw.apaw.adapters.out.immigrationissues.postgres.ImmigrationIssueEntity;
import es.upm.miw.apaw.adapters.out.immigrationissues.postgres.ImmigrationIssueRepository;
import es.upm.miw.apaw.adapters.out.immigrationissues.postgres.LawBasisEntity;
import es.upm.miw.apaw.adapters.out.immigrationissues.postgres.LawBasisRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.immigrationissues.CreationImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssueFindCriteria;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasis;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUsageReport;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.ImmigrationIssuesSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class ImmigrationIssueServiceIT {

    private static final String APAW_USER_PREFIX = "aaaaaaaa-bbbb-cccc-dddd-eeeeffff";
    private static final UUID APAW_USER_ID_0 = UUID.fromString(APAW_USER_PREFIX + "0000");
    private static final UUID APAW_USER_ID_1 = UUID.fromString(APAW_USER_PREFIX + "0001");

    @Autowired
    private ImmigrationIssueService immigrationIssueService;
    @Autowired
    private ImmigrationIssueRepository immigrationIssueRepository;
    @Autowired
    private LawBasisRepository lawBasisRepository;
    @MockitoBean
    private UserFinder userFinder;

    private UserSnapshot user;

    @BeforeEach
    void setUp() {
        this.user = UserSnapshot.builder()
                .id(APAW_USER_ID_0)
                .mobile("600000100")
                .firstName("cliente0")
                .familyName("García López")
                .email("cliente0@example.com")
                .build();
        when(this.userFinder.read(this.user.getId())).thenReturn(this.user);
    }

    @Test
    @Transactional
    void testCreateAssociatesExistingLawBasesAndResolvesUser() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0, ID_1));
        long lawBasisCountBefore = this.lawBasisRepository.count();

        ImmigrationIssue immigrationIssue = this.immigrationIssueService.create(creation);

        assertThat(immigrationIssue.getId()).isNotNull();
        assertThat(immigrationIssue.getSubject()).isEqualTo(creation.getSubject());
        assertThat(immigrationIssue.getLawBases()).extracting(LawBasis::getId).containsExactly(ID_0, ID_1);
        assertThat(immigrationIssue.getUserSnapshot()).isEqualTo(this.user);
        assertThat(immigrationIssue.getOpenedAt()).isNotNull();
        assertThat(this.lawBasisRepository.count()).isEqualTo(lawBasisCountBefore);
    }

    @Test
    @Transactional
    void testCreateAssignsSystemDefaults() {
        ImmigrationIssue immigrationIssue = this.immigrationIssueService.create(this.creation(List.of(ID_0)));

        assertThat(immigrationIssue.getId()).isNotNull();
        assertThat(immigrationIssue.getOpenedAt()).isAfter(LocalDateTime.now().minusMinutes(1));
        assertThat(immigrationIssue.getEstimatedCost()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    @Transactional
    void testCreatePersistsEntityWithUserIdAndLawBases() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0, ID_2));

        ImmigrationIssue immigrationIssue = this.immigrationIssueService.create(creation);

        ImmigrationIssueEntity entity = this.immigrationIssueRepository
                .findById(immigrationIssue.getId()).orElseThrow();
        assertThat(entity.getSubject()).isEqualTo(creation.getSubject());
        assertThat(entity.getClientNationality()).isEqualTo(creation.getClientNationality());
        assertThat(entity.getClientImmigrationStatus()).isEqualTo(creation.getClientImmigrationStatus());
        assertThat(entity.getResponseDueDate()).isEqualTo(creation.getResponseDueDate());
        assertThat(entity.getUserId()).isEqualTo(this.user.getId());
        assertThat(entity.getLawBases()).extracting(LawBasisEntity::getId).containsExactly(ID_0, ID_2);
    }

    @Test
    @Transactional
    void testCreateKeepsProvidedEstimatedCost() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0));
        creation.setEstimatedCost(new BigDecimal("725.50"));

        ImmigrationIssue immigrationIssue = this.immigrationIssueService.create(creation);

        assertThat(immigrationIssue.getEstimatedCost()).isEqualByComparingTo(new BigDecimal("725.50"));
    }

    @Test
    @Transactional
    void testCreateResolvesTheUserGivenById() {
        UserSnapshot otherUser = UserSnapshot.builder()
                .id(APAW_USER_ID_1)
                .mobile("600000101")
                .firstName("cliente1")
                .familyName("Martínez Ruiz")
                .email("cliente1@example.com")
                .build();
        when(this.userFinder.read(otherUser.getId())).thenReturn(otherUser);
        CreationImmigrationIssue creation = this.creation(List.of(ID_0));
        creation.setUserId(otherUser.getId());

        ImmigrationIssue immigrationIssue = this.immigrationIssueService.create(creation);

        assertThat(immigrationIssue.getUserSnapshot()).isEqualTo(otherUser);
        verify(this.userFinder, times(1)).read(otherUser.getId());
        verify(this.userFinder, never()).read(this.user.getId());
    }

    @Test
    @Transactional
    void testCreateCallsApawUserOnlyOnce() {
        this.immigrationIssueService.create(this.creation(List.of(ID_0, ID_1, ID_2)));

        verify(this.userFinder, times(1)).read(this.user.getId());
    }

    @Test
    @Transactional
    void testCreateUnknownLawBasis() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0, UUID.randomUUID()));

        assertThatThrownBy(() -> this.immigrationIssueService.create(creation))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("Law basis id not found");
    }

    @Test
    @Transactional
    void testCreateDuplicateSubject() {
        String subject = "Repeated subject " + UUID.randomUUID();
        when(this.userFinder.read(this.user.getId())).thenReturn(this.user);
        CreationImmigrationIssue first = this.creation(List.of(ID_0));
        first.setSubject(subject);
        this.immigrationIssueService.create(first);
        CreationImmigrationIssue second = this.creation(List.of(ID_0));
        second.setSubject(subject);

        assertThatThrownBy(() -> this.immigrationIssueService.create(second))
                .isInstanceOf(ConflictException.class)
                .hasMessageContaining(subject);
    }

    @Test
    @Transactional
    void testFindByClientNationality() {
        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder().clientNationality("Marruecos").build());

        assertThat(issues).extracting(ImmigrationIssue::getId)
                .contains(ISSUE_ID_1)
                .doesNotContain(ISSUE_ID_0, ISSUE_ID_2);
    }

    @Test
    @Transactional
    void testFindByClientNationalityWithoutMatches() {
        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder().clientNationality("Nowhere").build());

        assertThat(issues).isEmpty();
    }

    @Test
    @Transactional
    void testFindOverdue() {
        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder().overdue(true).build());

        assertThat(issues).extracting(ImmigrationIssue::getId)
                .contains(ISSUE_ID_0, ISSUE_ID_1, ISSUE_ID_2);
    }

    @Test
    @Transactional
    void testFindNotOverdue() {
        CreationImmigrationIssue creation = this.creation(List.of(ID_0));
        creation.setResponseDueDate(LocalDate.of(2099, 1, 1));
        ImmigrationIssue future = this.immigrationIssueService.create(creation);

        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder().overdue(false).build());

        assertThat(issues).extracting(ImmigrationIssue::getId)
                .contains(future.getId())
                .doesNotContain(ISSUE_ID_0, ISSUE_ID_1, ISSUE_ID_2);
    }

    @Test
    @Transactional
    void testFindByLawNameTraversesTheRelation() {
        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder().lawName("39/2015").build());

        assertThat(issues).extracting(ImmigrationIssue::getId)
                .contains(ISSUE_ID_1)
                .doesNotContain(ISSUE_ID_0, ISSUE_ID_2);
    }

    @Test
    @Transactional
    void testFindByLawNameDoesNotRepeatIssuesSharingSeveralLawBases() {
        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder().lawName("4/2000").build());

        assertThat(issues).extracting(ImmigrationIssue::getId).containsOnlyOnce(ISSUE_ID_0);
    }

    @Test
    @Transactional
    void testFindByLawNameIsCaseInsensitive() {
        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder().lawName("ley 12/1989").build());

        assertThat(issues).extracting(ImmigrationIssue::getId).contains(ISSUE_ID_1);
    }

    @Test
    @Transactional
    void testFindByFamilyNameCallsApawUserOnce() {
        when(this.userFinder.findByIds(any())).thenReturn(List.of(this.user));

        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder().familyName("García López").build());

        assertThat(issues).extracting(ImmigrationIssue::getId).contains(ISSUE_ID_0);
        assertThat(issues).allSatisfy(issue ->
                assertThat(issue.getUserSnapshot().getFirstName()).isEqualTo("cliente0"));
        verify(this.userFinder, times(1)).findByIds(any());
    }

    @Test
    @Transactional
    void testFindByFamilyNameWithoutMatches() {
        when(this.userFinder.findByIds(any())).thenReturn(List.of(this.user));

        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder().familyName("Nadie").build());

        assertThat(issues).isEmpty();
    }

    @Test
    @Transactional
    void testFindCombinesDatabaseAndUserCriteria() {
        when(this.userFinder.findByIds(any())).thenReturn(List.of(this.user));

        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder()
                        .clientNationality("Marruecos")
                        .lawName("39/2015")
                        .familyName("García López")
                        .build());

        assertThat(issues).isEmpty();
    }

    @Test
    @Transactional
    void testFindWithoutCriteriaKeepsSeededIssues() {
        List<ImmigrationIssue> issues = this.immigrationIssueService.find(
                ImmigrationIssueFindCriteria.builder().build());

        assertThat(issues).extracting(ImmigrationIssue::getId)
                .contains(ISSUE_ID_0, ISSUE_ID_1, ISSUE_ID_2);
    }

    @Test
    @Transactional
    void testFindLawBasisUsageReportDoesNotCallApawUser() {
        List<LawBasisUsageReport> reports = this.immigrationIssueService.findLawBasisUsageReport();

        assertThat(reports).isNotEmpty();
        assertThat(reports).extracting(LawBasisUsageReport::getLawCode)
                .contains(LAW_BASIS_0.getLawCode(), LAW_BASIS_4.getLawCode());
        verifyNoInteractions(this.userFinder);
    }

    private CreationImmigrationIssue creation(List<UUID> lawBasisIds) {
        return CreationImmigrationIssue.builder()
                .subject("Immigration issue " + UUID.randomUUID())
                .clientNationality("Colombia")
                .clientImmigrationStatus("Permiso en vigor")
                .responseDueDate(LocalDate.of(2026, 6, 1))
                .lawBasisIds(lawBasisIds)
                .userId(this.user.getId())
                .build();
    }
}