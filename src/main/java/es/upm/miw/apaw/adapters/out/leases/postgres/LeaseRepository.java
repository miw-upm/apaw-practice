package es.upm.miw.apaw.adapters.out.leases.postgres;

import es.upm.miw.apaw.domain.model.leases.LeaseAmendmentReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface LeaseRepository extends JpaRepository<LeaseEntity, UUID>,
        JpaSpecificationExecutor<LeaseEntity> {
    boolean existsByAmendmentsId(UUID id);

    boolean existsByLeaseNumber(String leaseNumber);

    boolean existsByCadastralReference(String cadastralReference);

    @Query("""
            select new es.upm.miw.apaw.domain.model.leases.LeaseAmendmentReport(
                lease.leaseType,
                count(distinct lease),
                count(amendment),
                coalesce(sum(amendment.additionalAmount), 0)
            )
            from LeaseEntity lease
            join lease.amendments amendment
            where amendment.approved = true
            group by lease.leaseType
            order by coalesce(sum(amendment.additionalAmount), 0) desc, lease.leaseType
            """)
    List<LeaseAmendmentReport> findLeaseAmendmentReport();
}
