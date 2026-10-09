package es.upm.miw.apaw.adapters.out.evidencemanagement.postgres;

import es.upm.miw.apaw.domain.model.evidencemanagement.Evidence;
import es.upm.miw.apaw.domain.model.evidencemanagement.EvidenceFindCriteria;
import es.upm.miw.apaw.domain.ports.out.evidencemanagement.EvidenceGateway;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class EvidenceAdapter implements EvidenceGateway {

    private final EvidenceRepository evidenceRepository;

    @Override
    @Transactional
    public Evidence create(Evidence evidence) {
        return this.evidenceRepository
                .save(new EvidenceEntity(evidence))
                .toDomain();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Evidence> find(EvidenceFindCriteria criteria) {
        return this.evidenceRepository
                .findAll(this.buildSpecification(criteria), Sort.by("title", "id"))
                .stream()
                .map(EvidenceEntity::toDomain)
                .toList();
    }

    private Specification<EvidenceEntity> buildSpecification(EvidenceFindCriteria criteria) {
        Specification<EvidenceEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasConfidential()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("confidential"), criteria.getConfidential()));
        }
        return this.addCustodyRecordCriteria(specification, criteria);
    }

    private Specification<EvidenceEntity> addCustodyRecordCriteria(
            Specification<EvidenceEntity> specification, EvidenceFindCriteria criteria) {
        if (!criteria.hasLongCustody() && !criteria.hasAction()) {
            return specification;
        }
        return specification.and((root, query, builder) -> {
            query.distinct(true);
            Join<EvidenceEntity, CustodyRecordEntity> custodyRecord = root.join("custodyRecords");
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.hasAction()) {
                predicates.add(builder.equal(builder.lower(custodyRecord.get("action")),
                        criteria.getAction().trim().toLowerCase()));
            }
            if (criteria.hasLongCustody()) {
                Predicate isLong = builder.greaterThanOrEqualTo(
                        custodyRecord.get("durationMinutes"), EvidenceFindCriteria.LONG_CUSTODY_MINUTES);
                predicates.add(criteria.getLongCustody() ? isLong : builder.or(
                        builder.isNull(custodyRecord.get("durationMinutes")), builder.not(isLong)));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        });
    }
}