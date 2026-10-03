package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ExonerationCaseRepository extends JpaRepository<ExonerationCaseEntity, UUID> {
    boolean existsByDebtsId(UUID id);

    boolean existsByCaseNumber(String caseNumber);
}
