package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.model.meeting.MeetingParticipantReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MeetingRepository extends JpaRepository<MeetingEntity, UUID> {
    boolean existsByLegalIssuesId(UUID id);

    boolean existsByTitle(String title);

    @Query("""
            select new es.upm.miw.apaw.domain.model.meeting.MeetingParticipantReport(
                participantId,
                count(distinct meeting),
                count(issue),
                sum(case when issue.resolved = false then 1 else 0 end)
            )
            from MeetingEntity meeting
            join meeting.legalIssues issue
            join meeting.participantIds participantId
            group by participantId
            order by sum(case when issue.resolved = false then 1 else 0 end) desc,
                     count(distinct meeting) desc,
                     count(issue) desc
            """)
    List<MeetingParticipantReport> findParticipantReport();
}
