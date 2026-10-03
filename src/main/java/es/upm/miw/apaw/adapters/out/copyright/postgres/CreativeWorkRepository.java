package es.upm.miw.apaw.adapters.out.copyright.postgres;

import es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CreativeWorkRepository extends JpaRepository<CreativeWorkEntity, UUID>, JpaSpecificationExecutor<CreativeWorkEntity> {
    boolean existsByRegistrationCode(String registrationCode);

    @Query("SELECT new es.upm.miw.apaw.domain.model.copyright.CreativeWorkClaimSummary(" +
           "cw.registrationCode, cw.userId, COUNT(c), SUM(c.requestedCompensation)) " +
           "FROM CreativeWorkEntity cw JOIN cw.claims c " +
           "GROUP BY cw.registrationCode, cw.userId " +
           "ORDER BY SUM(c.requestedCompensation) DESC")
    List<CreativeWorkClaimSummary> generateClaimSummaries();
}
