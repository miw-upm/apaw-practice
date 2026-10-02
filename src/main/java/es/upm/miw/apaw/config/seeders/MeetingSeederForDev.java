package es.upm.miw.apaw.config.seeders;

import es.upm.miw.apaw.adapters.out.meeting.postgres.LegalIssueEntity;
import es.upm.miw.apaw.adapters.out.meeting.postgres.LegalIssueRepository;
import es.upm.miw.apaw.adapters.out.meeting.postgres.MeetingEntity;
import es.upm.miw.apaw.adapters.out.meeting.postgres.MeetingRepository;
import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.meeting.LegalIssue;
import es.upm.miw.apaw.domain.model.meeting.Meeting;
import es.upm.miw.apaw.domain.model.meeting.MeetingStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Log4j2
@Component
@Profile({"dev", "test"})
@Order(1)
@RequiredArgsConstructor
public class MeetingSeederForDev implements ApplicationRunner {
    public static final String PREFIX = "22222222-3333-4444-5555-66667777";
    public static final UUID ID_0 = UUID.fromString(PREFIX + "0000");
    public static final LegalIssue ISSUE_0 = LegalIssue.builder()
            .id(ID_0)
            .title("Breach of contract analysis")
            .description("Assess the clauses breached by the supplier")
            .priority(1)
            .resolved(false)
            .creationDate(LocalDateTime.of(2025, 1, 5, 9, 0))
            .build();
    public static final UUID ID_1 = UUID.fromString(PREFIX + "0001");
    public static final LegalIssue ISSUE_1 = LegalIssue.builder()
            .id(ID_1)
            .title("Compensation calculation")
            .description("Estimate the damages claimed by the client")
            .priority(2)
            .resolved(false)
            .creationDate(LocalDateTime.of(2025, 1, 6, 10, 0))
            .build();
    public static final UUID ID_2 = UUID.fromString(PREFIX + "0002");
    public static final LegalIssue ISSUE_2 = LegalIssue.builder()
            .id(ID_2)
            .title("Custody arrangement review")
            .description("Review the custody terms agreed by both parties")
            .priority(3)
            .resolved(true)
            .creationDate(LocalDateTime.of(2025, 2, 11, 11, 0))
            .build();
    public static final UUID ID_3 = UUID.fromString(PREFIX + "0003");
    public static final LegalIssue ISSUE_3 = LegalIssue.builder()
            .id(ID_3)
            .title("Debt acknowledgement draft")
            .priority(1)
            .resolved(false)
            .creationDate(LocalDateTime.of(2025, 2, 12, 12, 0))
            .build();
    public static final UUID ID_4 = UUID.fromString(PREFIX + "0004");
    public static final LegalIssue ISSUE_4 = LegalIssue.builder()
            .id(ID_4)
            .title("Expert witness selection")
            .priority(2)
            .resolved(true)
            .creationDate(LocalDateTime.of(2025, 3, 18, 9, 30))
            .build();
    public static final UUID ID_5 = UUID.fromString(PREFIX + "0005");
    public static final LegalIssue ISSUE_5 = LegalIssue.builder()
            .id(ID_5)
            .title("Settlement offer assessment")
            .description("Not linked to any meeting yet")
            .priority(3)
            .resolved(false)
            .creationDate(LocalDateTime.of(2025, 4, 2, 10, 30))
            .build();

    private static final String MEETING_PREFIX = "33333333-4444-5555-6666-77778888";
    public static final UUID MEETING_ID_0 = UUID.fromString(MEETING_PREFIX + "0000");
    public static final Meeting MEETING_0 = Meeting.builder()
            .id(MEETING_ID_0)
            .title("Initial case review")
            .meetingDate(LocalDateTime.of(2025, 1, 20, 9, 0))
            .location("Meeting room A")
            .durationMinutes(60)
            .online(false)
            .description("First review of the documentation provided by the client")
            .meetingStatus(MeetingStatus.SCHEDULED)
            .legalIssues(List.of(ISSUE_0, ISSUE_1))
            .participants(List.of(user("0000", "600000200", "abogado0"), user("0001", "600000201", "cliente0")))
            .build();
    public static final UUID MEETING_ID_1 = UUID.fromString(MEETING_PREFIX + "0001");
    public static final Meeting MEETING_1 = Meeting.builder()
            .id(MEETING_ID_1)
            .title("Family mediation session")
            .meetingDate(LocalDateTime.of(2025, 2, 18, 11, 0))
            .location("Meeting room B")
            .durationMinutes(90)
            .online(false)
            .description("Mediation between the parties before the hearing")
            .meetingStatus(MeetingStatus.SCHEDULED)
            .legalIssues(List.of(ISSUE_2, ISSUE_3))
            .participants(List.of(user("0002", "600000202", "cliente1")))
            .build();
    public static final UUID MEETING_ID_2 = UUID.fromString(MEETING_PREFIX + "0002");
    public static final Meeting MEETING_2 = Meeting.builder()
            .id(MEETING_ID_2)
            .title("Evidence strategy call")
            .meetingDate(LocalDateTime.of(2025, 3, 25, 16, 0))
            .durationMinutes(45)
            .online(true)
            .description("Online call to agree on the evidence to be submitted")
            .meetingStatus(MeetingStatus.CANCELLED)
            .legalIssues(List.of(ISSUE_4))
            .participants(List.of(user("0000", "600000200", "abogado0"), user("0003", "600000203", "perito0")))
            .build();

    private static final String USER_PREFIX = "44444444-5555-6666-7777-88889999";

    private final LegalIssueRepository legalIssueRepository;
    private final MeetingRepository meetingRepository;

    private static UserSnapshot user(String idSuffix, String mobile, String firstName) {
        return UserSnapshot.builder()
                .id(UUID.fromString(USER_PREFIX + idSuffix))
                .mobile(mobile)
                .firstName(firstName)
                .build();
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        log.warn("------- Initial Load from JAVA -----------");
        this.seedLegalIssues();
        this.seedMeetings();
    }

    private void seedLegalIssues() {
        List<LegalIssueEntity> legalIssues = List.of(ISSUE_0, ISSUE_1, ISSUE_2, ISSUE_3, ISSUE_4, ISSUE_5).stream()
                .filter(issue -> !this.legalIssueRepository.existsById(issue.getId()))
                .map(LegalIssueEntity::new)
                .toList();
        this.legalIssueRepository.saveAll(legalIssues);
        log.warn("        ------- legal issues: {} added", legalIssues.size());
    }

    private void seedMeetings() {
        List<MeetingEntity> meetings = List.of(MEETING_0, MEETING_1, MEETING_2).stream()
                .filter(meeting -> !this.meetingRepository.existsById(meeting.getId()))
                .map(this::toEntity)
                .toList();
        this.meetingRepository.saveAll(meetings);
        log.warn("        ------- meetings: {} added", meetings.size());
    }

    private MeetingEntity toEntity(Meeting meeting) {
        return MeetingEntity.builder()
                .id(meeting.getId())
                .title(meeting.getTitle())
                .meetingDate(meeting.getMeetingDate())
                .location(meeting.getLocation())
                .durationMinutes(meeting.getDurationMinutes())
                .online(meeting.getOnline())
                .description(meeting.getDescription())
                .meetingStatus(meeting.getMeetingStatus())
                .legalIssues(meeting.getLegalIssues().stream()
                        .map(issue -> this.legalIssueRepository.getReferenceById(issue.getId()))
                        .collect(Collectors.toCollection(ArrayList::new)))
                .participantIds(meeting.getParticipants().stream().map(UserSnapshot::getId).toList())
                .build();
    }
}
