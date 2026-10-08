package es.upm.miw.apaw.adapters.out.probate.postgres;

import es.upm.miw.apaw.domain.model.probate.EstateHeirSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EstateRepository extends JpaRepository<EstateEntity, UUID>,
        JpaSpecificationExecutor<EstateEntity> {
    boolean existsByHeirsId(UUID id);

    boolean existsByFileNumber(String fileNumber);

    @Query("""
            select new es.upm.miw.apaw.domain.model.probate.EstateHeirSummary(
                heir.heirStatus,
                count(heir),
                sum(heir.sharePercentage))
            from EstateEntity estate
            join estate.heirs heir
            group by heir.heirStatus
            order by count(heir) desc
            """)
    List<EstateHeirSummary> heirStatusSummary();
}
