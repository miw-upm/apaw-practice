package es.upm.miw.apaw.domain.services.meeting;

import es.upm.miw.apaw.adapters.out.meeting.postgres.MeetingRepository;
import es.upm.miw.apaw.domain.exceptions.BadRequestException;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.model.meeting.LegalIssueResolvedUpdate;
import es.upm.miw.apaw.domain.model.meeting.MeetingParticipantReport;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class LegalIssueServiceIT {
    @Autowired
    private LegalIssueService legalIssueService;
    @Autowired
    private MeetingRepository meetingRepository;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    void testReadSeeder() {
        assertThat(this.legalIssueService.read(ID_0)).usingRecursiveComparison().isEqualTo(ISSUE_0);
    }

    @Test
    void testReadSeederWithoutDescription() {
        assertThat(this.legalIssueService.read(ID_3)).usingRecursiveComparison().isEqualTo(ISSUE_3);
    }

    @Test
    void testReadNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.legalIssueService.read(id))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testFindAllAllowsAdditionalLegalIssues() {
        LegalIssue extra = this.createIssue();
        List<LegalIssue> legalIssues = this.legalIssueService.findAll();
        assertThat(legalIssues).extracting(LegalIssue::getId)
                .contains(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5, extra.getId());
        assertThat(legalIssues).extracting(LegalIssue::getId)
                .containsSubsequence(ID_0, ID_1, ID_2, ID_3, ID_4, ID_5);
        assertThat(this.legalIssueService.findAll()).extracting(LegalIssue::getId)
                .containsExactlyElementsOf(legalIssues.stream().map(LegalIssue::getId).toList());
    }

    @Test
    void testFindAllIsSortedByTitle() {
        assertThat(this.legalIssueService.findAll()).extracting(LegalIssue::getTitle).isSorted();
    }

    @Test
    void testCreate() {
        LegalIssue legalIssue = this.createIssue();
        assertThat(legalIssue.getId()).isNotNull();
        assertThat(legalIssue.getCreationDate()).isNotNull();
        assertThat(legalIssue.getResolved()).isFalse();
        assertThat(this.legalIssueService.read(legalIssue.getId()))
                .usingRecursiveComparison().ignoringFields("creationDate").isEqualTo(legalIssue);
    }

    @Test
    void testCreateDuplicateTitle() {
        LegalIssue legalIssue = this.newIssue();
        legalIssue.setTitle(ISSUE_0.getTitle());
        assertThatThrownBy(() -> this.legalIssueService.create(legalIssue))
                .isInstanceOf(ConflictException.class).hasMessageContaining(ISSUE_0.getTitle());
    }

    @Test
    void testUpdateReplacesMutableFields() {
        LegalIssue original = this.legalIssueService.read(this.createIssue().getId());
        LegalIssue replacement = LegalIssue.builder()
                .title("Updated " + UUID.randomUUID()).priority(9).build();
        this.legalIssueService.update(original.getId(), replacement);
        LegalIssue updated = this.legalIssueService.read(original.getId());
        assertThat(updated.getId()).isEqualTo(original.getId());
        assertThat(updated.getTitle()).isEqualTo(replacement.getTitle());
        assertThat(updated.getDescription()).isNull();
        assertThat(updated.getPriority()).isEqualTo(9);
        assertThat(updated.getResolved()).isFalse();
        assertThat(updated.getCreationDate()).isEqualTo(original.getCreationDate());
    }

    @Test
    void testUpdateSameTitle() {
        LegalIssue legalIssue = this.createIssue();
        legalIssue.setDescription("Updated description");
        this.legalIssueService.update(legalIssue.getId(), legalIssue);
        assertThat(this.legalIssueService.read(legalIssue.getId()).getDescription())
                .isEqualTo("Updated description");
    }

    @Test
    void testUpdateNotFound() {
        UUID id = UUID.randomUUID();
        assertThatThrownBy(() -> this.legalIssueService.update(id, ISSUE_0))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(id.toString());
    }

    @Test
    void testUpdateDuplicateTitleLeavesLegalIssueUnchanged() {
        LegalIssue legalIssue = this.createIssue();
        LegalIssue replacement = LegalIssue.builder().title(ISSUE_0.getTitle()).priority(4).build();
        assertThatThrownBy(() -> this.legalIssueService.update(legalIssue.getId(), replacement))
                .isInstanceOf(ConflictException.class).hasMessageContaining(ISSUE_0.getTitle());
        assertThat(this.legalIssueService.read(legalIssue.getId()))
                .usingRecursiveComparison().ignoringFields("creationDate").isEqualTo(legalIssue);
    }

    @Test
    void testUpdateResolvedStates() {
        LegalIssue first = this.createIssue();
        LegalIssue second = this.createIssue();
        this.legalIssueService.updateResolvedStates(List.of(
                new LegalIssueResolvedUpdate(first.getId(), true),
                new LegalIssueResolvedUpdate(second.getId(), true)));
        assertThat(this.legalIssueService.read(first.getId()).getResolved()).isTrue();
        assertThat(this.legalIssueService.read(second.getId()).getResolved()).isTrue();
    }

    @Test
    void testUpdateResolvedStatesLeavesAbsentFieldsUntouched() {
        LegalIssue original = this.createIssue();
        this.legalIssueService.updateResolvedStates(
                List.of(new LegalIssueResolvedUpdate(original.getId(), true)));
        LegalIssue patched = this.legalIssueService.read(original.getId());
        assertThat(patched.getResolved()).isTrue();
        assertThat(patched).usingRecursiveComparison()
                .ignoringFields("resolved", "creationDate").isEqualTo(original);
    }

    @Test
    void testUpdateResolvedStatesNotFoundChangesNothing() {
        LegalIssue legalIssue = this.createIssue();
        UUID missingId = UUID.randomUUID();
        List<LegalIssueResolvedUpdate> updates = List.of(
                new LegalIssueResolvedUpdate(legalIssue.getId(), true),
                new LegalIssueResolvedUpdate(missingId, true));
        assertThatThrownBy(() -> this.legalIssueService.updateResolvedStates(updates))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingId.toString());
        assertThat(this.legalIssueService.read(legalIssue.getId()).getResolved()).isFalse();
    }

    @Test
    void testUpdateResolvedStatesRepeatedIdChangesNothing() {
        LegalIssue legalIssue = this.createIssue();
        List<LegalIssueResolvedUpdate> updates = List.of(
                new LegalIssueResolvedUpdate(legalIssue.getId(), true),
                new LegalIssueResolvedUpdate(legalIssue.getId(), false));
        assertThatThrownBy(() -> this.legalIssueService.updateResolvedStates(updates))
                .isInstanceOf(BadRequestException.class).hasMessageContaining(legalIssue.getId().toString());
        assertThat(this.legalIssueService.read(legalIssue.getId()).getResolved()).isFalse();
    }

    @Test
    void testDelete() {
        LegalIssue legalIssue = this.createIssue();
        this.legalIssueService.delete(legalIssue.getId());
        assertThatThrownBy(() -> this.legalIssueService.read(legalIssue.getId()))
                .isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteMissingLegalIssue() {
        UUID id = UUID.randomUUID();
        this.legalIssueService.delete(id);
        assertThatThrownBy(() -> this.legalIssueService.read(id)).isInstanceOf(NotFoundException.class);
    }

    @Test
    void testDeleteReferencedLegalIssue() {
        assertThatThrownBy(() -> this.legalIssueService.delete(ID_0))
                .isInstanceOf(ConflictException.class).hasMessageContaining(ID_0.toString());
        assertThat(this.legalIssueService.read(ID_0)).usingRecursiveComparison().isEqualTo(ISSUE_0);
        assertThat(this.meetingRepository.existsByLegalIssuesId(ID_0)).isTrue();
    }

    @Test
    void testFindParticipantReportHydratesParticipantsInOneCall() {
        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Set<UUID> requestedIds = invocation.getArgument(0);
            return requestedIds.stream().map(this::hydratedUser).toList();
        });

        List<MeetingParticipantReport> reports = this.legalIssueService.findParticipantReport();

        UUID userId = MEETING_0.getParticipants().get(0).getId();
        assertThat(reports)
                .filteredOn(report -> report.getUserSnapshot().getId().equals(userId))
                .singleElement()
                .satisfies(report -> {
                    assertThat(report.getUserSnapshot().getMobile()).isNotNull();
                    assertThat(report.getUserSnapshot().getFirstName()).isNotNull();
                    assertThat(report.getMeetingCount()).isGreaterThanOrEqualTo(2);
                });
        assertThat(reports).extracting(MeetingParticipantReport::getUnresolvedLegalIssueCount)
                .isSortedAccordingTo(Comparator.reverseOrder());
        verify(this.userFinder, times(1)).findByIds(any());
        verify(this.userFinder, never()).read(any());
    }

    @Test
    void testFindParticipantReportUserNotFound() {
        when(this.userFinder.findByIds(any())).thenReturn(List.of());

        assertThatThrownBy(() -> this.legalIssueService.findParticipantReport())
                .isInstanceOf(NotFoundException.class).hasMessageContaining("User id not found");
    }

    private UserSnapshot hydratedUser(UUID userId) {
        return UserSnapshot.builder()
                .id(userId)
                .mobile("600000999")
                .firstName("participante")
                .build();
    }

    private LegalIssue newIssue() {
        return LegalIssue.builder()
                .title("IT legal issue " + UUID.randomUUID())
                .description("IT description")
                .priority(5)
                .build();
    }

    private LegalIssue createIssue() {
        return this.legalIssueService.create(this.newIssue());
    }
}
