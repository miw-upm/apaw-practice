package es.upm.miw.apaw.adapters.out.immigrationissues.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssue;
import es.upm.miw.apaw.domain.model.immigrationissues.ImmigrationIssueFindCriteria;
import es.upm.miw.apaw.domain.model.immigrationissues.LawBasisUsageReport;
import es.upm.miw.apaw.domain.ports.out.immigrationissues.ImmigrationIssueGateway;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ImmigrationIssueAdapter implements ImmigrationIssueGateway {

    private final ImmigrationIssueRepository immigrationIssueRepository;
    private final LawBasisRepository lawBasisRepository;

    @Override
    @Transactional
    public ImmigrationIssue create(ImmigrationIssue immigrationIssue) {
        ImmigrationIssueEntity immigrationIssueEntity = new ImmigrationIssueEntity(immigrationIssue);
        immigrationIssueEntity.setLawBases(immigrationIssue.getLawBases().stream()
                .map(lawBasis -> this.lawBasisRepository.getReferenceById(lawBasis.getId()))
                .collect(Collectors.toCollection(ArrayList::new)));
        this.immigrationIssueRepository.save(immigrationIssueEntity);
        return immigrationIssue;
    }

    @Override
    public boolean existsBySubject(String subject) {
        return this.immigrationIssueRepository.existsBySubject(subject);
    }

    @Override
    public List<ImmigrationIssue> find(ImmigrationIssueFindCriteria criteria) {
        return this.immigrationIssueRepository.findAll(this.buildSpecification(criteria), Sort.by("subject"))
                .stream()
                .map(this::toDomainWithoutLawBases)
                .toList();
    }

    private ImmigrationIssue toDomainWithoutLawBases(ImmigrationIssueEntity entity) {
        ImmigrationIssue immigrationIssue = new ImmigrationIssue();
        BeanUtils.copyProperties(entity, immigrationIssue, "lawBases", "userId");
        immigrationIssue.setUserSnapshot(UserSnapshot.builder().id(entity.getUserId()).build());
        return immigrationIssue;
    }

    private Specification<ImmigrationIssueEntity> buildSpecification(ImmigrationIssueFindCriteria criteria) {
        Specification<ImmigrationIssueEntity> specification = (root, query, cb) -> cb.conjunction();
        specification = this.addClientNationality(criteria, specification);
        specification = this.addOverdue(criteria, specification);
        return this.addLawName(criteria, specification);
    }

    private Specification<ImmigrationIssueEntity> addClientNationality(
            ImmigrationIssueFindCriteria criteria, Specification<ImmigrationIssueEntity> specification) {
        if (!criteria.hasClientNationality()) {
            return specification;
        }
        return specification.and((root, query, cb) ->
                cb.equal(root.get("clientNationality"), criteria.getClientNationality()));
    }

    private Specification<ImmigrationIssueEntity> addOverdue(
            ImmigrationIssueFindCriteria criteria, Specification<ImmigrationIssueEntity> specification) {
        if (!criteria.hasOverdue()) {
            return specification;
        }
        return specification.and((root, query, cb) -> criteria.getOverdue()
                ? cb.lessThan(root.get("responseDueDate"), LocalDate.now())
                : cb.greaterThanOrEqualTo(root.get("responseDueDate"), LocalDate.now()));
    }

    private Specification<ImmigrationIssueEntity> addLawName(
            ImmigrationIssueFindCriteria criteria, Specification<ImmigrationIssueEntity> specification) {
        if (!criteria.hasLawName()) {
            return specification;
        }
        return specification.and((root, query, cb) -> {
            query.distinct(true);
            Join<ImmigrationIssueEntity, LawBasisEntity> lawBasis = root.join("lawBases");
            return cb.like(cb.lower(lawBasis.get("lawName")),
                    "%" + criteria.getLawName().toLowerCase(Locale.ROOT) + "%");
        });
    }

    @Override
    public List<LawBasisUsageReport> findLawBasisUsageReport() {
        return this.immigrationIssueRepository.findLawBasisUsageReport();
    }
}