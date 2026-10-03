package es.upm.miw.apaw.adapters.out.secondlawchance.postgres;

import es.upm.miw.apaw.domain.model.secondlawchance.SharedDebtReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ExonerationCaseRepository extends JpaRepository<ExonerationCaseEntity, UUID> {
    boolean existsByDebtsId(UUID id);

    boolean existsByCaseNumber(String caseNumber);

    @Query("""
            select new es.upm.miw.apaw.domain.model.secondlawchance.SharedDebtReport(
                debt.id,
                debt.contractNumber,
                debt.creditorName,
                debt.amount,
                debt.type,
                count(exonerationCase),
                count(distinct exonerationCase.userId)
            )
            from ExonerationCaseEntity exonerationCase
            join exonerationCase.debts debt
            group by debt.id, debt.contractNumber, debt.creditorName, debt.amount, debt.type
            having count(distinct exonerationCase.userId) > 1
            order by count(distinct exonerationCase.userId) desc, debt.amount desc, debt.contractNumber
            """)
    List<SharedDebtReport> findSharedDebtReport();

    @Query("""
            select debt.id as debtId, exonerationCase.userId as userId
            from ExonerationCaseEntity exonerationCase
            join exonerationCase.debts debt
            where debt.id in :debtIds
            group by debt.id, exonerationCase.userId
            order by debt.id, exonerationCase.userId
            """)
    List<DebtorRow> findDebtorRowsByDebtIds(@Param("debtIds") Collection<UUID> debtIds);

    interface DebtorRow {
        UUID getDebtId();

        UUID getUserId();
    }
}
