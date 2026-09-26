package es.upm.miw.apaw.adapters.out.euregulation.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface EURegulationRepository extends JpaRepository<EURegulationEntity, UUID> {
    boolean existsByOfficialReferenceNumber(String officialReferenceNumber);

    boolean existsBySequentialId(Long sequentialId);

    @Query("select max(euRegulation.sequentialId) from EURegulationEntity euRegulation")
    Long findMaxSequentialId();
}
