package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImmigrationIssueRepository extends JpaRepository<ImmigrationIssueEntity, UUID> {

    boolean existsBySubject(String subject);

    boolean existsByLawBases_Id(UUID id);
}