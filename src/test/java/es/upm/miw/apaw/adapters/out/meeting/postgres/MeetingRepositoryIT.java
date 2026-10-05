package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.meeting.MeetingParticipantReport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Comparator;
import java.util.UUID;

import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.MEETING_0;
import static es.upm.miw.apaw.config.seeders.MeetingSeederForDev.MEETING_1;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MeetingRepositoryIT {
    @Autowired
    private MeetingRepository meetingRepository;

    @Test
    void testFindParticipantReportIsSortedByUnresolvedLegalIssues() {
        assertThat(this.meetingRepository.findParticipantReport())
                .isNotEmpty()
                .extracting(MeetingParticipantReport::getUnresolvedLegalIssueCount)
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
                    assertThat(report.getUnresolvedLegalIssueCount()).isGreaterThanOrEqualTo(2);
                    assertThat(report.getUnresolvedLegalIssueCount())
                            .isLessThanOrEqualTo(report.getLegalIssueCount());
                });
    }

    @Test
    void testFindParticipantReportDiscriminatesResolvedLegalIssues() {
        UUID userId = MEETING_1.getParticipants().get(0).getId();
        assertThat(this.meetingRepository.findParticipantReport())
                .filteredOn(report -> report.getUserSnapshot().getId().equals(userId))
                .singleElement()
                .satisfies(report -> {
                    assertThat(report.getMeetingCount()).isGreaterThanOrEqualTo(1);
                    assertThat(report.getLegalIssueCount()).isGreaterThanOrEqualTo(2);
                    assertThat(report.getUnresolvedLegalIssueCount()).isGreaterThanOrEqualTo(1);
                    assertThat(report.getUnresolvedLegalIssueCount())
                            .isLessThan(report.getLegalIssueCount());
                });
    }

    @Test
    void testFindParticipantReportLeavesParticipantPendingOfHydration() {
        assertThat(this.meetingRepository.findParticipantReport())
                .isNotEmpty()
                .extracting(MeetingParticipantReport::getUserSnapshot)
                .allSatisfy(userSnapshot -> {
                    assertThat(userSnapshot.getId()).isNotNull();
                    assertThat(userSnapshot).extracting(UserSnapshot::getMobile).isNull();
                });
    }
}
