package es.upm.miw.apaw.adapters.out.meeting.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetingRepository extends JpaRepository<MeetingEntity, UUID> {
    boolean existsByLegalIssuesId(UUID id);
}
