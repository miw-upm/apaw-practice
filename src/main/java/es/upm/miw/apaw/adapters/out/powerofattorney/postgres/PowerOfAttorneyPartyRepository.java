package es.upm.miw.apaw.adapters.out.powerofattorney.postgres;

import es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyPartyReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface PowerOfAttorneyPartyRepository extends JpaRepository<PowerOfAttorneyPartyEntity, UUID> {

    @Query("""
            select new es.upm.miw.apaw.domain.model.powerofattorney.PowerOfAttorneyPartyReport(
                party.userId,
                count(distinct powerOfAttorney),
                sum(case when powerOfAttorney.principal = party then 1 else 0 end),
                sum(case when powerOfAttorney.attorney = party then 1 else 0 end)
            )
            from PowerOfAttorneyPartyEntity party
            join PowerOfAttorneyEntity powerOfAttorney
                on powerOfAttorney.principal = party
                or powerOfAttorney.attorney = party
            group by party.userId
            order by count(distinct powerOfAttorney) desc
            """)
    List<PowerOfAttorneyPartyReport> findReport();
}
