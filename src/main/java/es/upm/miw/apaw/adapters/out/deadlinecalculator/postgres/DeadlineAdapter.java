package es.upm.miw.apaw.adapters.out.deadlinecalculator.postgres;

import es.upm.miw.apaw.domain.model.UserSnapshot;
import es.upm.miw.apaw.domain.model.deadlinecalculator.Deadline;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineFindCriteria;
import es.upm.miw.apaw.domain.model.deadlinecalculator.DeadlineWorkloadReport;
import es.upm.miw.apaw.domain.model.deadlinecalculator.ScopeLevel;
import es.upm.miw.apaw.domain.ports.out.deadlinecalculator.DeadlineGateway;
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
public class DeadlineAdapter implements DeadlineGateway {
    private final DeadlineRepository deadlineRepository;
    private final NonWorkingDayRepository nonWorkingDayRepository;

    @Override
    @Transactional
    public Deadline create(Deadline deadline) {
        DeadlineEntity deadlineEntity = new DeadlineEntity(deadline);
        List<NonWorkingDayEntity> nonWorkingDayEntities = deadline.getNonWorkingDays().stream()
                .map(nonWorkingDay -> this.nonWorkingDayRepository.getReferenceById(nonWorkingDay.getId()))
                .collect(Collectors.toCollection(ArrayList::new));
        deadlineEntity.setNonWorkingDays(nonWorkingDayEntities);
        this.deadlineRepository.save(deadlineEntity);
        return deadline;
    }

    @Override
    public boolean existsByTitle(String title) {
        return this.deadlineRepository.existsByTitle(title);
    }

    @Override
    public List<DeadlineWorkloadReport> findWorkloadReport(LocalDate today) {
        return this.deadlineRepository.findWorkloadReport(today);
    }

    @Override
    public List<Deadline> find(DeadlineFindCriteria criteria, LocalDate today) {
        Specification<DeadlineEntity> specification = this.buildSpecification(criteria, today);
        return this.deadlineRepository.findAll(specification, Sort.by("dueDate", "id")).stream()
                .map(this::toDomainWithoutNonWorkingDays)
                .toList();
    }

    private Specification<DeadlineEntity> buildSpecification(DeadlineFindCriteria criteria, LocalDate today) {
        Specification<DeadlineEntity> specification = (root, query, builder) -> builder.conjunction();
        if (criteria.hasRegion()) {
            specification = specification.and((root, query, builder) ->
                    builder.equal(root.get("region"), criteria.getRegion()));
        }
        if (criteria.hasOverdue()) {
            specification = specification.and((root, query, builder) -> criteria.getOverdue()
                    ? builder.lessThan(root.get("dueDate"), today)
                    : builder.greaterThanOrEqualTo(root.get("dueDate"), today));
        }
        return this.addScopeLevel(specification, criteria.getScopeLevel());
    }

    private Specification<DeadlineEntity> addScopeLevel(
            Specification<DeadlineEntity> specification, ScopeLevel scopeLevel) {
        if (scopeLevel == null) {
            return specification;
        }
        return specification.and((root, query, builder) -> {
            query.distinct(true);
            return builder.equal(root.join("nonWorkingDays").get("scopeLevel"), scopeLevel);
        });
    }

    private Deadline toDomainWithoutNonWorkingDays(DeadlineEntity entity) {
        Deadline deadline = new Deadline();
        BeanUtils.copyProperties(entity, deadline, "nonWorkingDays", "userId");
        deadline.setLawyer(UserSnapshot.builder().id(entity.getUserId()).build());
        return deadline;
    }
}
