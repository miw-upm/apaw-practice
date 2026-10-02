package es.upm.miw.apaw.adapters.out.meeting.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LegalIssueRepository extends JpaRepository<LegalIssueEntity, UUID> {
    List<LegalIssueEntity> findAllByOrderByTitleAsc();

    boolean existsByTitle(String title);
}
