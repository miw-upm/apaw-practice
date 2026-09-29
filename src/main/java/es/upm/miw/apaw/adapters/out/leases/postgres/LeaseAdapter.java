package es.upm.miw.apaw.adapters.out.leases.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.leases.AmendmentType;
import es.upm.miw.apaw.domain.model.leases.Lease;
import es.upm.miw.apaw.domain.model.leases.LeaseAmendmentReport;
import es.upm.miw.apaw.domain.model.leases.LeaseFindCriteria;
import es.upm.miw.apaw.domain.ports.out.leases.LeaseGateway;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LeaseAdapter implements LeaseGateway {
    private final LeaseRepository leaseRepository;
    private final AmendmentRepository amendmentRepository;

    @Override
    @Transactional
    public Lease create(Lease lease) {
        LeaseEntity leaseEntity = new LeaseEntity(lease);
        List<AmendmentEntity> amendmentEntities = lease.getAmendments().stream()
                .map(amendment -> this.amendmentRepository.getReferenceById(amendment.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
        leaseEntity.setAmendments(amendmentEntities);
        this.leaseRepository.save(leaseEntity);
        return lease;
    }

    @Override
    public List<Lease> find(LeaseFindCriteria criteria) {
        return this.leaseRepository.findAll(this.buildSpecification(criteria), Sort.by("leaseNumber")).stream()
                .map(this::toDomainWithoutAmendments)
                .toList();
    }

    @Override
    public List<LeaseAmendmentReport> findAmendmentReport() {
        return this.leaseRepository.findLeaseAmendmentReport();
    }

    private Lease toDomainWithoutAmendments(LeaseEntity entity) {
        Lease lease = new Lease();
        BeanUtils.copyProperties(entity, lease, "amendments", "userId");
        lease.setUserSnapshot(UserSnapshot.builder().id(entity.getUserId()).build());
        return lease;
    }

    private Specification<LeaseEntity> buildSpecification(LeaseFindCriteria criteria) {
        Specification<LeaseEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasLeaseType()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("leaseType"), criteria.getLeaseType()));
        }
        if (criteria.hasInForce()) {
            specification = specification.and(this.inForce(criteria.getInForce()));
        }
        return this.addAmendmentType(specification, criteria.getAmendmentType());
    }

    private Specification<LeaseEntity> inForce(boolean inForce) {
        return (root, query, builder) -> {
            LocalDate today = LocalDate.now();
            Predicate started = builder.lessThanOrEqualTo(root.get("startDate"), today);
            Predicate notEnded = builder.or(builder.isNull(root.get("endDate")),
                    builder.greaterThanOrEqualTo(root.get("endDate"), today));
            Predicate current = builder.and(started, notEnded);
            return inForce ? current : builder.not(current);
        };
    }

    private Specification<LeaseEntity> addAmendmentType(
            Specification<LeaseEntity> specification, AmendmentType amendmentType) {
        if (amendmentType == null) {
            return specification;
        }
        return specification.and((root, query, builder) -> {
            query.distinct(true);
            return builder.equal(root.join("amendments").get("amendmentType"), amendmentType);
        });
    }

    @Override
    public boolean existsByLeaseNumber(String leaseNumber) {
        return this.leaseRepository.existsByLeaseNumber(leaseNumber);
    }

    @Override
    public boolean existsByCadastralReference(String cadastralReference) {
        return this.leaseRepository.existsByCadastralReference(cadastralReference);
    }
}
