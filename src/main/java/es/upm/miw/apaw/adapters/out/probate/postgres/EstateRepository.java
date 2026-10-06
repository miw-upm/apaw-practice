package es.upm.miw.apaw.adapters.out.probate.postgres;

import es.upm.miw.apaw.domain.model.probate.EstateUsageReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EstateRepository extends JpaRepository<EstateEntity, UUID> {
    boolean existsByHeirsId(UUID id);

    boolean existsByFileNumber(String fileNumber);

    @Query("""
            select new es.upm.miw.apaw.domain.model.probate.EstateUsageReport(
                heir.heirStatus,
                count(heir),
                sum(heir.sharePercentage))
            from HeirEntity heir
            group by heir.heirStatus
            """)
    List<EstateUsageReport> findUsageReport();
}
