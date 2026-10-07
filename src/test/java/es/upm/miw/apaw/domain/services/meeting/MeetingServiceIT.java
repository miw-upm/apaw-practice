package es.upm.miw.apaw.domain.services.meeting;

import es.upm.miw.apaw.adapters.out.meeting.postgres.LegalIssueEntity;
import es.upm.miw.apaw.adapters.out.meeting.postgres.LegalIssueRepository;
import es.upm.miw.apaw.adapters.out.meeting.postgres.MeetingEntity;
import es.upm.miw.apaw.adapters.out.meeting.postgres.MeetingRepository;
import es.upm.miw.apaw.domain.exceptions.ConflictException;
import es.upm.miw.apaw.domain.exceptions.NotFoundException;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.meeting.CreationMeeting;
import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.model.meeting.Meeting;
import es.upm.miw.apaw.domain.model.meeting.MeetingFindCriteria;
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
import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.MEETING_ID_0;
import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.MEETING_ID_1;
import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.MEETING_ID_2;
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
    private static final UUID LAWYER_ID = MEETING_0.getParticipants().get(0).getId();
    private static final String LAWYER_FIRST_NAME = "Lucia";

    @Autowired
    private MeetingService meetingService;
    @Autowired
    private LegalIssueService legalIssueService;
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private LegalIssueRepository legalIssueRepository;
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
    @Transactional
    void testCreateWithoutParticipants() {
        LegalIssue legalIssue = this.createLegalIssue();
        CreationMeeting creation = this.creation(List.of(legalIssue.getId()), List.of());

        Meeting meeting = this.meetingService.create(creation);

        assertThat(meeting.getParticipants()).isEmpty();
        assertThat(this.meetingRepository.findById(meeting.getId()).orElseThrow().getParticipantIds())
                .isEmpty();
        verify(this.userFinder, never()).findByIds(any());
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

    @Test
    void testFindWithoutCriteriaReturnsSeededMeetingsSortedByDate() {
        this.stubUserFinder();

        List<Meeting> meetings = this.meetingService.find(MeetingFindCriteria.builder().build());

        assertThat(meetings).extracting(Meeting::getId)
                .contains(MEETING_ID_0, MEETING_ID_1, MEETING_ID_2)
                .containsSubsequence(MEETING_ID_0, MEETING_ID_1, MEETING_ID_2);
        verify(this.userFinder, times(1)).findByIds(any());
        verify(this.userFinder, never()).read(any());
    }

    @Test
    void testFindDoesNotLoadLegalIssues() {
        this.stubUserFinder();

        assertThat(this.meetingService.find(MeetingFindCriteria.builder().build()))
                .isNotEmpty()
                .allSatisfy(meeting -> assertThat(meeting.getLegalIssues()).isNull());
    }

    @Test
    void testFindByMinDurationMinutes() {
        this.stubUserFinder();

        List<Meeting> meetings = this.meetingService.find(
                MeetingFindCriteria.builder().minDurationMinutes(60).build());

        assertThat(meetings).extracting(Meeting::getId)
                .contains(MEETING_ID_0, MEETING_ID_1)
                .doesNotContain(MEETING_ID_2);
    }

    @Test
    void testFindByOpenedFalseReturnsSeededMeetings() {
        this.stubUserFinder();

        assertThat(this.meetingService.find(MeetingFindCriteria.builder().opened(false).build()))
                .extracting(Meeting::getId)
                .contains(MEETING_ID_0, MEETING_ID_1, MEETING_ID_2);
    }

    @Test
    @Transactional
    void testFindByOpenedTrueExcludesPastMeetings() {
        UserSnapshot user = this.user("600000908", "participante8");
        LegalIssue legalIssue = this.createLegalIssue();
        CreationMeeting creation = this.creation(List.of(legalIssue.getId()), List.of(user.getId()));
        creation.setMeetingDate(LocalDateTime.now().plusDays(10));
        this.stubUserFinder();
        Meeting opened = this.meetingService.create(creation);

        assertThat(this.meetingService.find(MeetingFindCriteria.builder().opened(true).build()))
                .extracting(Meeting::getId)
                .contains(opened.getId())
                .doesNotContain(MEETING_ID_0, MEETING_ID_1, MEETING_ID_2);
    }

    @Test
    @Transactional
    void testFindByOpenedTrueExcludesCancelledFutureMeetings() {
        UUID cancelledId = this.saveCancelledFutureMeeting();
        this.stubUserFinder();

        assertThat(this.meetingService.find(MeetingFindCriteria.builder().opened(true).build()))
                .extracting(Meeting::getId)
                .doesNotContain(cancelledId);
        assertThat(this.meetingService.find(MeetingFindCriteria.builder().opened(false).build()))
                .extracting(Meeting::getId)
                .contains(cancelledId);
    }

    @Test
    void testFindByMaxLegalIssuePriority() {
        this.stubUserFinder();

        List<Meeting> meetings = this.meetingService.find(
                MeetingFindCriteria.builder().maxLegalIssuePriority(1).build());

        assertThat(meetings).extracting(Meeting::getId)
                .contains(MEETING_ID_0, MEETING_ID_1)
                .doesNotContain(MEETING_ID_2);
    }

    @Test
    void testFindByParticipantFirstName() {
        this.stubUserFinder();

        List<Meeting> meetings = this.meetingService.find(
                MeetingFindCriteria.builder().participantFirstName(LAWYER_FIRST_NAME).build());

        assertThat(meetings).extracting(Meeting::getId)
                .contains(MEETING_ID_0, MEETING_ID_2)
                .doesNotContain(MEETING_ID_1);
        verify(this.userFinder, times(1)).findByIds(any());
    }

    @Test
    void testFindCombinesEveryCriteria() {
        this.stubUserFinder();

        List<Meeting> meetings = this.meetingService.find(MeetingFindCriteria.builder()
                .minDurationMinutes(60)
                .opened(false)
                .maxLegalIssuePriority(1)
                .participantFirstName(LAWYER_FIRST_NAME)
                .build());

        assertThat(meetings).extracting(Meeting::getId)
                .contains(MEETING_ID_0)
                .doesNotContain(MEETING_ID_1, MEETING_ID_2);
    }

    @Test
    void testFindHydratesParticipants() {
        this.stubUserFinder();

        assertThat(this.meetingService.find(MeetingFindCriteria.builder().build()))
                .filteredOn(meeting -> meeting.getId().equals(MEETING_ID_1))
                .singleElement()
                .satisfies(meeting -> assertThat(meeting.getParticipants())
                        .isNotEmpty()
                        .allSatisfy(participant -> {
                            assertThat(participant.getFirstName()).isNotNull();
                            assertThat(participant.getMobile()).isNotNull();
                        }));
    }

    @Test
    void testFindUserNotFound() {
        when(this.userFinder.findByIds(any())).thenReturn(List.of());

        MeetingFindCriteria criteria = MeetingFindCriteria.builder().build();
        assertThatThrownBy(() -> this.meetingService.find(criteria))
                .isInstanceOf(NotFoundException.class).hasMessageContaining("User id not found");
    }

    private UUID saveCancelledFutureMeeting() {
        LegalIssue legalIssue = this.createLegalIssue();
        MeetingEntity meetingEntity = MeetingEntity.builder()
                .id(UUID.randomUUID())
                .title("IT cancelled meeting " + UUID.randomUUID())
                .meetingDate(LocalDateTime.now().plusDays(10))
                .durationMinutes(60)
                .online(false)
                .meetingStatus(MeetingStatus.CANCELLED)
                .legalIssues(List.of(this.legalIssueRepository.getReferenceById(legalIssue.getId())))
                .participantIds(List.of(UUID.randomUUID()))
                .build();
        return this.meetingRepository.save(meetingEntity).getId();
    }

    private void stubUserFinder() {
        when(this.userFinder.findByIds(any())).thenAnswer(invocation -> {
            Set<UUID> requestedIds = invocation.getArgument(0);
            return requestedIds.stream().map(this::hydratedUser).toList();
        });
    }

    private UserSnapshot hydratedUser(UUID userId) {
        return UserSnapshot.builder()
                .id(userId)
                .mobile("600000999")
                .firstName(LAWYER_ID.equals(userId) ? LAWYER_FIRST_NAME : "Marta")
                .build();
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
