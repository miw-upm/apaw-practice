package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.model.evidencemanagement.CustodianActivityReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface EvidenceRepository
        extends JpaRepository<EvidenceEntity, UUID>, JpaSpecificationExecutor<EvidenceEntity> {

    @Override
    @EntityGraph(attributePaths = "custodyRecords")
    List<EvidenceEntity> findAll(Specification<EvidenceEntity> specification, Sort sort);

    boolean existsByCustodyRecordsId(UUID custodyRecordId);

    @Query("""
            select new es.upm.miw.apaw.domain.model.evidencemanagement.CustodianActivityReport(
                custodyRecord.custodianId,
                count(custodyRecord),
                count(distinct evidence),
                sum(coalesce(custodyRecord.durationMinutes, 0))
            )
            from EvidenceEntity evidence
            join evidence.custodyRecords custodyRecord
            group by custodyRecord.custodianId
            order by sum(coalesce(custodyRecord.durationMinutes, 0)) desc, custodyRecord.custodianId asc
            """)
    List<CustodianActivityReport> findCustodianActivityReport();
}
