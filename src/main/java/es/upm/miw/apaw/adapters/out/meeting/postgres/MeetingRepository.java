package es.upm.miw.apaw.adapters.out.meeting.postgres;

import es.upm.miw.apaw.domain.model.meeting.MeetingParticipantReport;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MeetingRepository extends JpaRepository<MeetingEntity, UUID>,
        JpaSpecificationExecutor<MeetingEntity> {
    boolean existsByLegalIssuesId(UUID id);

    boolean existsByTitle(String title);

    @Override
    @EntityGraph(attributePaths = "participantIds")
    List<MeetingEntity> findAll(Specification<MeetingEntity> specification, Sort sort);

    @Query("""
            select new es.upm.miw.apaw.domain.model.meeting.MeetingParticipantReport(
                participantId,
                count(distinct meeting),
                count(issue),
                avg(issue.priority)
            )
            from MeetingEntity meeting
            join meeting.legalIssues issue
            join meeting.participantIds participantId
            group by participantId
            order by count(issue) desc,
                     count(distinct meeting) desc,
                     avg(issue.priority)
            """)
    List<MeetingParticipantReport> findParticipantReport();
}
