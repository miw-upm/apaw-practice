package es.upm.miw.apaw.domain.services.meeting;

import es.upm.miw.apaw.adapters.out.meeting.postgres.LegalIssueEntity;
import es.upm.miw.apaw.adapters.out.meeting.postgres.MeetingEntity;
import es.upm.miw.apaw.adapters.out.meeting.postgres.MeetingRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.meeting.CreationMeeting;
import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.model.meeting.Meeting;
import es.upm.miw.apaw.domain.model.meeting.MeetingStatus;
import es.upm.miw.apaw.domain.ports.out.user.UserFinder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.ID_0;
import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.MEETING_0;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class MeetingServiceIT {
    @Autowired
    private MeetingService meetingService;
    @Autowired
    private LegalIssueService legalIssueService;
    @Autowired
    private MeetingRepository meetingRepository;
    @MockitoBean
    private UserFinder userFinder;

    @Test
    @Transactional
    void testCreate() {
        UserSnapshot first = this.user("600000900", "participante0");
        UserSnapshot second = this.user("600000901", "participante1");
        LegalIssue legalIssue = this.createLegalIssue();
        CreationMeeting creation = this.creation(
                List.of(legalIssue.getId()), List.of(first.getId(), second.getId()));
        when(this.userFinder.findByIds(Set.of(first.getId(), second.getId())))
                .thenReturn(List.of(first, second));

        Meeting meeting = this.meetingService.create(creation);

        assertThat(meeting.getId()).isNotNull();
        assertThat(meeting.getTitle()).isEqualTo(creation.getTitle());
        assertThat(meeting.getMeetingDate()).isEqualTo(creation.getMeetingDate());
        assertThat(meeting.getLocation()).isEqualTo(creation.getLocation());
        assertThat(meeting.getDurationMinutes()).isEqualTo(creation.getDurationMinutes());
        assertThat(meeting.getDescription()).isEqualTo(creation.getDescription());
        assertThat(meeting.getLegalIssues()).extracting(LegalIssue::getId)
                .containsExactly(legalIssue.getId());
        assertThat(meeting.getParticipants()).containsExactly(first, second);

        MeetingEntity entity = this.meetingRepository.findById(meeting.getId()).orElseThrow();
        assertThat(entity.getTitle()).isEqualTo(creation.getTitle());
        assertThat(entity.getLegalIssues()).extracting(LegalIssueEntity::getId)
                .containsExactly(legalIssue.getId());
        assertThat(entity.getParticipantIds()).containsExactly(first.getId(), second.getId());
        assertThat(this.meetingRepository.existsByLegalIssuesId(legalIssue.getId())).isTrue();
    }

    @Test
    @Transactional
    void testCreateAssignsSystemAttributes() {
        UserSnapshot user = this.user("600000902", "participante2");
        LegalIssue legalIssue = this.createLegalIssue();
        CreationMeeting creation = this.creation(List.of(legalIssue.getId()), List.of(user.getId()));
        when(this.userFinder.findByIds(Set.of(user.getId()))).thenReturn(List.of(user));

        Meeting meeting = this.meetingService.create(creation);

        assertThat(meeting.getId()).isNotNull();
        assertThat(meeting.getOnline()).isFalse();
        assertThat(meeting.getMeetingStatus()).isEqualTo(MeetingStatus.SCHEDULED);
        assertThat(this.meetingRepository.findById(meeting.getId()).orElseThrow().getMeetingStatus())
                .isEqualTo(MeetingStatus.SCHEDULED);
    }

    @Test
    @Transactional
    void testCreateKeepsProvidedOnline() {
        UserSnapshot user = this.user("600000903", "participante3");
        LegalIssue legalIssue = this.createLegalIssue();
        CreationMeeting creation = this.creation(List.of(legalIssue.getId()), List.of(user.getId()));
        creation.setOnline(true);
        when(this.userFinder.findByIds(Set.of(user.getId()))).thenReturn(List.of(user));

        assertThat(this.meetingService.create(creation).getOnline()).isTrue();
    }

    @Test
    @Transactional
    void testCreateCallsApawUserOnce() {
        UserSnapshot first = this.user("600000904", "participante4");
        UserSnapshot second = this.user("600000905", "participante5");
        LegalIssue legalIssue = this.createLegalIssue();
        CreationMeeting creation = this.creation(
                List.of(legalIssue.getId()), List.of(first.getId(), second.getId()));
        when(this.userFinder.findByIds(any())).thenReturn(List.of(first, second));

        this.meetingService.create(creation);

        verify(this.userFinder, times(1)).findByIds(Set.of(first.getId(), second.getId()));
        verify(this.userFinder, never()).read(any());
    }

    @Test
    @Transactional
    void testCreateDeduplicatesParticipants() {
        UserSnapshot user = this.user("600000906", "participante6");
        LegalIssue legalIssue = this.createLegalIssue();
        CreationMeeting creation = this.creation(
                List.of(legalIssue.getId()), List.of(user.getId(), user.getId()));
        when(this.userFinder.findByIds(Set.of(user.getId()))).thenReturn(List.of(user));

        Meeting meeting = this.meetingService.create(creation);

        assertThat(meeting.getParticipants()).containsExactly(user);
        verify(this.userFinder, times(1)).findByIds(Set.of(user.getId()));
    }

    @Test
    void testCreateDuplicateTitle() {
        CreationMeeting creation = this.creation(List.of(ID_0), List.of(UUID.randomUUID()));
        creation.setTitle(MEETING_0.getTitle());

        assertThatThrownBy(() -> this.meetingService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(MEETING_0.getTitle());
        verify(this.userFinder, never()).findByIds(any());
    }

    @Test
    void testCreateLegalIssueNotFound() {
        UUID missingId = UUID.randomUUID();
        CreationMeeting creation = this.creation(List.of(missingId), List.of(UUID.randomUUID()));

        assertThatThrownBy(() -> this.meetingService.create(creation))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingId.toString());
        assertThat(this.meetingRepository.existsByTitle(creation.getTitle())).isFalse();
    }

    @Test
    void testCreateLegalIssueAlreadyAssignedToAnotherMeeting() {
        CreationMeeting creation = this.creation(List.of(ID_0), List.of(UUID.randomUUID()));

        assertThatThrownBy(() -> this.meetingService.create(creation))
                .isInstanceOf(ConflictException.class).hasMessageContaining(ID_0.toString());
        assertThat(this.meetingRepository.existsByTitle(creation.getTitle())).isFalse();
    }

    @Test
    @Transactional
    void testCreateUserNotFound() {
        UserSnapshot known = this.user("600000907", "participante7");
        UUID missingId = UUID.randomUUID();
        LegalIssue legalIssue = this.createLegalIssue();
        CreationMeeting creation = this.creation(
                List.of(legalIssue.getId()), List.of(known.getId(), missingId));
        when(this.userFinder.findByIds(any())).thenReturn(List.of(known));

        assertThatThrownBy(() -> this.meetingService.create(creation))
                .isInstanceOf(NotFoundException.class).hasMessageContaining(missingId.toString());
        assertThat(this.meetingRepository.existsByTitle(creation.getTitle())).isFalse();
    }

    private CreationMeeting creation(List<UUID> legalIssueIds, List<UUID> participantIds) {
        return CreationMeeting.builder()
                .title("IT meeting " + UUID.randomUUID())
                .meetingDate(LocalDateTime.of(2026, 5, 20, 10, 0))
                .location("IT meeting room")
                .durationMinutes(60)
                .description("IT description")
                .legalIssueIds(legalIssueIds)
                .participantIds(participantIds)
                .build();
    }

    private LegalIssue createLegalIssue() {
        return this.legalIssueService.create(LegalIssue.builder()
                .title("IT meeting legal issue " + UUID.randomUUID())
                .priority(1)
                .build());
    }

    private UserSnapshot user(String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.randomUUID())
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }
}
