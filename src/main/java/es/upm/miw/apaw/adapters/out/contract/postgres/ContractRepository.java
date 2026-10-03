package es.upm.miw.apaw.adapters.out.contract.postgres;

import es.upm.miw.apaw.domain.model.contract.ContractExpirationReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ContractRepository extends JpaRepository<ContractEntity, UUID> {

    boolean existsByClauses_Id(UUID id);

    @Query("""
            select new es.upm.miw.apaw.domain.model.contract.ContractExpirationReport(
                contract.userId,
                count(distinct contract.id),
                sum(case
                    when clause.id is not null
                        and clause.effectiveFrom <= :today
                        and (clause.effectiveUntil is null or clause.effectiveUntil >= :today)
                    then 1L else 0L
                end)
            )
            from ContractEntity contract
            left join contract.clauses clause
            where contract.endDate between :today and :limitDate
                and contract.automaticRenewal = false
            group by contract.userId
            order by count(distinct contract.id) desc
            """)

    List<ContractExpirationReport> findExpirationReport(
            @Param("today") LocalDate today,
            @Param("limitDate") LocalDate limitDate
    );
}