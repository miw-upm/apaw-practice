package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.meeting.MeetingParticipantReport;
import es.upm.miw.apaw.domain.model.meeting.MeetingStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.MEETING_0;
import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.MEETING_1;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MeetingRepositoryIT {
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private LegalIssueRepository legalIssueRepository;

    @Test
    void testFindParticipantReportIsSortedByHandledLegalIssues() {
        assertThat(this.meetingRepository.findParticipantReport())
                .isNotEmpty()
                .extracting(MeetingParticipantReport::getLegalIssueCount)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void testFindParticipantReportAggregatesSeveralMeetings() {
        UUID userId = MEETING_0.getParticipants().get(0).getId();
        assertThat(this.meetingRepository.findParticipantReport())
                .filteredOn(report -> report.getUserSnapshot().getId().equals(userId))
                .singleElement()
                .satisfies(report -> {
                    assertThat(report.getMeetingCount()).isGreaterThanOrEqualTo(2);
                    assertThat(report.getLegalIssueCount()).isGreaterThanOrEqualTo(3);
                    assertThat(report.getAverageLegalIssuePriority()).isGreaterThanOrEqualTo(1);
                });
    }

    @Test
    void testFindParticipantReportCountsLegalIssuesOfASingleMeeting() {
        UUID userId = MEETING_1.getParticipants().get(0).getId();
        assertThat(this.meetingRepository.findParticipantReport())
                .filteredOn(report -> report.getUserSnapshot().getId().equals(userId))
                .singleElement()
                .satisfies(report -> {
                    assertThat(report.getMeetingCount()).isGreaterThanOrEqualTo(1);
                    assertThat(report.getLegalIssueCount()).isGreaterThanOrEqualTo(2);
                    assertThat(report.getAverageLegalIssuePriority()).isGreaterThanOrEqualTo(1);
                });
    }

    @Test
    @Transactional
    void testFindParticipantReportAveragesLegalIssuePriority() {
        UUID userId = UUID.randomUUID();
        this.saveMeetingWithLegalIssuePriorities(userId, 1, 3);

        assertThat(this.meetingRepository.findParticipantReport())
                .filteredOn(report -> report.getUserSnapshot().getId().equals(userId))
                .singleElement()
                .satisfies(report -> {
                    assertThat(report.getMeetingCount()).isEqualTo(1);
                    assertThat(report.getLegalIssueCount()).isEqualTo(2);
                    assertThat(report.getAverageLegalIssuePriority()).isEqualTo(2);
                });
    }

    @Test
    void testFindParticipantReportLeavesUserSnapshotPendingOfHydration() {
        assertThat(this.meetingRepository.findParticipantReport())
                .isNotEmpty()
                .extracting(MeetingParticipantReport::getUserSnapshot)
                .allSatisfy(userSnapshot -> {
                    assertThat(userSnapshot.getId()).isNotNull();
                    assertThat(userSnapshot).extracting(UserSnapshot::getMobile).isNull();
                });
    }

    private void saveMeetingWithLegalIssuePriorities(UUID userId, int... priorities) {
        List<LegalIssueEntity> legalIssues = Arrays.stream(priorities)
                .mapToObj(this::saveLegalIssue)
                .collect(Collectors.toCollection(ArrayList::new));
        this.meetingRepository.save(MeetingEntity.builder()
                .id(UUID.randomUUID())
                .title("Repository meeting " + UUID.randomUUID())
                .meetingDate(LocalDateTime.of(2025, 7, 1, 10, 0))
                .durationMinutes(30)
                .online(false)
                .meetingStatus(MeetingStatus.SCHEDULED)
                .legalIssues(legalIssues)
                .participantIds(List.of(userId))
                .build());
    }

    private LegalIssueEntity saveLegalIssue(int priority) {
        return this.legalIssueRepository.save(LegalIssueEntity.builder()
                .id(UUID.randomUUID())
                .title("Repository legal issue " + UUID.randomUUID())
                .priority(priority)
                .resolved(false)
                .creationDate(LocalDateTime.of(2025, 7, 1, 9, 0))
                .build());
    }
}
